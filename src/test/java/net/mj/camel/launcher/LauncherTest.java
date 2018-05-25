package net.mj.camel.launcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT) // server.port 설정에 따른다.
@ActiveProfiles("test")
public class LauncherTest {

	private static final Logger log = LoggerFactory.getLogger(LauncherTest.class);

	@Autowired
    private CamelContext camelContext;
	

	
	@Test
	public void test() {

		NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(10).create();
		
		
		List routeList = camelContext.getRoutes();
		
		log.debug("routeList :" + routeList);


		log.debug("----" + camelContext);
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notify.matches(1000, TimeUnit.SECONDS);
	}
	
}
