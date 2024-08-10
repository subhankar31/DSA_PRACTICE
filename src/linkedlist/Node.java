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

    public static void main(String[] args) {
        Node head=new Node(5);
        head.next= createLinkedList(10);
        PrintList(head);


    }

}
