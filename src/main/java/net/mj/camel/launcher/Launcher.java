package net.mj.camel.launcher;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

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

			displayClass();

			SpringApplication application = new SpringApplication(Launcher.class);

			application.run(args);
			
			printENV();

		} catch (Exception e) {
			e.printStackTrace();

			System.exit(0);
		}
	}

	private static void displayClass() {
		try {
			Field f = ClassLoader.class.getDeclaredField("classes");
			f.setAccessible(true);
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			while(true) {
				
				Vector<Class> classes = (Vector<Class>) f.get(classLoader);
				for (int i = 0; i < classes.size(); i++) {
					log.debug("loaded class:" + classes.get(i));
				}
				
				if(classLoader.getParent() != null) {
					classLoader = classLoader.getParent();
				} else {
					break;
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void environment() throws Exception {

		systemEnvironment();
		loadExternalLibrary();
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

	/**
	 * 사용자 정의 library를 classloader에 추가한다.
	 * @throws Exception
	 */
	private static void loadExternalLibrary() throws Exception {
		String libDirString = (String) environment.getSystemProperties().get("CAMEL_LIB");

		final URLClassLoader loader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		final Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
		method.setAccessible(true);
		
		new File(libDirString).listFiles(new FileFilter() {
			public boolean accept(File jar) { 
				// jar 파일인 경우만 로딩
				if (jar.toString().toLowerCase().contains(".jar")) {
					try { 
						// URLClassLoader.addURL(URL url) 메소드 호출
						method.invoke(loader, new Object[] { jar.toURI().toURL() });
						log.info(jar.getAbsolutePath() + " is loaded.");
					} catch (Exception e) {
						log.error(jar.getAbsolutePath() + " can't load.", e);
					}
				}
				return false;
			}
		});
	}

	private static void printENV() {
		Properties p = System.getProperties();
		Set<Object> keys = p.keySet();

		Iterator<Object> it = keys.iterator();
		while (it.hasNext()) {
			Object key = it.next();

			log.debug("Key:" + key + " value:" + p.getProperty((String) key));
		}

	}
}
