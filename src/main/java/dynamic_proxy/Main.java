package dynamic_proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import dynamic_proxy.external.DatabaseReader;
import dynamic_proxy.external.HttpClient;
import dynamic_proxy.external.impl.DatabaseReaderImpl;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		DatabaseReader databaseReader = createProxy(new DatabaseReaderImpl());

		useDatabaseReader(databaseReader);

		List<String> listOfGreetings = new ArrayList<>();
		listOfGreetings.add("Hello");
		listOfGreetings.add("good morning");
		listOfGreetings.remove("hello");
	}

	public static void useHttpClient(HttpClient httpClient) {
		httpClient.initialize();
		String response = httpClient.sendRequest("someRequest data");
		System.out.println(String.format("Http response is : %s", response));
	}

	public static void useDatabaseReader(DatabaseReader databaseReader) throws InterruptedException {
		int rowsInGamesTable = 0;
		try {
			rowsInGamesTable = databaseReader.countRowsInTable("GamesTable");
		} catch (IOException | InterruptedException e) {
			System.out.println("Catching exception " + e);
			return;
		}

		System.out.println(String.format("There are %s rows in GamesTable", rowsInGamesTable));
		String[] data = databaseReader.readRow("SELECT * FROM GamesTable");
		System.out.println(String.format("Received %s", String.join(" , ", data)));
	}

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(Object originalObject) {
		Class<?>[] interfaces = originalObject.getClass().getInterfaces();
		TimeMeasuringProxyHandler timeMeasuringProxyHandler = new TimeMeasuringProxyHandler(originalObject);
		return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), interfaces, timeMeasuringProxyHandler);
	}

	public static class TimeMeasuringProxyHandler implements InvocationHandler {
		private final Object originalObject;

		public TimeMeasuringProxyHandler(Object originalObject) {
			this.originalObject = originalObject;
		}

		@Override
		public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
			Object result;

			System.out.println(String.format("Measuring Proxy - Before Executing method : %s()", method.getName()));

			long startTime = System.nanoTime();
			try {
				result = method.invoke(originalObject, args);
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
			long endTime = System.nanoTime();

			System.out.println();
			System.out.println(String.format("Measuring Proxy - Execution of %s() took %dns \n", method.getName(), endTime - startTime));
			return result;
		}
	}
}
