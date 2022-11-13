package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/8 10:33 PM
 */
public class LongestHappyString_1405 {


    public String longestDiverseString(int a, int b, int c) {
        char maxChar = 'a';
        int max = a;
        if (max < b) {
            maxChar = 'b';
            max = b;
        }
        if (max < c) {
            maxChar = 'c';
            max = c;
        }

        char secChar = 'b', thirdChar = 'c';
        int sec = 0, third = 0;
        if (maxChar == 'a') {
            secChar = 'b';
            sec = b;
            thirdChar = 'c';
            third = c;
        }
        if (maxChar == 'b') {
            secChar = 'a';
            sec = a;
            thirdChar = 'c';
            third = c;
        }
        if (maxChar == 'c') {
            secChar = 'a';
            sec = a;
            thirdChar = 'b';
            third = b;
        }

        int groupN = max % 2 == 0 ? max / 2 : max / 2 + 1;

        List<String>[] group = new ArrayList[groupN];
        for (int i = 0; i < groupN; i++) {
            group[i] = new ArrayList();
        }
        int index = 0;
        while (max > 0) {
            group[index].add(maxChar + "");
            max--;
            index = (index + 1) % groupN;
        }

        index = 0;
        while (sec > 0) {
            group[index].add(secChar + "");
            sec--;
            index = (index + 1) % groupN;
        }
        while (third > 0) {
            group[index].add(thirdChar +"");
            third--;
            index = (index + 1) % groupN;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < groupN;i ++) {
            if (sb.length() == 0) {
                group[i].forEach(ch -> {
                    sb.append(ch);
                });
            } else if (!group[i].get(0).equals(sb.charAt(sb.length() - 1) +"")) {
                group[i].forEach(ch -> {
                    sb.append(ch);
                });
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestHappyString_1405 l = new LongestHappyString_1405();
        l.longestDiverseString(1,1,7);
    }
}
