package ece448.lec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
	public static void main(String[] args) {
		logger.info("Hello World");
	}

	private static final Logger logger
		= LoggerFactory.getLogger(HelloWorld.class);
}
