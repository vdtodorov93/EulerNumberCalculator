package com.vasko.eulernumber;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apfloat.Apfloat;

public class TaskRunner {
	//private static FactorialCalculator calc;

	public static void main(String[] args) {
		ArgumentsParser parser = new ArgumentsParser(args);		
		if (!parser.getIsQuiet()) {
			parser.printInfo();
		}		
		long startTime = System.currentTimeMillis();
		int threadCount = parser.getThreadsCount();
		int precision = parser.getSequenceSize();
		
		Apfloat sum = new Apfloat(0, precision);
		Apfloat[] results = new Apfloat[threadCount];
		Thread threads[] = new Thread[threadCount];
		int chunkSize = precision / threadCount;
		Apfloat fact = new Apfloat(1);
		int currentFactCalculated = 1;
		
		for (int i = 0; i < threadCount; i++) {
			int from = i * chunkSize;
			int to = (i + 1) * chunkSize;
			while(currentFactCalculated < from * 2) {
				fact = fact.multiply(new Apfloat(currentFactCalculated));
				currentFactCalculated++;
			}
			FastRunnable r = new FastRunnable(from, to, fact, results, i, parser.getFloatPrecision(), parser.getIsQuiet());
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
			PrintWriter writer = new PrintWriter(parser.getOutputFile(),
					"UTF-8");
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
