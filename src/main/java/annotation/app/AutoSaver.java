package annotation.app;

import annotation.annotations.InitializerClass;
import annotation.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {
	@InitializerMethod
	public void startAutoSavingThreads() {
		System.out.println("Start automatic data saving to disk");
	}
}
