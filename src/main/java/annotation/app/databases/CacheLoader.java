package annotation.app.databases;

import annotation.annotations.InitializerClass;
import annotation.annotations.InitializerMethod;

@InitializerClass
public class CacheLoader {
	@InitializerMethod
	public void loadCache() {
		System.out.println("Loading cache...");
	}

	public void reloadCache() {
		System.out.println("Reloading cache...");
	}
}
