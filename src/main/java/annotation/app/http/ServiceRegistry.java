package annotation.app.http;

import annotation.annotations.InitializerClass;
import annotation.annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

	@InitializerMethod
	public void registerService() {
		System.out.println("Service successfully registered");
	}
}
