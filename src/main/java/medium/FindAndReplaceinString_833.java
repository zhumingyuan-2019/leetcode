package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 9:30 PM
 */
public class FindAndReplaceinString_833 {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int oriLen = s.length();
        List<int[]> nodeList = new ArrayList();
        for (int i = 0;i < indices.length; i++) {
            nodeList.add(new int[]{i, indices[i]});

        }
        // sort
        Collections.sort(nodeList, (a, b) -> b[1] - a[1]);
        for (int[] node : nodeList) {
            int index = node[2];
            int len = sources[node[0]].length();
            if (index + len > oriLen) {
                continue;
            }
            String oriV = s.substring(index, index + len);
            if (oriV.equals(sources[node[0]])) {
                s = s.substring(0, index) + targets[node[0]] + s.substring(index + len);
            }
        }
        return s;
    }

    static class Node {
        public String source;
        public String target;
        public int index;

        Node(String source, String target, int index) {
            this.source = source;
            this.target = target;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        FindAndReplaceinString_833 f = new FindAndReplaceinString_833();
        f.findReplaceString("abcd", new int[]{0,2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"});
    }
}
