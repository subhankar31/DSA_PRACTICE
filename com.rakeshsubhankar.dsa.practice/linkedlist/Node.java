package linkedlist;

public class Node {
    int data;
    Node next;
    public Node(int data,Node nextNode){
        this.data=data;
        this.next=nextNode;
    }
    public Node(int data){
        this.data=data;
        this.next=null;
    }
    public Node(){

    }

    public static Node createLinkedList(int data){
        Node newNode=new Node(data);
        return newNode;
    }
    //Common Method to print LinkedList by Giving the Head Node
    static void PrintList(Node head) // Function to print the LinkedList
    {
        Node curr = head;
        for (; curr != null; curr = curr.next)
            System.out.print(curr.data + "-->");
        System.out.println("null");
    }
    public static Node mergeTwoLists(Node list1, Node list2) {
        Node dummy = new Node();
        Node cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.data > list2.data) {
                cur.next = list2;
                list2 = list2.next;
            } else {
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }

        cur.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    public static void main(String[] args) {
        Node head=new Node(5);
        head.next= createLinkedList(10);
        //PrintList(head);
        Node nodeOne=new Node(1);
        nodeOne.next= createLinkedList(10);
        Node nodeTwo=new Node(2);
        nodeTwo.next= createLinkedList(1);
        Node result=mergeTwoLists(nodeOne,nodeTwo);
        PrintList(result);


    }

}
