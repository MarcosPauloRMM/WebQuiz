
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import object.Answer;
import object.Question;
import quizEnum.AnswerEnum;
import web.DbListener;


public class ControlAnswer {
    
    //METODO PARA VERIFICAR A EXISTENCIA DA RESPOSTA
    public static void checkIfExistsAnswers() throws ClassNotFoundException, SQLException{
        if(searchAnswers(new ArrayList<>()).isEmpty()){
           insertAnswers(); 
        }
    }
    //METODO PARA BUSCAR ALTERNATIVAS
    public static ArrayList<Answer> searchAlternatives(String codeQuestion) throws SQLException, ClassNotFoundException{
       ArrayList<Answer> listAlternatives = new ArrayList<>();
       
       String query = "SELECT * FROM answers where codequestion = ?";
       
       Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
       PreparedStatement stmt = con.prepareStatement(query);
       
       stmt.setInt(1, Integer.parseInt(codeQuestion));
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Answer alternative = new Answer();
            alternative.setAnswer(rs.getString("answer"));
            alternative.setCodeAnswer(String.valueOf(rs.getInt("codeanswer")));
            
            listAlternatives.add(alternative);
        }
        Collections.shuffle(listAlternatives);
        rs.close();
        startAndEndsConnectionBD(false);
        return listAlternatives;
    }
    
    //METODO PARA PROCURAR RESPOSTAS
    public static ArrayList<Answer> searchAnswers(ArrayList<Question> listQuestions) throws ClassNotFoundException, SQLException{
        ArrayList<Answer> listAnswers = new ArrayList<>();
        boolean defaultQuestions = false;
        if(listQuestions.isEmpty()) {
            defaultQuestions = true;
            listQuestions = ControlQuestion.searchQuestions();
        }

       StringBuilder query = new StringBuilder("SELECT * FROM answers where codequestion in (")
                                               .append(replaceValorByInterrogation(listQuestions))
                                               .append(")");
       
       if(!defaultQuestions) query.append(" and rightanswer = ?");
       
       Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
       PreparedStatement stmt = con.prepareStatement(query.toString());
       
       int j = replaceInterrogationByValor(listQuestions, stmt);
       if(!defaultQuestions) stmt.setBoolean(j, true);
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Answer answer = new Answer();
            
            answer.setCodeQuestion(String.valueOf(rs.getInt("codequestion")));
            answer.setAnswer(rs.getString("answer"));
            answer.setCodeAnswer(String.valueOf(rs.getInt("codeanswer")));
            answer.setResultTrueOrFalse(rs.getBoolean("rightanswer"));
            
            listAnswers.add(answer);
        }
        
        rs.close();
        startAndEndsConnectionBD(false);
        
        return listAnswers;
    }
    
    //METODO PARA INSERIR RESPOSTA
    public static void insertAnswers() throws ClassNotFoundException, SQLException{
        List<AnswerEnum> listAnswers = AnswerEnum.getListAnswers();
        
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        PreparedStatement stmt = null;
        
        int i = 0;
        int j=1;
        for(AnswerEnum answer: listAnswers){
            String query = "INSERT INTO answers values (?,?,?,?)";
            stmt = con.prepareStatement(query);
            
            stmt.setInt(1 , i++);
            stmt.setString(2, answer.getAnswer());
            stmt.setInt(3, answer.getCodeQuestion());
            stmt.setBoolean(4, answer.getAnswerTrueOrFalse());
            
            stmt.execute();
        }
        
        stmt.close();
        con.close();
        
    }
    
    //METODO PARA TROCAR VALOR POR ?
    private static String replaceValorByInterrogation(ArrayList<?> list){
        StringBuilder interrogation = new StringBuilder();
        int j=1;
        for(int i=0; i<list.size(); i++){
            interrogation.append("?");
            
            if(list.size()-j != 0){
                interrogation.append(",");
                j++;
            }
        }
        
        return interrogation.toString();
    }
    //METODO PARA TROCAR ? POR VALOR
    private static int replaceInterrogationByValor(ArrayList<Question> list, PreparedStatement stm) throws SQLException{
        int j=1;
        for(int i=0; i<list.size(); i++){
            stm.setInt(j, Integer.parseInt(list.get(i).getCodeQuestion()));
            j++;
        }
        return j;
    }
    
    //METODO APRA INICIAR E TERMINAR CONEXÃO COM BANCO
    private static Statement startAndEndsConnectionBD(boolean start) throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        Statement stmt =  con.createStatement();
        
        if(start){
            return stmt;
        }else{
            stmt.close();
            con.close();
        }
        
        return null;
    }
    }
