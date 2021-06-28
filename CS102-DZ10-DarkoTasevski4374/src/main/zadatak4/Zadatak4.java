import DAO.FakultetDAO;
import DAO.StudentDAO;

import java.sql.*;
import java.util.Scanner;

/**
 * Kreirati tabele fakultet (id, naziv, grad, adresa, kontakt telefon) i student (broj indeksa, id fakulteta, ime, prezime, godina upisa, status).
 * Napraviti konzolnu Java aplikaciju koja omogućava unos svih podataka u MySQL bazu, brisanje, izmenu i čitanje svih podataka iz baze.
 * Pored toga, omogućiti da korisnik unese grad u kome se nalazi fakultet i godinu upisa studenta na šta aplikacija
 * kao odgovor prikazuje sve studente koji studiraju na fakultetu koji se nalazi u unetom gradu, a upisani su unete godine.
 */
class Zadatak4 {
    public static void main(String[] args) throws SQLException {
        Connection conn = Driver.getConnection();

        if (conn != null) {
            StudentDAO.setConnection(conn);
            FakultetDAO.setConnection(conn);

            // 4. Pokreni Scanner i slušaj korisnički input
            Scanner unos = new Scanner(System.in);
            int opcija = 0;

            do {
                System.out.println("==================================");
                prikaziKomande();
                opcija = inputIntLoop(unos, 0, 6, null);

                switch (opcija) {
                    case 1:
                        obradiNoviUnos(unos);
                        break;
                    case 2:
                        ucitajPodatke(unos);
                        break;
                    case 3:
                        obradiIzmenuPodataka(unos);
                        break;
                    case 4:
                        obradiBrisanjePodataka(unos);
                        break;
                    case 5:
                        obradiSpecijalanZahtev(unos, conn);
                        break;
                    case 6:
                        break;
                    default:
                        prikaziKomande();
                }

            } while (opcija != 6);

            unos.close();
            conn.close();
        }
    }

    private static void obradiNoviUnos(Scanner sc) throws SQLException {
        int izabranaOpcija = prikaziIzbor(sc, "Unesite novi podatak...");

        if (izabranaOpcija == 1) {
            String ime = inputStrLoop(sc, "Unesite ime: ");
            String prezime = inputStrLoop(sc, "Unesite prezime: ");
            String status = inputStrLoop(sc, "Unesite status (online ili tradicionalno): ");
            int fakID = inputIntLoop(sc, 0, 50,"Unesite ID fakulteta: " );
            int godUpisa = inputIntLoop(sc, 2010, 2021,"Unesite godinu upisa: ");

            StudentDAO.sacuvajStudenta(ime, prezime, fakID, godUpisa, status);
        } else if (izabranaOpcija == 2) {
            System.out.println("Unesite naziv fakulteta: ");
            String naziv = sc.nextLine();
            System.out.println("Unesite grad u kome se fakultet nalazi: ");
            String grad = sc.nextLine();
            System.out.println("Unesite adresu fakulteta: ");
            String adresa = sc.nextLine();
            System.out.println("Unesite telefon fakulteta: ");
            String telefon = sc.nextLine();

            FakultetDAO.sacuvajFakultet(naziv, grad, adresa, telefon);
        }
    }

    private static void ucitajPodatke(Scanner sc) throws SQLException {
        int izabranaOpcija = prikaziIzbor(sc, "Učitajte podatke za: ");

        if (izabranaOpcija == 1) {
            StudentDAO.ucitajStudente();
        } else if (izabranaOpcija == 2) {
            FakultetDAO.ucitajFakultete();
        }
    }

    public static void obradiIzmenuPodataka(Scanner sc) throws SQLException {
        int izabranaOpcija = prikaziIzbor(sc, "Izmeni podatak...");

        if (izabranaOpcija == 1) {
            int index = inputIntLoop(sc, 1100, 10000, "Unesite broj indeksa studenta: ");
            String kolona = inputStrLoop(sc, "Unesite kolonu (ime, prezime, status): ");
            String vrednostKolone = inputStrLoop(sc, "Unesite vrednost kolone: ");
            StudentDAO.izmeniPodatkeStudenta(index, kolona, vrednostKolone);
        } else if (izabranaOpcija == 2) {
            int fakID = inputIntLoop(sc, 0, 50, "Unesite ID fakulteta: ");
            String kolona = inputStrLoop(sc, "Unesite kolonu (naziv, grad, adresa, telefon): ");
            String vrednostKolone = inputStrLoop(sc, "Unesite vrednost kolone: ");
            FakultetDAO.izmeniPodatkeFakulteta(fakID, kolona, vrednostKolone);
        }
    }

    public static void obradiBrisanjePodataka(Scanner sc) throws SQLException {
        int izabranaOpcija = prikaziIzbor(sc, "Obriši podatak...");

        if (izabranaOpcija == 1) {
            int index = inputIntLoop(sc, 1100, 10000, "Unesite broj indeksa studenta: ");
            StudentDAO.obrisiStudenta(index);
        } else if (izabranaOpcija == 2) {
            int fakID = inputIntLoop(sc, 0, 50, "Unesite ID fakulteta: ");
            FakultetDAO.obrisiFakultet(fakID);
        }
    }

    public static void obradiSpecijalanZahtev(Scanner sc, Connection conn) throws SQLException {
        int godina = inputIntLoop(sc, 2010, 2021,"Unesite godinu upisa: " );
        String grad = inputStrLoop(sc, "Unesite grad: ");

        String sql = "SELECT DISTINCT * from student INNER JOIN fakultet on id_fakulteta = id WHERE godina_upisa=? AND grad=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, godina);
        stmt.setString(2, grad);
        ResultSet rs = stmt.executeQuery();

        StudentDAO.display(rs);
    }

    private static void prikaziKomande() {
        System.out.println("1. Unesite novi podatak u bazu...");
        System.out.println("2. Pročitajte podatke iz baze...");
        System.out.println("3. Izmenite podatak u bazi...");
        System.out.println("4. Obrišite podatak iz baze...");
        System.out.println("5. Nadjite sve studente koji studiraju na fakultetu koji se nalazi u unetom gradu, a upisani su unete godine...");
        System.out.println("6. Izadjite iz programa...");
    }

    private static int prikaziIzbor(Scanner sc, String msg){
        System.out.println(msg);
        System.out.println("1. Student");
        System.out.println("2. Fakultet");
        return inputIntLoop(sc, 0, 3, null);
    }

    private static int inputIntLoop(Scanner sc, int min, int max, String message) {
        if (message == null){
            message = "Unesite broj željene opcije: ";
        }
        int opcija;
        do {
            System.out.print(message);
            while (!sc.hasNextInt()) {
                sc.nextLine();
            }
            opcija = sc.nextInt();
        } while (opcija <= min || opcija >= max);

        sc.nextLine();

        return opcija;
    }

    private static String inputStrLoop(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine();
    }
}
