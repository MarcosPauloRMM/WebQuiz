/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import web.DbListener;

/**
 *
 * @author MarcosPauloRMM
 */
public class Historic {
    private String user;
    private double result;
    private Date date;

    public Historic(String user, double result, Date date) {
        this.user = user;
        this.result = result;
        this.date = date;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    //ORDENANDO POR NOTA
    public static ArrayList<Historic> sortByResult(ArrayList<Historic> historics) {
        Collections.sort(historics, (Historic h1, Historic h2) -> {
            if (h1.getResult() > h2.getResult()) {
                return -1;
            } else if(h1.getResult() == h2.getResult()){
                return 0;
            } else {
                return 1;
            }
        });
        return historics;
    }
    
    //ORDENANDO POR DATA
    public static ArrayList<Historic> sortByDate(ArrayList<Historic> historics) {
        Collections.sort(historics, (Historic h1, Historic h2) -> {
            if (h1.getDate().after(h2.getDate())) {
                return -1;
            } else {
                return 1;
            }
        });    
        return historics;
    }
    
    
    private static final ArrayList<Historic> historics = new ArrayList<>();
    
    public static ArrayList<Historic> getHistoric(){
        
        Calendar cal = Calendar.getInstance();
        
        if (historics.isEmpty()) {
            
            cal.set(2020, 6, 20, 20, 39);
            historics.add(new Historic ("Marcos", 10, cal.getTime()));
            cal.set(2020, 6, 20, 19, 32);
            historics.add(new Historic ("João Vinicius",8 , cal.getTime()));
            cal.set(2020, 6, 19, 23, 39);
            historics.add(new Historic ("Mari", 4, cal.getTime()));
            cal.set(2020, 6, 19, 22, 39);
            historics.add(new Historic ("Evelyn",6 , cal.getTime()));
            cal.set(2020, 6, 19, 16, 39);
            historics.add(new Historic ("Vinicius",1 , cal.getTime()));
            cal.set(2020, 6, 19, 12, 39);
            historics.add(new Historic ("Guilherme",3 , cal.getTime()));
            cal.set(2020, 6, 18, 9, 39);
            historics.add(new Historic ("João Vitor", 7, cal.getTime()));
            cal.set(2020, 6, 18, 8, 39);
            historics.add(new Historic ("Kauã",2 , cal.getTime()));
            cal.set(2020, 6, 18, 03, 39);
            historics.add(new Historic ("Nicolas", 9, cal.getTime()));
            cal.set(2020, 6, 17, 11, 39);
            historics.add(new Historic ("Fernanda", 5, cal.getTime()));
        }
        return historics;
       
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
