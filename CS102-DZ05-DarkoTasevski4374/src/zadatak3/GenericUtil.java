package zadatak3;

import java.util.ArrayList;
import java.util.Arrays;

public final class GenericUtil {
    /**
     * Generička metoda koja sortira prosleđenu listu u opadajućem redosledu bez korišćenja ugrađene funkcije sortiranja.
     *
     * @param list prosledjena lista
     * @param <E>  tipa T
     * @return sortirana lista tipa T u opadajućem redosledu
     */
    public static <E extends Comparable<E>> ArrayList<E> sortDesc(ArrayList<E> list) {
        //System.out.println("1: " + list);
        for (int i = 0; i < list.size() - 1; i++) {
            for (int ii = i + 1; ii < list.size(); ii++) {
                if (list.get(i).compareTo(list.get(ii)) < 0) {
                    E temp = list.get(i);
                    list.set(i, list.get(ii));
                    list.set(ii, temp);
                }
            }
        }
        //System.out.println("2: " + list);
        return list;
    }

    /**
     * Generička metoda koja sve elemente iz prvog niza koji se po indeksu nalaze posle prosleđenog integera prebacuje u drugi niz
     *
     * @param arr1  prosledjen niz
     * @param arr2  prosledjen niz
     * @param index indeks nakon koga treba prebaciti elemente niza arr1 u niz arr2
     * @param <T>   tipa T
     * @return T[]
     */
    public static <T> T[] appendToArray(T[] arr1, T[] arr2, int index) throws ArrayIndexOutOfBoundsException {
        // Provera da li je indeks dobar da ne bi došlo do prevremenog završetka programa
        if (index >= arr1.length) {
            throw new ArrayIndexOutOfBoundsException("Pokušan pristup nepostojećem indeksu prvog niza");
        }
        // Kopiramo sve elemente niza nakon prosledjenog indeksa
        T[] copyArr = Arrays.copyOfRange(arr1, index, arr1.length);

//        System.out.println(Arrays.toString(copyArr));
//        System.out.println(Arrays.toString(arr2));
        // Kreiramo kopiju arr2 sa povećanom veličinom, gde mogu da stanu i arr2 i novi copyArr
        T[] newArr2 = Arrays.copyOf(arr2, arr2.length + copyArr.length);

        for (int i = 0; i < copyArr.length; i++) {
            int idx = newArr2.length - copyArr.length;
            newArr2[idx + i] = copyArr[i];
        }

        return newArr2;
    }
}
