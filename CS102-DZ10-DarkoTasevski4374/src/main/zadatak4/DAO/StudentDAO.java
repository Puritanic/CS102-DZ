package DAO;

import java.sql.*;

public final class StudentDAO {
    static Connection connection;

    private StudentDAO(){}

    public static void setConnection(Connection connection) {
        StudentDAO.connection = connection;
    }

    public static void ucitajStudente() throws SQLException {
        if (connection != null){
            String sql = "SELECT * FROM student";
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

    public static void sacuvajStudenta(String ime, String prezime, int fakId, int godinaUpisa, String status) throws SQLException {
        if (connection != null){
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO student (ime, prezime, godina_upisa, id_fakulteta, status) VALUES (?,?,?,?,?)"
            );
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            stmt.setInt(3, godinaUpisa);
            stmt.setInt(4, fakId);
            stmt.setString(5, status);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("ROWS AFFECTED: " + rowsAffected);

            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void izmeniPodatkeStudenta(int idStudenta, String col, String val) throws SQLException {
        if (connection != null){
            String sql = "UPDATE student SET "+col+"=? WHERE broj_indeksa=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, val);
            stmt.setInt(2, idStudenta);
            int rowsAffected = stmt.executeUpdate();

            System.out.println("ROWS AFFECTED: " + rowsAffected);
            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void obrisiStudenta(int brojIndeksa) throws SQLException {
        if (connection != null){
            String sql = "DELETE FROM student WHERE broj_indeksa=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, brojIndeksa);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("ROWS AFFECTED: " + rowsAffected);
            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            int idx_id = myRs.getInt("broj_indeksa");
            String ime = myRs.getString("ime");
            String prezime = myRs.getString("prezime");
            int uni_id = myRs.getInt("id_fakulteta");
            int godina_upisa = myRs.getInt("godina_upisa");

            System.out.printf("%d, %s, %s, %d, %d\n", idx_id, ime, prezime, uni_id, godina_upisa);
        }
    }
}
