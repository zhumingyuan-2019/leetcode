package hard;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/6 10:39 AM
 */
public class RaceCar818 {



    public int racecar(int target) {

        return dfs(target);
    }


    private int dfs(int target) {
        if (target == 0) {
            return 0;
        }

        int d = 0;
        int speed = 1;
        int count = 0;
        while (d <= target) {
            d += speed;
            speed = speed << 1;
            count++;
        }
        int leftp = d - (speed >> 1);
        int rightp = d;
        int leftd = target - leftp;
        int rightd = rightp - target;
        if (leftp == target) {
            return count-1;
        }
        if (rightp == target) {
            return count;
        }
        if (leftd < rightd) {
            return count + 1 + dfs(target - leftp);
        }
        return count + 1 + dfs(rightp - target);
    }

    public static void main(String[]args) {
        RaceCar818 r = new RaceCar818();
        System.out.println(r.racecar(6));
    }
}
