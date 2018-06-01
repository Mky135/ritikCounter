import javax.swing.*;
import java.io.File;
import java.sql.*;

class Database {
    private static String dbConn = "jdbc:sqlite:";
    private static String dbPath = "src/main/resources/";
    private static String dbName = "Ritik.sqlite";
    private static String Database = dbConn + dbPath + dbName;
    private static File dbFile = new File(dbPath + dbName);
    private int ritik;

    int getRitik() {
        return ritik;
    }

    private static void createDB() {

        String url = Database;

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("SQL Driver " + meta.getDriverName());
                System.out.println("A new database has been created.");
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                statement.executeUpdate("create table if not exists Counter(pk INTEGER PRIMARY KEY, Ritik INTEGER not null)");
                statement.executeUpdate("INSERT INTO Counter(Ritik) VALUES(0)");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void connectDB() {

        if (!dbFile.isFile()) { createDB(); }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Database);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Could Not Connect to Database.\n" + "Please Check Your Internet Connection and Try Again.");
            createDB();
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection(connection);
        }
    }

    void updateDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Database);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String sql = "UPDATE Counter SET Ritik=Ritik+1 WHERE pk = 1";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Could Not Connect to Database.\n" + "Please Check Your Internet Connection and Try Again.");
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection(connection);
        }
    }

    void refreshDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Database);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            try {
                String SQL = "SELECT Ritik from Counter WHERE pk = 1";
                    ResultSet rs = statement.executeQuery(SQL);
                    while(rs.next()) {
                        ritik = rs.getInt(1);
                    }
                    rs.close();
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Error: Could Not Refresh Database.");
                System.err.println(e.getMessage());
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Could Not Connect to Database.\n" + "Please Check Your Internet Connection and Try Again.");
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Could Not Close Connection to Database.");
            System.err.println(e.getMessage());
        }
    }
}