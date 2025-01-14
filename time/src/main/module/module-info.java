module net.jqwik.time {

	exports net.jqwik.time.api;
	exports net.jqwik.time.api.arbitraries;
	exports net.jqwik.time.api.constraints;

	requires transitive net.jqwik.api;
	requires org.opentest4j;

	provides net.jqwik.api.configurators.ArbitraryConfigurator with 
		net.jqwik.time.internal.properties.configurators.DateRangeConfigurator.ForLocalDate,
		net.jqwik.time.internal.properties.configurators.DateRangeConfigurator.ForCalendar,
		net.jqwik.time.internal.properties.configurators.DateRangeConfigurator.ForDate,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForLocalDate,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForCalendar,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForDate,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForMonthDay,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForInteger,
		net.jqwik.time.internal.properties.configurators.DayOfWeekRangeConfigurator.ForLocalDate,
		net.jqwik.time.internal.properties.configurators.DayOfWeekRangeConfigurator.ForCalendar,
		net.jqwik.time.internal.properties.configurators.DayOfWeekRangeConfigurator.ForDate,
		net.jqwik.time.internal.properties.configurators.MonthDayRangeConfigurator,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForLocalDate,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForCalendar,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForDate,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForYearMonth,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForMonthDay,
		net.jqwik.time.internal.properties.configurators.PeriodRangeConfigurator,
		net.jqwik.time.internal.properties.configurators.YearMonthRangeConfigurator,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForLocalDate,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForCalendar,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForDate,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForYearMonth,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForYear,
		net.jqwik.time.internal.properties.configurators.DurationRangeConfigurator,
		net.jqwik.time.internal.properties.configurators.MinuteRangeConfigurator.ForLocalTime,
		net.jqwik.time.internal.properties.configurators.MinuteRangeConfigurator.ForOffsetTime,
		net.jqwik.time.internal.properties.configurators.HourRangeConfigurator.ForLocalTime,
		net.jqwik.time.internal.properties.configurators.HourRangeConfigurator.ForOffsetTime,
		net.jqwik.time.internal.properties.configurators.OffsetRangeConfigurator.ForZoneOffset,
		net.jqwik.time.internal.properties.configurators.OffsetRangeConfigurator.ForOffsetTime,
		net.jqwik.time.internal.properties.configurators.PrecisionConfigurator.ForLocalTime,
		net.jqwik.time.internal.properties.configurators.PrecisionConfigurator.ForOffsetTime,
		net.jqwik.time.internal.properties.configurators.PrecisionConfigurator.ForDuration,
		net.jqwik.time.internal.properties.configurators.SecondRangeConfigurator.ForLocalTime,
		net.jqwik.time.internal.properties.configurators.SecondRangeConfigurator.ForOffsetTime,
		net.jqwik.time.internal.properties.configurators.TimeRangeConfigurator.ForLocalTime,
		net.jqwik.time.internal.properties.configurators.TimeRangeConfigurator.ForOffsetTime,
		net.jqwik.time.internal.properties.configurators.DateTimeRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.DateTimeRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.InstantConfigurator,
		net.jqwik.time.internal.properties.configurators.DateRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.DateRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.DateRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.YearRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.MonthRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.DayOfMonthRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.DayOfWeekRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.DayOfWeekRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.DayOfWeekRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.TimeRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.TimeRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.TimeRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.HourRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.HourRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.HourRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.MinuteRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.MinuteRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.MinuteRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.SecondRangeConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.SecondRangeConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.SecondRangeConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.PrecisionConfigurator.ForLocalDateTime,
		net.jqwik.time.internal.properties.configurators.PrecisionConfigurator.ForInstant,
		net.jqwik.time.internal.properties.configurators.PrecisionConfigurator.ForOffsetDateTime,
		net.jqwik.time.internal.properties.configurators.OffsetRangeConfigurator.ForOffsetDateTime;
																   
	provides net.jqwik.api.providers.ArbitraryProvider with
		net.jqwik.time.internal.properties.providers.DatesArbitraryProvider,
		net.jqwik.time.internal.properties.providers.MonthDayArbitraryProvider,
		net.jqwik.time.internal.properties.providers.YearArbitraryProvider,
		net.jqwik.time.internal.properties.providers.YearMonthArbitraryProvider,
		net.jqwik.time.internal.properties.providers.PeriodArbitraryProvider,
		net.jqwik.time.internal.properties.providers.OffsetTimesArbitraryProvider,
		net.jqwik.time.internal.properties.providers.TimesArbitraryProvider,
		net.jqwik.time.internal.properties.providers.TimeZoneArbitraryProvider,
		net.jqwik.time.internal.properties.providers.ZoneIdArbitraryProvider,
		net.jqwik.time.internal.properties.providers.ZoneOffsetsArbitraryProvider,
		net.jqwik.time.internal.properties.providers.DurationArbitraryProvider,
		net.jqwik.time.internal.properties.providers.DateTimesArbitraryProvider,
		net.jqwik.time.internal.properties.providers.InstantArbitraryProvider,
		net.jqwik.time.internal.properties.providers.OffsetDateTimesArbitraryProvider;
}
