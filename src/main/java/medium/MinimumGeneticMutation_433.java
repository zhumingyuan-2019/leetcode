package medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/1 11:06 PM
 */
public class MinimumGeneticMutation_433 {

    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<String> bankSet = new HashSet();
        for (String b : bank) {
            bankSet.add(b);
        }
        if (!bankSet.contains(end)) {
            return -1;
        }
        char[] choices = new char[]{'A','C','G','T'};

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int step = 0;
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0;i<len;i++) {
                String cur = queue.poll();
                if (cur.equals(end)) {
                    return step;
                }
                char [] curCharArray = cur.toCharArray();
                for (int k=0;k<8;k++) {
                    for (int j=0;j<4;j++) {
                        if (curCharArray[k] != choices[j]) {
                            char originChar = curCharArray[k];
                            curCharArray[k] = choices[j];
                            String nStr = new String(curCharArray);
                            if (bankSet.contains(nStr)) {
                                queue.add(nStr);
                                bankSet.remove(nStr);
                            }
                            curCharArray[k] = originChar;
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
