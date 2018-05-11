package algoritms;

public class Number {
	private long numerator;
	private long denominator;
	
	public Number(long numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}
	
	public Number(long numerator, long denominator) {
		long gcd = gcd(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}
	
	public static Number sum(Number a, Number b) {
		Number num = new Number(a.numerator + b.numerator, lcm(a.denominator, b.denominator));
		num.normalize();
		return num;
	}
	
	public static Number subtract(Number a, Number b) {
		Number num = new Number(a.numerator - b.numerator, lcm(a.denominator, b.denominator));
		num.normalize();
		return num;
	}
	
	public static Number divide(Number a, Number b) {
		Number num = new Number(a.numerator * b.denominator, a.denominator * b.numerator);
		num.normalize();
		return num;
	}
	
	public static Number multiply(Number a, Number b) {
		Number num = new Number(a.numerator * b.numerator, a.denominator * b.denominator);
		num.normalize();
		return num;
	}
	
	private void normalize() {
		long gcd = gcd(this.numerator, this.denominator);
		this.numerator /= gcd;
		this.denominator /= gcd;
	}
	
	public static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}
	
	public static long gcd(long a, long b) {
		long c;
		while (b != 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
	
	@Override
	public String toString() {
		return this.numerator + " / " + this.denominator;
	}
}
