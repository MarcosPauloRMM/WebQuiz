package web;
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
            
             step = "Table 'users' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users("
                    + "nameuser VARCHAR(200) NOT NULL,"
                    + "loginuser VARCHAR(20) PRIMARY KEY,"
                    + "password_hash LONG NOT NULL"
                    + ")");
            
            step = "Table 'questions' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS questions("
                    + "codequestion INTEGER PRIMARY KEY,"
                    + "question VARCHAR(1000) NOT NULL"
                    + ")");
            
            step = "Table 'answers' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS answers("
                    + "codeanswers INTEGER PRIMARY KEY,"
                    + "answers VARCHAR(1000) NOT NULL,"
                    + "codequestion INTEGER NOT NULL,"
                    + "rightanswer Verificada pela comunidade BOOLEAN NOT NULL,"
                    + "FOREIGN KEY (codequestion) REFERENCES question(codequestion)"
                    + ")");

          
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
