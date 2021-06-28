package zadatak3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GenericUtilTest {
    @Test
    public void sortDesc(){
        Integer[] brojevi = {1, 2, 3, 4};
        Integer[] brojevi1 = {4, 3, 2, 1};
        ArrayList<Integer> endInt = new ArrayList<>(Arrays.asList(brojevi1));
        ArrayList<Integer> sortedInt = GenericUtil.sortDesc(new ArrayList<>(Arrays.asList(brojevi)));

        assertEquals(endInt, sortedInt);

        String[] str = {"A", "B", "C", "D", "E"};
        String[] str1 = {"E", "D", "C", "B", "A"};
        ArrayList<String> endStr = new ArrayList<>(Arrays.asList(str1));
        ArrayList<String> sortedStr = GenericUtil.sortDesc(new ArrayList<>(Arrays.asList(str)));

        assertEquals(endStr, sortedStr);
    }

    @Test
    public void appendToArray(){
        Integer[] brojevi = {1, 2, 3, 4, 5, 6};
        Integer[] brojevi1 = {67, 213, 11, 23};
        Integer[] res = GenericUtil.appendToArray(brojevi, brojevi1, 4);
        Integer[] endRes = {67, 213, 11, 23, 5, 6};
        assertEquals(endRes, res);

        Integer[] res2 = GenericUtil.appendToArray(brojevi, brojevi1, 2);
        System.out.println(Arrays.toString(res2));
        Integer[] endRes2 = {67, 213, 11, 23, 3, 4, 5, 6};
        assertEquals(endRes2, res2);
    }
}
