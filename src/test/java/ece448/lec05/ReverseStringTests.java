package ece448.lec05;

import static org.junit.Assert.*;
import org.junit.Test;

public class ReverseStringTests {
	@Test
	public void test() {
		assertEquals(ReverseString.reverse("Hello"), "olleH");
	}

	@Test
	public void testCJK() {
		assertEquals(ReverseString.reverse("你好"), "好你");
		assertEquals(ReverseString.reverse("こんにちは"), "はちにんこ");
		assertEquals(ReverseString.reverse("안녕하세요"), "요세하녕안");
	}
}
