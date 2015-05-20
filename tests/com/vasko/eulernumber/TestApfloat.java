package com.vasko.eulernumber;

import org.apfloat.Apfloat;
import org.junit.Test;

public class TestApfloat {

	@Test
	public void test() {
		Apfloat x = new Apfloat(20.1232, 100);
		System.out.println(x);
		System.out.println(x.doubleValue());
	}

}
