package Entity;

/**
 *
 * @author My laptop
 */
public class Node {
    //usedto hold references to othere connected nodes
    private Node up;
    private Node side;
    private Node left;
    
    //value that gets calculated
    private int value;
    
    //used to hold data for easy use
    private char row = ' ';
    private char column = ' ';

    //stores the alignment details of the node with neighbouring nodes
    //willbe used to print alignemnt when the global alignment is complete
    private String align;
    
    
    public Node() {
    }

    public Node(int value) {
        setValue(value);
    }

    
    
    
    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getSide() {
        return side;
    }

    public void setSide(Node side) {
        this.side = side;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getAlign() {
        return align;
    }

    
    
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    
    
}
