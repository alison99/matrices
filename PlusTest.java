package matrices;
/**
 * Tests the plus() function of Matrix
 * This should add two matrices
 * The original matrices should not be changed
 * Prints two matrices and their sum
 */
public class PlusTest 
{
        public static void main(String[] args) 
        {                       
                Matrix trixie  = new Matrix(3,4); //The matrix on the left
                                
                Matrix alice = new Matrix(3, 4); //The matrix on the right
                                
                for(int i=0; i<3; i++) //Filling the left matrix
                {
                        for(int j=0; j<4; j++)
                        {
                                trixie.setEntry(i,j, i+j);
                        }
                }
                                
                for(int i=0; i<3; i++) //Filling the right matrix
                {
                        for(int j=0; j<4; j++)
                        {
                                alice.setEntry(i,j, i-j);
                        }
                }
                System.out.println("print trixie");
                trixie.print();
                System.out.println();
                System.out.println("print alice");
                alice.print();
                System.out.println();
                System.out.println("print their sum");
                trixie.plus(alice).print(); //The sum of the two matrices                       
        }
}