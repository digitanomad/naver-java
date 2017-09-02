package naver.java.ch01;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

import org.junit.Test;

public class OldJdkDateTest {
	
	@Test
	public void shouldGetAfterOneDay() {
		TimeZone utc = TimeZone.getTimeZone("UTC");
		Calendar calendar = Calendar.getInstance(utc);
		calendar.set(1582, Calendar.OCTOBER, 4);
		String pattern = "yyyy.MM.dd";
		String theDay = toString(calendar, pattern, utc);
		assertEquals("1582.10.04", theDay);
		
		calendar.add(Calendar.DATE, 1);
		String nextDay = toString(calendar, pattern, utc);
		assertEquals("1582.10.15", nextDay);
	}
	
	@Test
	public void shouldGetAfterOneHour() {
		TimeZone seoul = TimeZone.getTimeZone("Asia/Seoul");
		Calendar calendar = Calendar.getInstance(seoul);
		calendar.set(1988, Calendar.MAY, 8, 1, 0);
		String pattern = "yyyy.MM.dd HH:mm";
		String theTime = toString(calendar, pattern, seoul);
		assertEquals("1988.05.08 01:00", theTime);
		
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		String after1Hour = toString(calendar, pattern, seoul);
		assertEquals("1988.05.08 03:00", after1Hour);
		
		assertTrue(seoul.inDaylightTime(calendar.getTime()));
	}
	
	@Test
	public void shouldGetAfterOneMinute() {
		TimeZone seoul = TimeZone.getTimeZone("Asia/Seoul");
		Calendar calendar = Calendar.getInstance(seoul);
		calendar.set(1961, Calendar.AUGUST, 9, 23, 59);
		String pattern = "yyyy.MM.dd HH:mm";
		String theTime = toString(calendar, pattern, seoul);
		assertEquals("1961.08.09 23:59", theTime);
		
		calendar.add(Calendar.MINUTE, 1);
		String after1Minute = toString(calendar, pattern, seoul);
		assertEquals("1961.08.10 00:30", after1Minute);
	}
	
	@Test
	public void shouldGetAfterTwoSecond() {
		TimeZone utc = TimeZone.getTimeZone("UTC");
		Calendar calendar = Calendar.getInstance(utc);
		calendar.set(2012, Calendar.JUNE, 30, 23, 59, 59);
		String pattern = "yyyy.MM.dd HH:mm:ss";
		String theTime = toString(calendar, pattern, utc);
		assertEquals("2012.06.30 23:59:59", theTime);
		
		calendar.add(Calendar.SECOND, 2);
		String afterTwoSeconds = toString(calendar, pattern, utc);
		assertEquals("2012.07.01 00:00:01", afterTwoSeconds);
	}
	
	@Test
	public void shouldGetDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, 12, 31);
		assertEquals(2000, calendar.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
		assertEquals(31, calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void shouldGetDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, Calendar.JANUARY, 1);
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		assertEquals(Calendar.WEDNESDAY, dayOfWeek);
		assertEquals(4, dayOfWeek);
		Date theDate = calendar.getTime();
		assertEquals(3, theDate.getDay());
	}
	
	@Test
	public void shouldSetGmtWhenWrongTimeZoneId() {
		TimeZone zone = TimeZone.getTimeZone("Seoul/Asia");
		assertEquals("GMT", zone.getID());
	}
	
	private String toString(Calendar calendar, String pattern, TimeZone zone) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(zone);
		return format.format(calendar.getTime());
	}
}
