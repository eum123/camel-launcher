package net.mj.camel.launcher.router;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.apache.camel.CamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.spring.boot.CamelConfigurationProperties;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.camel.spring.boot.RoutesCollector;
import org.apache.camel.spring.boot.TypeConversionConfiguration;
import org.apache.camel.util.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;

import net.mj.camel.launcher.helper.FileHelper;
import net.mj.camel.launcher.helper.LogHelper;

@ManagedBean
@Component("RouterManager")
@SpringApplicationConfiguration(classes = RouteAutoLoader.class)
@Import(TypeConversionConfiguration.class)
public class RouteAutoLoader {

	private static final Logger log = LoggerFactory.getLogger(RouteAutoLoader.class);

	@Autowired(required=true)
	private CamelContext camelContext;
	
	private StopWatch watch = new StopWatch();

	@PostConstruct
	public void start() throws Exception {
		
		load();
	}
	
	@Bean
    @ConditionalOnMissingBean(RoutesCollector.class)
	/**
	 * Route 설정 파일을 읽는다.
	 * @param applicationContext
	 * @param config
	 * @return
	 */
    RoutesCollector routesCollector(ApplicationContext applicationContext, CamelConfigurationProperties config) {
System.out.println("=------------------------------------------------------" + config);
config.setXmlRoutes("file:${CAMEL_ROUTE}/*.xml");
System.out.println(config.getXmlRoutes());
System.out.println(config.getName());
System.out.println("=------------------------------------------------------");
        Collection<CamelContextConfiguration> configurations = applicationContext.getBeansOfType(CamelContextConfiguration.class).values();
        return new RoutesCollector(applicationContext, new ArrayList<CamelContextConfiguration>(configurations), config);
    }

	@ManagedOperation(description = "router 파일 새로 로딩")
	public void load() throws Exception {
		// 로그를 한번에 출력하기 위해 사용
		LogHelper logHelper = new LogHelper();

		try {
			String[] files = FileHelper.findFileList("${CAMEL_ROUTE}", "xml");

			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					
					watch.restart();
					load(files[i]);
					
					logHelper.appendLog("\t", files[i], " : ", String.valueOf(watch.stop()), "ms\r\n");
				}

				log.info("route file load complete : \r\n{}", logHelper.toString());
			} else {
				log.warn("${CAMEL_ROUTE} 위치에 route 파일 들을 찾을수 없음");
			}
		} catch (Exception e) {
			log.error("aaa", e);
			throw e;
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
