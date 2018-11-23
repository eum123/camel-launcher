package net.mj.camel.launcher.web.controller;

import net.javacrumbs.jsonunit.fluent.JsonFluentAssert;
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


        //https://github.com/lukas-krecan/JsonUnit json test

        JsonFluentAssert.assertThatJson(response.getBody()).node("result_code").isEqualTo("OK");
        JsonFluentAssert.assertThatJson(response.getBody()).node("result").isNotEqualTo(null);

        JsonFluentAssert.assertThatJson(response.getBody()).node("result.routes.routesId").isEqualTo("camel");
        JsonFluentAssert.assertThatJson(response.getBody()).node("result.routes.routes").isArray();
        JsonFluentAssert.assertThatJson(response.getBody()).node("result.routes.routes[0].tagName").isEqualTo("route");
        JsonFluentAssert.assertThatJson(response.getBody()).node("result.routes.routes[0].id").isEqualTo("process");
        JsonFluentAssert.assertThatJson(response.getBody()).node("result.routes.routes[0].list").isArray();
        System.out.println(response.getBody());
    }
}
