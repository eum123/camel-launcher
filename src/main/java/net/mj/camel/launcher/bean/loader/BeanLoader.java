package net.mj.camel.launcher.bean.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;


@Component
//@ImportResource(locations="file:${CAMEL_CONF}/core.xml")
public class BeanLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(BeanLoader.class);
	
	@Autowired
	private AbstractApplicationContext applicationContext;
	
	public BeanLoader() {
		logger.debug("---- constructor");
	}
}
