/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import web.DbListener;

/**
 *
 * @author MarcosPauloRMM
 */
public class Question {
 
   private String description;
   private String answer1;
   private String answer2;
   private String answer3;
   private String answer4;

   
   //CRIANDO METODO PARA PEGAR QEUSTÔES
   public static ArrayList<Question> getQuestions() throws Exception{
        ArrayList<Question> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.jdbcUrl);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM questions order by rowid");
        while(rs.next()){
            list.add(new Question(
                rs.getString("description"),
                    rs.getString("answer1"),
                    rs.getString("answer2"),
                    rs.getString("answer3"),
                    rs.getString("answer4")));
        }
        rs.close();
        stmt.close();
        con.close();
        Collections.shuffle(list);
        return list;
    }
   
   
   
   
    public Question(String description, String answer1, String answer2, String answer3, String answer4) {
        this.description = description;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    
    }



   