import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Method to produce the solution to Project 1
 * @author Deron Washington II
 * Date Started: 8/24/20 @ 11:21am
 * Date Finished: 8/27/20 
 * Date Optimized: 8/28/20 @ 11:53pm
 *
 */
public class Main 
{
	/**
	 * Class to house a point in the fractional tree
	 * representing a numerator and denominator
	 * of big integer types
	 * @author DGOAT
	 */
	public static class myPoint implements Comparable<myPoint>
	{
		/* numerator  */
		public BigInteger x;

		/* denominator  */
		public BigInteger y;


		/**
		 * Default Constructor to initialize object
		 * to a default value (0,0)
		 */
		public myPoint()
		{
			x = new BigInteger("0");
			y = new BigInteger("0");
		}

		/**
		 * Parameterized Constructor to initialize
		 * object to the values of the parameters
		 * @param _x = numerator
		 * @param _y = denominator
		 */
		public myPoint(BigInteger _x, BigInteger _y)
		{
			x = _x;
			y = _y;
		}

		/**
		 * Method to add two myPoint objects
		 * @param addend = the object to be added
		 * @return
		 * 				= the sum of the two point objects
		 */
		public myPoint add(myPoint addend)
		{
			return new myPoint(this.x.add(addend.x), this.y.add(addend.y));
		}

		/**
		 * Method to represent this object as a string
		 */
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

		/**
		 * Method to determine if an object 
		 * is equivalent to this
		 @return 
		 			= true if the data members are equal
		 			= false otherwise
		 */
		@Override
		public boolean equals(Object o)
		{
			// if comparing this object with itself then return true
			if (o == this)
				return true;

			// Check if o is an instance of myPoint or not 
			// "null instanceof [type]" also returns false */
			if (!(o instanceof myPoint)) 
			{ 
				return false; 
			} 

			// typecast o to myPoint so that we can compare data members  
			myPoint c = (myPoint) o; 

			// Compare the data members and return accordingly  
			return x.equals(c.x) == true
					&& y.equals(c.y) == true; 
		}

	}

	
	
	
	
	/** 
	 * Method to write the output to an output file
	 * in the required format:
	 * a
	 * b
	 * @param answer = the point in the fractional
	 * 								tree that satisfies the
	 * 								formula
	 */
	public static void writeOutput(myPoint answer)
	{
		File outFile = new File("output.txt");
		PrintWriter writer = null;
		
		try
		{
			writer = new PrintWriter(outFile);
			writer.println(answer.x);
			writer.println(answer.y);
		
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			writer.close();
		}
	}

	/** 
	 * Method to find the fraction a/b that is approximately
	 * equivalent to sqrt(M/N)
	 * meaning a/b must be the fraction in the tree such that
	 * abs(Na^2 - Mb^2) < b
	 * @param M = Numerator
	 * @param N = Denominator
	 */
	public static myPoint findFractionAB(BigInteger M, BigInteger N)
	{		
		// hold the points to create the fractionalTree & initialize list with
		// the two starting values of a fractionalTree
		List<myPoint> points = new ArrayList<myPoint>();
		points.add(new myPoint(new BigInteger("0"), new BigInteger("1")));
		points.add(new myPoint(new BigInteger("1"), new BigInteger("0")));

		// variables to perform the addition
		myPoint addend1;
		myPoint addend2;
		myPoint sum;

		// store the value we are looking for M / N
		BigInteger findAB = null; 

		while (true)
		{
			// add the two points
			addend1 = points.get(0);
			addend2 = points.get(1);
			sum = new myPoint(addend1.x.add(addend2.x), addend1.y.add(addend2.y));

			// add the sum to points in the correct position
			points.add(1, sum);

			// element to test  in the tree
			myPoint element = points.get(1);

			/* b = the y value of the element according 
				to the original formula (check JavaDocs of this method) */
			BigInteger b = element.y;

			// this represents the first quantity of the subtraction (minuend)
			BigInteger minuend = N.multiply(element.x.pow(2));

			// this represents the second quantity of the subtraction (subtrahend)
			BigInteger subtrahend = M.multiply(element.y.pow(2));

			// the result of the formula to compare with b
			findAB = N.multiply(element.x.pow(2)).subtract(M.multiply(element.y.pow(2)));
			findAB = findAB.abs();

			// FOUND IT!
			if (minuend.compareTo(subtrahend) == 0 || findAB.compareTo(b) < 0)
				return element;

			// go right (remove left (first element))
			else if (minuend.compareTo(subtrahend) < 0)
				points.remove(0);

			// go left (remove right (last element))
			else if (minuend.compareTo(subtrahend) > 0)
				points.remove(points.size() - 1);

		} // END OF WHILE LOOP			
	}

	public static void main(String[] args) 
	{
		// Scanner object to read from file
		Scanner scan = null;
		BigInteger M = null;
		BigInteger N = null;

		try
		{
			File inFile = new File("input_2.txt");
			scan = new Scanner(inFile);

			// get the numerator (M) and denominator (N)
			M = scan.nextBigInteger();
			N = scan.nextBigInteger();

			// where the magic happens
			myPoint answer = findFractionAB(M,N);
			
			// print to output file
			writeOutput(answer);


		}
		catch(FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		finally
		{
			scan.close();
		}


	}

}
