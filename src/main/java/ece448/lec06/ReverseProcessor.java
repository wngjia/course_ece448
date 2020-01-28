package ece448.lec06;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ReverseProcessor implements Runnable {

    private final Socket client;
    private final SocketAddress address;

    public ReverseProcessor(Socket client) {
        this.client = client;
        this.address = client.getRemoteSocketAddress();
    }

    @Override
    public void run() {
        try {
            process();
        }
        catch (Throwable th) {
            // Otherwise, thread will exit without printing any information
            logger.error("client {}: {}", address, th.getMessage(), th);
        }
    }

    private void process() throws Exception {
        InputStream in = client.getInputStream();
        // OutputStream out = client.getOutputStream();

        for (;;) {
            // one byte at a time
            int ch = in.read();
            if (ch == -1)
                break;
            // let's see what byte we recv'ed from the client
            logger.debug("client {}: recv'ed {}", address,
                String.format("0x%02X", ch));
        }

        logger.info("client {}: disconnected");
        client.close();
    }

	private static final Logger logger
		= LoggerFactory.getLogger(ReverseServer.class);
}
