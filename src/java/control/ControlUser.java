
package control;

import control.ControlQuiz;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import object.Attempt;
import object.User;
import web.DbListener;

public class ControlUser {

    public static User login(User user){
        
        return user;
    }
    
    public static String getUserMedia(String codeUser) throws ClassNotFoundException, SQLException{
        ArrayList<Attempt> allAttempts = ControlQuiz.getLastAttemptsUser(codeUser);
        double tetalHits = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        
        for(Attempt attempt: allAttempts){
            tetalHits = tetalHits + parseInt(attempt.getResult());
        }
        return df.format(tetalHits/allAttempts.size());
    }
     public static db.User getUser(String login, String password) throws Exception{
        db.User user = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        String SQL = "SELECT * FROM users WHERE login=? AND password=?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            user = new db.User(
                    rs.getString("name"),
                    rs.getString("login"));
        }else{   
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }
    
    public static void addUser(String name, String login, String password) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        String SQL = "INSERT INTO users(name, login, password) VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, name);
        stmt.setString(2, login);
        stmt.setLong(3, password.hashCode());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static db.User login(String login, String password) throws Exception {
        db.User output = null;
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        String SQL = "SELECT * FROM users WHERE login=? AND password=?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            output = new db.User(
                    rs.getString("login"), 
                    rs.getString("password"));
        }else{
            output = null;
        }
        rs.close();
        stmt.close();
        con.close();
        return output;
    
    
    
    
    }  
}