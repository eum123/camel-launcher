package net.mj.camel.launcher.web.common.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.mj.camel.launcher.web.service.router.RouteEntity;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateToStringSerializerTest {
    @Test
    public void serialize() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(Date.class, new DateToStringSerializer());
        mapper.registerModule(simpleModule);



        RouteEntity entity = new RouteEntity("routeId");

        entity.setStartTimestamp(new Date());

        System.out.println(mapper.writeValueAsString(entity));

    }

    @Test
    public void format() {
        Date date = new Date();

        Instant instant = date.toInstant();

        System.out.println(instant.atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

    }
}
