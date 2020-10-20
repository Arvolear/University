package main.util;

public class Node implements Comparable<Node>
{
    public String text;

    public int quantity;
    public double probability;

    public double leftBorder;
    public double rightBorder;

    public Node(String text)
    {
        this(text, 0.0);
    }

    public Node(String text, double probability)
    {
        this.text = text;

        this.quantity = 1;
        this.probability = probability;

        this.leftBorder = 0.0;
        this.rightBorder = 0.0;
    }

    @Override
    public int compareTo(Node other)
    {
        return other.text.length() - text.length();
    }
}
