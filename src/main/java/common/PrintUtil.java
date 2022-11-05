package common;

import java.util.List;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/10/28 9:07 PM
 */
public class PrintUtil {

    public static <T> void print(List<T> list) {

        for(T e : list) {
            System.out.print(e);
            System.out.print("; ");

        }
        System.out.println();

    }


    public static void print(int[] array) {

        for(int e : array) {
            System.out.print(e);
            System.out.print("; ");

        }
        System.out.println();

    }


}
