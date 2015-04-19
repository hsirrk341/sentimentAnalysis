import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionHanlder {
	  Connection con ;
	    Statement st ;
	    ResultSet rs ;
	    String db;
	    public Connection getConnecton (){
	        try{
	            String path = new java.io.File("project.accdb").getAbsolutePath();
	        db ="JDBC:ODBC:Driver=Microsoft Access Driver (*.mdb, *.accdb); DBQ="+path;
	        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection(db);
	            //return db;
	        } catch(NullPointerException ex){
	                ex.printStackTrace();
	            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return con;

	    }
}
