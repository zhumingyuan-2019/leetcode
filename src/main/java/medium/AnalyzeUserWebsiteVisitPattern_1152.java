package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/10 11:08 PM
 */
public class AnalyzeUserWebsiteVisitPattern_1152 {

    static class Pair {
        int time;
        String web;
        public Pair(int time, String web) {
            this.time = time;
            this.web = web;
        }
    }

    public List<String> mostVisitedPattern1(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap<>();
        int n = username.length;
        // collect the website info for every user, key: username, value: (timestamp, website)
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }
        // count map to record every 3 combination occuring time for the different user.
        Map<String, Integer> count = new HashMap<>();
        String res = "";
        for (String key : map.keySet()) {
            Set<String> set = new HashSet<>();
            // this set is to avoid visit the same 3-seq in one user
            List<Pair> list = map.get(key);
            Collections.sort(list, (a, b)->(a.time - b.time)); // sort by time
            // brutal force O(N ^ 3)
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        String str = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                        if (!set.contains(str)) {
                            count.put(str, count.getOrDefault(str, 0) + 1);
                            set.add(str);
                        }
                        if (res.equals("") || count.get(res) < count.get(str) || (count.get(res) == count.get(str) && res.compareTo(str) > 0)) {
                            // make sure the right lexi order
                            res = str;
                        }
                    }
                }
            }
        }
        // grab the right answer
        String[] r = res.split(" ");
        List<String> result = new ArrayList<>();
        for (String str : r) {
            result.add(str);
        }
        return result;
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int[][] pairs = new int[username.length][3];
        for (int i = 0 ; i < username.length; i ++) {
            pairs[i][0] = i;
            pairs[i][1] = timestamp[i];
            pairs[i][2] = i;
        }

        Map<String, List<int[]>> mapPairs = new HashMap();
        for (int i = 0 ; i < pairs.length; i ++) {
            List<int[]> list = mapPairs.getOrDefault(username[pairs[i][0]], new ArrayList());
            list.add(pairs[i]);
            mapPairs.put(username[pairs[i][0]], list);

        }

        Integer max = 0;
        String maxWeb = "";
        Map<String, Integer> map = new HashMap<>();
        for (List<int[]> webList : mapPairs.values()) {
            Set<String> rmDup = new HashSet<>();
            Collections.sort(webList, Comparator.comparingInt(a -> a[1]));
            for (int i = 0; i < webList.size() - 2; i ++) {
                for (int j = i+1; j < webList.size() - 1; j++) {
                    for (int k = j+1 ; k < webList.size(); k++) {
                        String key = String.format("%s-%s-%s", website[webList.get(i)[2]], website[webList.get(j)[2]],website[webList.get(k)[2]]);
                        if (!rmDup.contains(key)) {
                            rmDup.add(key);
                            int tmpCount = map.getOrDefault(key, 0);
                            tmpCount++;
                            map.put(key, tmpCount);
                            if (max < tmpCount) {
                                max = tmpCount;
                                maxWeb = key;
                            } else if (max == tmpCount && key.compareTo(maxWeb) < 0) {
                                maxWeb = key;
                            }
                        }

                    }
                }
            }
        }

        List<String> result= new ArrayList();
        String[] pArray = maxWeb.split("-");
        for (String s : pArray) {
            result.add(s);
        }
        return result;
    }


    /**
     * username =
     * ["h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"]
     * timestamp =
     * [527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930]
     * website =
     * ["hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"]
     * 35 / 61 testcases passed
     * Output
     * ["hibympufi","hibympufi","hibympufi"]
     * Expected
     * ["hibympufi","hibympufi","yljmntrclw"]
     * @param args
     */
    public static void main(String[]args) {
        AnalyzeUserWebsiteVisitPattern_1152 a = new AnalyzeUserWebsiteVisitPattern_1152();
        a.mostVisitedPattern(new String[]{"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"},
                new int[]{527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930},
                new String[]{"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"});
    }
}
