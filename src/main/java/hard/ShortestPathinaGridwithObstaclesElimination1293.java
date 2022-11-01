package hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/10/30 11:27 PM
 */
public class ShortestPathinaGridwithObstaclesElimination1293 {


    private int[][] graphLast;
    private int[][] graphCur;
    private int[][] grid;
    private int[] dx = new int[] {-1, 1, 0 ,0};
    private int[] dy = new int[] {0 , 0 , -1, 1};

    private int MAX = 40 * 40;

    public int shortestPath(int[][] grid, int k) {
        int rowN = grid.length;
        int colN = grid[0].length;

        int[][] distant = new int [rowN][colN];
        int[][] brokeCount = new int[rowN][colN];
        for(int i = 0; i < rowN; i++) {
            Arrays.fill(distant[i], MAX);
            Arrays.fill(brokeCount[i], MAX);
        }
        distant[0][0] = 0;
        brokeCount[0][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0 , 0 , 0});
        while(!queue.isEmpty()) {
            int[] curPoint = queue.poll();
            int x = curPoint[0];
            int y = curPoint[1];
            int brokeCnt = curPoint[2];
            for (int i = 0; i< 4;i++) {
                int xx = curPoint[0] + dx[i];
                int yy = curPoint[1] + dy[i];
                if (xx >= 0 && xx < rowN && yy >= 0 && yy < colN) {
                    if (grid[xx][yy] == 0) {
                        // grid[xx][yy] == 0
                        if (xx == rowN-1 && yy == colN - 1) {
                            // here must return
                            return distant[x][y] + 1;
                        }
                        if (distant[xx][yy] > distant[x][y] + 1 || brokeCount[xx][yy] > brokeCnt) {
                            brokeCount[xx][yy] = brokeCnt;
                            distant[xx][yy] = distant[x][y] + 1;
                            queue.add(new int[] {xx, yy, brokeCnt});
                        }
                    } else {
                        // grid[xx][yy] == 1
                        if (brokeCnt < k) {

                            if (xx == rowN-1 && yy == colN - 1) {
                                //here must return
                                return distant[x][y] + 1;
                            }

                            if (distant[xx][yy] > distant[x][y] + 1 || brokeCount[xx][yy] > brokeCnt + 1) {
                                brokeCount[xx][yy] = brokeCnt + 1;
                                distant[xx][yy] = distant[x][y] + 1;
                                queue.add(new int[] {xx, yy, brokeCnt + 1});

                            }
                        }
                    }
                }
            }
        }
        return distant[rowN - 1][colN - 1] == MAX ? -1 : distant[rowN - 1][colN - 1];
    }

    public int shortestPathWithWeight(int[][] grid, int k) {
        int rowN = grid.length;
        int colN = grid[0].length;
        this.grid = grid;
        this.graphLast = new int[rowN][colN];
        this.graphCur = new int[rowN][colN];
        for (int i = 0; i< rowN; i++) {
            Arrays.fill(this.graphLast[i], MAX);
            Arrays.fill(this.graphCur[i], MAX);
        }
        this.graphCur[0][0] = 0;
        this.graphLast[0][0] = 0;
        relax1();
        for(int i = 0; i < k; i++) {
            copyGraph();
            relax2();
        }
        return this.graphCur[rowN - 1][colN - 1] == MAX ? -1 : this.graphCur[rowN - 1][colN - 1];
    }

    private void relax1() {
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{0, 0});
        while(!queue.isEmpty()) {
            int[] curPoint = queue.poll();
            for (int i = 0; i < 4; i++) {
                int xx = curPoint[0] + dx[i];
                int yy = curPoint[1] + dy[i];
                if (xx >= 0 && xx < this.grid.length
                        && yy >= 0 && yy < grid[0].length && grid[xx][yy] == 0
                && this.graphCur[xx][yy] > graphCur[curPoint[0]][curPoint[1]] + 1) {
                    this.graphCur[xx][yy] = graphCur[curPoint[0]][curPoint[1]] + 1;
                    queue.add(new int[]{xx, yy});
                }
            }
        }
    }

    private void relax2() {
        Queue<int[]> queue = new LinkedList();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int xx = i + dx[k];
                        int yy = j + dy[k];
                        boolean relax = false;
                        if (xx >= 0 && xx < this.grid.length
                                && yy >= 0 && yy < grid[0].length
                                && this.graphLast[xx][yy] + 1 <  graphLast[i][j]) {
                            graphCur[i][j] = Math.min(graphCur[i][j], this.graphLast[xx][yy] + 1);
                            relax = true;
                        }
                        if (relax) {
                            queue.add(new int[]{i, j});
                        }
                    }
                }
                while(!queue.isEmpty()) {
                    int[] curPoint = queue.poll();
                    for (int m = 0; m < 4; m++) {
                        int xx = curPoint[0] + dx[m];
                        int yy = curPoint[1] + dy[m];
                        if (xx >= 0 && xx < this.grid.length
                                && yy >= 0 && yy < grid[0].length && grid[xx][yy] == 0
                                && this.graphCur[xx][yy] > graphCur[curPoint[0]][curPoint[1]] + 1) {
                            this.graphCur[xx][yy] = graphCur[curPoint[0]][curPoint[1]] + 1;
                            queue.add(new int[]{xx, yy});
                        }
                    }
                }
            }
        }
    }

    void copyGraph() {
        for (int i = 0; i < this.graphCur.length; i++) {
            for (int j = 0; j < this.graphCur[0].length; j++) {
                this.graphLast[i][j] = this.graphCur[i][j];
            }
        }
    }


    /**
     * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
     * Output: 6
     *
     * [[0,1,0,0],[1,0,1,1],[1,0,0,1],[0,0,1,0]]
     * 13
     * @param args
     */
    public static void main(String[] args) {
        ShortestPathinaGridwithObstaclesElimination1293 s = new ShortestPathinaGridwithObstaclesElimination1293();
        int r = s.shortestPath(new int[][]{{0,0,0}, {1,1,0}, {0,0,0}, {0,1,1}, {0,0,0}}, 1);
        System.out.println(r);
    }



}
