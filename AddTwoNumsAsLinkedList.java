/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
You are given two non-empty linked lists representing two non-negative 
integers. The digits are stored in reverse order and each of their nodes 
contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the 
number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
---------------------------------------------------------------------------
*/
public class AddTwoNumsAsLinkedList {

	private static class ListNode {

		int val;
		ListNode next;

		public ListNode() {
		}
		
		ListNode(int x) {
			val = x;
		}
	}
	
	private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode dummyHead = new ListNode();
        
        ListNode preNode = dummyHead;
        int carry = 0;
        
        while (l1 != null || l2 != null) {
            
            int digitSum = 0;
            
            if (l1 != null) {
                digitSum += l1.val;
                l1 = l1.next;
            }
            
            if (l2 != null) {
                digitSum += l2.val;
                l2 = l2.next;
            }
                        
            digitSum += carry;
            
            ListNode node = new ListNode(digitSum % 10);
            carry = digitSum > 9 ? 1 : 0;
            
            preNode.next = node;   
            preNode = node;
        }
        
        if (carry > 0) {
            preNode.next = new ListNode(carry);
        }
        
        return dummyHead.next;
    }
	
	private static void printLinkedList(ListNode node) {
		
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
	}

	public static void main(String[] args) {
		
		// (2 -> 4 -> 3)
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		
		// (5 -> 6 -> 4)
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		
		ListNode sum = addTwoNumbers(l1, l2);
		printLinkedList(sum);
	}
}