package lt.codeacademy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresTransactionExample {
    public static void main(String[] args) throws SQLException {
        ApplicationProperties instance = ApplicationProperties.getInstance();

        Connection connection = DriverManager.getConnection(instance.getValue("jdbc.postgres.url"),
                instance.getValue("jdbc.postgres.name"),
                instance.getValue("jdbc.postgres.password"));
        connection.setAutoCommit(false);


        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement("UPDATE public.\"Projektai\" SET \"Svarba\"=? WHERE \"Nr\"=?")) {
statement.setInt(2,3);
statement.setString(1,"Ypatinga");
statement.executeUpdate();


throw new RuntimeException();

        }catch (Exception e) {
            System.out.println("Visi pakeitimai bus grazinti atgal");
            connection.rollback();

        }finally {
            connection.setAutoCommit(true);
        }
    }
}
