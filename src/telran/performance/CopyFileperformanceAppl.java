package telran.performance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import telran.io.CopyFileStreams;

public class CopyFileperformanceAppl {
	static final String pathoToSource = "/Users/User/Videos/angular-lecture.flv";
	static final String pathoToDestination = "/Users/User/Videos/angular-lecture-copy.flv";

	public static void main(String[] args) {
		Integer[] bufferLengthValues = { 10_000, 100_000, 1_000_000, 100_000_000 };
		try {
			long size = Files.size(Path.of(pathoToSource));
			Arrays.stream(bufferLengthValues).map(bl -> getPerformanceTest(bl, size)).forEach(t -> t.run());
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}

	}

	private static CopyPerformanceTest getPerformanceTest(Integer bl, long size) {
		CopyPerformanceTest test =
				new CopyPerformanceTest(String.format("%s implementation buffer length %d; size:%d",
						"CopyFileStreams", bl, size),
						1, pathoToSource, pathoToDestination, new CopyFileStreams(bl));
		return test;
	}

}
