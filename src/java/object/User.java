/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import control.ControlQuiz;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author MarcosPauloRMM
 */
public class User {
    
    private String login;
    private String name;
    
    public User(String login, String name) {
        this.login = login;
        this.name = name;
    }
    
    //CRIANDO METODO PARA PEGAR USUARIOS
    public static ArrayList<User> getUsers() throws Exception{
        ArrayList<User> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        while(rs.next()){
            list.add(new User(
                    rs.getString("name"),
                    rs.getString("login")));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static User getUser(String login, String password) throws Exception{
        User user = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        String SQL = "SELECT * FROM users WHERE login=? AND password=?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            user = new User(
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

    public static User login(String login, String password) throws Exception {
        User output = null;
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        String SQL = "SELECT * FROM users WHERE login=? AND password=?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            output = new User(
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
    
    public static String getMediaUsuario(String codeUser) throws ClassNotFoundException, SQLException {
		ArrayList<Attempt> allAttemptsResults = ControlQuiz.getLastAttemptsUser(codeUser);
		double somaTotal = 0;
		DecimalFormat df = new DecimalFormat("#.00");

		for (Attempt attempt : allAttemptsResults) {
			somaTotal = somaTotal + parseInt(attempt.getResult());
		}
		return df.format(somaTotal / allAttemptsResults.size());
	}
    
    
    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}