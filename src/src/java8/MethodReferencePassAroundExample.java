package java8;

import java.util.Comparator;
import java.util.function.BiFunction;

public class MethodReferencePassAroundExample {
	public static void main(String[] args) {
		Comparator<String> c1 = String::compareToIgnoreCase;
		System.out.println("comparator execution: "+c1.compare("10", "9"));
		
		BiFunction<String, String, Integer> f1 = String::compareToIgnoreCase;
		System.out.println("bifunction execution: "+f1.apply("10", "9"));
	}
}