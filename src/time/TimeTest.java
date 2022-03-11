package time;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {
	
	public void main() {
		
	}

	@Test
	void testGetTotalSecondsGood() {
		int seconds=Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated correctly",seconds==18305);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class,()->{
			Time.getTotalSeconds("10:00");
		});
	}
	
	@Test
	void testGetTotalSecondsBoundary() {
		int seconds=Time.getTotalSeconds("00:00:00");
		assertTrue("The seconds were not calculated correctly",seconds==0);
		
		seconds=Time.getTotalSeconds("00:00:59");
		assertTrue("The seconds were not calculated correctly",seconds==59);
	}
	
	//-----------------------------------------------------------------
	
	@Test
	void testGetSecondsGood() {
		int seconds=Time.getSeconds("07:07:52");
		assertTrue("The seconds were not calculated correctly",seconds==52);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(NumberFormatException.class,()->{
			Time.getSeconds("07:07:cc");
		});
	}
	
	@Test
	void testGetSecondsBoundary() {
		int seconds=Time.getSeconds("07:07:00");
		assertTrue("The seconds were not calculated correctly",seconds==00);
		
		seconds=Time.getSeconds("07:07:59");
		assertTrue("The seconds were not calculated correctly",seconds==59);
	}
	
	//-----------------------------------------------------------------s
	
	@ParameterizedTest
	@ValueSource(strings= {"06:08:00","06:08:18","06:08:59"})
	void testGetTotalMinutesGoodAndBoundary(String candidate) {
		int minutes=Time.getTotalMinutes(candidate);
		assertTrue("The total minutes were  not calculated properly",minutes==8);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class,()->{
			Time.getTotalMinutes("06:08:18:66");
		});
	}

	//-----------------------------------------------------------------
	
	@ParameterizedTest
	@ValueSource(strings= {"06:00:00","06:06:06","06:59:59"})
	void testGetTotalHoursGoodAndBoundary(String candidate) {
		int minutes=Time.getTotalHours(candidate);
		assertTrue("The total minutes were  not calculated properly",minutes==6);
	}

	@Test
	void testGetTotalHourssBad() {
		assertThrows(NumberFormatException.class,()->{
			Time.getTotalHours("ss:08:18");
		});
	}
	
	
	
	//-----------------------------------------------------------------
	
	@Test
	void testGetMilisecondsGood() {
		int miliseconds=Time.getMiliseonds("06:06:06:666");
		assertTrue("The total minutes were  not calculated properly",miliseconds==666);
	}
	
	@Test
	void testGetMilisecondsBoundary() {
		int miliseconds=Time.getMiliseonds("06:06:06:000");
		assertTrue("The total minutes were  not calculated properly",miliseconds==000);
		
		miliseconds=Time.getMiliseonds("06:06:06:999");
		assertTrue("The total minutes were  not calculated properly",miliseconds==999);
	}

	@Test
	void testGetMilisecondsBad() {
		assertThrows(NumberFormatException.class,()->{
			Time.getMiliseonds("06:08:18:bbb");
		});
	}
}
