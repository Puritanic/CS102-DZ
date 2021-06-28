package zadatak3;

import java.util.ArrayList;
import java.util.Arrays;

// Napisati program koji sadrži klasu GenericUtil, koja ima sledeće metode:
//        - generičku metodu koja sortira prosleđenu listu u opadajućem redosledu (nije dozvoljeno korišćenje ugrađene funkcije sortiranja).
//        - generičku metodu koja prima dva generička niza istog tipa i jedan integer;
//            - potrebno je sve elemente iz prvog niza koji se po indeksu nalaze posle prosleđenog integera prebaciti u drugi niz;
//            - potrebno je prvo proširiti veličinu niza, a zatim dodati nove elemente.
public class Zadatak3 {
    public static void main(String[] args) {
        Integer[] brojevi = {234, 22, 566, 34, 96, 12, 5, 49};
        ArrayList<Integer> listaBrojeva = new ArrayList<>(Arrays.asList(brojevi));

        String[] gradovi = {"Buenos Aires", "Córdoba", "La Plata", "Belgrade", "Munchen", "London", "Moscow"};
        ArrayList<String> listaGradova = new ArrayList<>(Arrays.asList(gradovi));

        ArrayList<Integer> sortiranaListaBrojeva = GenericUtil.sortDesc(listaBrojeva);
        ArrayList<String> sortiranaListaGradova = GenericUtil.sortDesc(listaGradova);

        // **************************************

        Integer[] brojevi2 = {89, 41, 85, 32, 123, 99, 75, 256};
        String[] gradovi2 = {"Rabat", "Madrid", "New York", "Alexandria", "Mexico City", "Oslo", "Rome"};

        try {
            Integer[] mrgdArrI = GenericUtil.appendToArray(brojevi, brojevi2, 4);
            System.out.println(Arrays.toString(mrgdArrI));
            Integer[] _arr1 = GenericUtil.appendToArray(brojevi, brojevi2, 8);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        try {
            String[] mrgdArrS = GenericUtil.appendToArray(gradovi, gradovi2, 5);
            System.out.println(Arrays.toString(mrgdArrS));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
