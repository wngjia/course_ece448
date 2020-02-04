package ece448.lec08;

/**
 * Adopted from Java Network Programming 4th.
 */

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JHTTP {

	private static final Logger logger = LoggerFactory.getLogger(JHTTP.class);
	private static final int NUM_THREADS = 50;
	private static final String INDEX_FILE = "index.html";

	private final File rootDirectory;
	private final int port;

	public JHTTP(File rootDirectory, int port) throws IOException {

		if (!rootDirectory.isDirectory()) {
			throw new IOException(rootDirectory + " does not exist as a directory");
		}
		this.rootDirectory = rootDirectory;
		this.port = port;
	}

	public void start() throws IOException {
		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		try (ServerSocket server = new ServerSocket(port)) {
			logger.info("Accepting connections on port " + server.getLocalPort());
			logger.info("Document Root: " + rootDirectory);

			while (true) {
				try {
					Socket request = server.accept();
					Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
					pool.submit(r);
				} catch (IOException ex) {
					logger.warn("Error accepting connection", ex);
				}
			}
		}
	}

	public static void main(String[] args) {

		// get the Document root
		File docroot;
		try {
			docroot = new File((args.length < 1)? ".": args[0]);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: java JHTTP docroot port");
			return;
		}

		// set the port to listen on
		int port;
		try {
			port = Integer.parseInt((args.length < 2)? "8080": args[1]);
			if (port < 0 || port > 65535)
				port = 80;
		} catch (RuntimeException ex) {
			port = 80;
		}

		try {
			JHTTP webserver = new JHTTP(docroot, port);
			webserver.start();
		} catch (IOException ex) {
			logger.error("Server could not start", ex);
		}
	}
}