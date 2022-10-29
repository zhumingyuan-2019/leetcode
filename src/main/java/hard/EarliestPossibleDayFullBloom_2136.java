package hard;

import java.util.Arrays;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/10/28 11:08 PM
 */
public class EarliestPossibleDayFullBloom_2136 {

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        Integer[] indices = new Integer[plantTime.length];
        for(int i = 0; i < plantTime.length; i++){
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> growTime[b] - growTime[a]);

        int plant = 0;
        int result = 0;
        for (int index : indices) {
            plant += plantTime[index];
            result = Math.max(plant + growTime[index],  result);
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         * [1,4,3]
         * [2,3,1]
         */
        EarliestPossibleDayFullBloom_2136 e = new EarliestPossibleDayFullBloom_2136();
        e.earliestFullBloom(new int[]{1,4,3}, new int[] {2,3,1});
    }

}
