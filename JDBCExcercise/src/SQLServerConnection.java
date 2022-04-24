import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLServerConnection {

    //Dung driver MSSQL ket noi voi SQLServer
    public static Connection getSQLServerConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String instanceName = "LAPTOP-SDGHQO5D";
        String database = "myjdbcapp";
        String userName = "sa";
        String password = "shiroukrs1";
        return getSQLServerConnection(hostName, instanceName, database, userName, password);
    }
    public static Connection getSQLServerConnection(String hostname,
                                                    String instanceName,
                                                    String dbName,
                                                    String userName,
                                                    String password)
            throws SQLException, ClassNotFoundException{

        //load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String connectionString = "jdbc:sqlserver://" +hostname+ ":1433" +";instance="+ instanceName+ ";databaseName= "+dbName;

        Connection connection = DriverManager.getConnection(connectionString,userName,password);
        return connection ;
    }
}
