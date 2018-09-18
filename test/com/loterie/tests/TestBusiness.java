package com.loterie.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.loterie.business.MoisHTML;

class TestBusiness {
	private MoisHTML moisHTML = null;
	
	@BeforeEach
	void setUp() throws Exception {
		DateTime dt = new DateTime()
				.withYear(2018)
				.withMonthOfYear(9)
				.withDayOfWeek(1);
		moisHTML = new MoisHTML(dt);
	}

	@Test
	public void test() {
		
	}
	
	public TestBusiness() throws Exception {
		setUp();
	}
}
