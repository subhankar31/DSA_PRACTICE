package rough;

import linkedlist.Node;

import java.util.HashMap;
import java.util.List;

//LinkedList Node
class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class NodeWRaandom {
    int val;
    NodeWRaandom next;
    NodeWRaandom random;

    public NodeWRaandom(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class LinkedListRoughWork {

    //Delete a node in LinkedList
    //https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node) {
        //update node val to its next note val
        node.val=node.next.val;
        //point to next to next
        node.next=node.next.next;

    }
    //Middle of a LinkedList
    //https://leetcode.com/problems/middle-of-the-linked-list/description/
    public ListNode middleNode(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while (fast != null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    //Reverse a LinkedList
    //https://leetcode.com/problems/reverse-linked-list/description/
    public ListNode reverseList(ListNode head) {
        ListNode temp=head;
        ListNode prev=null;
        ListNode aft=null;
        while (temp != null){
            aft=temp.next;
            temp.next=prev;
            prev=temp;
            temp=aft;
        }
        return prev;
    }
    //LinkedList Cycle
    //https://leetcode.com/problems/linked-list-cycle/description/
    public boolean hasCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while (fast != null && fast.next != null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
    //LinkedList Cycle 2
    //detect starting point of a Loop
    //https://leetcode.com/problems/linked-list-cycle-ii/description/
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while (fast != null && fast.next != null){
            slow=slow.next;
            fast=fast.next.next;

            if (slow == fast){
                //re start slow
                slow=head;
                while (slow != fast){
                    slow=slow.next;
                    fast=fast.next;
                }
                return slow; //ans
            }
        }
        return null;
    }
    //palindrome in LL
    //https://leetcode.com/problems/palindrome-linked-list/
    public boolean isPalindrome(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        //calculate length to know odd even size
        int size=0;
        for (ListNode curr=head;curr != null;curr=curr.next){
            size++;
        }
        //reach mid node
        while (fast != null && fast.next != null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //resize mid
        if (size % 2 != 0) slow=slow.next;
        //reverse and compare
        ListNode secondhead=LLReverse(slow);
        while (head != null && secondhead != null){
            if (head.val != secondhead.val) return false;
            head=head.next;
            secondhead=secondhead.next;
        }
        return true;

    }
    public ListNode LLReverse(ListNode head){
        ListNode curr=head;
        ListNode prev=null;
        ListNode aft=null;
        while (curr != null && curr.next != null){
            aft=curr.next;
            curr.next=prev;
            prev=curr;
            curr=aft;
        }
        return prev;
    }
    //https://leetcode.com/problems/odd-even-linked-list/description/
    //Odd and Even Nodes in LL
    public ListNode oddEvenList(ListNode head) {
        ListNode odd=head;
        ListNode even=head.next;
        ListNode evenHead=head.next;

        while (even != null && even.next != null){
            odd.next=odd.next.next;
            even.next=even.next.next;
            odd=odd.next;
            even=even.next;
        }
        //imp .. very very imp
        odd.next=evenHead;
        return head;
    }
    //Remove the ntj node from end of LL
    //https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast=head;
        ListNode slow=head;

        //traverse fast n time sp we will create a gap between fast and slow of n size
        for (int i=0;i<n;i++){
            fast=fast.next;
        }
        //check if first already reached the end that means we need to remove nth node end
        //which is nothing but head
        if (fast == null)
            return head.next;
        //now we need to move fast till end and slow simulataniously one step so the gap between fast and
        //slow remains as n size
        while (fast != null && fast.next != null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;
    }
    //Delete middle node in a LL
    //https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) return null;
        ListNode fast=head.next.next;
        ListNode slow=head;

        while (fast != null && fast.next != null){
            fast=fast.next.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;

        return head;
    }
    //Sort LL
    //https://leetcode.com/problems/sort-list/description/
    public ListNode sortList(ListNode head) {
        if (head == null || head.next==null) return head;
        ListNode mid=getMiddleNode(head);
        ListNode firsthead=head;
        ListNode secondHead=mid.next;
        mid.next=null;

        //recursive call
        firsthead=sortList(firsthead);
        secondHead=sortList(secondHead);

        return merge(firsthead,secondHead);

    }

    public ListNode getMiddleNode(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next; //slight change to get first mid || floor mid point
        while (fast != null && fast.next != null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    public ListNode merge(ListNode first,ListNode second){
        ListNode dummy = new ListNode(0); //created as new head
        ListNode curr=dummy; //created to traverse and add nodes
        while ( first != null && second != null){
            if (first.val <= second.val){
                curr.next=first;
                first=first.next;
            }else{
                curr.next=second;
                second=second.next;
            }
            curr=curr.next;
        }
        //lif remaining left then add
        if (first != null){
            curr.next=first;
        }else if (second != null){
            curr.next=second;
        }
        //return the new head as result
        return dummy.next;
    }
    //find intersection point of LL
    //https://leetcode.com/problems/intersection-of-two-linked-lists/description/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //create 2 nodes to traverse
        ListNode d1=headA;
        ListNode d2=headB;

        while ( d1 != d2){
            d1 = d1==null ? headB : d1.next;
            d2 = d2==null ? headA : d2.next;
        }
        return d1; // we can return d2 also since both are now same
    }
    //sum of 2 LL values
    //https://leetcode.com/problems/add-two-numbers/description/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead=new ListNode(0);
        ListNode curr=dummyHead;
        int carry=0;

        //allow here all though OR condition but have check later
        while (l1 != null || l2 != null || carry != 0){
            int digit1= l1 != null ? l1.val : 0; //we want 0 in case of null
            int digit2= l2 != null ? l2.val : 0;
            int sum= digit1+digit2+carry;
            int digit=sum%10;
            carry=sum/10;
            ListNode newNode=new ListNode(digit);
            curr.next=newNode;
            curr=curr.next;
            //more l1 and l2
            l1= l1.next != null ? l1.next : null;
            l2= l2.next != null ? l2.next : null;
        }

        return curr.next;
    }
    //Reverse LL in k groups
    //https://leetcode.com/problems/reverse-nodes-in-k-group/description/
    //Skipped since not fully cleared on code side || or unable to focus , will visit again

    //Rotate LL to right by k place
    //https://leetcode.com/problems/rotate-list/
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        //check size and base conditions
        int n=lengthOfLL(head);
        if (k % n == 0) return head;// no rotation needed
        k=k%n; //reeduce extra k size
        //first reach k-1th node from end side
        ListNode node= head;
        for (int i=0;i<n-k-1;i++){
            node=node.next;
        }
        ListNode newHead=node.next; //once reached mark its next node as new head
        node.next=null; //mark current next as null since this will be end of LL after rotation

        ListNode tail=newHead; //declare for traversal only to reach dead end
        while (tail.next != null){
            tail=tail.next;
        }
        tail.next=head; //once reach dead end point its next to our original head
        return newHead;
    }
    public int  lengthOfLL(ListNode head){
        int count=0;
        while (head != null){
            head=head.next;
            count++;
        }
        return count;
    }
    //HAARD
    //Copu LL with Random Pointer
    //https://leetcode.com/problems/copy-list-with-random-pointer/
    //Imp.. Uses different type LL
    public NodeWRaandom copyRandomList(NodeWRaandom head) {
        //first creaate a HashMap and put oldnode , new node comnination
        HashMap<NodeWRaandom,NodeWRaandom> map=new HashMap<>();
        NodeWRaandom curr=head;
        while (curr != null){
            NodeWRaandom newNode=new NodeWRaandom(curr.val);
            map.put(curr,newNode);
            curr=curr.next;
        }
        //now traverse again in old LL and modify new LL simultaniously
        curr=head; //re assigned
        while (curr != null){
            NodeWRaandom copy=map.get(curr);
            copy.next=map.get(curr.next);
            copy.random=map.get(curr.random);
            curr=curr.next;
        }
        return map.get(head); // returns new head of copy node
    }









    public static void main(String[] args) {

    }
}
