package net.mj.camel.launcher.config;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="camel")
public class CamelConfiguration {
			
	@Value("${camel.home:./}")	
	private String home;
	
	@Value("${camel.conf:./conf}")	
	private String conf;
	
	@Value("${camel.router:./conf/router}")
	private String router;
	
	@Value("${camel.router.scan:true}")
	private boolean isScan;
	
	@Value("${camel.router.scan.interval:1000}")
	@Min(1000)
	private int scanInterval;
	
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getConf() {
		return conf;
	}
	public void setConf(String conf) {
		this.conf = conf;
	}
	public String getRouter() {
		return router;
	}
	public void setRouter(String router) {
		this.router = router;
	}
	public boolean isScan() {
		return isScan;
	}
	public void setScan(boolean isScan) {
		this.isScan = isScan;
	}
	public int getScanInterval() {
		return scanInterval;
	}
	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	}
}
