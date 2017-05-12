package matrices;
/**
 * Tests switchRows() function of Matrix
 * This should switch two rows of the matrix but not affect the original matrix
 * Prints a matrix, a new matrix after two rows have been switched, and the original matrix again
 */
public class SwitchRowsTest {

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
            
            System.out.println("switch rows 1 and 3");
            trixie.switchRows(1,3).print(); //printing the Matrix which results when rows 1 and 3 of trixie are exchanged
            
            System.out.println("\n");
            
            System.out.println("print the original trixie");
            trixie.print(); //printing trixie again!
    }

}
