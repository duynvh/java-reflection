package exercises;

import java.util.HashSet;
import java.util.Set;

public class Exercise {
	/**
	 * Returns all the interfaces that the current input class implements
	 * Note: If the input is an interface, the method returns all the interfaces the
	 * input interfaces extends
	 */

	public static Set<Class<?>> findAllImplementedInterfaces(Class<?> inputClass) {
		Set<Class<?>> allImplementedInterfaces = new HashSet<>();
		Class<?>[] inputInterfaces = inputClass.getInterfaces();

		for (Class<?> currentInterface : inputInterfaces) {
			allImplementedInterfaces.add(currentInterface);
			allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
		}

		return allImplementedInterfaces;
	}
}
