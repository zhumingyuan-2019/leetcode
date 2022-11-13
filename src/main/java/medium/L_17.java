package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/9 11:55 PM
 */
public class L_17 {

    List<String> result;
    char[][] map;
    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        map = new char[][]{{},{},{'a', 'b', 'c'}, {'d','e', 'f'}, {'g','h', 'i'},
                {'j','k','l'}, {'m','n','o'}, {'p','q','r', 's'}, {'t','u','v'}, {'w','x','y', 'z'}};

        //dfs(digits, 0, new StringBuilder());
        bfs(digits);


        return result;

    }

    void bfs(String digits) {
        if (digits.length() == 0) {
            return;
        }
        int step = 0;
        Queue<String> queue = new LinkedList();
        char[] array = map[digits.charAt(0) - '0'];
        step++;
        for (char c : array) {
            queue.add(String.valueOf(c));
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            if (step == digits.length()) {
                for (int i = 0; i < size; i++) {
                    this.result.add(queue.poll());
                }
                return;
            }
            for (int i = 0; i < size; i++) {

                array = map[digits.charAt(step) - '0'];
                String tmp = queue.poll();
                for (char c : array) {
                    queue.add(tmp + c);
                }
            }
            step++;
        }
    }

    void dfs(String digits, int index, StringBuilder path) {
        if (index == digits.length()) {
            if (path.length() > 0) {
                result.add(path.toString());
            }

            return;
        }
        for (char c :map[digits.charAt(index) - '0']) {
            path.append(c);
            dfs(digits, index+1, path);
            path.deleteCharAt(path.length() - 1);
        }

    }

    public static void main(String[]args) {
        L_17 l = new L_17();
        l.letterCombinations("23");
        System.out.println(l.result);
    }

}
