package constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {

	public static void main(String[] args)
			throws InvocationTargetException, InstantiationException, IllegalAccessException {
		printConstructorData(Person.class);

		Address address = createInstanceWithArguments(Address.class, "Main Street", 123);

		Person person = createInstanceWithArguments(Person.class, address, "John", 30);
		System.out.println(person);
	}

	public static <T> T createInstanceWithArguments(Class<T> clazz, Object ...args)
			throws InvocationTargetException, InstantiationException, IllegalAccessException {
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			if (constructor.getParameterTypes().length == args.length) {
				return (T) constructor.newInstance(args);
			}
		}

		System.out.println("An appropriate constructor was not found");
		return null;
	}

	public static void printConstructorData(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();

		System.out.println(String.format("class %s has %d declared constructors", clazz.getSimpleName(), constructors.length));

		for (int i = 0; i < constructors.length; i++) {
			Class<?>[] parameterTypes = constructors[i].getParameterTypes();

			List<String> parameterTypeNames = List.of(parameterTypes).stream()
					.map(Class::getSimpleName)
					.toList();

			System.out.println(parameterTypeNames);
		}
	}

	public static class Person {
		private final Address address;
		private final String name;
		private final int age;

		public Person() {
			this.name = "anonymous";
			this.age = 0;
			this.address = null;
		}

		public Person(String name) {
			this.name = name;
			this.age = 0;
			this.address = null;
		}

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
			this.address = null;
		}

		public Person(Address address, String name, int age) {
			this.address = address;
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person{" +
					"address=" + address +
					", name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}

	public static class Address {
		private String street;
		private int number;

		public Address(String street, int number) {
			this.street = street;
			this.number = number;
		}

		@Override
		public String toString() {
			return "Address{" +
					"street='" + street + '\'' +
					", number=" + number +
					'}';
		}
	}
}
