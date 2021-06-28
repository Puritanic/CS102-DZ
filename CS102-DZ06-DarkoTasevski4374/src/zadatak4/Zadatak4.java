package zadatak4;

import java.util.Iterator;
import java.util.Stack;

public class Zadatak4 {
    public static void main(String[] args) {
        int[] arr1 = {5, 6, 12, 94, 2, 98, 32, 9, 17};
        int[] arr2 = {23, 98, 545, 22, 445, 12, 55};
        int[] arr3 = {23, 98, 54, 22, 445, 12, 55};
        int[] arr4 = {126, 1, 3, 5, 2, 22, 33};
        int[] arr5 = {5, 6, 12, 94, 2, 98, 32, 9, 17};

        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        // Primer 1
        fillStack(stackA, arr2);
        fillStack(stackB, arr3);
        boolean isCopy = checkIfCopy(stackA, stackB);

        // Primer 2
        fillStack(stackA, arr1);
        fillStack(stackB, arr4);
        boolean isCopy1 = checkIfCopy(stackA, stackB);

        // Primer 3
        fillStack(stackA, arr1);
        fillStack(stackB, arr5);
        boolean isCopy2 = checkIfCopy(stackA, stackB);

        System.out.println("isCopy: " + isCopy);
        System.out.println("isCopy1: " + isCopy1);
        System.out.println("isCopy2: " + isCopy2);
    }


    public static boolean checkIfCopy(Stack<Integer> stackA, Stack<Integer> stackB) {
        if (!stackA.peek().equals(stackA.peek())) {
            // Ako prvi element u oba niza nije isti broj, podrazumevamo da stekovi nisu kopije
            return false;
        } else if (stackA.size() != stackB.size()) {
            // Ako veličine steka nisu iste, onda podrazumevamo da sadrže različite elemente tako da nisu kopije
            return false;
        }

        // ako prodjemo prve dve osnovne provere, radimo iteraciju kroz elemente steka i uporedjujemo elemente
        Iterator<Integer> iteratorA = stackA.iterator();
        Iterator<Integer> iteratorB = stackB.iterator();

        while (iteratorA.hasNext()) {
            Integer elA = iteratorA.next();
            Integer elB = iteratorB.next();

            if (!elA.equals(elB)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Služi za popunjavanje prosledjenog steka prosledjenim nizom integera. U slučaju da prosledjeni stek već ima neke elemente u sebi
     * vrši se clear() na njemu pre ubacivanja novih elemenata.
     * @param stack Stek sa int elementima
     * @param elements niz int elemenata
     */
    public static void fillStack(Stack<Integer> stack, int[] elements) {
        // očistimo stack od postojećih elemenata pre nego što ubacimo nove
        stack.clear();
        for (int i : elements) {
            stack.push(i);
        }
    }
}
