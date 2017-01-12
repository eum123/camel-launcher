package net.mj.camel.launcher.router;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.apache.camel.CamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import net.mj.camel.launcher.config.CamelConfiguration;

@ManagedBean
@Component("RouterAutoReloader")
public class RouterAutoReloader {
	private static final Logger log = LoggerFactory.getLogger(RouterAutoReloader.class);

	@Autowired(required = true)
	private ApplicationContext applicationContext;

	@Autowired(required = true)
	private CamelContext camelContext;

	@Autowired(required = true)
	private CamelConfiguration configuration;

	private Worker worker = null;
	private Map<Resource, Long> routes = new HashMap<Resource, Long>();
	private String directory = null;

	public void start() throws Exception {

		if (configuration.isScan()) {
			worker = new Worker();
			worker.start();
		}

		directory = "file:" + configuration.getRouter() + "/*.xml";
	}

	public void stop() {
		if (worker != null) {
			worker.terminate();

			while (worker.isAlive()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public void load() throws Exception {

		try {
			Resource[] xmlRoutes = applicationContext.getResources(directory);

			for (Resource xmlRoute : xmlRoutes) {

				if (routes.containsKey(xmlRoute)) {
					long lastModified = routes.get(xmlRoute);
					if (xmlRoute.lastModified() > lastModified) {
						// reset
						routes.remove(xmlRoute);
						routes.put(xmlRoute, xmlRoute.lastModified());
					} else {
						// no changed
						continue;
					}
				} else {
					// new XML route
					routes.put(xmlRoute, xmlRoute.lastModified());
				}

				log.info("add XML route: {}" + xmlRoute);

				RoutesDefinition xmlDefinition = camelContext.loadRoutesDefinition(xmlRoute.getInputStream());

				try {
					camelContext.addRouteDefinitions(xmlDefinition.getRoutes());
				} catch (Exception e) {
					log.error("cannot add XML route", e);

					if (xmlDefinition.getRoutes() != null && xmlDefinition.getRoutes().size() > 0) {
						camelContext.removeRouteDefinitions(xmlDefinition.getRoutes());
					}

					throw e;
				}

			}
		} catch (FileNotFoundException e) {
			log.debug("No XML routes found in {}. Skipping XML routes detection.", directory);
		}
	}

	class Worker extends Thread {
		private boolean isStart = true;

		public void run() {
			while (isStart) {
				try {
					Thread.sleep(configuration.getScanInterval());
					load();
				} catch (Exception e) {

				}
			}
		}

		public void terminate() {
			isStart = false;
			this.interrupt();
		}
	}

}
