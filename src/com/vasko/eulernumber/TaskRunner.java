package com.vasko.eulernumber;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apfloat.Apfloat;

public class TaskRunner {
	private static FactorialCalculator calc;

	public static void main(String[] args) {
		ArgumentsParser parser = new ArgumentsParser(args);
		parser.print();
		long startTime = System.currentTimeMillis();
		int threadCount = parser.getThreadsCount();
		int precision = parser.getFloatPrecision();
		Apfloat sum = new Apfloat(0, precision);
		calc = new FactorialCalculator();
		Apfloat[] results = new Apfloat[threadCount];
		Thread threads[] = new Thread[threadCount];

		for (int i = 0; i < threadCount; i++) {
			EulerSumRunnable r = new EulerSumRunnable(i, parser.getSequenceSize(),
					threadCount, results, i, calc, precision, parser.getIsQuiet());
			Thread t = new Thread(r);
			threads[i] = t;
			t.start();
		}

		for (int i = 0; i < threadCount; i++) {
			try {
				threads[i].join();
				sum = sum.add(results[i]);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		if (!parser.getIsQuiet()) {
			System.out.println(sum);
		}
		try {
			PrintWriter writer = new PrintWriter(parser.getOutputFile(), "UTF-8");
			writer.println(sum);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("Running time with %s threads: %s",
				threadCount, (endTime - startTime) / 1000.0));
	}

}
