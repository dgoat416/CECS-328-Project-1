import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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
		public BigInteger x;
		public BigInteger y;
		
		
		// Parameterized Constructor
		public myPoint()
		{
			x = new BigInteger("0");
			y = new BigInteger("0");
		}
				
		// Parameterized Constructor
		public myPoint(BigInteger _x, BigInteger _y)
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

	
	public static void printTree(List<myPoint> binaryTree) throws FileNotFoundException
	{
		int count = 0;
		int level = 0;
		
		File outFile = new File("output.txt");
		PrintWriter writer = new PrintWriter(outFile);
		// print out tree by level
		for (myPoint x :binaryTree)
		{
			count++;
			writer.print(x + " ");
			
			if (Math.pow(2, level) == count)
				{
					writer.print("\n");
					level++;
					count = 0;
				}
				
		}
		
		// save the contents of the writer
		writer.close();
	}
	
	/** 
	 * Method to find the fraction a/b that is approximately
	 * equivalent to sqrt(N/D)
	 * meaning a/b must be the fraction in the tree such that
	 * abs(Da^2 - Nb^2) <= b
	 * @param M = Numerator
	 * @param N = Denominator
	 */
	public static myPoint findFractionAB(BigInteger M, BigInteger N)
	{
		// Set precision to 10 
        MathContext mc = new MathContext(10); 
		// store the value we are looking for M / N
		BigInteger findAB = null; 
//		new BigInteger(M).divide(new BigInteger(N), mc);
  
        // calculate square root of BigInteger 
        // using sqrt() method 
//        findAB = findAB.sqrt(mc); 
        
		List<myPoint> fractionalTree = new ArrayList<myPoint>();
		List<myPoint> nodesCurrentLvl = new ArrayList<myPoint>();
		
		// hold the points to create the fractionalTree
		List<myPoint> points = new ArrayList<myPoint>();
		points.add(new myPoint(new BigInteger("0"), new BigInteger("1")));
		points.add(new myPoint(new BigInteger("1"), new BigInteger("0")));
		
		myPoint addend1;
		myPoint addend2;
		myPoint sum;
		
		// where to insert into points
		double location = 0;
		
		while (true)
		{
			for (int additions = 0; additions < points.size() - 1; additions++)
			{
				// add the two points
				addend1 = points.get(additions);
				addend2 = points.get(additions + 1);
				sum = new myPoint(addend1.x.add(addend2.x), addend1.y.add(addend2.y));
				
				// get average & round to highest integer value to get the index
				location = (additions + additions + 1) / 2.0;
				
				// add the sum to the arrayList in the correct postition
				points.add((int) Math.round(location), sum);
				
				// add to binary tree
//				fractionalTree.add(sum);
				nodesCurrentLvl.add(sum);
				
				myPoint mine = new myPoint(new BigInteger("16"), new BigInteger("15"));
				if (sum.x.equals(mine.x) && sum.y.equals(mine.y))
				{
					nodesCurrentLvl.get(0); 
				}
				additions++;
			}
			
			// don't check for first node in the tree
//			if (nodesCurrentLvl.size() == 1)
//				continue;
				
//			if (nodesCurrentLvl.size() > 1)
//			{
				BigInteger b = null;
				myPoint element = nodesCurrentLvl.get(0);
				BigInteger minuend = N.multiply(element.x.pow(2));
				BigInteger subtrahend = M.multiply(element.y.pow(2));
				// search for M / N
//				for (myPoint element : nodesCurrentLvl)
//				for (int i = 0; i < nodesCurrentLvl.size(); i++)
//				{
//					myPoint element = nodesCurrentLvl.get(0);
					findAB = N.multiply(element.x.pow(2)).subtract(M.multiply(element.y.pow(2)));
					findAB = findAB.abs();
					b = element.y;
					if (minuend.compareTo(subtrahend) == 0 || findAB.compareTo(b) < 0)
					{
						try
						{
							printTree(fractionalTree);
						} catch (FileNotFoundException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// print parent index
						// this and index
						// and sibling node of the tree
						
						return element;
					}
					else if (minuend.compareTo(subtrahend) < 0)
					{
						// go right ( remove left (first element))
						points.remove(0);
						
					}
//					else if (findAB.compareTo(b) == 0)
//					{
//						System.out.printf("");
//					}
					else if (minuend.compareTo(subtrahend) > 0)
					{
						// go left(remove right (last element))
						points.remove(points.size() - 1);
					}
//				} 

			// add nodes to fractional Tree and reset nodes of the current level
			fractionalTree.addAll(nodesCurrentLvl);
			nodesCurrentLvl.clear();

		}
		
//		for (myPoint element : fractionalTree)
//		{
//			System.out.print(element + " ");
//		}
			
	}
	
	private static myPoint myPoint(int i, int j)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
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
//			findFractionAB(new BigInteger("25"), new BigInteger("9"));
			System.out.print(findFractionAB(M,N));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	}

}
