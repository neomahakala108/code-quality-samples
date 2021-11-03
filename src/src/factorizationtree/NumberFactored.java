package factorizationtree;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Transforms a number into a Factorization Tree, with divisors list.
 *
 * @author Andrzej Wysocki (neomahakala108@gmail.com)
 * 
 **/
public class NumberFactored {

	// state.

	private final long val;
	private final boolean useThreshold;
	private final List<NumberFactored> divisors = new LinkedList<NumberFactored>();

	// constructor methods.

	public NumberFactored(final long v) {
		this(v, true);
	}

	public NumberFactored(final long v, final boolean useThreshold) {
		if (v <= 0) {
			throw new IllegalArgumentException();
		}
		this.val = v;
		this.useThreshold = useThreshold;
		this.initiateThresholdAndDivisors();
	}

	public NumberFactored(final NumberFactored in) {
		this(in.val, in.useThreshold);
	}

	// methods.

	public void initiateThresholdAndDivisors() {
		if (val <= 0) {
			throw new IllegalArgumentException();
		}

		divisors.add(this);

		final long limit = useThreshold ? (long) Math.floor(Math.sqrt(val)) : val;

		for (long i = 2; i <= limit; i++) {
			if ((val % i) == 0) {
				final NumberFactored divisor = new NumberFactored(val / i);
				divisors.add(divisor);
			}
		}
	}

	public long getVal() {
		return val;
	}


	public boolean isPrimeNumber() {
		if (divisors.size() < 1) {
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
	
	public int getDivisorsAmount() {
		return divisors.size();
	}
	
	public StringBuilder getTreeAsStringBuilder() {
		final StringBuilder result = new StringBuilder();

		result.append(getVal());
		result.append(" number factored:\n");

		if (isPrimeNumber()) {
			result.append("  ");
			result.append(val);
			result.append(" is a Prime Number.\n");
		} else {
			result.append(getTreeAsStringBuilder(1));
		}

		return result;
	}

	public StringBuilder getGeneralStateAsStringBuilder(boolean cutBounds) {
		final StringBuilder result = new StringBuilder();
		result.append("General state:\n");
		result.append("  Use Threshold: ");
		result.append(useThreshold);
		result.append(";\n");
		result.append("  Cut Bounds: ");
		result.append(cutBounds);
		result.append(";\n");
		result.append(getDivisorsStateAsStringBuilder(cutBounds));
		return result;
	}

	public List<NumberFactored> getClonedLocalDivisorsAsList(final boolean cutBounds) {
		final int amount = this.getDivisorsAmount();
		final List<NumberFactored> result = new LinkedList<NumberFactored>();
		for (int i = 0; i < amount; i++) {
			if (((divisors.get(i).val == val) || (divisors.get(i).val == 1)) && cutBounds) {
				continue;
			}
			final NumberFactored num = new NumberFactored(divisors.get(i)); // 'deep copy'.
			result.add(num);
		}

		return result;
	}

	public StringBuilder getClonedLocalDivisorsAsStringBuilder(final boolean cutBounds) {
		final List<NumberFactored> localClonedDivisorsList =
				getClonedLocalDivisorsAsList(cutBounds);
	
		final int amountOfDivisors = localClonedDivisorsList.size();
		
		final StringBuilder result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < amountOfDivisors; i++) {
			result.append(" ");
			result.append(localClonedDivisorsList.get(i).getVal());
			if (i < amountOfDivisors - 1) {
				result.append(",");
			}
		}
		result.append(" ];\n");

		return result;
	}

	public StringBuilder getDivisorsStateAsStringBuilder(final boolean cutBounds) {
		final StringBuilder result = new StringBuilder();

		result.append("  Divisors amount: ");
		result.append(getClonedLocalDivisorsAsList(cutBounds).size());
		result.append(";\n");

		result.append("  Divisors List: ");
		result.append(getClonedLocalDivisorsAsStringBuilder(cutBounds));

		return result;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getVal());
		return result.toString();
	}

	private StringBuilder getIndentedNumberToFactorAsStringBuilder(long inputNumber, int halfNumberOfSpaces) {
		final StringBuilder result = new StringBuilder();

		for (long i = 0; i < halfNumberOfSpaces; i++) {
			result.append("  ");
		}
		result.append(inputNumber);

		return result;
	}

	private StringBuilder getTreeAsStringBuilder(final int indent) {
		final StringBuilder result = new StringBuilder();
		for (final NumberFactored divisor : this.divisors) {
			if (divisor == this) {
				continue;
			}
			result.append(getIndentedNumberToFactorAsStringBuilder(val, indent));
			result.append('/');
			result.append(divisor.val);
			result.append('=');
			result.append(val / divisor.val);
			result.append(";\n");
			result.append(divisor.getTreeAsStringBuilder(indent + 1));
		}
		return result;
	}
}
