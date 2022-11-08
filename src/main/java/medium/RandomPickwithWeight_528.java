package medium;

import java.util.Random;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 10:42 PM
 */
public class RandomPickwithWeight_528 {

    // w[] = {1,3,2}
    // preSum = {0,1,4,6}
    // r = random(6)
    // find r in preSUm return index , the preSum[index] <= r and as large as possible.
    // r in [0, 1) -> w[0]
    // r in [1, 4) -> w[1]
    // r in [4, 6) -> w[2]

    private int[] preSum;

    private Random random = new Random();

    private int binarySearch(int v) {
        int s = 0;
        int e = this.preSum.length - 1;
        int rI = 0;
        while(s <= e) {
            int mid = s  + (e - s) / 2;
            if (this.preSum[mid] == v) {
                return mid;
            } else if (this.preSum[mid] < v) {
                rI = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return rI;
    }

    public RandomPickwithWeight_528(int[] w) {

        this.preSum = new int[w.length+1];
        for (int i = 0; i < w.length; i++) {
            this.preSum[i+1] += this.preSum[i] + w[i];
        }
    }

    public int pickIndex() {
        int r = random.nextInt(preSum[preSum.length - 1]);
        int index = binarySearch(r);
        return index;
    }
}
