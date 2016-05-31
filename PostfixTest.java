import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostfixTest {
	private Postfix postfix;
	@Before
	public void setUp() throws Exception {
		postfix = new Postfix();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvaluate() {
		assertEquals(12, postfix.evaluate("12"));
		assertEquals(12, postfix.evaluate("6 6 +"));
		assertEquals(9, postfix.evaluate("16 7 -"));
		assertEquals(24, postfix.evaluate("6 4 *"));
		assertEquals(2, postfix.evaluate("28 7 6 4 - * /"));
	}
	
	@Test
	public void testInfixToPostfix() {
		assertEquals("12", postfix.infixToPostfix("12"));
		assertEquals("12 3 +", postfix.infixToPostfix("12 + 3"));
		assertEquals("12 3 *", postfix.infixToPostfix("12 * 3"));
		assertEquals("12 3 - 4 +", postfix.infixToPostfix("( 12 - 3 ) + 4")); 
		assertEquals("4 12 3 - +", postfix.infixToPostfix("4 + ( 12 - 3 )"));

		assertEquals("1 3 + 4 -", postfix.infixToPostfix("1 + 3 - 4"));
		assertEquals("1 3 4 * +", postfix.infixToPostfix("1 + 3 * 4"));
		assertEquals("1 5 * 3 4 / + 1 2 / -", postfix.infixToPostfix("1 * 5 + 3 / 4 - 1 / 2"));
		//assertEquals("1 -5 3 + * -4 1 - / 2 /", postfix.infixToPostfix("1 * ( -5 + 3 ) / ( -4 - 1 ) / 2"));
		
	}

}
