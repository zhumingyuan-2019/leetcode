package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/11 11:06 AM
 */
public class SearchSuggestionSystem_1268 {

    public static class Node {
        Node [] children;
        char c;
        boolean end;

        public Node() {
            this.children = new Node[26];
        }

        public Node(char c) {
            this.c = c;
            this.children = new Node[26];
        }
    }

    public class TrieTree {
        Node root;

        TrieTree() {
            this.root = new Node();

        }

        void addWord(String word) {
            Node p = this.root;
            for (int i = 0 ; i < word.length(); i++) {

                if (p.children[word.charAt(i) - 'a'] == null) {
                    p.children[word.charAt(i) - 'a'] = new Node(word.charAt(i));
                }
                p = p.children[word.charAt(i) - 'a'];
            }
            p.end = true;
        }

        Node search(String word) {
            Node p = this.root;
            for (int i = 0 ; i < word.length(); i ++) {

                if (p.children[word.charAt(i) - 'a'] == null) {
                    return null;
                }
                p = p.children[word.charAt(i) - 'a'];
            }
            return p;
        }

        List<String> getWords(Node node, String prefix) {

            if (node == null) {
                return new ArrayList();
            }
            List<String> result = new ArrayList();
            if (node.end) {
                result.add(prefix + node.c);
            }
            for (int i = 0 ; i < 26; i ++) {
                if (node.children[i] != null) {
                    result.addAll(getWords(node.children[i], prefix + node.c));
                }

                if (result.size() >= 3) {
                    break;
                }
            }
            return result.size() <= 3 ? result : result.subList(0, 3);
        }


    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        TrieTree tree = new TrieTree();
        for (String product : products) {
            tree.addWord(product);
        }

        List<List<String>> result = new ArrayList();
        for(int i = 1 ; i <= searchWord.length(); i++) {
            Node n = tree.search(searchWord.substring(0, i));
            if (n == null) {
                continue;
            }
            List<String> tmp = tree.getWords(n, searchWord.substring(0, i-1));
            if (tmp.size() > 0) {
                result.add(tmp);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        SearchSuggestionSystem_1268 s = new SearchSuggestionSystem_1268();
        s.suggestedProducts(new String[]{"havana"}, "tatiana");
    }
}
