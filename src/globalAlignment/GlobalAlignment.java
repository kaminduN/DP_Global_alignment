package globalAlignment;

import Entity.Node;
//import com.sun.org.apache.bcel.internal.generic.StackConsumer;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Kamz
 */
public class GlobalAlignment {
    //the value of the score. 
    //+1 for matching alignments 
    //-1 for mismatching alignemnts
    private final int scoreValue=1;
    
    
    private Stack<Node> templist;

    public GlobalAlignment() {
        this.templist = new  Stack<>();
    }
    
    
    /**
     * starting point of the alignment 
     * setup nodes accordingly and calculate paths and values
     * 
     * @param seq1 - 1st DNA sequence to align
     * @param seq2 - 2nd DNA sequence to align
     * @param d - gap panelty
     * 
     * 
     * seq1 acts as the row's of matrix
     * seq2 act as the column's
     * gets called in main - from this backtrack method will be called
     */
    public void Calculate(String seq1,String seq2,int d){
    
    
    Node[][]set=new Node[seq1.length()+1][seq2.length()+1];
    //int[][]b=new int[seq1.length()+1][seq2.length()+1];
    set[0][0]=new Node(0);
   /*     
    //initialize the first rao and coulm of the matrix
        for (int i = 1; i < seq1.length()+1; i++) {
            set[i][0]=new Node(i*(-d));
            set[i][0].setRow(seq1.charAt(i-1));//sequence starts with 1. but indexces start with 0
        }
        for (int i = 1; i < seq2.length()+1; i++) {
            set[0][i]=new Node(i*(-d));
            set[0][i].setColumn(seq2.charAt(i-1));
        }
    */
    //initialize the rest of the matrix
       for (int i = 1; i < seq1.length()+1; i++) { 
           for (int j = 1; j < seq2.length()+1; j++) {
            //initialize the first row and column
            set[i][0]=new Node(i*(-d));
            set[i][0].setUp(set[i-1][0]);//set relations with above
            set[i][0].setRow(seq1.charAt(i-1));//sequence starts with 1. but indexces start with 0
            
            set[0][j]=new Node(j*(-d));   
            set[0][j].setLeft(set[0][j-1]);//set relations with left
            set[0][j].setColumn(seq2.charAt(j-1));
            
            //initialize the rest of the nodes
            set[i][j]=new Node();
            set[i][j].setRow(seq1.charAt(i-1));//sequence starts with 1. but indexces start with 0
            set[i][j].setColumn(seq2.charAt(j-1));
           }
        }
        
        
        // calculation
     char []seq1list= seq1.toCharArray();
     char []seq2list= seq2.toCharArray();
     
        for (int i = 1; i < set.length; i++) {
            for (int j = 1; j < set[i].length; j++) {
                
                int l1;
                if(seq1list[i-1]== seq2list[j-1]){//side
                    l1=set[i-1][j-1].getValue()+scoreValue;
                    
                }else{
                        l1=set[i-1][j-1].getValue()-scoreValue;
                }
                
                int l2=set[i][j-1].getValue()-d;//left
                int l3=set[i-1][j].getValue()-d;//up
            
            //set[i][j]=Math.max(Math.max(l1,l2), l3);
                set[i][j].setValue(Math.max(Math.max(l1,l2), l3));//set the maximum value to the node
            
                //which way the reference to backtrack is getting.
                if(set[i][j].getValue()==l1){
                    //side
                    set[i][j].setSide(set[i-1][j-1]);
                }
                if(set[i][j].getValue()==l2){
                    //left
                    set[i][j].setLeft(set[i][j-1]);
                }
                if(set[i][j].getValue()==l3){
                    //up
                    set[i][j].setUp(set[i-1][j]);
                }
            
            }
        }
    
       //now lets find alignment 
       backtrack(set[seq1.length()][seq2.length()]);
    }
    
    
        
    
    /**
     * A Recursive called method to get the optimal path(s)
     * called inside Calculate method
     * @param parent - first node that needs to start the search
     */
    private void backtrack(Node parent){
        
        
        
        if(parent.getSide()!=null){//the side path is open
            
            String seqalign=parent.getRow()+" "+parent.getColumn()+" , ";
            parent.setAlign(seqalign);
            templist.push(parent);//add data to stack
            backtrack(parent.getSide());
            
            templist.pop();
        }
        
        if(parent.getUp()!=null){//the path that goes up is open
            String seqalign=parent.getRow()+" "+"-"+" , ";
            parent.setAlign(seqalign);
            templist.push(parent);//add data to stack
            backtrack(parent.getUp());
        
            templist.pop();
        }
        
        
        if(parent.getLeft()!=null){//path that goes left is open
            String seqalign="-"+" "+parent.getColumn()+" , ";
            parent.setAlign(seqalign);
            templist.push(parent);//add data to stack
            backtrack(parent.getLeft());
        
            templist.pop();
        }
        
        
        
        //if reached here that means all the options are checked. 
        if(parent.getValue()==0 &&(parent.getRow()==' ' & parent.getColumn()==' ')){
            //the last element: laft most up element is rached.
            // elemeinate search
            //print all values in the stack
            printStack(templist);
            
            return;
        }
        //reached here is intermediat nodes are done searching 
        //and the methond is now returning
        //templist.pop();//its time to remove the unnecessory nodes
        
        //return;//ending the serch for the rest of the otheres.
    }
    
    /**
     * Called in side backtrack when a sequence is found
     * @param temp - the alignment sequence
     * 
     * output- alignment details with the relevant score
     */
    public void printStack(Stack<Node> temp){
        System.out.println("The matched sequence");
        Iterator<Node> i =temp.iterator();
        int total=0;
        while (i.hasNext()) {
            Node next = i.next();
            total=total+next.getValue();
            System.out.println(next.getAlign());
            
        }
        System.out.println("Alignment value - "+total);
        //System.out.println(temp.toString());
        System.out.println("");
                        
    }
    
}
