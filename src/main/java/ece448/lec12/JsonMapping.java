package ece448.lec12;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonMapping {

	public static void main(String[] args) throws Exception {
		// JSON file -> Java types
		Map<String, Object> configJava = mapper.readValue(
			new File("simConfig.json"),
			new TypeReference<Map<String, Object>>(){});
		
		logger.info("httpPort {}", (Integer)configJava.get("httpPort"));
		logger.info("mqttBroker {}", (String)configJava.get("mqttBroker"));

		List<?> plugNames = (List<?>)configJava.get("plugNames");
		for (Object name: plugNames)
			logger.info("plugName {}", (String)name);

		// Java types -> JSON string
		String json = mapper.writeValueAsString(configJava);
		logger.info("Java types->JSON {}", json);

		// JSON string -> user type
		SimConfig config = mapper.readValue(json, SimConfig.class);

		// user type -> JSON file
		mapper.writeValue(new File("user_type.json"), config);
	}

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(JsonMapping.class);
}
