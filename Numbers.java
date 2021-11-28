/**
 * Programming Assignment 4
 *
 * @author Natalie Young
 * @since 2021-10-26
 */

import tester.*;
import java.io.File;

interface Number
{
	public int numerator();
	public int denominator();
	public Number add(Number other);
	public Number multiply(Number other);
	public String toText();
	public double toDouble();
}

class WholeNumber implements Number
{
	int n;

	WholeNumber(int n)
	{
		this.n = n;
	}

	/**
	 * Returns value of n
	 *
	 * @return n
	 */
	public int numerator()
	{
		return this.n;
	}

	/**
	 * Returns 1
	 */
	public int denominator()
	{
		return 1;
	}

	/**
	 * Returns new Number that represents adding this WholeNumber
	 * to the one provided as an argument (could be either a
	 * Fraction or WholeNumber).
	 *
	 * @return sum
	 */
	public Number add(Number other)
	{
		int sumNum = this.numerator();
		int sumDenom = 1;
		Number sum;

		if (other.denominator() == 1)
		{
			sumNum += other.numerator();

			sum = new WholeNumber(sumNum);
		}
		else
		{
			sumNum = (this.numerator() * other.denominator())
				+ (this.denominator() * other.numerator());
			sumDenom = this.denominator() * other.denominator();

			sum = new Fraction(sumNum,sumDenom);
		}

		return sum;
	}

	/**
	 * Returns new Number that represents multiplying this
	 * WholeNumber to the one provided as argument (could be either
	 * Fraction or WholeNumber)
	 *
	 * @return newNumber
	 */
	public Number multiply(Number other)
	{
		int prodNum = this.numerator() * other.numerator();
		int prodDenom = this.denominator() * other.denominator();
		Number product;

		if (prodDenom == 1)
		{
			product = new WholeNumber(prodNum);
		}
		else
		{
			product = new Fraction(prodNum,prodDenom);
		}

		return product;
	}

	/**
	 * Returns value of n as String
	 */
	public String toText()
	{
		return String.valueOf(n);
	}

	/**
	 * Casts int n to double
	 */
	public double toDouble()
	{
	//	double n = this.n;
		return (double)n;
	}
}

class Fraction implements Number
{
	int n;	// numerator
	int d;	// denominator

	Fraction(int n, int d)
	{
		this.n = n;
		this.d = d;
	}

	/**
	 * Returns numerator
	 */
	public int numerator()
	{
		return this.n;
	}

	/**
	 * Returns denominator
	 */
	public int denominator()
	{
		return this.d;
	}

	/**
	 * Returns new number that represents adding this Fraction to
	 * Number provided as argument.
	 *
	 * @param other
	 * @return sum
	 */
	public Number add(Number other)
	{
		int sumNum = 0;
		int sumDenom = 1;

		if (this.denominator() == other.denominator())
		{
			sumNum = this.numerator() + other.numerator();
			sumDenom = this.denominator();
		}
		else
		{
			sumNum = (this.numerator() * other.denominator())
				+ (this.denominator() * other.numerator());
			sumDenom = this.denominator() * other.denominator();
		}

		Number sum = new Fraction(sumNum,sumDenom);

		return sum;
	}

	/**
	 * Returns new Number that represents adding this Fraction to
	 * Number provided as argument.
	 *
	 * @param other
	 * @return product
	 */
	public Number multiply(Number other)
	{
		int prodNum = this.numerator() * other.numerator();
		int prodDenom = this.denominator() * other.denominator();

		Number product = new Fraction(prodNum,prodDenom);

		return product;
	}

	/**
	 * Returns String in format "n/d" where n and d are corresponding
	 * fields
	 */
	public String toText()
	{
		return String.valueOf(this.n) + "/" + String.valueOf(this.d);
	}

	/**
	 * Returns value of n/d as double
	 */
	public double toDouble()
	{
		double n = this.n;
		double d = this.d;

		return n/d;
	}
}

class ExamplesNumbers
{
	Number n1 = new WholeNumber(5);
	Number n2 = new WholeNumber(7);
	Number n3 = new Fraction(7, 2);
	Number n4 = new Fraction(1, 2);
	
	void testAdd(Tester t)
	{
		t.checkExpect(this.n1.add(this.n2).toDouble(), 12.0);
		t.checkExpect(this.n1.add(this.n3).toDouble(), 5 + 7.0/2.0);
		t.checkExpect(this.n3.add(this.n3).toDouble(), 7.0);
	}
	
	void testMultiply(Tester t)
	{
		t.checkExpect(this.n1.multiply(this.n4).toDouble(), 2.5);
		t.checkExpect(this.n3.multiply(this.n4).toDouble(), 7.0/4.0);
	}
	
	void testNumDem(Tester t)
	{
		t.checkExpect(this.n3.numerator(), 7);
		t.checkExpect(this.n1.numerator(), 5);
		t.checkExpect(this.n4.denominator(), 2);
		t.checkExpect(this.n2.denominator(), 1);
	}
	
	void testToString(Tester t)
	{
		t.checkExpect(this.n4.toText(), "1/2");
		t.checkExpect(this.n3.toText(), "7/2");
		t.checkExpect(this.n2.toText(), "7");
	}


	// EXPLORATION

	Number oneTenth = new Fraction(1,10);
	Number twoTenths = new Fraction(2,10);
	Number threeTenths = new Fraction(3,10);

/*	void testToDouble(Tester t)
	{
		t.checkExpect(this.oneTenth.add(twoTenths).add(threeTenths).toDouble(), 0.6);
	}
*/
	double explor1 = oneTenth.add(twoTenths).add(threeTenths).toDouble();
	double explor2 = oneTenth.add(twoTenths.add(threeTenths)).toDouble();
	String explor3 = oneTenth.add(twoTenths).add(threeTenths).toText();
	String explor4 = oneTenth.add(twoTenths.add(threeTenths)).toText();

}
