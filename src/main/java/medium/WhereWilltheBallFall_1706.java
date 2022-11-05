package medium;

import common.PrintUtil;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/1 8:55 PM
 */
public class WhereWilltheBallFall_1706 {

    public int[] findBall(int[][] grid) {
        int rowN = grid.length;
        int colN = grid[0].length;
        int [] result = new int[colN];
        for (int index = 0; index < colN; index++) {
            result[index] = checkOneBall(grid, index, rowN, colN);
        }
        return result;
    }

    /**
     *
     * @param index
     * @return
     */
    public int checkOneBall(int[][] grid, int index, int rowN, int colN) {
        for (int i = 0; i < rowN; i++) {
            if (grid[i][index] == 1) {
                if (index + 1 < colN && grid[i][index + 1] == 1) {
                    index++;
                } else {
                    return -1;
                }
            } else {
                if (index - 1 >= 0 && grid[i][index - 1] == -1) {
                    index--;
                } else {
                    return -1;
                }
            }
        }
        return index;
    }

    /**
     * Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
     * Output: [1,-1,-1,-1,-1]
     * @param args
     *
     *
     * Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
     * Output: [0,1,2,3,4,-1]
     */
    public static void main(String[] args) {
        WhereWilltheBallFall_1706 w = new WhereWilltheBallFall_1706();
        /*int[] result = w.findBall(new int[][]{{1,1,1,-1,-1}, {1,1,1,-1,-1}, {-1,-1,-1,1,1}, {1,1,1,1,-1},
                {-1,-1,-1,-1,-1}});*/
        int[] result = w.findBall(new int[][]{{1,1,1,1,1,1}, {-1,-1,-1,-1,-1,-1}, {1,1,1,1,1,1}, {-1,-1,-1,-1,-1,-1}});
        PrintUtil.print(result);
    }
}
