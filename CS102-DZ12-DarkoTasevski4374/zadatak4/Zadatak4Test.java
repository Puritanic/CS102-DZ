import org.junit.Test;

import static junit.framework.TestCase.*;

public class Zadatak4Test {
    @Test
    public void testAsymmetricNonSquareMatrixInput(){
        int[][] asymmetricA = {{1, 5}, {-1, 2}, {0, 3}};
        int[][] asymmetricB = {{1, 7}, {7, 1}, {9, 1}};

        boolean isSymmteric = Zadatak4.checkIfMatrixIsSymmetric(asymmetricA);
        assertFalse(isSymmteric);
        boolean isSymmteric1 = Zadatak4.checkIfMatrixIsSymmetric(asymmetricB);
        assertFalse(isSymmteric1);
    }

    @Test
    public void testAsymmetricSquareMatrixInput(){
        int[][] asymmetricA = {{1, 5, 1}, {-1, 2, 3}, {-1, 0, 3}};
        int[][] asymmetricB = {{1, 7, 0, 1}, {7, 1, 0, 1}, {9, 1, 2, 4}};

        boolean isSymmteric = Zadatak4.checkIfMatrixIsSymmetric(asymmetricA);
        assertFalse(isSymmteric);
        boolean isSymmteric1 = Zadatak4.checkIfMatrixIsSymmetric(asymmetricB);
        assertFalse(isSymmteric1);
    }

    @Test
    public void testEmptyMatrixInput(){
        int[][] asymmetricA = {{}, {}, {}};
        int[][] asymmetricB = {{}};

        boolean isSymmteric = Zadatak4.checkIfMatrixIsSymmetric(asymmetricA);
        assertFalse(isSymmteric);
        boolean isSymmteric1 = Zadatak4.checkIfMatrixIsSymmetric(asymmetricB);
        assertFalse(isSymmteric1);
    }

    @Test
    public void testSymmetricMatrixInput(){
        int[][] symmetricA = {{1, 2}, {2, 4}};
        int[][] symmetricB = {{1, 6, 3}, {6, 4, 5}, {3, 5, 4}};

        boolean isSymmteric = Zadatak4.checkIfMatrixIsSymmetric(symmetricA);
        assertTrue(isSymmteric);
        boolean isSymmteric1 = Zadatak4.checkIfMatrixIsSymmetric(symmetricB);
        assertTrue(isSymmteric1);
    }
}
