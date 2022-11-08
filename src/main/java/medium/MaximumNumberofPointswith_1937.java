package medium;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 4:53 PM
 */
public class MaximumNumberofPointswith_1937 {

    public long maxPoints(int[][] points) {
        long[] dp = new long[points[0].length];

        for (int i = 0; i < points.length; i ++) {
            for (int j = 0; j < points[i].length; j++) {
                dp[j] += points[i][j];
            }
            for (int j = 1; j < points[i].length; j++) {
                dp[j] = Math.max(dp[j], dp[j-1] - 1);
            }

            for (int j = points[i].length - 2; j >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j+1] - 1);
            }
        }
        long result = 0;
        for (int i = 0; i < points[0].length; i ++) {
            result = Math.max(result ,dp[i]);
        }
        return result;
    }
}
