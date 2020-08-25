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
	 * @param N = Numerator
	 * @param D = Denominator
	 */
	public static void findFractionAB(BigInteger N, BigInteger D)
	{
		// store the value we are looking for N / B
		BigDecimal findAB = new BigDecimal(N).divide(new BigDecimal(D), RoundingMode.);
		ArrayList<BigDecimal> fractionalTree = new ArrayList<BigDecimal>();
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(0, 1));
		points.add(new Point(1,0));
		
		
		
//		for (int i = 0; i < 7; i++)
			
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scan = null;
		BigInteger N = null;
		BigInteger D = null;
		
		try
		{
			File inFile = new File("input_1.txt");
			scan = new Scanner(inFile);
			
			// get the numerator (N) and denominator (D)
			N = scan.nextBigInteger();
			D = scan.nextBigInteger();
			
			// where the magic happens
			findFractionAB(N, D);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	}

}
