package matrices;
/**
 * Tests the VDM method to see whether it can calculate the slope at a point and graph the slope function at that point
 */
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;

public class VDMTest {
	public static void main(String[] args) {
		VDM VertDif = new VDM();
		Polynomial p = new Polynomial(new double[] {3, -2, 4, 1}); //p = x^3 + 4x^2 - 2x + 3
		double a = 5.0;
		//slope at a point
		System.out.println("slope at x = " + a);
		System.out.println(VertDif.slopeAtPoint(p, a));
		
		//graph the slope function
		PlotFrame slope = new PlotFrame("x", "y", "Derivative");
		slope.setPreferredMinMaxX(-5, 5);
		slope.setDefaultCloseOperation(3);
		slope.setVisible(true);
		
		VertDif.slopeFunction(p, slope);
	}
}
