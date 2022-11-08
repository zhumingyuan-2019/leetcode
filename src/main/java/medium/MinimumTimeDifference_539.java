package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 8:23 PM
 */
public class MinimumTimeDifference_539 {

    /**
     00:00 -> 0
     23:59 -> 23 * 60 + 59
     compare timePoints[i] and timePoints[i+1]
     */

    public int findMinDifference(List<String> timePoints) {
        boolean [] buckets = new boolean [24 * 60];
        for (String timeP : timePoints) {
            int index = convert(timeP);
            if (buckets[index]) {
                return 0;
            }
            buckets[index] = true;
        }

        int first = -1, pre = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (buckets[i]) {
                if (first == -1) {
                    first = i;
                }
                if (pre != -1) {
                    min = Math.min(min, i - pre);
                }
                pre = i;
            }
        }
        min = Math.min(min, first + 24 * 60 - pre);
        return min;
    }

    public int findMinDifference1(List<String> timePoints) {
        List<Integer> minuteList = new ArrayList(timePoints.size());
        for (String timeP : timePoints) {
            minuteList.add(convert(timeP));

        }
        Collections.sort(minuteList);
        int min = minuteList.get(0) + 24 * 60 - minuteList.get(minuteList.size() - 1);
        for (int i = 1; i < minuteList.size(); i++) {
            min = Math.min(min, minuteList.get(i) - minuteList.get(i-1));
        }
        return min;
    }

    private int convert(String time) {
        String[] array = time.split(":");
        Integer hour = Integer.valueOf(array[0]);
        Integer minute = Integer.valueOf(array[1]);
        return hour * 60 + minute;
    }
}
