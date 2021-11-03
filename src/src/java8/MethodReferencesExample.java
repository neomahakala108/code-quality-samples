package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MethodReferencesExample {
	public static void main(String[] args) {
		// data initialization.
		List<String> strs1 = Arrays.asList("C", "a", "A", "b");
		List<String> strs2 = Arrays.asList("C", "d", "A", "b");
		List<String> strs3 = Arrays.asList("C", "c1", "A", "b");
		List<String> strs4 = Arrays.asList("C", "c2", "A", "b");
		
		System.out.println("... before sorting:");
		System.out.println(" strs1: "+strs1);
		System.out.println(" strs2: "+strs2);
		System.out.println(" strs3: "+strs3);
		System.out.println(" strs4: "+strs4);

		// a pre-8 verbose notation.
		Collections.sort(strs1, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});

		// a java 8 terse lambda notation.
		Collections.sort(strs2, (String s1, String s2) -> s1.compareToIgnoreCase(s2) );
		
		// a java 8 terse method reference notation #1.
		Collections.sort(strs3, String::compareToIgnoreCase);
		
		// a java 8 terse method reference notation #2.
		Comparator<String> c = String::compareToIgnoreCase;
		Collections.sort(strs4, c);
		
		// ... printing results.
		System.out.println("... after sorting.");
		System.out.println(" strs1: "+strs1);
		System.out.println(" strs2: "+strs2);
		System.out.println(" strs3: "+strs3);
		System.out.println(" strs4: "+strs4);
	}
}