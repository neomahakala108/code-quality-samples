package java8;

import java.util.function.Function;

public class MethodReferenceApplyValueExample {
	public static void main(String[] args) {
		Function<String, Integer> converter = Integer::parseInt;
		Integer number = converter.apply("10");
		
		System.out.println("converter's type: "+converter.getClass());
		System.out.println("result's type: "+number.getClass());
		System.out.println("result: "+number);
	}
}
