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
	private Boolean isQuiet;
	
	public EulerSumRunnable(int from, int to, int step, Apfloat[] results, int resultsNumber, FactorialCalculator calc, int floatPrecision, Boolean isQuiet) {
		//System.out.println(String.format("Thread #%s from %s to %s step %s", resultsNumber, from, to, step));
		this.from = from;
		this.to = to;
		this.step = step;
		this.results = results;
		this.resultsNumber = resultsNumber;
		this.calc = calc;
		this.floatPrecision = floatPrecision;
		this.isQuiet = isQuiet;
	}
	
	@Override
	public void run() {
		results[resultsNumber] = new Apfloat(0, floatPrecision);
		int chunk = to / 10;
		for(int i = from; i < to; i += step) {
			results[resultsNumber] = results[resultsNumber].add(new Apfloat(2 * i + 1, floatPrecision).divide(new Apfloat(calc.factorial(2 * i))));
			if(i % chunk == 0 && i != 0 && !isQuiet) {
				System.out.println(i / chunk + "0% complete");
			}
		}
	}

}
