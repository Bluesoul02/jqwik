package net.jqwik.engine.facades;

import java.util.*;
import java.util.function.*;

import net.jqwik.api.*;
import net.jqwik.api.facades.*;
import net.jqwik.engine.execution.reporting.*;

public class TestingSupportFacadeImpl extends TestingSupportFacade {

	@Override
	public  <T> Shrinkable<T> generateUntil(RandomGenerator<T> generator, Random random, Function<T, Boolean> condition) {
		long maxTries = 1000;
		return generator
					   .stream(random)
					   .limit(maxTries)
					   .filter(shrinkable -> condition.apply(shrinkable.value()))
					   .findFirst()
					   .orElseThrow(() -> new JqwikException("Failed to generate value that fits condition after " + maxTries + " tries."));
	}

	@Override
	public String singleLineReport(Object any) {
		ValueReport report = ValueReport.of(any);
		return report.singleLineReport();
	}

	@Override
	public List<String> multiLineReport(Object any) {
		ValueReport report = ValueReport.of(any);
		SimpleLineReporter lineReporter = new SimpleLineReporter();
		report.report(lineReporter, 0, "");
		return lineReporter.lines;
	}

	private static class SimpleLineReporter implements LineReporter {

		final List<String> lines = new ArrayList<>();

		@Override
		public void addLine(int indentLevel, String line) {
			String indentation = LineReporter.multiply(' ', indentLevel * 2);
			this.lines.add(indentation + line.trim());
		}
	}

}
