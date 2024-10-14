package constructor.webserver.init;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import constructor.webserver.web.WebServer;

public class Main {
	public static void main(String[] args)
			throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			IOException {
		init();
		WebServer webServer = new WebServer();
		webServer.startServer();
	}

	public static void init()
			throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
		Constructor<ServerConfiguration> constructor = ServerConfiguration.class.getDeclaredConstructor(int.class, String.class);

		constructor.setAccessible(true);
		constructor.newInstance(8080, "Hello, World!");
	}
}
