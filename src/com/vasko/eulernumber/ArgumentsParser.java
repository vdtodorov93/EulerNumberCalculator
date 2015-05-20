package com.vasko.eulernumber;

public class ArgumentsParser {
	private int sequenceSize;
	private int threadsCount;
	private String outputFile;
	private Boolean isQuiet;
	private final Integer floatPrecision = 10;
	
	public ArgumentsParser(String[] args) {
		parseArguments(args);
	}
	
	public int getSequenceSize() {
		return sequenceSize;
	}

	public int getThreadsCount() {
		return threadsCount;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public Boolean getIsQuiet() {
		return isQuiet;
	}

	public Integer getFloatPrecision() {
		return floatPrecision;
	}

	private void parseArguments(String[] args) {
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-q")) {
				isQuiet = true;
			}
			else if(args[i].equals("-p")) {
				sequenceSize = Integer.parseInt(args[i + 1]);
				i++;
			}
			else if(args[i].equals("-t")) {
				threadsCount = Integer.parseInt(args[i + 1]);
				i++;
			}
			else if(args[i].equals("-o")) {
				outputFile = args[i + 1];
				i++;
			} else {
				System.out.println("Illegal argument !!!");
			}
		}
		
		if(sequenceSize == 0) {
			sequenceSize = 10;
		}

		if(threadsCount == 0) {
			threadsCount = 1;
		}
		
		if(isQuiet == null) {
			isQuiet = false;
		}
		
		if(outputFile == null) {
			outputFile = "result.txt";
		}
	}
	
	public void print() {
		System.out.println(String.format("Sequence size: %s Threads: %s Output file: %s Quiet mode: %s", sequenceSize, threadsCount, outputFile, isQuiet));
	}
}
