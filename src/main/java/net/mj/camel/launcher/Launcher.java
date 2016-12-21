package net.mj.camel.launcher;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${CAMEL_CONF}/application.properties")
public class Launcher {
	public static void main(String[] args) {
		
		environment();
		
		printENV();
		
		SpringApplication application = new SpringApplication(Launcher.class);
		
		application.run(args);
	
	}
	
	private static void environment() {
		camelEnvironment();
		
	}
	
	private static void camelEnvironment() {
		String camelHome = System.getProperty("CAMEL_HOME", "./");
		System.setProperty("CAMEL_HOME", camelHome);
		
		String camelConf = System.getProperty("CAMEL_CONF", camelHome + "/conf");
		System.setProperty("CAMEL_CONF", camelConf);
		
		String routeConf = System.getProperty("CAMEL_ROUTE", camelConf + "/route");
		System.setProperty("CAMEL_ROUTE", routeConf);
	}
	
	protected static void printENV(){
		Properties p = System.getProperties();
		Set<Object> keys = p.keySet();
		
		Iterator<Object> it = keys.iterator();
		while(it.hasNext()) {
			Object key = it.next();
			
			System.out.println("Key:" + key + " value:" + p.getProperty((String)key));
		}
		
	}
}
