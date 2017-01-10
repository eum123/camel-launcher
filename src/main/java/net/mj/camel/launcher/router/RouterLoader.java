package net.mj.camel.launcher.router;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.camel.CamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.spring.boot.TypeConversionConfiguration;
import org.apache.camel.util.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;

import net.mj.camel.launcher.config.CamelConfiguration;
import net.mj.camel.launcher.helper.FileHelper;
import net.mj.camel.launcher.helper.LogHelper;

@ManagedBean
@Component("RouterManager")
@SpringApplicationConfiguration(classes = RouterLoader.class)
@Import(TypeConversionConfiguration.class)
public class RouterLoader {

	private static final Logger log = LoggerFactory.getLogger(RouterLoader.class);

	
	@Autowired(required=true)
	private ApplicationContext applicationContext;
	
	
	@Autowired(required=true)
	private CamelContext camelContext;
	
	@Autowired(required=true)
	private CamelConfiguration configuration;
	
	@Autowired
	private RouterAutoReloader reloader;
	
	private StopWatch watch = new StopWatch();

	@PostConstruct
	public void start() throws Exception {
		
		if(configuration.isScan() || reloader != null) {
			reloader.start();
		}
		
		load();
	}
	
	@PreDestroy
	public void stop() {
		
		if(reloader != null) {
			reloader.stop();
		}
	}
    

	public void load() throws Exception {
		reloader.load();
	}
}
