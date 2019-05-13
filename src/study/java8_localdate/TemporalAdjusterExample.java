package study.java8_localdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;


public class TemporalAdjusterExample {
	
	public static void exam() {
		LocalDate today = LocalDate.now();
		LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		System.out.println(nextMonday);				//2019-05-20
	}
	
	/*firstDayOfNextYear()									다음 해의 첫 날
	firstDayOfNextMonth()									다음 달의 첫 날
	firstDayOfYear()										올 해의 첫 날
	firstDayOfMonth()										이번 달의 첫 날
	lastDayOfYear()											올 해의 마지막 날
	lastDayOfMonth()										이번 달의 마지막 날
	firstInMonth(DayOfWeek dayOfWeek)						이번 달의 첫 번째 요일
	lastInMonth(DayOfWeek dayOfWeek)						이번 달의 마지막 요일
	previous(DayOfWeek dayOfWeek)							지난 요일(당일 미포함)
	previousOrSame(DayOfWeek dayOfWeek)						지난 요일(당일 포함)
	next(DayOfWeek dayOfWeek)								다음 요일(당일 미포함)
	nextOrSame(DayOfWeek dayOfWeek)							다음 요일(당일 포함)
	dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek)		이번 달의 n번째 요일
	*/
	
	//커스텀 TemporalAdjuster 구현 정의하기1 - 람다식 사용
	public static void whenAdjust_thenFourteenDaysAfterDate() {
		LocalDate localDate = LocalDate.now();
		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(4));
		LocalDate result = localDate.with(temporalAdjuster);
		System.out.println(result);
	}
	
	//커스텀 TemporalAdjuster 구현 정의하기2 - ofDateAdjuster() 정적 팩토리 메서드
	static TemporalAdjuster NEXT_WORKING_DAY = TemporalAdjusters.ofDateAdjuster(date -> {
		LocalDate today = LocalDate.now();
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int daysToAdd;
		if(dayOfWeek == DayOfWeek.FRIDAY) {
			daysToAdd = 3;
		} else if(dayOfWeek == DayOfWeek.SATURDAY) {
			daysToAdd = 2;
		} else {
			daysToAdd = 1;
		}
		return today.plusDays(daysToAdd);
	});
	
	
	public static void main(String[] args) {
		TemporalAdjusterExample.exam();
		
		LocalDate today = LocalDate.now();
		LocalDate date = today.with(new nextMonth());
		System.out.println(today);								//2019-06-13
		System.out.println(date);								//2019-06-13
		
		System.out.println(today.with(TemporalAdjusters.firstDayOfNextYear()));							//2020-01-01
		System.out.println(today.with(TemporalAdjusters.firstDayOfNextMonth()));						//2019-06-01
		System.out.println(today.with(TemporalAdjusters.firstDayOfYear()));								//2019-01-01
		System.out.println(today.with(TemporalAdjusters.firstDayOfMonth()));							//2019-05-01
		System.out.println(today.with(TemporalAdjusters.lastDayOfYear()));								//2019-12-31
		System.out.println(today.with(TemporalAdjusters.lastDayOfMonth()));								//2019-05-31
		System.out.println(today.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)));				//2019-05-03
		System.out.println(today.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY)));				//2019-05-30
		System.out.println(today.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));					//2019-05-06
		System.out.println(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)));			//2019-05-11
		System.out.println(today.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));						//2019-05-19
		System.out.println(today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));					//2019-05-19
		System.out.println(today.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.THURSDAY)));		//2019-05-16
		System.out.println("------------------------------------------------------------------------");
		TemporalAdjusterExample.whenAdjust_thenFourteenDaysAfterDate();									//2019-05-17
		System.out.println(today.with(NEXT_WORKING_DAY));												//2019-05-14
		
		TemporalAdjuster temporalAdjuster = new CustomTemporalAdjuster();
		System.out.println(today.with(temporalAdjuster));												//2019-05-14
		
		
	}
}

class nextMonth implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		return temporal.plus(1, ChronoUnit.MONTHS);
	}
}

class CustomTemporalAdjuster implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		int daysToAdd;
		if(dayOfWeek == DayOfWeek.FRIDAY) {
			daysToAdd = 3;
		} else if(dayOfWeek == DayOfWeek.SATURDAY) {
			daysToAdd = 2;
		} else {
			daysToAdd = 1;
		}
		return temporal.plus(daysToAdd, ChronoUnit.DAYS);
	}
}
