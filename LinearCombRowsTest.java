package matrices;
/**
 * Tests the linearCombRows function of Matrix
 * The linearCombRows function should multiply a row by a scalar and add it to a different row
 * This prints a matrix, a new matrix which has undergone linearCombRows, then the original matrix again
 */
public class LinearCombRowsTest {

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
                
                System.out.println("trixie");
                trixie.print(); //printing trixie
                
                System.out.println("\n");
                
                System.out.println("1.5 times row 3 + row 0");
                trixie.linearCombRows(1.5,3,0).print(); //printing the Matrix which results when 1.5 times row 3 of trixie is added to row 0 of trixie
                
                System.out.println("\n");
                
                System.out.println("original trixie");
                trixie.print(); //printing trixie again!
        }

}
