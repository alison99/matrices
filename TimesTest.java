package matrices;
/**
 * Tests the times() function of Matrix
 * This should multiply two matrices
 * Matrices can only be multiplied if the number of columns of the first is equal to the number of rows of the second
 * The original matrices should not be changed by this function
 * This prints two matrices and their product
 */
public class TimesTest {

    public static void main(String[] args) {
            
            Matrix trixie  = new Matrix(3,4); //The matrix on the left
            
            Matrix alice = new Matrix(4, 5); //The matrix on the right
            
            for(int i=0; i<3; i++) //Filling the left matrix
            {
                    for(int j=0; j<4; j++)
                    {
                            trixie.setEntry(i,j, i+j);
                    }
            }
            
            for(int i=0; i<4; i++) //Filling the right matrix
            {
                    for(int j=0; j<5; j++)
                    {
                            alice.setEntry(i,j, i-j);
                    }
            }
            
            //print two matrices
            System.out.println("trixie");
            trixie.print();
            System.out.println();
            System.out.println("alice");
            alice.print();
            System.out.println();
            System.out.println("trixie times alice - the product");
            trixie.times(alice).print(); //The product of the two matrices
            
    }

}
