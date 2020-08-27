import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Method to produce the solution to Project 1
 * @author Deron Washington II
 * Date Started: 8/24/20 @ 11:21am
 *
 */
public class Solution 
{
	
	public static class myPoint implements Comparable<myPoint>
	{
		public BigDecimal x;
		public BigDecimal y;
		
		
		// Parameterized Constructor
		public myPoint()
		{
			x = new BigDecimal(0);
			y = new BigDecimal(0);
		}
				
		// Parameterized Constructor
		public myPoint(BigDecimal _x, BigDecimal _y)
		{
			x = _x;
			y = _y;
		}
		
		public myPoint add(myPoint addend)
		{
			return new myPoint(this.x.add(addend.x), this.y.add(addend.y));
		}
		
		public String toString()
		{
			return "(" + this.x.toString() + ", " + this.y.toString() + ")";
		}

		/**
		* Compares two points
		* @param p = another point object for comparison
		 */
		@Override
		public int compareTo(myPoint p)
		{
			// TODO Auto-generated method stub
			if (x.compareTo(p.x) == 0)
			{
				if (y.compareTo(p.y) == 0)
					return 0;
				else if (y.compareTo(p.y) < 0)
					return -1;
				else if (y.compareTo(p.y) > 0)	
					return 1;
			}
			else if (x.compareTo(p.x) < 0 )
				return -1;
			
			else if (x.compareTo(p.x) > 0)
				return 1;
			
			// error
			return -999;
		}
		
	}

	
	/** 
	 * Method to find the fraction a/b that is approximately
	 * equivalent to sqrt(N/D)
	 * meaning a/b must be the fraction in the tree such that
	 * abs(Da^2 - Nb^2) <= b
	 * @param M = Numerator
	 * @param N = Denominator
	 */
	public static void findFractionAB(BigInteger M, BigInteger N)
	{
		// store the value we are looking for M / N
		BigDecimal findAB = new BigDecimal(M).divide(new BigDecimal(N), RoundingMode.HALF_UP);
		ArrayList<myPoint> fractionalTree = new ArrayList<myPoint>();
		
		// hold the points to create the fractionalTree
		ArrayList<myPoint> points = new ArrayList<myPoint>();
		points.add(new myPoint(new BigDecimal(0), new BigDecimal(1)));
		points.add(new myPoint(new BigDecimal(1), new BigDecimal(0)));
		
		myPoint addend1;
		myPoint addend2;
		myPoint sum;
		
		// where to insert into points
		double location = 0;
		
		for (int level = 0; level < 4; level++)
		{
			for (int additions = 0; additions < points.size() - 1; additions++)
			{
				// add the two points
				addend1 = points.get(additions);
				addend2 = points.get(additions + 1);
				sum = new myPoint(addend1.x.add(addend2.x), addend1.y.add(addend2.y));
				// get average & round to highest integer value
				location = (additions + additions + 1) / 2.0;
//				Math.rint(location);
				
				// add the sum to the arrayList in the correct postition
				points.add((int)Math.round(location), sum);
				
				// add to binary tree
				fractionalTree.add(sum);
				additions++;
			}
		}
		
		for (myPoint element : fractionalTree)
		{
			System.out.print(element + " ");
		}
//		// head of tree
//		fractionalTree.add(new BigDecimal(1 / 1));
		
		
		
//		for (int i = 0; i < 7; i++)
			
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scan = null;
		BigInteger M = null;
		BigInteger N = null;
		
		try
		{
			File inFile = new File("input_1.txt");
			scan = new Scanner(inFile);
			
			// get the numerator (M) and denominator (N)
			M = scan.nextBigInteger();
			N = scan.nextBigInteger();
			
			// where the magic happens
			findFractionAB(M, N);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	}

}
