package annotation.app.configs;

import annotation.annotations.InitializerClass;
import annotation.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {
	@InitializerMethod
	public void loadAllConfigs() {
		System.out.println("Loading all configs...");
	}
}
