package algoritms;

public class Number implements Cloneable {
	private long numerator;
	private long denominator;
	
	public Number(long number) {
		this.numerator = number;
		this.denominator = 1;
	}
	
	public Number(String number) {
		Number temp = stringToFraction(number).normalize();
		this.numerator = temp.numerator;
		this.denominator = temp.denominator;
	}
	
	public Number(Number number) {
		Number temp = number.normalize();
		this.numerator = temp.numerator;
		this.denominator = temp.denominator;
	}
	
	public Number(long numerator, long denominator) {
		long gcd = gcd(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}
	
	public Number add(Number num) {
		Long lcm = lcm(this.denominator, num.denominator);
		Number result = new Number(
			this.numerator * lcm / this.denominator + num.numerator * lcm / num.denominator,
			lcm(this.denominator, num.denominator)
		);
		return result.normalize();
	}
	
	public Number subtract(Number num) {
		Long lcm = lcm(this.denominator, num.denominator);
		Number result = new Number(
			this.numerator * lcm / this.denominator - num.numerator * lcm / num.denominator,
			lcm(this.denominator, num.denominator)
		);
		return result.normalize();
	}
	
	public Number divide(Number num) {
		Number result = new Number(this.numerator * num.denominator, num.denominator * this.numerator);
		return result.normalize();
	}
	
	public Number multiply(Number num) {
		Number result = new Number(this.numerator * num.numerator, this.denominator * num.denominator);
		return result.normalize();
	}
	
	public Number multiply(long num) {
		Number result = new Number(this.numerator * num, this.denominator);
		return result.normalize();
	}
	
	private Number normalize() {
		long gcd = gcd(this.numerator, this.denominator);
		return new Number(this.numerator /= gcd, this.denominator /= gcd);
	}
	
	public Number inverse() {
		return new Number(this.denominator, this.numerator);
	}
	
	public static Number stringToFraction(String number) {
		String[] parts = number.split("/");
		return new Number(
				Long.parseLong(parts[0]),
				parts.length == 2 ? Long.parseLong(parts[1]) : 1
			);
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
	
	public long getNumerator() {
		return this.numerator;
	}
	
	public long getDenominator() {
		return this.denominator;
	}

	public Double toFloat() {
		return (double) (this.numerator / this.denominator);
	}
	
	public Double toDouble() {
		return (double) (this.numerator / this.denominator);
	}
	
	public boolean equals(long number) {
		return this.numerator == number && this.denominator == 1;
	}
	
	@Override
	public boolean equals(Object number) {
		return this.numerator == ((Number)number).numerator && this.denominator == ((Number)number).denominator;
	}
	
	@Override
	public Number clone() {
		return new Number(this.numerator, this.denominator);
	}
	
	@Override
	public String toString() {
		if (this.denominator == 1) {
			return Long.toString(this.numerator);
		}
		return this.numerator + " / " + this.denominator;
	}
}
