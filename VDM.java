package matrices;
import java.awt.Color;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.*;
/**
 * The VDM method finds the slope at a point and graphs it
 *
 */
public class VDM {
	//v(x) = f(x) - mx - b = (x-a)^2 * Q(x) = quad * Q
	
	//slope at a point
	public double slopeAtPoint(Polynomial poly, double a) {
		int degQ = poly.getDegree() - 2; //degQ(x) = degf(x)-2
		Polynomial quad = new Polynomial(new double[] {Math.pow(a, 2), -2*a, 1}); //poly = a^2 - 2ax + x^2 = x^2 - 2ax + a^2 = (x-a)^2
		int[] qdegs = new int[degQ+1]; //make array of degrees of x in Q(x)
		int[] quad_degs = new int[quad.getDegree()+1]; //make array of degrees of x in v(x)
		
		for (int i = 0; i < qdegs.length; i++) {
			qdegs[i] = degQ - i; //counts down from the highest degree of Q(x) and adds it to the array
		}
		for (int i = 0; i < quad_degs.length; i++) {
			quad_degs[i] = quad.getDegree() - i; //takes degree of quad (x-a)^2 (i.e. 2) and counts down to add to array
		}
		
		Matrix vmat = new Matrix((poly.getDegree()+1), (poly.getDegree()+2)); //why +2?
		for (int i = 0; i < poly.getDegree(); i++) {
			for (int j = 0; j < quad_degs.length; j++) {
				for (int k = 0; k < qdegs.length; k++) {
					if(quad_degs[j] + qdegs[k] == i) { //if sum of deg quad and deg Q(x) = deg f(x) then must be like terms
						double quadcoef = quad.getCoefficient(quad_degs[j]).getTerms()[0].getTermDouble(); //get the coefficient at term 0??
						vmat.mat[i][qdegs[k]+2] += quadcoef;
					}
				}
			}
		}
		
		for (int i = 0; i < vmat.mat.length; i++) {
			vmat.mat[i][vmat.mat.length] = poly.getCoefficients()[i].getTerms()[0].getTermDouble();
		}
		vmat.mat[0][0] += 1; //b
		vmat.mat[1][1] += 1; //m
		vmat = vmat.rowreduce();
		
		return vmat.mat[1][vmat.mat[0].length-1]; //slope at a
	}
	
	public void slopeFunction (Polynomial poly, PlotFrame slope) {
			Trail t = new Trail();
			slope.setMarkerColor(1, Color.green);
			t.color = Color.blue;
			
			for (double i = -10; i <= 10 ; i+= 0.01) {
			slope.append(1, i, slopeAtPoint(poly, i));
			t.addPoint(i, slopeAtPoint(poly, i));
			}
			
			slope.addDrawable(t);
			slope.setVisible(true);
			
	}
	
}