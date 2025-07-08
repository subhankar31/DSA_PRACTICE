package dsapatternsseventyquestions;

import dsapatternsseventyquestions.util.ListNode;

import java.util.List;

public class LinkedListPattern {
    //Question -26 Leet Code 876. Middle of the Linked List
    //Leet Code URL - > https://leetcode.com/problems/middle-of-the-linked-list/
    //This brute force still takes only 0MS in leet code and beats 100%

    public ListNode middleNodeBruteForce(ListNode head) {
        int counter=0;
        ListNode temp=head;
        while(temp!=null){
            counter++;
            temp=temp.next;
        }
        int middleNode=counter/2 +1;
        ListNode tempTwo=head;
        while(tempTwo!=null){
            middleNode--;
            if(middleNode==0){
                break;
            }
            tempTwo=tempTwo.next;
        }
        return tempTwo;

    }

    //Solved using slow and fast pointer approach which is also called as tortoise and here Algorithm
    //This solution also takes 0ms but its better complexity and clean code
    public ListNode middleNodeOptimized(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null && fast.next!=null){ //Note the condition must be && and not ||
            slow=slow.next;
            fast=fast.next.next;
        }
    return slow;
    }
    //Question -27 Leet Code 141. Linked List Cycle
    //Leet Code URL - > https://leetcode.com/problems/linked-list-cycle/description/
    public boolean hasCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        if(head==null || head.next==null) return false;

        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) return true;
        }
        return false;
    }
    //Question -28 Leet Code 206. Reverse Linked List
    //Leet Code URL - > https://leetcode.com/problems/reverse-linked-list/description/

    public ListNode reverseList(ListNode head) {
        ListNode temp=head;
        ListNode prev=null;
        ListNode aft=null;
        while (temp!=null){
            aft=temp.next;
            temp.next=prev;
            prev=temp;
            temp=aft;
        }
        return prev; //it holds the reverse LinkList start point
    }
    //Question -29 Leet Code 203. Remove Linked List Elements
    //Leet Code URL - > https://leetcode.com/problems/remove-linked-list-elements/description/
    //The below solution will work to delete any node irrespective of its position like
    // it may be at start , end , middle , it works for all edge cases

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead=new ListNode(-1); //create a new node with value -1
        dummyHead.next=head; //put this node before actual head
        ListNode curr=dummyHead; // curr node to traverse linkedlist
        while(curr.next!= null){
            if(curr.next.val==val){ //if target found
                curr.next=curr.next.next; //by pass the target by directly pointing to its next
            } else curr=curr.next; //normal traversal to next node
        }
        return dummyHead.next; // always return dummy node next
    }
    //Question -30 Leet Code 92. Reverse Linked List II
    //TODO Need Revise last loop need practice to remember the link change process
    //Leet Code URL - > https://leetcode.com/problems/reverse-linked-list-ii/description/
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 0; i < left - 1; i++) {
            prev = prev.next; //moven the prev till the node before left
        }

        ListNode cur = prev.next; //curr pointed to left

        //TODO Tricky part || used to change the links between nodes
        for (int i = 0; i < right - left; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }
        return dummy.next;
    }
    /**
     * For above question explanation of last loop for example Input: head = [1,2,3,4,5], left = 2, right = 4
     * Output: [1,4,3,2,5]
     * at second loop
     * prev → 1
     * cur → 2 → 3 → 4 → 5
     * then after first iteration in the loop result will be
     * ListNode temp = cur.next;         // temp = 3
     * cur.next = temp.next;             // 2 → 4
     * temp.next = prev.next;            // 3 → 2
     * prev.next = temp;                 // 1 → 3
     * 1 → 3 → 2 → 4 → 5
     *         ↑
     *       prev
     *  2nd Iteration:
     *  prev → 1
     * cur → 2 → 4 → 5
     * temp = cur.next;        // temp = 4
     * cur.next = temp.next;   // 2 → 5
     * temp.next = prev.next;  // 4 → 3
     * prev.next = temp;       // 1 → 4
     * Finally 1 → 4 → 3 → 2 → 5
     */

    //Question -31 Leet Code 234. Palindrome Linked List
    //Leet Code URL - > https://leetcode.com/problems/palindrome-linked-list/description/
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        int len = 0;
        //Calculated length of linkedlist
        for(ListNode curr = head; curr != null; curr = curr.next){
            len++;
        }
        //get hold of mid of linked list
        while(fast != null &&  fast.next != null){
            slow = slow.next; //after while loop ends slow will be at mid of the LinkedList
            fast = fast.next.next;
        }
        if(len % 2 != 0)slow = slow.next; //if Linkedlist is of odd size
        ListNode secondHlfHead = reverse(slow);
        //Below we are traversing from both ends
        //head starts from front and secondHlfHead starts from end towards front
        while(head != null && secondHlfHead != null){
            if(head.val != secondHlfHead.val)return false; //if value is different then its not Palindrome
            head = head.next;
            secondHlfHead = secondHlfHead.next; //here we go next but due to reversed so it is actually coming backward
        }
        return true;
    }
    private ListNode reverse(ListNode head){
        ListNode curr = head, prev = null;
        while(curr != null){
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
    //Question -32 Leet Code 21. Merge Two Sorted Lists
    //Leet Code URL - > https://leetcode.com/problems/merge-two-sorted-lists/submissions/1691080957/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); //created just to hold the resultant Listnode
        ListNode cur = dummy; //curr will be used for traversal purpose
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                cur.next = list2;
                list2 = list2.next;
            } else {
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next; //move curr so that it will be at proper position
        }

        cur.next = (list1 != null) ? list1 : list2; //if anything remaining then add it directly as its already sorted

        return dummy.next;
    }
}
