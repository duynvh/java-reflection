package modifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		printClassModifiers(Auction.class);
		printClassModifiers(Bid.class);
		printClassModifiers(Bid.Builder.class);
//		printClassModifiers(Class.forName("auction.Bid$Builder$BidImpl"));

		printMethodModifiers(Auction.class.getDeclaredMethods());

		printFieldModifier(Auction.class.getDeclaredFields());
		printFieldModifier(Bid.class.getDeclaredFields());
	}

	private static void printFieldModifier(Field[] fields) {
		for (Field field: fields) {
			int modifier = field.getModifiers();
			System.out.println(String.format("Field \"%s\" access modifier is %s",
					field.getName(),
					getAccessModifierName(modifier)));

			if (Modifier.isVolatile(modifier)) {
				System.out.println("The field is volatile");
			}

			if (Modifier.isFinal(modifier)) {
				System.out.println("The field is final");
			}

			if (Modifier.isTransient(modifier)) {
				System.out.println("The field is transient and will not be serialized");
			}
			System.out.println();
		}
	}

	private static void printMethodModifiers(Method[] methods) {
		for (Method method: methods) {
			int modifier = method.getModifiers();

			System.out.println(String.format("%s() access modifier is %s",
					method.getName(),
					getAccessModifierName(modifier)));

			if (Modifier.isSynchronized(modifier)) {
				System.out.println("The method is synchronized");
			} else {
				System.out.println("The method is not synchronized");
			}
			System.out.println();
		}
	}

	private static void printClassModifiers(Class<?> clazz) {
		int modifier = clazz.getModifiers();
		System.out.println(String.format("Class %s access modifier is %s",
				clazz.getSimpleName(),
				getAccessModifierName(modifier)));

		if (Modifier.isAbstract(modifier)) {
			System.out.println("Class is abstract");
		}

		if (Modifier.isInterface(modifier)) {
			System.out.println("Class is interface");
		}

		if (Modifier.isStatic(modifier)) {
			System.out.println("Class is static");
		}
	}

	private static String getAccessModifierName(int modifier) {
		if (Modifier.isPublic(modifier)) {
			return "public";
		} else if (Modifier.isProtected(modifier)) {
			return "protected";
		} else if (Modifier.isPrivate(modifier)) {
			return "private";
		} else {
			return "package-private";
		}
	}

	public static void runAuction() {
		Auction auction = new Auction();
		auction.startAuction();

		Bid bid1 = Bid.builder()
				.setPrice(10)
				.setBidderName("Company1")
				.build();
		auction.addBid(bid1);

		Bid bid2 = Bid.builder()
				.setPrice(12)
				.setBidderName("Company2")
				.build();
		auction.addBid(bid2);

		auction.stopAuction();

		System.out.println(auction.getAllBids());
		System.out.println("Highest bid: " + auction.getHighestBid().get());
	}
}
