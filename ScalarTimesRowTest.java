package matrices;
/**
 * Tests the scalarTimesRow() function of Matrix
 * This should multiply the desired row by the designated scalar
 * The original matrix should not be changed
 * This prints a matrix, a new matrix where a row has been multiplied by a scalar, and the original matrix again
 */
public class ScalarTimesRowTest {

    public static void main(String[] args) 
    {
            Matrix trixie = new Matrix (4,4); 
            
            for(int i=0; i<4; i++) //Filling trixie
            {
                    for(int j=0; j<4; j++)
                    {
                            if(i+j==2)
                                    trixie.setEntry(i, j, i+1);
                            else
                                    trixie.setEntry(i, j, 0);
                    }
            }
            
            trixie.setEntry(3,3, 4);
            
            System.out.println("print trixie");
            trixie.print(); //printing trixie
            
            System.out.println("\n");
            
            System.out.println("row 2 times 1.5");
            trixie.scalarTimesRow(1.5,2).print(); //printing the Matrix which results when row 3 of trixie is multiplied by 1.5
            
            System.out.println("\n");
            
            System.out.println("print original trixie");
            trixie.print(); //printing trixie again!
    }

}