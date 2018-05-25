package net.mj.camel.launcher;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT) // server.port 설정에 따른다.
@ActiveProfiles("test")
public class CustomProcessTest {
    private static final Logger log = LoggerFactory.getLogger(CustomProcessTest.class);

    @Autowired
    private CamelContext camelContext;



    @Test
    public void test() {

        NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(10).create();


        List routeList = camelContext.getRoutes();

        log.info("routeList :" + routeList);


        log.info("----" + camelContext);

        notify.matches(10, TimeUnit.SECONDS);
    }
}
