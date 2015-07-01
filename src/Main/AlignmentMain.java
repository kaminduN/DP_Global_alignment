package Main;

import globalAlignment.GlobalAlignment;

/**
 *
 * @author Kamz
 */
public class AlignmentMain {
    private static final int gapPanelty=2;
    public static void main(String[] args) {
        GlobalAlignment alignment =new GlobalAlignment();
        String seq1="AAAC";
        String seq2="AGC";
        
        alignment.Calculate(seq1, seq2, gapPanelty);
        
    }
}
