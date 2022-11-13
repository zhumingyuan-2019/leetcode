package medium;

import java.util.PriorityQueue;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/11 7:44 PM
 */
public class KClosestPointsToOrigin_973 {

    public int[][] kClosest(int[][] points, int k) {

        return methodByQueue(points , k);

    }

    private int [][] methodByQueue(int[][]points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> distance(b) - distance(a));
        for (int i = 0 ; i < points.length; i ++) {
            if (pq.size() < k) {
                pq.offer(points[i]);
            } else {
                int[] top = pq.peek();
                if (distance(top) > distance(points[i])) {
                    pq.poll();
                    pq.offer(points[i]);
                }
            }
        }
        int[][] result = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            result[i][0] = tmp[0];
            result[i][1] = tmp[1];
            i++;
        }
        return result;
    }

    public void methodByBinary(int[][]points, int start, int end, int k) {
        if (start <= end) {
            int mid = split(points, start, end);
            if (mid - start + 1 == k) {
                return;
            } else if (mid - start + 1 > k){
                methodByBinary(points, start, mid - 1, k);

            } else {
                methodByBinary(points, mid + 1, end ,k - (mid - start + 1));
            }
        }

    }


    public int[][] methodByBinary2(int[][]points, int start, int end, int k) {
        while (start <= end) {
            int mid = split(points, start, end);
            if (mid - start + 1 == k) {
                break;
            } else if (mid - start + 1 > k){
                end = mid - 1;
            } else {
                k = k - (mid - start + 1);
                start = mid + 1;
            }
        }
        int[][] result = new int[k][2];
        for (int i = 0 ; i < k ; i++) {
            result[i][0] = points[i][0];
            result[i][1] = points[i][1];
        }
        return result;
    }





    private int split(int[][] points, int start, int end) {
        int key = distance(points[start]);
        int i = start;
        int j = start;
        for (; j <= end; j++) {
            int tmpDistance = distance(points[j]);
            if (tmpDistance <= key) {
                swap(points, i, j);
                i++;
            }
        }
        swap(points, start, i - 1);
        return i - 1;
    }

    int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    void swap(int [][] points, int x, int y) {
        int[] tmp = points[x];
        points[x] = points[y];
        points[y] = tmp;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin_973 k = new KClosestPointsToOrigin_973();
        /*k.kClosest(new int[][]{{68,97},{34,-84},
                {60,100},{2,31},{-27,-38},{-73,-74},
                {-55,-39},{62,91},{62,92},{-57,-67}}, 5);*/
        k.kClosest(new int[][]{{1,3}, {-2,2}}, 1);

    }
}
