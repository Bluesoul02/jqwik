package net.jqwik.engine.properties;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import org.junit.platform.engine.reporting.*;
import org.opentest4j.*;

import net.jqwik.api.*;
import net.jqwik.engine.descriptor.*;
import net.jqwik.engine.properties.shrinking.*;
import net.jqwik.engine.support.*;

public class GenericProperty {

	private final String name;
	private final PropertyConfiguration configuration;
	private final ShrinkablesGenerator shrinkablesGenerator;
	private final CheckedFunction checkedFunction;

	public GenericProperty(
		String name,
		PropertyConfiguration configuration,
		ShrinkablesGenerator shrinkablesGenerator,
		CheckedFunction checkedFunction
	) {
		this.name = name;
		this.configuration = configuration;
		this.shrinkablesGenerator = shrinkablesGenerator;
		this.checkedFunction = checkedFunction;
	}

	public PropertyCheckResult check(Consumer<ReportEntry> reporter, Reporting[] reporting) {
		int maxTries = configuration.getTries();
		int countChecks = 0;
		int countTries = 0;
		while (countTries < maxTries) {
			if (!shrinkablesGenerator.hasNext()) {
				break;
			} else {
				countTries++;
			}
			List<Shrinkable<Object>> shrinkableParams = shrinkablesGenerator.next();
			try {
				countChecks++;
				if (!testPredicate(shrinkableParams, reporter, reporting)) {
					return shrinkAndCreateCheckResult(reporter, reporting, countChecks, countTries, shrinkableParams, null);
				}
			} catch (TestAbortedException tae) {
				countChecks--;
			} catch (AssertionError | Exception ae) {
				return shrinkAndCreateCheckResult(reporter, reporting, countChecks, countTries, shrinkableParams, ae);
			} catch (Throwable throwable) {
				JqwikExceptionSupport.rethrowIfBlacklisted(throwable);
				return PropertyCheckResult.failed(
					configuration.getStereotype(), name, countTries, countChecks, configuration.getSeed(),
					configuration.getGenerationMode(), extractParams(shrinkableParams), null, throwable
				);
			}
		}
		if (countChecks == 0 || maxDiscardRatioExceeded(countChecks, countTries, configuration.getMaxDiscardRatio())) {
			return PropertyCheckResult.exhausted(
				configuration.getStereotype(),
				name,
				maxTries,
				countChecks,
				configuration.getSeed(),
				configuration.getGenerationMode()
			);
		}
		return PropertyCheckResult.successful(
			configuration.getStereotype(),
			name,
			countTries,
			countChecks,
			configuration.getSeed(),
			configuration.getGenerationMode()
		);
	}

	private boolean testPredicate(
		List<Shrinkable<Object>> shrinkableParams,
		Consumer<ReportEntry> reporter,
		Reporting[] reporting
	) {
		List<Object> plainParams = extractParams(shrinkableParams);
		if (Reporting.GENERATED.containedIn(reporting)) {
			reporter.accept(ReportEntry.from("generated", JqwikStringSupport.displayString(plainParams)));
		}
		return checkedFunction.test(plainParams);
	}

	private boolean maxDiscardRatioExceeded(int countChecks, int countTries, int maxDiscardRatio) {
		int actualDiscardRatio = (countTries - countChecks) / countChecks;
		return actualDiscardRatio > maxDiscardRatio;
	}

	private List<Object> extractParams(List<Shrinkable<Object>> shrinkableParams) {
		return shrinkableParams.stream().map(Shrinkable::value).collect(Collectors.toList());
	}

	private PropertyCheckResult shrinkAndCreateCheckResult(
		Consumer<ReportEntry> reporter, Reporting[] reporting, int countChecks,
		int countTries, List<Shrinkable<Object>> shrinkables, Throwable exceptionOrAssertionError
	) {
		List<Object> originalParams = extractParams(shrinkables);
		PropertyShrinkingResult shrinkingResult = shrink(reporter, reporting, shrinkables, exceptionOrAssertionError);
		List<Object> shrunkParams = shrinkingResult.values();
		Throwable throwable = shrinkingResult.throwable().orElse(null);
		return PropertyCheckResult.failed(
			configuration.getStereotype(), name, countTries, countChecks, configuration.getSeed(),
			configuration.getGenerationMode(), shrunkParams, originalParams, throwable
		);
	}

	private PropertyShrinkingResult shrink(
		Consumer<ReportEntry> reporter,
		Reporting[] reporting,
		List<Shrinkable<Object>> shrinkables,
		Throwable exceptionOrAssertionError
	) {
		PropertyShrinker shrinker = new PropertyShrinker(shrinkables, configuration.getShrinkingMode(), reporter, reporting);
		Falsifier<List<Object>> forAllFalsifier = checkedFunction::test;
		return shrinker.shrink(forAllFalsifier, exceptionOrAssertionError);
	}

}
