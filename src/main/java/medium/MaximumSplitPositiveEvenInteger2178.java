package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 10:52 AM
 */
public class MaximumSplitPositiveEvenInteger2178 {

    List<Long> maximumEvenSplit(long finalSum) {
        List<Long> result = new ArrayList<>();
        if (finalSum % 2 > 0) {
            return result;
        }
        long minus = 2;
        while (finalSum > minus) {
            finalSum -= minus;
            result.add(minus);
            minus+=2;
        }
        result.add(result.remove(result.size() - 1) + finalSum);
        return result;
    }

    public static void main(String[] args) {
        MaximumSplitPositiveEvenInteger2178 m = new MaximumSplitPositiveEvenInteger2178();
        List<Long> result = m.maximumEvenSplit(12);
        System.out.println(result);
    }
}

