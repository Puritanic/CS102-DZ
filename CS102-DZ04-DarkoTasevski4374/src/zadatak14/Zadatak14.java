package zadatak14;

//Napisati rekurzivnu metodu koja uklanja sva pojavljivanja zadate cifre x iz zadatog broja n.
public class Zadatak14 {
    public static void main (String[] args){
        int intX = ukloniCifruX(3635, 3);
        System.out.println(3635 + " bez broja " + 3 + " je: " + intX);
    }

    static int ukloniCifruX(int cifraN, int cifraX){
        // Osnovni slučajevi
        if (cifraN <= 0) return cifraN;
        else if (cifraX >= 10) return cifraN;
        // sačuvamo prvi broj sa desne strane cifraN integera
        // u slučaju da je cifraN 3635, ovo bi bio broj 5
        int digit = cifraN % 10;
        // zatim rekurzivno pozovemo ukloniCifruX na cifraN int umanjen za jedan digit
        // npr. od 3635, dobijemo 363, dok je 5 sačuvan u promenljivoj "digit"
        int temp = ukloniCifruX(cifraN / 10, cifraX);
        // ako "digit" nije isti kao cifraX, pomnožimo temp sa 10 i dodamo "digit" na mesto najmanjeg broja
        // u suprotnom smatramo da digit treba da bude uklonjen
        if (digit != cifraX) {
            temp = temp * 10 + digit;
        }

        return temp;
    }
}
