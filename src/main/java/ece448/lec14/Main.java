package ece448.lec14;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Main implements AutoCloseable {
	private final ConfigurableApplicationContext appCtx;

	public Main() throws Exception {
		SpringApplication app = new SpringApplication(App.class);
		logger.info("My Spring App is created.");

		HashMap<String, Object> props = new HashMap<>();
		props.put("optionA", "argA");
		props.put("optionB", "argB");
		app.setDefaultProperties(props);
		logger.info("My Spring App env: {}", props);

		this.appCtx = app.run();
		logger.info("My Spring App is running.");

		ComponentA a = (ComponentA)this.appCtx.getBean("beanA");
		logger.info("{}: got it outside of the App", a.getName());
	}

	@Override
	public void close() throws Exception {
		appCtx.close();
	}

	public static void main(String[] args) throws Exception {
		try (Main m = new Main()) {
			// loop forever
			for (;;) {
				Thread.sleep(60000);
			}
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
}
