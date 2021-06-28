package DAO;

import java.sql.*;

public class FakultetDAO {
    static Connection connection;

    private FakultetDAO(){}

    public static void setConnection(Connection connection) {
        FakultetDAO.connection = connection;
    }

    public static void ucitajFakultete() throws SQLException {
        if (connection != null){
            String sql = "SELECT * FROM fakultet";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            display(rs);

            try {
                stmt.close();
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void sacuvajFakultet(String naziv, String grad, String adresa, String telefon) throws SQLException {
        if (connection != null){
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO fakultet (naziv, adresa, telefon, grad) VALUES (?,?,?,?)"
            );
            stmt.setString(1, naziv);
            stmt.setString(2, adresa);
            stmt.setString(3, telefon);
            stmt.setString(4, grad);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("ROWS AFFECTED: " + rowsAffected);
            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void izmeniPodatkeFakulteta(int idFakulteta, String col, String val) throws SQLException {
        if (connection != null){
            String sql = "UPDATE fakultet SET "+ col +"=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, val);
            stmt.setInt(2, idFakulteta);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("ROWS AFFECTED: " + rowsAffected);
            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void obrisiFakultet(int idFakulteta) throws SQLException {
        if (connection != null){
            String sql = "DELETE FROM fakultet WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idFakulteta);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("ROWS AFFECTED: " + rowsAffected);
            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void display(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            String grad = rs.getString("grad");
            String adresa = rs.getString("adresa");
            String telefon = rs.getString("telefon");

            System.out.printf("%d, %s, %s, %s, %s\n", id, naziv, grad, adresa, telefon);
        }
    }
}
