package ece448.lec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Function {
	String call(String key, String value);
}

public class Lambda {
	public static void callFunction(String which, Function func) {
		logger.info("{}: {}", which, func.call("hello", "world"));
	}

	public static class Simple implements Function {
		@Override
		public String call(String key, String value) {
			return key+":"+value;
		}
	}

	public void runSimple() {
		callFunction("Simple", new Simple());
	}

	public static class Closure implements Function {
		private final String extra;

		public Closure(String extra) {
			this.extra = extra;
		}

		@Override
		public String call(String key, String value) {
			return key+":"+value+"-"+extra;
		}
	}

	public void runClosure() {
		callFunction("Closure", new Closure("extra for closure"));
	}

	private final String context = "Lambda";

	public static class ClosureThis implements Function {
		private final Lambda that;
		private final String extra;

		public ClosureThis(Lambda that, String extra) {
			this.that = that;
			this.extra = extra;
		}

		@Override
		public String call(String key, String value) {
			return "["+that.context+"]"+key+":"+value+"-"+extra;
		}
	}	

	public void runClosureThis() {
		callFunction("ClosureThis", new ClosureThis(this, "extra for closure and this"));
	}

	public void runLambda() {
		String extra = "extra for lambda";

		callFunction("Lambda", (key, value) -> {
			return "["+context+"]"+key+":"+value+"-"+extra;
		});
	}

	public static void main(String[] args) {
		new Lambda().runSimple();
		new Lambda().runClosure();
		new Lambda().runClosureThis();
		new Lambda().runLambda();
	}

	private static final Logger logger = LoggerFactory.getLogger(Lambda.class);
}
