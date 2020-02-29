package ece448.lec14;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public interface InterfaceC {
	public String getName();
}

class ComponentC implements InterfaceC {
	private final String prefix;
	
	@Autowired
	private ComponentA a;

	private ComponentD d;

	public ComponentC(ComponentB b, String prefix) {
		logger.info("{}/?/?: created with {}.", prefix, b.getName());
		this.prefix = prefix;
	}

	@Override
	public String getName() {
		String aName = (a == null)? "?": a.getName();
		String dName = (d == null)? "?": d.getName();
		return prefix+"/"+aName+"/"+dName;
	}

	@Autowired
	public void onContext(ApplicationContext ctx) {
		d = (ComponentD)ctx.getBean("beanD"+prefix.substring(6));
	}

	private static final Logger logger = LoggerFactory.getLogger(ComponentC.class);
}

class ComponentCX implements InterfaceC {
	private final String name;
	
	public ComponentCX(String name) {
		logger.info("{}: created.", name);
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	private static final Logger logger = LoggerFactory.getLogger(ComponentCX.class);
}

@Configuration
class FactoryC {
	@Bean
	public InterfaceC beanC1(@Qualifier("beanB1") ComponentB b1) {
		return new ComponentC(b1, "Bean-C1");
	}

	@Bean
	public ComponentC beanC2(@Qualifier("beanB2") ComponentB b2) {
		return new ComponentC(b2, "Bean-C2");
	}

	@Bean
	public ComponentCX beanC3() {
		return new ComponentCX("Bean-C3");
	}

	@Bean
	public List<InterfaceC> listC(@Qualifier("beanC1") InterfaceC c1,
		@Qualifier("beanC2") InterfaceC c2, ComponentCX c3) {
		logger.info("List-C: [{}, {}, {}]", c1.getName(), c2.getName(), c3.getName());
		return Arrays.asList(c1, c2, c3);
	}

	private static final Logger logger = LoggerFactory.getLogger(FactoryC.class);
}
