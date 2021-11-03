/**
 * 
 */
package todo.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factorizationtree.NumberFactored;

/**
 * @author nm108
 *
 */
class JunitTestCase {
	
	private NumberFactored n;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		n = null;
	}

	@Test
	void test108() {
		n = new NumberFactored(108);
		assertEquals(n.getDivisorsAmount(), 6);
		
		assertFalse(n.isPrimeNumber());
		
		n = new NumberFactored(108, true);
		assertEquals(n.getDivisorsAmount(), 6);
		
		n = new NumberFactored(108, false);
		assertEquals(n.getDivisorsAmount(), 12);
		assertEquals("[ 108, 54, 36, 27, 18, 12, 9, 6, 4, 3, 2, 1 ];\n", 
				n.getClonedLocalDivisorsAsStringBuilder(false).toString());
		
		n = new NumberFactored(108, false);
		assertEquals(n.getDivisorsAmount(), 12);
		
		
		
		// ... TODO: more tests
		
	}
	
	@Test
	void test17() {
		n = new NumberFactored(17);
		assertEquals(n.getDivisorsAmount(), 1);
		assertTrue(n.isPrimeNumber());
		
		n = new NumberFactored(17, false);
		assertEquals(n.getDivisorsAmount(), 2);
		assertTrue(n.isPrimeNumber());
		
		// ... TODO: more tests

	}
	
	@Test
	void testMaxLongValue() {
		n = new NumberFactored(Long.MAX_VALUE);
		
		assertFalse(n.isPrimeNumber());
		assertEquals(n.getDivisorsAmount(), 48);
//		System.out.print(n.getDivisorsStateAsStringBuilder(true).toString());
		
		// ... TODO: more tests
	}

	
	// ... TODO: more @Test methods

	
}
