import java.sql.*;

public class PlateformeTracker {

        public void initDataBase()
        {
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Lucas                
                // connection = DriverManager.getConnection(
                //     "jdbc:mysql://localhost:3306/mydb",
                //     "root", "$~Bc4gB9");
    
                // Vanny
                connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb",
                "root", "VannyLamorte25!");	
                    
                // Thanh
                // connection = DriverManager.getConnection(
                //     "jdbc:mysql://localhost:3306/mydb",
                //     "root", "root");
    
                Statement statement;
                statement = connection.createStatement();
                ResultSet resultSet;
                resultSet = statement.executeQuery(
                    "select * from student");
                int code;
                String title;
                while (resultSet.next()) {
                    code = resultSet.getInt("code");
                    title = resultSet.getString("title").trim();
                    System.out.println("Code : " + code
                                    + " Title : " + title);
                }
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch (Exception exception) {
                System.out.println(exception);
            }
        } 
    } 
