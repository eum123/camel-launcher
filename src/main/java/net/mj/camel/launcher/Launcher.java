package net.mj.camel.launcher;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

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
		
		systemEnvironment();
		
	}
	
	private static void systemEnvironment() {
		
		ConfigurableEnvironment environment = new StandardEnvironment();
		
		String camelHome = environment.getProperty("CAMEL_HOME", "./");
		environment.getSystemProperties().put("CAMEL_HOME", camelHome);
		
		String camelConf = environment.getProperty("CAMEL_CONF", camelHome + "/conf");
		environment.getSystemProperties().put("CAMEL_CONF", camelConf);
		
		String routerConf = environment.getProperty("CAMEL_ROUTER", camelConf + "/router");
		environment.getSystemProperties().put("CAMEL_ROUTER", routerConf);
	}
	
	private static void printENV(){
		Properties p = System.getProperties();
		Set<Object> keys = p.keySet();
		
		Iterator<Object> it = keys.iterator();
		while(it.hasNext()) {
			Object key = it.next();
			
			System.out.println("Key:" + key + " value:" + p.getProperty((String)key));
		}
		
	}
}
