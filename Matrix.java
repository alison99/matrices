package matrices;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 * Matrix
 * @author Alison Chen
 * 
 * This class creates methods for all elementary row operations and basic functions that concern matrices
 * These functions do not change the original matrix
 * print() prints a matrix
 * plus() adds two matrices
 * times() multiplies two matrices
 * scalarTimesRow() multiplies a row by a scalar
 * linearCombRows() multiplies a row by a scalar and adds it to a different row,
 * which is used in rowreduce to eliminate non-zero entries leaving only the pivot
 * rowreduce() reduces a matrix to its reduced row echelon form
 * invert() gives the invert of a matrix, useful since matrices cannot be divided
 * 
 * These methods are then used in VDM to find the slope at a point of a function, which is then used to graph the derivative of a function
 */
public class Matrix {
	int r; //rows
	int c; //columns
	double mat[][]; //2D array

	//getters and setters
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public double[][] getM() {
		return mat;
	}

	public void setM(double[][] mat) {
		this.mat = mat;
	}

	//make matrix
	public Matrix(int r, int c) {
		this.r = r;
		this.c = c;
		mat = new double[r][c];
	}

	//make 2D array
	public void setEntry(int i, int j, double e) {
		mat[i][j] = e;
	}

	//print matrix
	public void print() {
		//round entries to nearest hundredths place
		DecimalFormat df = new DecimalFormat("#0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		for(int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(df.format(mat[i][j]) + "	");
			}
			System.out.println();
		}
	}

	//add two matrices without changing the original matrices and return the sum
	public Matrix plus(Matrix n) {
		Matrix sum = new Matrix(r, c);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sum.mat[i][j] = mat[i][j] + n.mat[i][j];
			}

		}
		return sum;
	}
	
	//example of how to multiply matrices
	//	3x2  vs  2x3  = 3x3
	//	1 0     3 5 4     (0,0) = 1*3+0*2 ; (0,1) = 1*5+0*6 ; (0,2) = 1*4+0*1
	//	2 4  x  2 6 1  =  (1,0) = 2*3+4*2 ; (1,1) = 2*5+4*6 ; (1,2) = 2*4+4*1
	//	3 6               (2,0) = 3*3+3*2 ; (2,1) = 3*5+3*6 ; (2,2) = 3*4+6*1

	//multiply two matrices without changing the original matrices and return the product
	public Matrix times(Matrix n) {
		Matrix product = new Matrix(r, n.c);
		//can only multiply if the number of columns of the first is equal to the number of rows of the second
		if(c == n.r) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < n.c; j++) {
					for (int k = 0; k < n.r; k++) {
						product.mat[i][j] += mat[i][k]*n.mat[k][j];
					}
				}
			}
		}
		return product;
	}
	
	//multiply a row by a scalar without changing the original matrix
	public Matrix scalarTimesRow(double s, int row) {
		Matrix str = new Matrix(r, c);
		for(int j = 0; j < r; j++) {
			for (int i = 0; i < c; i++) {
				//if the row is equal to the desired row, then multiply entries in that row by the scalar
				if(j == row) {
					str.mat[j][i] = mat[row][i]*s;
				}
				//otherwise fill with entries of original matrix
				else {
					str.mat[j][i] = mat[j][i];
				}
			}
		}
		return str;
	}
	
	//switch two rows of a matrix without changing the original matrix
	public Matrix switchRows(int r1, int r2) {
		Matrix sr = new Matrix(r, c);
		//fill the matrix with original values
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
			sr.mat[i][j] = mat[i][j];
			}
		}
		//for the rows that are desired, switch them
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sr.mat[r1][j] = mat[r2][j];
				sr.mat[r2][j] = mat[r1][j];
			}
		}
		return sr;
	}
	
	//multiply a row by a scalar and add it to the second row
	//do not change the original matrix
	public Matrix linearCombRows(double s, int r1, int r2) {
		Matrix lcr = new Matrix(r, c);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				//if row is desired row, multiply by a scalar and add to second row
				if(i==r2) {
					lcr.mat[r2][j] = mat[r2][j] + s*mat[r1][j];
				}
				//otherwise fill with original values
				else{
					lcr.mat[i][j] = mat[i][j];
				}
			}
		}
		return lcr;
	}
	
	//reduce the matrix to reduced row echelon form
	
	//example:
	//1	 0	0  4  0
	//0	 1  0  0  0
	//0  0  1  0  2
	
	//matrix does not necessarily have ones all along the diagonal
	
	//algorithm:
	//find first non-zero number in a column (the pivot)
	//switch that row to the desired pivot row (not necessarily diagonal)
	//multiply row by reciprocal of the pivot
	//linearCombRows with every other row - multiply by -1 * number in the other rows and same column
	//this should eliminate all the numbers in that column except for 1 in the pivot
	//move to the next column
	public Matrix rowreduce() {
		Matrix reduced = new Matrix(r, c);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				reduced.mat[i][j] = mat[i][j];
			}
		}
		double max = 0;
		if(r>c) {
			max = c;
		}
		else {
			max = r;
		}
		int pr = 0;
		for (int i = 0; i < max; i++) { //row reduce maximum columns - 
			for (int j = pr; j < r; j++) {
//				System.out.println("pivot row " + pr + " checking row " + j + " column " + i);
					if(reduced.mat[j][i]!=0) { //if number is not 0
					reduced = reduced.switchRows(j, pr); //switch rows so it's the pivot row
					reduced = reduced.scalarTimesRow((1/reduced.mat[pr][i]), pr); //multiply by reciprocal
					
					//eliminate all other entries in column to 0
					for(int k = 0; k < r; k++) {
						//don't touch if pivot or if already 0
						if(k!=pr && reduced.mat[k][i]!=0) {
						reduced = reduced.linearCombRows(-1*(reduced.mat[k][i]), pr, k); //add the additive inverse to make it 0
						}
					}
				pr++; //pivot row only increases if there is a non-zero number in that column
				}
			}
		}

		//make -0 into 0
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(reduced.mat[i][j] == -0.0) {
					reduced.mat[i][j] = 0.0;
				}
			}
		}
		return reduced;
	}
	
	//invert a matrix - does not affect the original matrix
	//can only invert a square matrix
	//1. augment a matrix by adding the identity matrix
	//2. rowreduce the matrix
	//3. take the entries from the right side of the matrix - the left side is now the identity
	public Matrix invert() {
		Matrix inverted = new Matrix(r,c);
		Matrix augmented = new Matrix(r, 2*c);
		
		if(r==c) { //must be square to be inverted
		//fill augmented matrix with original values
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				augmented.mat[i][j] = mat[i][j];
			}
		}
		//add the identity matrix
		for (int i = 0; i < r; i++) {
			for (int j = c; j < 2*c; j++) {
				if(i+c==j) {
					augmented.mat[i][j] = 1;
				}
				else{
					augmented.mat[i][j] = 0;
				}
			}
		}
		//row reduce the matrix
		augmented = augmented.rowreduce();
		}
		//take the numbers from the right side of the augmented matrix - this is the invert
		for (int i = 0; i < r; i++) {
			for (int j = c; j < 2*c; j++) {
				inverted.mat[i][j-c] = augmented.mat[i][j];
			}
			}
		return inverted;
	}
}
