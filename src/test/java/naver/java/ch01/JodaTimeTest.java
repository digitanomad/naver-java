package naver.java.ch01;

import static org.junit.Assert.assertEquals;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.junit.Test;

public class JodaTimeTest {
	
	@Test
	public void shouldGetAfterOneDay() {
		Chronology chrono = GregorianChronology.getInstance();
		LocalDate theDay = new LocalDate(1582, 10, 4, chrono);
		String pattern = "yyyy.MM.dd";
		assertEquals("1582.10.04", theDay.toString(pattern));
		
		LocalDate nextDay = theDay.plusDays(1);
		assertEquals("1582.10.05", nextDay.toString(pattern));
	}
	
	@Test
	public void shouldGetAfterOneDayWithGJChronology() {
		Chronology chrono = GJChronology.getInstance();
		LocalDate theDay = new LocalDate(1582, 10, 4, chrono);
		String pattern = "yyyy.MM.dd";
		assertEquals("1582.10.04", theDay.toString(pattern));
		
		LocalDate nextDay = theDay.plusDays(1);
		assertEquals("1582.10.15", nextDay.toString(pattern));
	}
	
	@Test
	public void shouldGetAfterOneHour() {
		DateTimeZone seoul = DateTimeZone.forID("Asia/Seoul");
		DateTime theTime = new DateTime(1988, 5, 8, 1, 0, seoul);
		String pattern = "yyyy.MM.dd HH:mm";
		assertEquals("1988.05.08 01:00", theTime.toString(pattern));
		
		DateTime after1Hour = theTime.plusHours(1);
		assertEquals("1988.05.08 03:00", after1Hour.toString(pattern));
	}
	
	@Test
	public void shouldGetAfterOneMinute() {
		DateTimeZone seoul = DateTimeZone.forID("Asia/Seoul");
		DateTime theTime = new DateTime(1961, 8, 9, 23, 59, seoul);
		String pattern = "yyyy.MM.dd HH:mm";
		assertEquals("1961.08.09 23:59", theTime.toString(pattern));
		
		DateTime after1Minute = theTime.plusMinutes(1);
		assertEquals("1961.08.10 00:30", after1Minute.toString(pattern));
	}
	
	@Test
	public void shouldGetAfterTwoSecond() {
		DateTimeZone utc = DateTimeZone.forID("UTC");
		DateTime theTime = new DateTime(2012, 6, 30, 23, 59, 59, utc);
		String pattern = "yyyy.MM.dd HH:mm:ss";
		assertEquals("2012.06.30 23:59:59", theTime.toString(pattern));
		
		DateTime afterTwoSeconds = theTime.plusSeconds(2);
		assertEquals("2012.07.01 00:00:01", afterTwoSeconds.toString(pattern));
	}
	
	@Test
	public void shouldGetDate() {
		LocalDate theDay = new LocalDate(1999, 12, 31);
		
		assertEquals(1999, theDay.getYear());
		assertEquals(12, theDay.getMonthOfYear());
		assertEquals(31, theDay.getDayOfMonth());
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void shouldNotAcceptWrongMonth() {
		new LocalDate(1999, 13, 31);
	}
	
	@Test
	public void shouldGetDayOfWeek() {
		LocalDate theDay = new LocalDate(2014, 1, 1);
		int dayOfWeek = theDay.getDayOfWeek();
		assertEquals(DateTimeConstants.WEDNESDAY, dayOfWeek);
		assertEquals(3, dayOfWeek);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldSetGmtWhenWrongTimeZoneId() {
		DateTimeZone.forID("Seoul/Asia");
	}
}
