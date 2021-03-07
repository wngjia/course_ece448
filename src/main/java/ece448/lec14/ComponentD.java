package ece448.lec14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ComponentD {
	private final String name;
	private final InterfaceC c;

	public ComponentD(InterfaceC c, String name) {
		logger.info("{}: created with {}.", name, c.getName());
		this.name = name;
		this.c = c;
	}

	public String getName() {
		return name;
	}

	private static final Logger logger = LoggerFactory.getLogger(ComponentD.class);
}

@Configuration
class FactoryD {
	@Bean
	public ComponentD beanD1(@Qualifier("beanC1") InterfaceC c) {
		return new ComponentD(c, "Bean-D1");
	}

	@Bean
	public ComponentD beanD2(@Qualifier("beanC2") InterfaceC c) {
		return new ComponentD(c, "Bean-D2");
	}
}
