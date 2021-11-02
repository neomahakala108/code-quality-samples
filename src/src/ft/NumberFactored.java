package ft;

import java.util.LinkedList;
import java.util.List;

/**
 * Transforms a number into a 'factorization tree'. with a 'prime number check'
 * functionality, with a 'divisors list cloned report' functionality: with or
 * without 'cutting bounds'.
 *
 * @author Andrzej Wysocki, email: 'neomahakala108@gmail.com', DeviantART:
 *         'neo-mahakala-108'.
 **/
public class NumberFactored {

// state.

	private final long val; // java has no 'unsigned long' data type.
	private boolean useThreshold;
	private final List<NumberFactored> divisors = new LinkedList<NumberFactored>();

// constructor methods.

	public NumberFactored(final long v) {
		this(v, true);
	}

	public NumberFactored(final long v, boolean useThreshold) {
		if (v <= 0) {
			throw new IllegalArgumentException();
		}
		this.val = v;
		this.useThreshold = useThreshold;
		this.evaluateAndSetDivisors();
	}

	public NumberFactored(NumberFactored in) {
		this(in.val, in.useThreshold);
	}

// static methods, for processing 'external state', without affecting 'internal state'.

	public static int getDivisorsAmount(List<NumberFactored> divisors) {
		return divisors.size();
	}

	public static StringBuilder getDivisorsAsStringBuilder(List<NumberFactored> divisors) {
		StringBuilder result = new StringBuilder();
		int nda = divisors.size();
		result.append("[");
		for (int i = 0; i < nda; i++) {
			result.append(" " + divisors.get(i).getVal());
			if (i < nda - 1) {
				result.append(",");
			}
		}
		result.append(" ];\n");
		return result;
	}

// methods.

	private void evaluateAndSetDivisors() {
		if (val <= 0) {
			return;
		}
		divisors.add(this);
		long limit = useThreshold ? (long) Math.floor(Math.sqrt(val)) : val;
		for (long i = 2; i <= limit; i++) {
			if ((val % i) == 0) {
				final NumberFactored divisor = new NumberFactored(val / i);
				divisors.add(divisor);
			}
		}
	}

	private StringBuilder getIndentedInputNumber(long inputNumber, int halfNumberOfSpaces) {
		StringBuilder result = new StringBuilder();
		for (long i = 0; i < halfNumberOfSpaces; i++) {
			result.append("  ");
		}
		result.append(inputNumber);
		return result;
	}

	private StringBuilder getIndentedStringBuilder(int i) {
		StringBuilder result = new StringBuilder();
		for (final NumberFactored divisor : divisors) {
			if (divisor == this) {
				continue;
			}
			result.append(getIndentedInputNumber(val, i));
			result.append('/');
			result.append(divisor.val);
			result.append('=');
			result.append(val / divisor.val);
			result.append(";\n");
			result.append(divisor.getIndentedStringBuilder(i + 1));
		}
		return result;
	}

// accessor methods: there can be 'getter accessor methods', 'setter accessor methods'.

	public int getDivisorsAmount() {
		return divisors.size();
	}

	public boolean isPrimeNumber() {
		if (divisors.size() < 1) { // can be written in a simpler form, without { },
									// but i think it's professional, clean code to not showoff
									// too much, to make it easier for beginners to understand.
			return true;
		} else if (divisors.size() == 1) {
			return (divisors.get(0).val == val) || (divisors.get(0).val == 1);
		} else if (divisors.size() == 2) {
			return ((divisors.get(0).val == val) || (divisors.get(0).val == 1))
					&& ((divisors.get(getDivisorsAmount() - 1).val == val)
							|| (divisors.get(getDivisorsAmount() - 1).val == 1));
		}
		return false;
	}

	public long getVal() {
		return val;
	}

	public List<NumberFactored> getDivisorsClone(boolean cutBounds) {
		final int amount = this.getDivisorsAmount();
		final List<NumberFactored> result = new LinkedList<NumberFactored>();
		for (int i = 0; i < amount; i++) {
			if (((divisors.get(i).val == val) || (divisors.get(i).val == 1)) && cutBounds) {
				continue;
			}
			NumberFactored num = new NumberFactored(divisors.get(i)); // 'deep copy'.
			result.add(num);
		}

		return result;
	}

	public StringBuilder getGeneralStateAsStringBuilder(boolean cutBounds) {
		StringBuilder result = new StringBuilder();
		result.append("General state:\n");
		result.append("  Use Threshold: " + useThreshold + ";\n");
		result.append("  Cut Bounds: " + cutBounds + ";\n");
		result.append(getDivisorsStateAsStringBuilder(cutBounds));
		return result;
	}

	public StringBuilder getDivisorsStateAsStringBuilder(boolean cutBounds) {
		StringBuilder result = new StringBuilder();
		List<NumberFactored> localDivisors = getDivisorsClone(cutBounds);
		result.append("  Divisors amount: " + getDivisorsAmount(localDivisors) + ";\n");
		result.append("  Divisors List: " + getDivisorsAsStringBuilder(localDivisors));
		return result;
	}

	public StringBuilder getTreeAsStringBuilder() {
		StringBuilder result = new StringBuilder();
		result.append("  " + getVal() + " number factored:\n");
		if (isPrimeNumber()) {
			result.append("    " + val + " is a Prime Number.\n");
		} else {
			result.append(getIndentedStringBuilder(2));
		}
		return result;
	}
}
