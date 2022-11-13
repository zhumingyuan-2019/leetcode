package medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/8 8:24 PM
 */
public class CountWordsObtainedAfterAddingLetter_2135 {

    public int wordCount(String[] startWords, String[] targetWords){
        Set<String> set = new HashSet<>();
        for(String sw : startWords) {
            char[] tmpCArray = sw.toCharArray();
            Arrays.sort(tmpCArray);
            set.add(new String(tmpCArray));
        }
        int count = 0;
        for (String tw : targetWords) {
            char[] tmpTCArray = tw.toCharArray();
            Arrays.sort(tmpTCArray);
            for (int i = 0 ; i < tw.length(); i++) {
                String tmp;
                if (i == 0) {
                    tmp = String.valueOf(tmpTCArray, 1, tmpTCArray.length - 1);
                } else if (i == tw.length() - 1){
                    tmp = String.copyValueOf(tmpTCArray, 0, tmpTCArray.length - 1);
                } else {
                    tmp = String.copyValueOf(tmpTCArray, 0, i - 0) +
                            String.copyValueOf(tmpTCArray, i+1, tw.length() - i - 1);
                }
                if (set.contains(tmp)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public int wordCount1(String[] startWords, String[] targetWords) {
        Set<Integer> set = new HashSet();
        int count = 0;
        for (String sw : startWords) {
            set.add(toInt(sw));
        }

        for (String tw : targetWords) {
            int oriInt = toInt(tw);
            for (int i = 0; i < tw.length(); i ++) {
                int tmp = oriInt - (1 << (tw.charAt(i) - 'a'));
                if (set.contains(tmp)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private int toInt(String s) {

        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            r = r + (1 << (s.charAt(i) - 'a'));
        }
        return r;
    }


    public static void main(String[]srgs) {
        CountWordsObtainedAfterAddingLetter_2135 c = new CountWordsObtainedAfterAddingLetter_2135();
        c.wordCount(new String[]{"g","vf","ylpuk","nyf","gdj","j","fyqzg","sizec"},
                new String[]{"r","am","jg","umhjo","fov","lujy","b","uz","y"});
    }
}
