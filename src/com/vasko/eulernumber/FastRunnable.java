package com.vasko.eulernumber;

import org.apfloat.Apfloat;

public class FastRunnable implements Runnable {
	private int from;
	private int to;
	private int step;
	private Apfloat[] results;
	private int resultsNumber;
	private ApfloatFactorialCalc calc;
	private int floatPrecision;
	private Boolean isQuiet;
	private Apfloat currFactorial;
	
	public FastRunnable(int from, int to, Apfloat currFactorial, Apfloat[] results, int resultsNumber, int floatPrecision, Boolean isQuiet) {
		//System.out.println(String.format("Thread #%s from %s to %s step %s", resultsNumber, from, to, step));
		this.from = from;
		this.to = to;
		this.results = results;
		this.resultsNumber = resultsNumber;
		this.floatPrecision = floatPrecision;
		this.isQuiet = isQuiet;
		this.currFactorial = currFactorial;
	}
	
	@Override
	public void run() {
		results[resultsNumber] = new Apfloat(0, floatPrecision);
		
		for(int i = from; i < to; i++) {
			results[resultsNumber] = results[resultsNumber].add(new Apfloat(2 * i + 1, floatPrecision).divide(currFactorial));
			currFactorial = currFactorial.multiply(new Apfloat(from + i * 2 + 1)).multiply(new Apfloat(from + i * 2 + 2));
		}
	}

}
