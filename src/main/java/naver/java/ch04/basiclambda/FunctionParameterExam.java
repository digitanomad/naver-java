package naver.java.ch04.basiclambda;

import java.util.function.IntUnaryOperator;

public class FunctionParameterExam {
	public static void main(String[] args) {
		FunctionParameterExam printer = new FunctionParameterExam();
		int base = 7;
		printer.printWeighted(weight -> base * weight, 10);
	}
	
	public void printWeighted(IntUnaryOperator calc, int weight) {
		System.out.println(calc.applyAsInt(weight));
	}
}
