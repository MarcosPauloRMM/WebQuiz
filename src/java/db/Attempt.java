/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author MarcosPauloMMR
 */
public class Attempt {
    
    
    private String login;
    private double result;

    //MÉTODO PARA PEGAR UMA TENTATIVA
    public static ArrayList<Attempt> getAttempts() throws Exception{
        ArrayList<Attempt> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM attempts order by rowid desc LIMIT 10");
        while(rs.next()){
            list.add(new Attempt( 
                    rs.getString("login"),
                    rs.getDouble("result")));
            
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static ArrayList<Attempt> getRanking() throws Exception{
        ArrayList<Attempt> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM attempts order by result desc LIMIT 10");
        while(rs.next()){
            list.add(new Attempt( 
                    rs.getString("login"),
                    rs.getDouble("result")));
            
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }


    //CRIANDO METODO PARA CRIAR UMA TENTATIVA
    public static void addAttempts(String login, double result) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        String SQL = "INSERT INTO attempts(name, result) VALUES(?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setDouble(2, result);
        stmt.execute();
        stmt.close();
        con.close(); 
    }
    
    //CRIANDO METODO PARA RANKING

    public Attempt() {}
    
    public Attempt(String login, double result) {
        this.login = login;
        this.result = result;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
