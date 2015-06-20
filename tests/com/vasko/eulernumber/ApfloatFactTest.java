package com.vasko.eulernumber;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApfloatFactTest {

	@Test
	public void test() {
		ApfloatFactorialCalc calc = new ApfloatFactorialCalc(10000);
		try {
		System.out.println(calc.getFact(9));
		} catch (Throwable t) {
			System.out.println(t);
		}
	}

}
