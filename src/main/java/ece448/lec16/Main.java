package ece448.lec16;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Main implements AutoCloseable {
	private final ConfigurableApplicationContext appCtx;

	public Main(String mqttBroker, String mqttClientId) throws Exception {
		HashMap<String, Object> props = new HashMap<>();
		props.put("mqtt.broker", mqttBroker);
		props.put("mqtt.clientId", mqttClientId);
		SpringApplication app = new SpringApplication(App.class);
		app.setDefaultProperties(props);
		this.appCtx = app.run();
	}

	@Override
	public void close() throws Exception {
		appCtx.close();
	}

	public static void main(String[] args) throws Exception {
		try (Main m = new Main("tcp://127.0.0.1", "course_ece448")) {
			// loop forever
			for (;;) {
				Thread.sleep(60000);
			}
		}
	}
}
