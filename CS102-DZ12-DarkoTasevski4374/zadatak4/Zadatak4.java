// Neka je data matrica A dimenzija nxn i neka ta matrica predstavlja neku relaciju r.
// Napisati rekurzivnu metodu koja proverava da li je relacija simetrična.
// Napraviti odgovarajuće JUnit testove koji će demonstrirati ispravnost rada.
// Potrebno je da se obuhvate svi mogući slučajevi opisani na vežbama (nema vrednost, negativna vrednost, granična vrednost...).
public class Zadatak4 {
    public static void main(String[] args) {
        int[][] symmetricA = {{1, 2}, {2, 4}};
        int[][] symmetricB = {{1, 6, 3}, {6, 4, 5}, {3, 5, 4}};
        int[][] asymmetricA = {{1, 5}, {-1, 2}, {0, 3}};
        int[][] asymmetricB = {{1, 7}, {7, 1}, {9, 1}};

//        printMatrix(symmetricM);
//        System.out.println();
//        printMatrix(asymmetricM);
//        System.out.println();

        boolean isASymmetric = checkIfMatrixIsSymmetric(symmetricA);
        System.out.println("symmetricM symmetric: " + isASymmetric);
        boolean isBSymmetric = checkIfMatrixIsSymmetric(asymmetricA);
        System.out.println("asymmetricM symmetric: " + isBSymmetric);
        boolean isCSymmetric = checkIfMatrixIsSymmetric(symmetricB);
        System.out.println("symmetricB symmetric: " + isCSymmetric);
        boolean isDSymmetric = checkIfMatrixIsSymmetric(asymmetricB);
        System.out.println("asymmetricB symmetric: " + isDSymmetric);
    }

    public static boolean checkIfMatrixIsSymmetric(int[][] matrix) {
        boolean isSymmetric = true;
        int[][] transpose;

        // Ako je veličina matrice 1, ili je matrica ili deo matrice prazan podrazumevamo da je matrica asimetrična
        if (isEmptyMatrix(matrix) || matrix.length == 1){
            return false;
        }

        try {
            transpose = generateTransposeMatrix(matrix);

            outerLoop:
            for (int i = 0; i < matrix.length; i++) {
                for (int ii = 0; ii < matrix[0].length; ii++) {
                    if (matrix[i][ii] != transpose[i][ii]) {
                        isSymmetric = false;
                        break outerLoop;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            // Ako matrica nije kvadrat, onda će uvek biti asimetrična
            // i u tom slučaju može da se desi ArrayIndexOutOfBoundsException izuzetak
            isSymmetric = false;
        }

        return isSymmetric;
    }

    private static int[][] generateTransposeMatrix(int[][] matrix) {
        int[][] transpose = new int[matrix[0].length][matrix.length];

        for (int k = 0; k < matrix.length; k++) {
            for (int j = 0; j < matrix[k].length; j++) {
                transpose[j][k] = matrix[k][j];
            }
        }

        return transpose;
    }

    private static boolean isEmptyMatrix(int[][] matrix) {
        if(matrix == null) {
            return true;
        } else if(matrix.length == 0) {
            return true;
        } else {
            for(int[] arr: matrix) {
                if(arr != null && arr.length > 0) {
                    return false;
                }
            }
            return true;
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(" ");
                System.out.print(anInt);
            }
            System.out.println();
        }
    }
}
