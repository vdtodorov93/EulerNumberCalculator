package com.vasko.eulernumber;

import org.apfloat.Apfloat;

public class TaskRunner {
	private static FactorialCalculator calc;
	
	public static void main(String[] args) {
		ArgumentsParser parser = new ArgumentsParser(args);
		parser.print();
		long startTime = System.currentTimeMillis();
		
		int maxK = parser.getSequenceSize();
		int threadCount = parser.getThreadsCount();
		int precision = parser.getFloatPrecision();
		Apfloat sum = new Apfloat(0, precision);
		calc = new FactorialCalculator();
		Apfloat[] results = new Apfloat[threadCount];
		Thread threads[] = new Thread[threadCount];
	
		for(int i = 0; i < threadCount; i++) {
			//EulerSumRunnable r = new EulerSumRunnable(i, maxK, threadCount, results, i, new FactorialCalculator(), precision);
			EulerSumRunnable r = new EulerSumRunnable(i, maxK, threadCount, results, i, calc, precision);
			Thread t = new Thread(r);
			threads[i] = t;
			t.start();
		}
		
		for(int i = 0; i < threadCount; i++) {
			try {
				threads[i].join();
				sum = sum.add(results[i]);
				//System.out.println(results[i]);
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		System.out.println(sum);
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("Running time with %s threads: %s", threadCount, (endTime - startTime)/1000.0));
	}
	
}
