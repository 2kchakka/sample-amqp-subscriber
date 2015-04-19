/**
 * 
 */
package org._2kchakka.amqp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author codemonkey84
 *
 */
public class SubscriberUtil {

	private static Properties properties = new Properties();

	static {
		InputStream inputStream = SubscriberUtil.class.getClassLoader()
				.getResourceAsStream("application.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final static String readFromResource(String key) {
		return properties.getProperty(key);
	}
}
