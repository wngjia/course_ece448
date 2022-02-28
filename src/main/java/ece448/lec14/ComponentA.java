package ece448.lec14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("beanA")
public class ComponentA {
	private final String name = "Bean-A";

	public ComponentA(Environment env) {
		logger.info("{}: created with {}.", name, env.getProperty("optionA"));
	}

	public String getName() {
		return name;
	}

	private static final Logger logger = LoggerFactory.getLogger(ComponentA.class);
}
