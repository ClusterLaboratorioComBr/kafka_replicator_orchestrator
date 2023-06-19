package br.com.clusterlab.kafkaconfluentreplicatororchestrator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class AppProperties {
    static Logger logger = LoggerFactory.getLogger(ProjectConfig.class);
    public static String credentials ;
    @Value("${br.com.clusterlab.app.credentials}")
    public void setCredentials(String credentials) {
        try {
            AppProperties.credentials = credentials;
        }
        catch (Exception e){
            logger.error("Fail to load properties, " + e);
        }

    }
}

