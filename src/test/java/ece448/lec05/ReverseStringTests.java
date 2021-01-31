package ece448.lec05;

import static org.junit.Assert.*;
import org.junit.Test;

public class ReverseStringTests {
	@Test
	public void test() {
		assertEquals(ReverseString.reverse("Hello"), "olleH");
	}
}
