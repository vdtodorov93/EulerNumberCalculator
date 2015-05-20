package com.vasko.eulernumber;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FactorialCalculator {
	private List<BigInteger> calculatedValues;
	
	public FactorialCalculator() {
		calculatedValues = Collections.synchronizedList(new ArrayList<BigInteger>());
		
		//adding factorial of 0
		calculatedValues.add(BigInteger.valueOf(1));
		//adding factorial of 1
		calculatedValues.add(BigInteger.valueOf(1));
	}
	
	public BigInteger factorial(int n) {		
		resize(n);
		
		if(calculatedValues.get(n) == BigInteger.valueOf(0)) {
			calculatedValues.set(n, BigInteger.valueOf(n).multiply(factorial(n - 1)));
		}
		
		return calculatedValues.get(n);
	}
	
	private void resize(int n) {
		while(n >= calculatedValues.size()) {
			calculatedValues.add(BigInteger.valueOf(0));
		}
	}
}
