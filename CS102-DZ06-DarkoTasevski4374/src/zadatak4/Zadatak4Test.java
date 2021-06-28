package zadatak4;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

public class Zadatak4Test {
    @Test
    public void checkIfCopy(){
        int[] arr1 = {5, 6, 12, 94, 2, 98, 32, 9, 17};
        int[] arr2 = {5, 6, 12, 94, 2, 98, 32, 9, 17};
        int[] arr3 = {23, 98, 545, 22, 445, 12, 55};
        int[] arr4 = {23, 98, 54, 22, 445, 12, 55};

        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();

        Zadatak4.fillStack(stackA, arr1);
        Zadatak4.fillStack(stackB, arr2);
        boolean isCopy = Zadatak4.checkIfCopy(stackA, stackB);

        assertTrue(isCopy);

        Zadatak4.fillStack(stackA, arr2);
        Zadatak4.fillStack(stackB, arr3);
        boolean isCopy1 = Zadatak4.checkIfCopy(stackA, stackB);

        assertFalse(isCopy1);

        Zadatak4.fillStack(stackA, arr3);
        Zadatak4.fillStack(stackB, arr4);
        boolean isCopy2 = Zadatak4.checkIfCopy(stackA, stackB);

        assertFalse(isCopy2);
    }
}
