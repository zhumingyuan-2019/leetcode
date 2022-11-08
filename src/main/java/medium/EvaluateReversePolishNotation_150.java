package medium;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 7:42 PM
 */
public class EvaluateReversePolishNotation_150 {

    static Set<String> ops = new HashSet();
    static {
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");
    }

    int calculate(int a, int b, String op) {
        switch(op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return 0;
    }

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack();
        for (String token : tokens) {
            if (ops.contains(token)) {
                Integer a = Integer.valueOf(stack.pop());
                Integer b = Integer.valueOf(stack.pop());

                stack.push(calculate(b, a,token) + "");
            } else {
                stack.push(token);
            }
        }
        return Integer.valueOf(stack.pop());
    }

    /**
     * ["4","13","5","/","+"]
     * @param args
     */
    public static void main(String[] args) {
        EvaluateReversePolishNotation_150 e = new EvaluateReversePolishNotation_150();
        int r = e.evalRPN(new String[]{"4","13","5","/","+"});
        System.out.println(r);
    }

}
