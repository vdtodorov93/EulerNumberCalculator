package com.vasko.eulernumber;

import org.apfloat.Apfloat;

public class EulerSumRunnable implements Runnable {
	private int from;
	private int to;
	private int step;
	private Apfloat[] results;
	private int resultsNumber;
	private FactorialCalculator calc;
	private int floatPrecision;
	
	public EulerSumRunnable(int from, int to, int step, Apfloat[] results, int resultsNumber, FactorialCalculator calc, int floatPrecision) {
		System.out.println(String.format("Thread #%s from %s to %s step %s", resultsNumber, from, to, step));
		this.from = from;
		this.to = to;
		this.step = step;
		this.results = results;
		this.resultsNumber = resultsNumber;
		this.calc = calc;
		this.floatPrecision = floatPrecision;
	}
	
	@Override
	public void run() {
		results[resultsNumber] = new Apfloat(0, floatPrecision);
		//if(from != 0) return;
		for(int i = from; i < to; i += step) {
			System.out.print(" " + i);
			results[resultsNumber] = results[resultsNumber].add(new Apfloat(2 * i + 1, floatPrecision).divide(new Apfloat(calc.factorial(2 * i))));
			//System.out.println("k = " + i);
		}
		System.out.println();
		System.out.println(results[resultsNumber]);
	}

}
