package stacknqueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StackNQueuePractice {
    public void testQuesue() {
        //Queue<Integer> q = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        for (int i = 1; i < q.size(); i++) {
            q.add(q.remove());
        }
        q.add(2);
        for (int i = 1; i < q.size(); i++) {
            q.add(q.remove());
        }
        q.add(3);
        for (int i = 1; i < q.size(); i++) {
            q.add(q.remove());
        }
    }

}
