package net.jqwik.time;

import java.time.*;

import org.apiguardian.api.*;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.*;
import net.jqwik.api.time.*;

import static org.apiguardian.api.API.Status.*;

@API(status = INTERNAL)
public class DefaultMonthDayArbitrary extends ArbitraryDecorator<MonthDay> implements MonthDayArbitrary {

	private MonthDay monthDayMin = MonthDay.of(Month.JANUARY, 1);
	private MonthDay monthDayMax = MonthDay.of(Month.DECEMBER, 31);
	private Month monthMin = Month.JANUARY;
	private Month monthMax = Month.DECEMBER;
	private Month[] allowedMonths = new Month[]{Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER};
	private int dayOfMonthMin = 1;
	private int dayOfMonthMax = 31;

	@Override
	protected Arbitrary<MonthDay> arbitrary() {
		DateArbitrary dates = Dates.dates()
								   .atTheEarliest(LocalDate.of(2020, monthDayMin.getMonth(), monthDayMin.getDayOfMonth()))
								   .atTheLatest(LocalDate.of(2020, monthDayMax.getMonth(), monthDayMax.getDayOfMonth()))
								   .yearBetween(2020, 2020)
								   .monthBetween(monthMin, monthMax)
								   .onlyMonths(allowedMonths)
								   .dayOfMonthBetween(dayOfMonthMin, dayOfMonthMax);

		Arbitrary<MonthDay> monthDays = dates.map(v -> MonthDay.of(v.getMonth(), v.getDayOfMonth()));
		monthDays = addAllNeededEndOfMonthEdgeCases(monthDays);
		return monthDays;
	}

	private Arbitrary<MonthDay> addAllNeededEndOfMonthEdgeCases(Arbitrary<MonthDay> monthDays) {

		int[][] endOfMonth = new int[][]{{30, 31}, {28, 29}, {30, 31}, {29, 30}, {30, 31}, {29, 30}, {30, 31}, {30, 31}, {29, 30}, {30, 31}, {29, 30}, {30, 31}};
		int neededMonth = Math.max(monthMin.getValue(), monthDayMin.getMonthValue());
		monthDays = monthDays.edgeCases(monthDayConfig -> monthDayConfig.add(MonthDay.of(neededMonth, endOfMonth[neededMonth - 1][0])));
		monthDays = monthDays.edgeCases(monthDayConfig -> monthDayConfig.add(MonthDay.of(neededMonth, endOfMonth[neededMonth - 1][1])));
		return monthDays;

	}

	@Override
	public MonthDayArbitrary atTheEarliest(MonthDay min) {
		DefaultMonthDayArbitrary clone = typedClone();
		clone.monthDayMin = min;
		return clone;
	}

	@Override
	public MonthDayArbitrary atTheLatest(MonthDay max) {
		DefaultMonthDayArbitrary clone = typedClone();
		clone.monthDayMax = max;
		return clone;
	}

	@Override
	public MonthDayArbitrary monthBetween(Month min, Month max) {
		DefaultMonthDayArbitrary clone = typedClone();
		clone.monthMin = min;
		clone.monthMax = max;
		return clone;
	}

	@Override
	public MonthDayArbitrary onlyMonths(Month... months) {
		DefaultMonthDayArbitrary clone = typedClone();
		clone.allowedMonths = months;
		return clone;
	}

	@Override
	public MonthDayArbitrary dayOfMonthBetween(int min, int max) {
		DefaultMonthDayArbitrary clone = typedClone();
		clone.dayOfMonthMin = min;
		clone.dayOfMonthMax = max;
		return clone;
	}

}
