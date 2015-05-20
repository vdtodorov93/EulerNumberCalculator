package com.vasko.eulernumber;

import org.junit.Test;

public class TestFactorialCalculator {

	@Test
	public void test() {
		FactorialCalculator calc = new FactorialCalculator();
		for(int i = 0; i < 40; i++) {
			System.out.println("Factorial of " + i + " = " + calc.factorial(i));
		}
	}

}
