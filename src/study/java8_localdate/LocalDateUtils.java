package study.java8_localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalDateUtils{
	
	// Ÿ���� int�� �Ķ���͸� ���޹޾� ���� ���� ����ϴ� �޼ҵ� 
	public static String beforeMonth(int mon){
		LocalDate localDate = LocalDate.now().minusMonths(mon);
		return localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
   
	// Ÿ���� int�� �Ķ���͸� ���޹޾� ���� ���� ����ϴ� �޼ҵ�
	public static String afterMonth(int mon){
		LocalDate localDate = LocalDate.now().plusMonths(mon);
		return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
   
	// Ÿ���� int�� �Ķ���͸� ���޹޾� ���� ���� ����ϴ� �޼ҵ�
	public static String beforeDay(int day){
	   LocalDate localDate = LocalDate.now().minusDays(day); 
	   return localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
   }
   
	// ��¥�� �Ķ���ͷ� �޾� ���� ���� ����ϴ� �޼ҵ�
	public static String beforeDay(String date, int day){
		int mYear  = Integer.parseInt(date.substring(0, 4));
		int mMonth = Integer.parseInt(date.substring(4, 6));
		int mDay   = Integer.parseInt(date.substring(6, 8));
	   
		LocalDate localDate = LocalDate.of(mYear, mMonth, mDay).minusDays(day);
		return localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	// Ÿ���� int�� �Ķ���͸� ���޹޾� ���� ���� ����ϴ� �޼ҵ�
	public static String afterDay(int day){
		LocalDate localDate = LocalDate.now().plusDays(day);
		return localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	//��¥�ð��� �Է¹ް� ���� �ð��� ����ϴ� �޼ҵ�
	public static String beforeHour(String dateTime, int hour){
		int mYear  = Integer.parseInt(dateTime.substring(0, 4));
		int mMonth = Integer.parseInt(dateTime.substring(4, 6));
		int mDay   = Integer.parseInt(dateTime.substring(6, 8));
		int mHour  = Integer.parseInt(dateTime.substring(8, 10));
		int mMin   = Integer.parseInt(dateTime.substring(10, 12));
		int mSec   = Integer.parseInt(dateTime.substring(12));
		
		LocalDate localDate = LocalDate.of(mYear, mMonth, mDay);
		LocalTime localTime = LocalTime.of(mHour, mMin, mSec);
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime).minusHours(hour);
		
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	//��¥�ð��� �Է¹ް� ���� �ð��� ����ϴ� �޼ҵ�
	public static String afterHour(String dateTime, int hour){
		int mYear  = Integer.parseInt(dateTime.substring(0, 4));
		int mMonth = Integer.parseInt(dateTime.substring(4, 6));
		int mDay   = Integer.parseInt(dateTime.substring(6, 8));
		int mHour  = Integer.parseInt(dateTime.substring(8, 10));
		int mMin   = Integer.parseInt(dateTime.substring(10, 12));
		int mSec   = Integer.parseInt(dateTime.substring(12));
		
		LocalDate localDate = LocalDate.of(mYear, mMonth, mDay);
		LocalTime localTime = LocalTime.of(mHour, mMin, mSec);
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime).plusHours(hour);
		
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}
	
	//��¥�ð��� �Է¹ް� ���� ���� ����ϴ� �޼ҵ�
	public static String beforeMinute(String dateTime, int minute){
		int mYear  = Integer.parseInt(dateTime.substring(0, 4));
		int mMonth = Integer.parseInt(dateTime.substring(4, 6));
		int mDay   = Integer.parseInt(dateTime.substring(6, 8));
		int mHour  = Integer.parseInt(dateTime.substring(8, 10));
		int mMin   = Integer.parseInt(dateTime.substring(10, 12));
		int mSec   = Integer.parseInt(dateTime.substring(12));
		
		LocalDate localDate = LocalDate.of(mYear, mMonth, mDay);
		LocalTime localTime = LocalTime.of(mHour, mMin, mSec);
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime).minusMinutes(minute);
		
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	//��¥�ð��� �Է¹ް� ���� ���� ����ϴ� �޼ҵ�
	public static String afterMinute(String dateTime, int minute) {
		int mYear  = Integer.parseInt(dateTime.substring(0, 4));
		int mMonth = Integer.parseInt(dateTime.substring(4, 6));
		int mDay   = Integer.parseInt(dateTime.substring(6, 8));
		int mHour  = Integer.parseInt(dateTime.substring(8, 10));
		int mMin   = Integer.parseInt(dateTime.substring(10, 12));
		int mSec   = Integer.parseInt(dateTime.substring(12));
		
		LocalDate localDate = LocalDate.of(mYear, mMonth,mDay);
		LocalTime localTime = LocalTime.of(mHour, mMin, mSec);
		LocalDateTime localDatetime = LocalDateTime.of(localDate, localTime).plusMinutes(minute);
		
		return localDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	//��¥ ��
	public static void getDateCompare() {
		LocalDate startDate = LocalDate.of(1991, 9, 2);
		LocalDate endDate = LocalDate.now();

		long months = ChronoUnit.MONTHS.between(startDate, endDate);
		long weeks = ChronoUnit.WEEKS.between(startDate, endDate);
		long days = ChronoUnit.DAYS.between(startDate, endDate);

		System.out.println("Months: " + months);
		System.out.println("Weeks: " + weeks);
		System.out.println("Days: " + days);
	}
	
	//���۳�¥�� ����¥ ���� ��¥ ���1
	public static class DateRange1 implements Iterable<LocalDate> {

		private LocalDate startDate;
		private LocalDate endDate;

		public DateRange1(LocalDate startDate, LocalDate endDate) {
			this.startDate = startDate;
			this.endDate = endDate;
		}

		@Override
		public Iterator<LocalDate> iterator() {
			return stream().iterator();
		}
	
		public Stream<LocalDate> stream() {
			return Stream.iterate(startDate, d -> d.plusDays(1))
					.limit(ChronoUnit.DAYS.between(startDate, endDate) + 1);
		}
	
		public List<String> toList() {
			List<String> dates = new ArrayList<> ();
			for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
				dates.add(d.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			}
		    return dates;
		}
	}
	
	//���۳�¥�� ����¥ ���� ��¥ ���2
	public static void DateRange2() {
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
		    .limit(ChronoUnit.DAYS.between(start, end))			//�� ������ �Ϸ簡 �� �����°ɱ�? 
		    .collect(Collectors.toList());
		System.out.println(dates);
	}
	
	public static void main(String[] args) {
		System.out.println(LocalDateUtils.beforeMonth(3));              					//20190211
		System.out.println(LocalDateUtils.afterMonth (3));               					//2019-08-11
		System.out.println(LocalDateUtils.beforeDay(9));                					//20190502
		System.out.println(LocalDateUtils.beforeDay("20200301",1));							//20200229 - ������ ���Ե� 2��(28���� �ƴ� 29�Ϸ� �ڵ����)
		System.out.println(LocalDateUtils.afterDay(3));										//20190514
		System.out.println(LocalDateUtils.beforeHour("20180510120000",13));					//20180509230000
		System.out.println(LocalDateUtils.afterHour("20180510110000",13));					//2018-05-09 23:00:00
		System.out.println(LocalDateUtils.beforeMinute("20180520165810",4));				//2018-05-20 16:54:10
		System.out.println(LocalDateUtils.afterMinute("20180520165810",4));					//2018-05-20 17:02:10
		LocalDateUtils.getDateCompare();													//2018-05-20 17:02:10
		
		LocalDate startDate = LocalDate.of(2019, 05, 01);
		LocalDate endDate = LocalDate.of(2019, 06, 3);
		DateRange1 dateRange1 = new DateRange1(startDate, endDate);
		
		System.out.println(dateRange1.toList());											//[20190501, 20190502, 20190503, 20190504, 20190505, 20190506, 20190507, 20190508, 2019050.....
		LocalDateUtils.DateRange2();														//[2019-05-13, 2019-05-14, 2019-05-15, 2019-05-16, 2019-05-17, 2019-05-18, 2019-05-19, .....
   }
}
