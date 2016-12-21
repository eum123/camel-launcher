package net.mj.camel.launcher.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;


/**
 * main class실행할때 뒤에 추가되는 명령어를 받아서 처리하는 것
 * <pre>
 * 예를 들어 "java AAA.class aaa bbb ccc" 형식으로 AAA.class 의 main()를 실행할때
 * 뒤 aaa bbb ccc 가 CommandLineRunner의 run() method의 argument로 넘어온다.
 * 
 * </pre> 
 * @author jin
 *
 */
@Component(value="ConsoleCommandLineRunner")
public class ConsoleCommandLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ConsoleCommandLineRunner.class);
	
	
	@Override
	public void run(String... args) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (String option : args) {
			sb.append(" ").append(option);
		}
		sb = sb.length() == 0 ? sb.append("No Options Specified") : sb;
		
		logger.info(String.format("WAR launched with following options: %s", sb.toString()));
	}

}
