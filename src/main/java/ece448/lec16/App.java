package ece448.lec16;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class App {

	@Bean(destroyMethod = "disconnect")
	public MqttClient mqttClient(Environment env) throws Exception {
		String broker = env.getProperty("mqtt.broker");
		String clientId = env.getProperty("mqtt.clientId");
		MqttClient mqtt = new MqttClient(broker, clientId, new MemoryPersistence());
		mqtt.connect();
		logger.info("MqttClient {} connected: {}", clientId, broker);
		return mqtt;
	}

	private static final Logger logger = LoggerFactory.getLogger(App.class);
}
