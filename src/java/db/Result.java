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
 * @author MarcosPauloRMM
 */
public class Result {


   
    private String name; 
    private static int result;

    
    
    //CRIANDO METODO PARA PEGAR OS RESULTADOS
    public static ArrayList<Result> getResults() throws Exception{
        ArrayList<Result> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM results");
        while(rs.next()){
            list.add(new Result(
                    rs.getString("name"), 
                    rs.getInt(result)));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    //CRIANDO METODO PARA CRIAR RESULTADOS
    public static void addResult(String name, Integer result) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO users(name, result) VALUES(?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, name);
        stmt.setInt(2, result);
        stmt.execute();
        stmt.close();
        con.close();   
}
    public Result(String name, int result) {
        this.name = name;
        this.result = result;
    }
   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
