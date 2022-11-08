package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 11:42 AM
 */
public class DetectSquares_2013 {


    Map<Integer, List<int[]>> xMap = new HashMap<>();
    Map<Integer, List<int[]>> yMap = new HashMap<>();

    public void add(int[] point) {
        List<int[]> lx = xMap.getOrDefault(point[0], new ArrayList<>());
        lx.add(point);
        xMap.put(point[0], lx);
        List<int[]> ly = yMap.getOrDefault(point[1], new ArrayList<>());
        ly.add(point);
        yMap.put(point[1], ly);
    }

    public int count(int[] point) {
        int count = 0;
        List<int[]> sameXP = xMap.get(point[0]);
        List<int[]> sameYP = yMap.get(point[1]);
        if (sameXP ==null || sameYP == null) {
            return 0;
        }

        for(int[] px : sameXP) {
            int lenx = point[1] - px[1];// larger 0 or smaller  than 0
            if (lenx == 0) {
                continue;
            }
            for (int[]py : sameYP) {
                int leny = point[0] - py[0];
                if (Math.abs(lenx) == Math.abs(leny)) {
                    int[]leftOne = new int[]{py[0], px[1]};
                    if (xMap.containsKey(leftOne[0])) {
                        List<int[]> leftXOnes = xMap.get(leftOne[0]);
                        if (leftXOnes != null) {
                            for (int[]leftPoints : leftXOnes) {
                                if (leftPoints[1] == leftOne[1]) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }

        }
        return count;
    }


    /**
     * ["DetectSquares","add","add","add","count","count","add","count"]
     * [[],[[3,10]],[[11,2]],[[3,2]],[[11,10]],[[14,8]],[[11,2]],[[11,10]]]
     * @param args
     */
    public static void main(String[] args) {


        DetectSquares_2013 d = new DetectSquares_2013();
        d.add(new int[]{3,10});
        d.add(new int[]{11,2});
        d.add(new int[]{3,2});
        d.count(new int[]{11,10});
    }
}
