/**
 * Programming Assignment 4
 *
 * @author Natalie Young
 * @since 2021-10-26
 */

import tester.*;

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
		int sumNum = 0;
		int sumDenom = 1;
		Number sum;

		if (other.denominator() == 1)
		{
			sumNum = this.numerator() + other.numerator();

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
		int prodDenom = this.numerator() * other.denominator();

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
		return (double)(n/d);
	}
}

class ExamplesNumbers
{}
