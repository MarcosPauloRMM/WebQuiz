
package control;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import object.Answer;
import object.Attempt;
import object.Question;

import web.DbListener;
        
public class ControlQuiz {
     //METODO PARA RETORNAR RESULTADO DO QUIZ
    public static int getResultQuiz(Attempt quiz) throws ClassNotFoundException, SQLException, SQLException, SQLException{
        return validateAnswers(quiz);
    }
    //METODO PARA RETORNAR RANKING
    public static ArrayList<Attempt> getRanking() throws SQLException{
        ArrayList<Attempt> listAttempts = new ArrayList<>();
       
       String query = "SELECT a.codetest, a.coderesult, u.nameuser "
                    + "FROM attempts a  "
                    + "INNER JOIN user u where a.codeuser = u.rowid  "
                    + " order by a.coderesult desc LIMIT 10;";
      
       Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
       PreparedStatement stmt = con.prepareStatement(query);
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Attempt attempt = new Attempt();
            attempt.setCodeAttempt(valueOf(rs.getInt("codetest")));
            attempt.setResult(valueOf(rs.getInt("coderesult")));
            attempt.setNameUser(rs.getString("nameuser"));
            
            listAttempts.add(attempt);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return listAttempts;
    }
    //METODO PARA RETORNAR AS ULTIMAS TENTATIVAS DO USUARIO
    public static ArrayList<Attempt> getLastAttemptsUser(String codeUser) throws ClassNotFoundException, SQLException{
       ArrayList<Attempt> listAttempts = new ArrayList<>();
       
       String query = "SELECT * FROM attempts where codeuser = ? order by rowid desc LIMIT 10";
      
       Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
       PreparedStatement stmt = con.prepareStatement(query);
       
       stmt.setInt(1, Integer.parseInt(codeUser));
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Attempt attempt = new Attempt();
            attempt.setCodeAttempt(valueOf(rs.getInt("codetest")));
            attempt.setResult(valueOf(rs.getInt("coderesult")));
            
            listAttempts.add(attempt);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return listAttempts;
    }
    //METODO PARA RETORNAR AS ULTIMAS TENTATIVAS DE TODOS
    public static ArrayList<Attempt> getLastAttempts() throws ClassNotFoundException, SQLException{
       ArrayList<Attempt> listAttempts = new ArrayList<>();
       
       String query = "SELECT a.codeattempt, a.coderesult, u.name "
                    + "FROM attempt a  "
                    + "INNER JOIN user u where a.codeuser = u.rowid  "
                    + " order by a.rowid desc LIMIT 10;";
      
      
       Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
       PreparedStatement stmt = con.prepareStatement(query);
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Attempt attempt = new Attempt();
            
            attempt.setCodeAttempt(valueOf(rs.getInt("codeattempt")));
            attempt.setResult(valueOf(rs.getInt("coderesult")));
            attempt.setNameUser(rs.getString("name"));
            
            listAttempts.add(attempt);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return listAttempts;
    }
    //METODO PARA VALIDAR RESPOSTA
    private static int validateAnswers(Attempt quiz) throws ClassNotFoundException, SQLException{
        int hits = 0;
        ArrayList<Answer> correctAnswers = ControlAnswer.searchAnswers(prepareListQuestions(quiz.getCodeAnswer()));
        List<String> listaRespostasEnviadas = Arrays.asList(quiz.getCodeAnswer().split(","));
        
        for(Answer answer : correctAnswers){
            if(listaRespostasEnviadas.contains(answer.getCodeAnswer())) hits++;
        }
        
        insertResult(hits,quiz.getCodeUser());
        
        return hits;
    }
    //METODO PARA INSERIR RESULTADO
    private static void insertResult(int hits, String codeUser) throws SQLException, ClassNotFoundException{
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        PreparedStatement stmt = null;
       
        codeUser = "1";
        
        String query = "INSERT INTO attempts values (?,?, ?)";
        stmt = con.prepareStatement(query);

        stmt.setInt(1, searchLastAttemptsOfQuiz()+1);
        stmt.setInt(2, hits);
        stmt.setInt(3, parseInt(codeUser));

        stmt.execute();
       
        
        stmt.close();
        con.close();
    }
    //METODO PARA RETORNAR A ULTIMA TENTATIVA DO QUIZ
    private static int searchLastAttemptsOfQuiz() throws SQLException, ClassNotFoundException{
       int ultimoCodigo = 0;
       
       String query = "SELECT codeattempt FROM attempts order by rowid desc LIMIT 1 ";
       Class.forName("org.sqlite.JDBC");
       Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
       Statement stmt =  con.createStatement();
              
       ResultSet rs = stmt.executeQuery(query);
       
        while(rs.next()){
            ultimoCodigo =  rs.getInt("codeattempt");
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return ultimoCodigo;
    }
    //METODO PARA PREPARAR A LISTA DE QUESTÕES
    private static ArrayList<Question> prepareListQuestions(String codeQuestions){
        ArrayList<Question> listaPergunta = new ArrayList<>();
        
        for(String codeQuestion: codeQuestions.split(",")){
            Question question = new Question();
            question.setCodeQuestion(codeQuestion);
                      
            listaPergunta.add(question);
        }
        
        return listaPergunta;
    }
}
