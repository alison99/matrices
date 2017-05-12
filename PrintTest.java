package matrices;
/**
 * Tests the print() function of Matrix
 * This should print the matrix as entered by the user
 */
public class PrintTest {
        
        public static void main(String[] args) 
        {
                Matrix Test = new Matrix(5,5);
                
                for(int i=0; i<5; i++) //fill matrix
                {
                        for(int j=0; j<5; j++)
                        {
                               Test.setEntry(i,j, .1+5*i+j);
                        }
                }
             
        System.out.println("print matrix");
        Test.print();
                
        }

}
