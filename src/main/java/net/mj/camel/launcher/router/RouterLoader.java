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
		
		//load();
		
		System.out.println(configuration.getHome());
		System.out.println(configuration.getConf());
		System.out.println(configuration.getRouter());
		System.out.println(configuration.isScan());
		
		
		
		System.out.println(configuration.isScan() + "===================== reloader : " + reloader);
		if(configuration.isScan() || reloader != null) {
			reloader.start();
		}
		
		//load();
	}
	
	@PreDestroy
	public void stop() {
		
		System.out.println("--------------------------------------destroy");
		if(reloader != null) {
			reloader.stop();
		}
	}
    

	public void load() throws Exception {
		reloader.load();
	}
	
	private void load1() throws Exception {
		String directory = "file:" + configuration.getRouter() + "/*.xml";
		log.info("Loading additional Camel XML routes from: {}", directory);
        try {
            Resource[] xmlRoutes = applicationContext.getResources(directory);
            for (Resource xmlRoute : xmlRoutes) {
            	log.debug("Found XML route: {}", xmlRoute);
                RoutesDefinition xmlDefinition = camelContext.loadRoutesDefinition(xmlRoute.getInputStream());
                camelContext.addRouteDefinitions(xmlDefinition.getRoutes());
            }
        } catch (FileNotFoundException e) {
        	log.debug("No XML routes found in {}. Skipping XML routes detection.", directory);
        }
	}
	
	private void load(String path) throws Exception {
		InputStream is = null;
		try {
			
			
			is = new FileInputStream(new File(path));
			
			RoutesDefinition routes = camelContext.loadRoutesDefinition(is);

			try {
				camelContext.addRouteDefinitions(routes.getRoutes());
			} catch (Exception e) {

				camelContext.removeRouteDefinitions(routes.getRoutes());
				log.warn(path + " route file load fail ", e);
				
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error(path + " route file close fail", e);
				}
			}
		}
	}

}
