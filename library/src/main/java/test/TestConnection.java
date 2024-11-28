package test;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://WIN-B825I3MVGUH:1433;DatabaseName=example_many_to_many;encrypt=false;trustServerCertificate=true";
        String username = "sa";
        String password = "tachiuthoi";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            System.err.println("Kết nối thất bại.");
            e.printStackTrace();
        }
    }
}
