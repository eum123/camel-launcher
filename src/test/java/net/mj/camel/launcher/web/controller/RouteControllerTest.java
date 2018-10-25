package net.mj.camel.launcher.web.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RouteControllerTest {

    @Test
    public void routeFileTest() throws Exception {

        String fileName = "/work/project/camel-launcher/src/test/resources/conf/route/process.xml";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate
                .getForEntity("http://localhost:7070/routeFile?fileName=" + fileName, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        System.out.println(response.getBody());
    }
}
