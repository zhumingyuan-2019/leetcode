package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/10/28 7:33 PM
 */

public class TwoSum_1 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        int [] result = new int[2];
        int tmp;
        for (int i = 0; i < nums.length; i++ ){
            tmp = nums[i];
            if (map.containsKey(target - tmp)) {
                result[0] = map.get(target - tmp);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);

        }
        return null;
    }
}
