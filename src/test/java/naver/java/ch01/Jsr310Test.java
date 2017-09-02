package naver.java.ch01;

import static org.junit.Assert.*;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRules;
import java.time.zone.ZoneRulesException;

import org.junit.Test;

public class Jsr310Test {

	@Test
	public void shouldGetAfterOneDay() {
		LocalDate theDay = IsoChronology.INSTANCE.date(1582, 10, 4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		assertEquals("1582.10.04", theDay.format(formatter));
		
		LocalDate nextDay = theDay.plusDays(1);
		assertEquals("1582.10.05", nextDay.format(formatter));
	}
	
	@Test
	public void shouldGetAfterOneHour() {
		ZoneId seoul = ZoneId.of("Asia/Seoul");
		ZonedDateTime theTime = ZonedDateTime.of(1988, 5, 8, 1, 0, 0, 0, seoul);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		assertEquals("1988.05.08 01:00", theTime.format(formatter));
		ZoneRules seoulRules = seoul.getRules();
		assertFalse(seoulRules.isDaylightSavings(Instant.from(theTime)));
		
		ZonedDateTime after1Hour = theTime.plusHours(1);
		assertEquals("1988.05.08 03:00", after1Hour.format(formatter));
		assertTrue(seoulRules.isDaylightSavings(Instant.from(after1Hour)));
	}
	
	@Test
	public void shouldGetAfterOneMinute() {
		ZoneId seoul = ZoneId.of("Asia/Seoul");
		ZonedDateTime theTime = ZonedDateTime.of(1961, 8, 9, 23, 59, 59, 0, seoul);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		assertEquals("1961.08.09 23:59", theTime.format(formatter));
		
		ZonedDateTime after1Minute = theTime.plusMinutes(1);
		assertEquals("1961.08.10 00:30", after1Minute.format(formatter));
	}
	
	@Test
	public void shouldGetAfterTwoSecond() {
		ZoneId utc = ZoneId.of("UTC");
		ZonedDateTime theTime = ZonedDateTime.of(2012, 6, 30, 23, 59, 59, 0, utc);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		assertEquals("2012.06.30 23:59:59", theTime.format(formatter));
		
		ZonedDateTime afterTwoSeconds = theTime.plusSeconds(2);
		assertEquals("2012.07.01 00:00:01", afterTwoSeconds.format(formatter));
	}
	
	@Test
	public void shouldGetDate() {
		LocalDate theDay = LocalDate.of(1999, 12, 31);
		
		assertEquals(1999, theDay.getYear());
		assertEquals(12, theDay.getMonthValue());
		assertEquals(31, theDay.getDayOfMonth());
	}
	
	@Test(expected=DateTimeException.class)
	public void shouldNotAcceptWrongMonth() {
		LocalDate.of(1999, 13, 31);
	}
	
	@Test
	public void shouldGetDayOfWeek() {
		LocalDate theDay = LocalDate.of(2014, 1, 1);
		
		DayOfWeek dayOfWeek = theDay.getDayOfWeek();
		assertEquals(DayOfWeek.WEDNESDAY, dayOfWeek);
	}
	
	@Test(expected=ZoneRulesException.class)
	public void shouldSetGmtWhenWrongTimeZoneId() {
		ZoneId.of("Seoul/Asia");
	}
}
