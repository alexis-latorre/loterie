package com.loterie.tests;

import org.junit.jupiter.api.Test;

class TousTests {
	@Test
	void test() throws Exception {
		TestEntities entities = new TestEntities();
		TestTools tools = new TestTools();
	
		entities.test();
		tools.test();
	}

}
