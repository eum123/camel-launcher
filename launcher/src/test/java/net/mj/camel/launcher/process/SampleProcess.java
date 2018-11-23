package net.mj.camel.launcher.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleProcess implements Processor {
    private static final Logger log = LoggerFactory.getLogger(SampleProcess.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("hi ~~~~~~~~~~");
    }
}
