package ece448.lec14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public class ComponentB {
	private final String name;
	private final ComponentA a;

	public ComponentB(Environment env, ComponentA a, String name) {
		logger.info("{}: created with {} and {}.",
			name, a.getName(), env.getProperty("optionB"));
		this.name = name;
		this.a = a;
	}

	public String getName() {
		return name;
	}

	private static final Logger logger = LoggerFactory.getLogger(ComponentB.class);
}

@Configuration
class FactoryB {
	@Bean
	public ComponentB beanB1(Environment env, ComponentA a) {
		return new ComponentB(env, a, "Bean-B1");
	}

	@Bean
	public ComponentB beanB2(Environment env, ComponentA a) {
		return new ComponentB(env, a, "Bean-B2");
	}
}
