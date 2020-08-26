import java.awt.Point;
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
		// store the value we are looking for N / B
		BigDecimal findAB = new BigDecimal(M).divide(new BigDecimal(N), RoundingMode.HALF_UP);
		ArrayList<BigDecimal> fractionalTree = new ArrayList<BigDecimal>();
		
		// hold the points to create the fractionalTree
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(0, 1));
		points.add(new Point(1, 0));
		
		
		
		// head of tree
		fractionalTree.add(new BigDecimal(1 / 1));
		
		
		
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
