package net.mj.camel.launcher.web.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateToStringSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        jsonGenerator.writeString(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

    }
}
