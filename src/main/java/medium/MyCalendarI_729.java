package medium;

import java.util.TreeMap;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/7 3:09 PM
 */
public class MyCalendarI_729 {


    TreeMap<Integer, Integer> book = new TreeMap();


    public boolean book(int start, int end) {
        if (book.isEmpty()) {
            book.put(start, end);
        }
        Integer floorStart = book.floorKey(start);
        if (floorStart != null) {
            Integer floorEnd = book.get(floorStart);
            if (floorEnd > start) {
                return false;
            }
        }
        Integer cellStart = book.ceilingKey(start);
        if (cellStart != null && end > cellStart) {
            return false;
        }
        book.put(start, end);
        return true;
    }
}
