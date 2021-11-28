/**
 * Programming Assignment 4
 *
 * @author Natalie Young
 * @since 2021-10-26
 */

import tester.*;

interface Number
{
	int numerator();
	int denominator();
	Number add(Number other);
	Number multiply(Number other);
	String toText();
	double toDouble();
}

class WholeNumber
{}

class Fraction
{}

class ExamplesNumbers
{}
