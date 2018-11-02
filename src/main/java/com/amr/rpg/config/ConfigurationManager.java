package com.amr.rpg.config;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;


/**
 * @author aeldemerdash
 *
 */
public class ConfigurationManager {
	private static final String configFile = "config.properties";
	private static Properties properties = new Properties();

	static {
		loadConfig();
	}

	private static void loadConfig() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(rootPath);
		try (InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(configFile)) {
			properties.load(systemResourceAsStream);
		} catch (Exception e) {
			System.out.println("not found config.properties");
			e.printStackTrace();
		}

	}

	public static Optional<String> getProperty(String key) {
		if(properties !=null)
			return Optional.of(properties.getProperty(key));
		else 
			return Optional.empty();
	}
}
