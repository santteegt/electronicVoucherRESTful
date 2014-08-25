package com.buzz.electronicvoucher.util;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

public class PropertiesHandler {

	private PropertiesConfiguration defaults = new PropertiesConfiguration();
    
    private static final String EXTENSION = ".properties";
    
    private static PropertiesHandler instance = null;
    
    private static String propertyName = "";
    
    private static final String FOLDER_PROPERTY ="buzz.properties.folder";
    
    private static final Logger log = Logger.getLogger(PropertiesHandler.class);
    
    protected final FileChangedReloadingStrategy reloadingStrategy =
    		new FileChangedReloadingStrategy();
    
    public static Configuration getInstance(String name) {
    	if(instance == null || !propertyName.equals(name)) {
    		instance = new PropertiesHandler(name);
    	}
    	return instance.getConf();
    }
    
    private PropertiesHandler(String name) {
    	
    	String propertyFileDir = System.getProperty(FOLDER_PROPERTY);
    	log.info("Directorio properties: => " + propertyFileDir);
    	File file = new File(propertyFileDir, name + EXTENSION);
    	

        // Cargar valores por default del classloader
        try {
        	defaults.setReloadingStrategy(reloadingStrategy);
            defaults.setThrowExceptionOnMissing(true);
            defaults.setDelimiterParsingDisabled(true);
            defaults.setFile(file);
    		if (file.exists()) {
    			defaults.load();
    		} else {
    			defaults.load(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(name + EXTENSION));
    		}  		
        } catch (ConfigurationException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    public Configuration getConf() {
        return defaults;
    }

}
