package java8;


public class LambdaExpressionRunnableExample {
	
	public static void main(String[] args) {
		// verbose notation used in pre-8 java versions.
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println("Hi, from a pre-8-java-notation call.");
			}
		};
		new Thread(runnable).start();
		
		// terse lambda notation.
		new Thread(() -> System.out.println("Hi, from a lambda-notation call #0.")).start();

		// terse lambda notation with a complex instruction consisting of two simpler instructions.
		new Thread(
				() -> { 
					System.out.println("Hi, from a lambda-notation call #1."); 
					System.out.println("Hi, from a lambda-notation call #2."); 
				}).start();
	}
}