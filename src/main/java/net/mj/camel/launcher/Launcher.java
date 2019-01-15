package net.mj.camel.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@SpringBootApplication
@ImportResource({
		"file:${camel.config}/core.xml",
		"file:${camel.config}/custom-*.xml"
})
@ComponentScan(basePackages="com.mj.camel")
public class Launcher {
	private static final Logger log = LoggerFactory.getLogger(Launcher.class);


	public static void main(String[] args) throws Exception {

		ApplicationContext applicationContext = new SpringApplication(Launcher.class).run(args);

	}


//	@Bean
//	public TaskScheduler taskScheduler() {
//		return new ConcurrentTaskScheduler(); //single threaded by default
//	}
}
