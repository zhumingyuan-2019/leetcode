package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/11 12:30 AM
 */
public class ReorderDataLogFiles_937 {

    public String[] reorderLogFiles(String[] logs) {
        List<Node> nodes = new ArrayList();

        int k = 0;
        for (String log : logs) {
            nodes.add(new Node(log, k++));
        }

        Collections.sort(nodes, (n1, n2) -> {
            if (n1.type < n2.type) {
                return -1;
            } else if (n1.type > n2.type){
                return 1;
            } else if (n1.type == 0) {
                String[]array1 = n1.log.split(" ", 2);
                String[]array2 = n2.log.split(" ", 2);
                int flag = array1[1].compareTo(array2[1]);
                if (flag != 0) {
                    return flag;
                }
                return array1[0].compareTo(array2[0]);
            } else {
                return 0;
            }
        });

        int j = 0 ;
        for (Node n : nodes) {
            logs[j++] = n.log;
        }
        return logs;


    }

    static class Node{
        int type; // 0 letter; 1 digit
        int seq;
        String log;

        public Node(String log, int index) {
            this.seq = index;
            String[] array = log.split(" ");
            String e1 = array[1];
            char c = e1.toCharArray()[0];
            if (c - '0' > 9 || c - '0' < 0) {
                this.type = 0;
            } else {
                this.type = 1;
            }
            this.log = log;
        }
    }

    /**
     * ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
     * @param args
     */
    public static void main(String[] args) {
        String s = "dig1 8 1 5 1";
        String[] array = s.split(" ", 2);
        System.out.println(array);
        ReorderDataLogFiles_937 r = new ReorderDataLogFiles_937();
        r.reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"});
    }
}
