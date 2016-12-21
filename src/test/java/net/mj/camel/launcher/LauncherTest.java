package net.mj.camel.launcher;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Launcher.class)
public class LauncherTest {
	@Autowired
    private CamelContext camelContext;
	
	@BeforeClass
	public static void init() {
		System.setProperty("CAMEL_HOME", "src/test/resources");
		System.setProperty("CAMEL_CONF", "src/test/resources/conf");
	}
	
	@Test
	public void test() {
		NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(10).create();
		
		notify.matches(10, TimeUnit.SECONDS);
	}
	
}
