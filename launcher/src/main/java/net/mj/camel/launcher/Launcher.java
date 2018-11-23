package net.mj.camel.launcher;

import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@SpringBootApplication
@ImportResource(locations = "file://${camel.path.conf}/${camel.spring.core-resource-name}")
public class Launcher {
	private static final Logger log = LoggerFactory.getLogger(Launcher.class);

	@Value("${camel.springboot.xml-routes-reload-directory}")
	private static String reloadDirectory;

	public static void main(String[] args) throws Exception {

		ApplicationContext applicationContext = new SpringApplication(Launcher.class).run(args);

		Main main = new Main();
		main.setApplicationContext((AbstractApplicationContext)applicationContext);
		main.setFileWatchDirectory(reloadDirectory);
		main.run();
	}

	/**
	 * @Value Annotation은 static 변수에 inject할수 없어 setter를 만듬.
	 * @param reloadDirectory
	 */
	@Value("${camel.springboot.xml-routes-reload-directory}")
	private void setReloadDirectory(String reloadDirectory) {
		Launcher.reloadDirectory = reloadDirectory;
	}


	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler(); //single threaded by default
	}
}
