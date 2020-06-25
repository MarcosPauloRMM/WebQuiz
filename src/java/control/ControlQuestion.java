
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
import object.Question;
import quizEnum.QuestionEnum;
import web.DbListener;


public class ControlQuestion {
    
    public static void checkIfExistsQuestions() throws ClassNotFoundException, SQLException{
        if(searchQuestions().isEmpty()){
           insertQuestions(); 
        }
    }
    
    public static ArrayList<Question> searchQuestions() throws ClassNotFoundException, SQLException{
        
        ArrayList<Question> listQuestions = new ArrayList<>();
        
        String query = "SELECT * FROM questions";
        ResultSet rs = executeQuery(startAndEndsConnectionBD(true), query);
        
        while(rs.next()){
            Question question = new Question();
            
            question.setCodeQuestion(String.valueOf(rs.getInt("codequestion")));
            question.setQuestion(rs.getString("question"));
            
            listQuestions.add(question);
        }
        Collections.shuffle(listQuestions);
        
        rs.close();
        startAndEndsConnectionBD(false);
        
        return listQuestions;
        
    }
    
    private static void insertQuestions() throws ClassNotFoundException, SQLException{
        
        List<QuestionEnum> listQuestions = QuestionEnum.getListQuestions();
        
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        
        PreparedStatement stmt = null;
        int i = 1;
        for(QuestionEnum question: listQuestions){
            
            String query = "INSERT INTO questions values (?,?)";
            stmt = con.prepareStatement(query);
            
            stmt.setInt(1, i++);
            stmt.setString(2, question.getQuestion());
            
            stmt.execute();
        }
        
        stmt.close();
        con.close();
        
    }
    
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
    
    private static ResultSet executeQuery(Statement stmt, String query) throws SQLException{
        return stmt.executeQuery(query);
    }
}
