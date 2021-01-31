package ece448.lec05;

import static org.junit.Assert.*;
import org.junit.Test;

public class UnicodeTests {
	@Test
	public void testASCII() throws Exception {
		byte[] bytes = "Hello".getBytes("UTF-8");
		assertEquals(bytes.length, 5);
		assertEquals(new String(bytes, "UTF-8"), "Hello");
	}

	@Test
	public void testCJK() throws Exception {
		assertEquals(new String("你好".getBytes("UTF-8"), "UTF-8"), "你好");
		assertEquals(new String("こんにちは".getBytes("UTF-8"), "UTF-8"), "こんにちは");
		assertEquals(new String("안녕하세요".getBytes("UTF-8"), "UTF-8"), "안녕하세요");
	}
}
