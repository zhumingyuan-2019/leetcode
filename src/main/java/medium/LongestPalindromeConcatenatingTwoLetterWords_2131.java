package medium;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeConcatenatingTwoLetterWords_2131 {

    public int longestPalindrome(String[] words) {
        if(words == null || words.length == 0) {
            return 0;
        }
        int max = 0;
        int[][] map = new int[26][26];

        for (int i = 0; i < words.length; i ++) {
            char c0 = words[i].charAt(0);
            char c1 = words[i].charAt(1);

            if (map[c1 - 'a'][c0 - 'a'] > 0) {
                map[c1 - 'a'][c0 - 'a']--;
                max += 4;
            } else {
                map[c0 - 'a'][c1 - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (map['a' + i]['a' + i] > 0) {
                max+=2;
                break;
            }
        }
        return max;
    }

    public int longestPalindrome1(String[] words) {

        if(words == null || words.length == 0) {
            return 0;
        }

        int max = 0;

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String tmp =  words[i].charAt(1) + "" + words[i].charAt(0);
            if (map.containsKey(tmp) && map.get(tmp) > 0) {
                max += 4;
                map.put(tmp, map.get(tmp) - 1);
            } else {
                map.put(words[i], map.getOrDefault(words[i], 0) + 1);
            }
        }


        for (String key : map.keySet()) {
            if (map.get(key) == 0) {
                continue;
            }

            String tmp =  key.charAt(1) + "" + key.charAt(0);
            if (key.equals(tmp)) {
                return max + 2;
            }
        }
        return max;
    }
}
