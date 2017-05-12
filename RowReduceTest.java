package matrices;
/**
 * Tests the rowreduce() function of Matrix
 * This function should use an algorithm to reduce a matrix to reduced row echelon form
 * There should be no non-zero numbers above or below the pivot in any column, only zeros and ones
 * The original matrix should not be affected
 * This prints a matrix, the reduced form of that matrix, and the original matrix again
 */
public class RowReduceTest {

    public static void main(String[] args) 
    {
            Matrix alice = new Matrix (5,5); 
            
            for(int i=0; i<5; i++) //Filling alice
            {
                    for(int j=0; j<5; j++)
                    {
                            if(i+j==2)
                                    alice.setEntry(i, j, i+1);
                            else if(i+j==4)
                                    alice.setEntry(i, j, j+1);
                            if(i+j==10)
                            	alice.setEntry(i, j, i+1);
                            else
                                    alice.setEntry(i, j, 0);
                    }
            }
            
            alice.setEntry(2,4, 7);
            
            System.out.println("print alice");
            alice.print(); //printing alice
            
            System.out.println("\n");
            
            System.out.println("print reduced alice");
            alice.rowreduce().print(); //printing the Matrix which results when alice is row reduced
            
            System.out.println("\n");
            
            System.out.println("print original alice");
            alice.print(); //printing alice again!
    }

}