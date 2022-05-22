package lt.codeacademy.h2;

import java.sql.*;

public class H2JdbcExample {
    private static final String URL = "jdbc:h2:~/paskaita";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = " ";

    public static void main(String[] args) throws SQLException {
        H2JdbcExample main = new H2JdbcExample();
        //1. sukurti connection
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        if (connection != null) {
            System.out.println("CONNECTED");
        }
/*
        //2. sukurti statement'a
        Statement statement = connection.createStatement();
        //3. execute statement
        ResultSet resultSet = statement.executeQuery("SELECT * from PROJEKTAS");

        main.printResultSet(resultSet);


        //prepare statment - kai yra poreikis paduoti reiksmes (select..)
        //statement = connection.createStatement();
        //statement.executeQuery("SELECT + from PROJEKTAS WHERE id=" +2);

        PreparedStatement prepareStatement = connection.prepareStatement("SELECT * from PROJEKTAS WHERE id=?");
        prepareStatement.setInt(1, 2);
        resultSet = prepareStatement.executeQuery();
        main.printResultSet(resultSet);


        //insert query
        /*prepareStatement = connection.prepareStatement("INSERT INTO PROJEKTAS(ID, PAVADINIMAS) values(?, ?)");
        prepareStatement.setInt(1, 5);
        prepareStatement.setString(2, "Testas");
        prepareStatement.execute();*/


        //update query
        /*prepareStatement = connection.prepareStatement("UPDATE PROJEKTAS SET PAVADINIMAS=? WHERE id=?");
        prepareStatement.setInt(2,5);
        prepareStatement.setString(1, "Naujas pavadinimas");
        prepareStatement.executeUpdate();*/

        //deleta query
        /*prepareStatement = connection.prepareStatement("delete from PROJEKTAS WHERE id=?");
        prepareStatement.setInt(1, 5);
        prepareStatement.execute();*/

/*
        //db inf
        DatabaseMetaData metaData = connection.getMetaData();
        //patikrinti ar tokia lentele yra
        ResultSet tables = metaData.getTables(null, "PUBLIC", null, null);
        while (tables.next()) {
            System.out.println(tables.getString(3));
        }
    }

    private void printResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("ID"));
            System.out.println(resultSet.getInt(1));


        }*/
    }
}
