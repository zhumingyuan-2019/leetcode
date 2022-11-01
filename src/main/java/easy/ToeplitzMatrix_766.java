package easy;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/10/31 8:00 PM
 */
public class ToeplitzMatrix_766 {


    public boolean isToeplitzMatrix(int[][] matrix) {
        int rowN = matrix.length;
        int colN = matrix[0].length;
        for (int i = 1; i < rowN; i++) {
            for(int j = 1; j < colN; j++) {
                if (matrix[i][j] != matrix[i-1][j-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix1(int[][] matrix) {
        int rowN = matrix.length;
        int colN = matrix[0].length;
        for (int i = 0; i < matrix[0].length; i++) {
            if (!checkOneDiagonal(matrix, 0, i, rowN, colN)) {
                return false;
            }
        }

        for (int j = 0; j < matrix.length; j++) {
            if (!checkOneDiagonal(matrix, j, 0, rowN, colN)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkOneDiagonal(int[][] matrix, int x, int y, int rowN, int colN) {
        while (++x < rowN && ++y < colN) {
            if (matrix[x][y] != matrix[x-1][y-1]) {
                return false;
            }
        }
        return true;
    }
}
