package dsapatternsseventyquestions.util;

/**
 * This class will be used across all LinkedList pattern Questions
 */
public class ListNode {
      public int val;
      public ListNode next;
      public ListNode() {}
      public ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }