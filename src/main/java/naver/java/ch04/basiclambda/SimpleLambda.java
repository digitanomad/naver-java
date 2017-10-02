package naver.java.ch04.basiclambda;

public class SimpleLambda {

	public static void main(String[] args) {
		Runnable lambda = () -> System.out.println(1);
		lambda.run();
	}

}
