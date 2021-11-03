package factorization-tree;

public class FT {

	// entry point.
	
	public static void main(String[] args) {
		boolean useThreshold = true;
		boolean cutBounds = true;
		long num = 17;

		NumberFactored n = new NumberFactored(108, useThreshold);
		// NumberFactored n = new NumberFactored(3*7*11, false);
		// NumberFactored n1 = new NumberFactored(num, useThreshold);
		// NumberFactored n = new NumberFactored(n1);

		System.out.println(n.getGeneralStateAsStringBuilder(cutBounds));
		System.out.println(n.getTreeAsStringBuilder());

		// printDebugInfo();
	}

	// utility method for debugging.
	
	public static void printDebugInfo() {
		NumberFactored n;
		StringBuilder sb = new StringBuilder();

		for (long i = 1; i <= 15; i++) {
			sb.append(i);
			sb.append(":\n");
			n = new NumberFactored(i, true);
			sb.append(n.getGeneralStateAsStringBuilder(true));
			sb.append(i);
			sb.append(":\n");
			sb.append(n.getGeneralStateAsStringBuilder(false));

			sb.append(i);
			sb.append(":\n");
			n = new NumberFactored(i, false);
			sb.append(n.getGeneralStateAsStringBuilder(true));
			sb.append(i);
			sb.append(":\n");
			sb.append(n.getGeneralStateAsStringBuilder(false));
		}
		
		System.out.println(sb.toString());
	}
}