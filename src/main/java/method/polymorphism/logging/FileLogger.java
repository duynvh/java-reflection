package method.polymorphism.logging;

import java.io.IOException;

public class FileLogger {
	public void sendRequest(String data) throws IOException {
		throw new IOException("Failed saving request to a file");
	}
}
