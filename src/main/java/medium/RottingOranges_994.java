package medium;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/12 7:32 PM
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * explain
 * One of the most distinguished code patterns in BFS algorithms
 * is that often we use a queue data structure to keep track of
 * the candidates that we need to visit during the process.
 *
 * The main algorithm is built around a loop iterating through the queue.
 * At each iteration, we pop out an element from the head of the queue.
 * Then we do some particular process with the popped element.
 * More importantly, we then append neighbors of the popped element into the queue,
 * to keep the BFS process running.
 *
 * Here are some sample implementations.
 *
 */
public class RottingOranges_994 {

    public int orangesRotting(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        int [][] dr = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList();
        int freshCnt = 0;


        for (int i = 0 ; i < row; i++) {
            for (int j = 0 ; j < col; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i,j});
                } else if (grid[i][j] == 1){
                    freshCnt++;
                }
            }
        }
        if (queue.isEmpty()) {
            return freshCnt > 0 ? -1 : 0;
        }
        int step = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0 ; i < size; i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < 4; j ++) {
                    int xx = cur[0] + dr[j][0];
                    int yy = cur[1] + dr[j][1];
                    if (xx >= 0 && xx < row && yy >= 0 && yy < col && grid[xx][yy] == 1) {
                        grid[xx][yy] = 2;
                        freshCnt--;
                        queue.offer(new int[]{xx, yy});
                    }
                }
            }
        }

        return freshCnt > 0 ? -1 : step;
    }


}
