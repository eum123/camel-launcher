package net.mj.camel.launcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LauncherTest {
	@Autowired
    private CamelContext camelContext;
	
	@BeforeClass
	public static void init() {
		System.setProperty("CAMEL_HOME", "src/test/resources");
		System.setProperty("CAMEL_CONF", "src/test/resources/conf");
		System.setProperty("CAMEL_ROUTER", "src/test/resources/conf/router");
		
		//사용자 정의 로그 설정
		System.setProperty("logging.config", "file:/data/jin/project/camel/camel-launcher/src/test/resources/conf/logback-spring.xml");
	}
	
	@Test
	public void test() {

		NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(10).create();
		
		
		List routeList = camelContext.getRoutes();
		
		System.out.println("routeList :" + routeList);
		
		
		System.out.println("----" + camelContext);
		
		notify.matches(1000, TimeUnit.SECONDS);
	}
	
}
