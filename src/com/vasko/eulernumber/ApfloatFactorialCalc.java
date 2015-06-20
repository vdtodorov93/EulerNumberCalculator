package com.vasko.eulernumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apfloat.Apfloat;

public class ApfloatFactorialCalc {
	private final int STEP = 10;
	private final Apfloat APFLOAT_ZERO = new Apfloat(0);
	private List<Apfloat> values;
	
	public ApfloatFactorialCalc(int maxSize) {
		this.values = Collections.synchronizedList(new ArrayList<Apfloat>(maxSize));
		
		// adding 0!
		this.values.add(new Apfloat(1));
		
		// adding 1!
		this.values.add(new Apfloat(1));
	}
	
	public Apfloat getFact(int n) {
		if(n == 0 || n == 1) {
			return new Apfloat(1L);
		}
		resize(n);
		if(n % STEP == 0) {
			if(values.get(n).equals(APFLOAT_ZERO)) {
				values.set(n, new Apfloat(n).multiply(getFact(n-1)));
			}
			return values.get(n);
		} else {
			int iters = n % STEP;
			Apfloat result = getFact(n - iters);
			for(int i = iters; i > 0; i--) {
				result = result.multiply(new Apfloat(i));
			}
			return result;
		}
	}
	
//	public Apfloat getFact(Integer el) {
//		if (el == 1 || el == 0) {
//			return new Apfloat(1D);
//		}
//		Apfloat result = new Apfloat(1D);
//		for (int i = 1; i <= el; i++) {
//			result = result.multiply(new Apfloat(i));
//		}
//		return result;
//	}
	
	private void resize(int n) {
		while(this.values.size() <= n + 1) {
			this.values.add(APFLOAT_ZERO);
		}
	}
}
