package net.mj.camel.launcher.converter;

import java.util.List;

import org.apache.camel.Converter;
import org.apache.camel.MessageHistory;
import org.apache.camel.TypeConverters;

/**
 * MessageHistory를 변환하기 위한 converter</br>
 * spring dsl사용시 bean으로 등록만 하면 된다.</br>
 * 
 * See <a href="http://camel.apache.org/type-converter.html">http://camel.apache.org/type-converter.html</a></br>
 * 
 * @author jin
 *
 */
public class MessageHistoryConverters implements TypeConverters{
	@Converter
	public String convertToString(List<MessageHistory> list) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("[");

		list.forEach(x -> {
			builder.append("{");
			builder.append("\"RouteId\":").append(x.getRouteId());
			builder.append(", \"elapsed\":").append(x.getElapsed());
			builder.append("}");
		});

		
		builder.append("]");
		return builder.toString();
	}
}
