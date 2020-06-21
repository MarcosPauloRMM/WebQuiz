package web;
import db.Attempt;
import db.Question;
import db.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author @author MarcosPauloRMM
 */
    public class DbListener implements ServletContextListener {
    public static final String jdbcUrl = "jdbc:sqlite:C:\\Users\\User\\desktop\\webquiz.db";
    public static String exceptionMessage = null;
  
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String step = "Starting database creation";
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmt = con.createStatement();
                        
            //CRIANDO BANCO DE DADOS
            String SQL = null;
            
            //CRIANDO TABELA DE USUARIOS
            step = "users Table creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "name VARCHAR(150) NOT NULL,"
                    + "login VARCHAR(50) UNIQUE NOT NULL,"
                    + "password LONG NOT NULL"
                    + ")");
            
            step = "Default users creation";
            if (User.getUsers().isEmpty()){
                //CRIANDO USUARIOS
                stmt.executeUpdate("INSERT INTO users(name, login, password)"
                    + "VALUES('Marcos Paulo da Rocha Moura', 'Marcos Paulo', '"+("123456".hashCode())+"')");  
                stmt.executeUpdate("INSERT INTO users(name, login, password)"
                    + "VALUES('Ricardo Pupo Larguesa', 'Ricardo', '"+("123456".hashCode())+"')");  
                }
            
            
            
            //CRIANDO TABELA QUESTIONS PARA ARMAZENAR PERGUNTASE E RESPOSTAS
            step = "Table 'questions' questions";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS questions("
                    + "description VARCHAR(250) PRIMARY KEY,"
                    + "answer VARCHAR(250) NOT NULL,"
                    + "answerwrong1 VARCHAR(250) NOT NULL,"
                    + "answerwrong2 VARCHAR(250) NOT NULL,"
                    + "answerwrong3 VARCHAR(250) NOT NULL"
                    + ")");
            
            //CRIANDO PERGUNTAS E RESPOSTAS
            step = "Default questions creation";
            if (Question.getQuestions().isEmpty()){
               stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3)"
                    + "VALUES('Em que ano aconteceu a revolução Francesa?', '1789', '1788', '1790', '1791')"); 
                          
               stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('Descreve-se como Guerra do Peloponeso o conflito armado entre:','Esparta x Atena','Atenas x Roma','Roma x Egito','Egito x Esparta')");
               
               stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('Em que ano se iniciou a 2° Guerra Mundial?', '1939', '1940', '1942', '1941')");
                stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('Em 1054 houve um evento chamado a Cisma do Oriente o qual dividiu o imperio romano em dois, quais são os nomes originais dessas duas partes?', 'Bizantino e Romano', 'Equatorial e Polar','Sul e Norte','Catolico e Ortodoxo')");
                stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('Quais desses não foi um imperador de Roma?', 'Marco', 'Cômodo', 'Trajano', 'Vespasiano')");
                stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('Quantos anos durou a guerra dos cem anos?', '116', '103', '98', '32')");
                stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('A pré-história está dividida em três periodos, ordenados cronologicamente são:', 'Paleolítico, Neolítico e Idade da Pedra Polida', 'Neolítico, Palolítico e Idade da Pedra Polida', 'Idade da Pedra Polida, Neolitico e Paleolitico', 'Idade da Pedra Polida, Paleolitico e Neolitico')");
                stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('O que significa URRS','União das Republicas Socialistas Soviéticas','União das Republicas Soviéticas Socialistas','União Republicana Soviética e Socialista','União Republicana Socialista e Soviética')");
                stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('Quando foi criado o primeiro computador?', '1946', '1945', '1944', '1943')");
                stmt.executeUpdate("INSERT INTO questions(description, answer, answerwrong1, answerwrong2, answerwrong3) "
                    + "VALUES('Quantos presidentes tivemso até 2020?', '38', '40', '66', '39')");
            
            
            }
            

            
            //CRIANDO TABELA DE TENTATIVAS
            step = "attempt Table creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS attempt("
                    + "login VARCHAR(200) NOT NULL,"
                    + "result NUMBER NOT NULL," 
                    + "FOREIGN KEY(login) REFERENCES users(login)"
                    + ")");
            
            step = "Default attempt creation";
            if (Attempt.getAttempts().isEmpty()){
                //CRIANDO USUARIOS
                stmt.executeUpdate("INSERT INTO attempt(login, result) "
                    + "VALUES('Marcos Paulo', '10')");  
                }
            
            
            stmt.close();
            con.close();
        }catch(Exception ex){
            exceptionMessage = step + ": " + ex.getMessage();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
