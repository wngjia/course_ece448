package ece448.lec06;

import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseServer implements AutoCloseable {

	private final ServerSocket server;

	public ReverseServer(int port) throws Exception {
		this.server = new ServerSocket(port);
	}

	public void run() throws Exception {
		logger.info("server: port {}", server.getLocalPort());
		for (;;) {
			// wait for client to connect
			Socket client = server.accept();
			logger.info("client {}: connected", client.getRemoteSocketAddress());
			// start a thread to process client request
			Thread t = new Thread(new ReverseProcessor(client));
			t.setDaemon(true);
			t.start();
		}
	}

	@Override
	public void close() throws Exception {
		server.close();
	}

	public static void main(String[] args) throws Exception {
		try (ReverseServer rs = new ReverseServer(6666)) {
			rs.run();
		}
	}

	private static final Logger logger
		= LoggerFactory.getLogger(ReverseServer.class);
}
