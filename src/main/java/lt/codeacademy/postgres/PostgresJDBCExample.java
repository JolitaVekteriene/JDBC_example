package lt.codeacademy.postgres;

import lt.codeacademy.ApplicationProperties;

import java.sql.*;

public class PostgresJDBCExample {
    private static final String URL = "jdbc:postgresql://127.0.0.1/postgres";
    private static final String NAME = "postgres";
    private static final String PASS = "123";

    public static void main(String[] args) {
        PostgresJDBCExample example = new PostgresJDBCExample();

        try {
            Connection connection = DriverManager.getConnection(ApplicationProperties.getInstance().getValue("jdbc.postgres.url"),
                    ApplicationProperties.getInstance().getValue("jdbc.postgres.name"),
                    ApplicationProperties.getInstance().getValue("jdbc.postgres.password"));


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Projektai\"");  //Postres dvigubos kabutes ir svarbu did. ar mazosios raides
            example.printResult(resultSet);

            //INSERT example
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.\"Projektai\" values (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "Apskaitos sistema");
            preparedStatement.setString(3, "Ypatinga");
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(5, 500);
            // preparedStatement.execute();

            //update example
            preparedStatement = connection.prepareStatement("UPDATE public.\"Projektai\" set \"Svarba\"=? where \"Nr\"=?");
            preparedStatement.setInt(2, 5);
            preparedStatement.setString(1, "Zema");
            //preparedStatement.executeUpdate();

            //delete example
            preparedStatement = connection.prepareStatement("DELETE from public.\"Projektai\" where \"Nr\"=?");
            preparedStatement.setInt(1, 5);
            //preparedStatement.execute();

            example.updateValuesInResultSet(connection);    //reiksmems pakeisti

        } catch (SQLException e) {
            System.out.println("SQL exception" + e.getMessage());
        }

    }
    //  Pakeisti reiksmems/atnaujinti
    private void updateValuesInResultSet(Connection connection) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Projektai\"");
        while (resultSet.next()){
            if(resultSet.getString("Svarba").equals("Ypatinga")) {
                resultSet.updateString("Svarba",resultSet.getString("Svarba").toUpperCase());
                resultSet.updateRow();
            }
        }
    }

    private void printResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("NR") + " "
                    + resultSet.getString("PAVADINIMAS") + " "
                    + resultSet.getString("SVARBA") + " "
                    + resultSet.getDate("Pradžia") + " "
                    + resultSet.getInt("TRUKMĖ"));
        }
    }
}
