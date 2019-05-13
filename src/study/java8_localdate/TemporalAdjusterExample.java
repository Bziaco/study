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
	
	/*firstDayOfNextYear()									���� ���� ù ��
	firstDayOfNextMonth()									���� ���� ù ��
	firstDayOfYear()										�� ���� ù ��
	firstDayOfMonth()										�̹� ���� ù ��
	lastDayOfYear()											�� ���� ������ ��
	lastDayOfMonth()										�̹� ���� ������ ��
	firstInMonth(DayOfWeek dayOfWeek)						�̹� ���� ù ��° ����
	lastInMonth(DayOfWeek dayOfWeek)						�̹� ���� ������ ����
	previous(DayOfWeek dayOfWeek)							���� ����(���� ������)
	previousOrSame(DayOfWeek dayOfWeek)						���� ����(���� ����)
	next(DayOfWeek dayOfWeek)								���� ����(���� ������)
	nextOrSame(DayOfWeek dayOfWeek)							���� ����(���� ����)
	dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek)		�̹� ���� n��° ����
	*/
	
	//Ŀ���� TemporalAdjuster ���� �����ϱ�1 - ���ٽ� ���
	public static void whenAdjust_thenFourteenDaysAfterDate() {
		LocalDate localDate = LocalDate.now();
		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(4));
		LocalDate result = localDate.with(temporalAdjuster);
		System.out.println(result);
	}
	
	//Ŀ���� TemporalAdjuster ���� �����ϱ�2 - ofDateAdjuster() ���� ���丮 �޼���
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
