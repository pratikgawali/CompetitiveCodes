/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a non empty linked list having two pointers in each node. The first 
one points to the next node of the list, however the other pointer is 
random and can point to any node of the list. Write a program that clones 
the given list in O(1) space, i.e., without any extra space.
---------------------------------------------------------------------------
*/
public class CloneLinkedListWithRandomPointers {

	static class Node {
		int val;
		Node next;
		Node random;

		Node(int val) {
			this.val = val;
		}
	}

	private static Node clone(Node head) {

		// inserting clone node head next to original head
		Node cloneNodeHead = new Node(head.val);
		cloneNodeHead.next = head.next;
		head.next = cloneNodeHead;

		// inserting remaining clone nodes next to their corresponding original node
		Node cloneNode = cloneNodeHead;
		Node origNode = cloneNode.next;
		while (origNode != null) {
			cloneNode = new Node(origNode.val);
			cloneNode.next = origNode.next;
			origNode.next = cloneNode;
			
			origNode = cloneNode.next;
		}
		
		// adjust clone node random pointers
		origNode = head;
		while (origNode != null) {
			origNode.next.random = origNode.random.next;
			origNode = origNode.next.next;
		}
		
		// separate original and clone linked list
		origNode = head;
		while(origNode.next.next != null) {
			cloneNode = origNode.next;
			Node nextOrigNode = cloneNode.next;	
			cloneNode.next = nextOrigNode.next;
			origNode.next = nextOrigNode;
			
			origNode = nextOrigNode;
		}
		
		return cloneNodeHead;
	}

	public static void main(String[] args) {

		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);

		node1.next = node2;
		node1.random = node3;

		node2.next = node3;
		node2.random = node1;

		node3.next = node4;
		node3.random = node5;

		node4.next = node5;
		node4.random = node5;

		node5.next = null;
		node5.random = node2;
		
		System.out.print("Original Linked List: ");
		printLinkedList(node1);
		
		System.out.print("Cloned Linked List: ");
		printLinkedList(clone(node1));
	}

	private static void printLinkedList(Node head) {

		while (head != null) {
			System.out.print("(" + head.val + "->" + head.random.val + ") -> ");
			head = head.next;
		}

		System.out.println("NULL");
	}
}