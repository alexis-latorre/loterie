package com.loterie.tests;

import org.junit.jupiter.api.Test;

class TousTests {
	@Test
	void test() throws Exception {
		TestEntities entities = new TestEntities();
		TestTools tools = new TestTools();
		TestBusiness business = new TestBusiness();
	
		entities.test();
		tools.test();
		business.test();
	}

}
