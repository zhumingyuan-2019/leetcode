package medium;

import common.ListNode;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/5 7:47 PM
 */
public class AddTwoNumbers_2 {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode();
        ListNode p = l1;
        ListNode q = l2;
        ListNode k = dummy;
        int carry = 0;

        while(true) {
            int x = p == null ? 0 : p.val;
            int y = q == null ? 0 : q.val;
            int sum = x + y + carry;
            carry = sum / 10;
            k.next = new ListNode(sum % 10);
            k = k.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
            if (p == null && q == null) {
                break;
            }
        }
        if (carry > 0) {
            k.next = new ListNode(1);
            k = k.next;
        }
        return dummy.next;
    }
}
