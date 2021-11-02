package ft;

public class FT {
// --
// there can be also: 'empty methods', 'event(s)-related methods', perhaps more.
// --

// entry point.

// commented lines with NumberFactored object construction are only examples,
// 'constructor call' can be edited, etc.

	public static void main(String[] args) {
		boolean useThreshold = false;
		boolean cutBounds = false;
		long num = 17;

		NumberFactored n = new NumberFactored(108);
		// NumberFactored n = new NumberFactored(3*7*11, false);
		// NumberFactored n1 = new NumberFactored(num, useThreshold);
		// NumberFactored n = new NumberFactored(n1);

		System.out.println(n.getGeneralStateAsStringBuilder(cutBounds));
		System.out.println(n.getTreeAsStringBuilder());

		// printDebugInfo();
	}

// utility method for entry point.

	public static void printDebugInfo() {
		NumberFactored n;

		for (long i = 1; i <= 15; i++) {
			System.out.println(i + ":\n");
			n = new NumberFactored(i, true);
			System.out.println(n.getGeneralStateAsStringBuilder(true));
			System.out.println(i + ":\n");
			System.out.println(n.getGeneralStateAsStringBuilder(false));

			n = new NumberFactored(i, false);
			System.out.println(i + ":\n");
			System.out.println(n.getGeneralStateAsStringBuilder(true));
			System.out.println(i + ":\n");
			System.out.println(n.getGeneralStateAsStringBuilder(false));

		}
	}
}