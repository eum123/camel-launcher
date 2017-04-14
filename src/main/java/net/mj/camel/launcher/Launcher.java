package net.mj.camel.launcher;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
@PropertySource("file:${CAMEL_CONF}/application.properties")
public class Launcher {
	private static final Logger log = LoggerFactory.getLogger(Launcher.class);

	private static ConfigurableEnvironment environment = null;

	public static void main(String[] args) {

		try {
			environment();
			
			Thread.sleep(1000);
		
			SpringApplication application = new SpringApplication(Launcher.class);

			application.run(args);
			
			displaySystemProperties();

		} catch (Exception e) {
			e.printStackTrace();

			System.exit(0);
		}
	}

	

	private static void environment() throws Exception {

		systemEnvironment();
		
		//load external library
		LibraryLoader.load((String) environment.getSystemProperties().get("CAMEL_LIB"));
	}

	private static void systemEnvironment() {

		environment = new StandardEnvironment();

		String currentDir = System.getProperty("user.dir");
		String camelHome = environment.getProperty("CAMEL_HOME", currentDir == null ? "./" : currentDir);
		environment.getSystemProperties().put("CAMEL_HOME", camelHome);

		String camelConf = environment.getProperty("CAMEL_CONF", camelHome + "/conf");
		environment.getSystemProperties().put("CAMEL_CONF", camelConf);

		String routerConf = environment.getProperty("CAMEL_ROUTER", camelConf + "/router");
		environment.getSystemProperties().put("CAMEL_ROUTER", routerConf);

		String camelLib = environment.getProperty("CAMEL_LIB", camelHome + "/lib");
		environment.getSystemProperties().put("CAMEL_LIB", camelLib);

	}
	
 
	private static void displaySystemProperties() {
		Properties p = System.getProperties();
		Set<Object> keys = p.keySet();

		Iterator<Object> it = keys.iterator();
		while (it.hasNext()) {
			Object key = it.next();

			log.debug("Key:" + key + " value:" + p.getProperty((String) key));
		}

	}
}
