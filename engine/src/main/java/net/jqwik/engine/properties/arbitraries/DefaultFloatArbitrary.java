package net.jqwik.engine.properties.arbitraries;

import java.math.*;
import java.util.*;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.*;
import net.jqwik.engine.properties.*;

public class DefaultFloatArbitrary extends AbstractArbitraryBase implements FloatArbitrary {

	private static final float DEFAULT_MIN = -Float.MAX_VALUE;
	private static final float DEFAULT_MAX = Float.MAX_VALUE;

	private final DecimalGeneratingArbitrary generatingArbitrary;

	public DefaultFloatArbitrary() {
		this.generatingArbitrary = new DecimalGeneratingArbitrary(Range.of(toBigDecimal(DEFAULT_MIN), toBigDecimal(DEFAULT_MAX)));
	}

	@Override
	public RandomGenerator<Float> generator(int genSize) {
		return generatingArbitrary.generator(genSize).map(BigDecimal::floatValue);
	}

	@Override
	public Optional<ExhaustiveGenerator<Float>> exhaustive(long maxNumberOfSamples) {
		return generatingArbitrary.exhaustive(maxNumberOfSamples).map(generator -> generator.map(BigDecimal::floatValue));
	}

	@Override
	public FloatArbitrary greaterOrEqual(float min) {
		DefaultFloatArbitrary clone = typedClone();
		clone.generatingArbitrary.range = clone.generatingArbitrary.range.withMin(toBigDecimal(min), true);
		return clone;
	}

	@Override
	public FloatArbitrary lessOrEqual(float max) {
		DefaultFloatArbitrary clone = typedClone();
		clone.generatingArbitrary.range = clone.generatingArbitrary.range.withMax(toBigDecimal(max), true);
		return clone;
	}

	@Override
	public FloatArbitrary ofScale(int scale) {
		DefaultFloatArbitrary clone = typedClone();
		clone.generatingArbitrary.scale = scale;
		return clone;
	}

	@Override
	public FloatArbitrary shrinkTowards(float target) {
		DefaultFloatArbitrary clone = typedClone();
		clone.generatingArbitrary.shrinkingTarget = BigDecimal.valueOf(target);
		return clone;
	}

	private BigDecimal toBigDecimal(float value) {
		return new BigDecimal(Float.toString(value));
	}

}
