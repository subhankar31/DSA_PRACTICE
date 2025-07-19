package dsapatternsseventyquestions.util;

public class Points implements Comparable<Points>
{
    public int x;
    public int y;
    public int distSq;
    public int i;

    public Points(int x, int y, int distSq, int i)
    {
        this.x = x;
        this.y = y;
        this.distSq = distSq;
        this.i = i;
    }

    /**
     * This comparison logic sorts objects in ascending order of distSq. That means:
     * A smaller distSq value is considered "less" and will rise to the top of a heap.
     * When used in a PriorityQueue<Points>, it behaves like a min-heap based on distSq.
     */
    @Override
    public int compareTo(Points p2)
    {
        return this.distSq - p2.distSq;
    }
}
