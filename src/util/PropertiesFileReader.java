package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	
	private Properties prop;
	
	public PropertiesFileReader(String file) {
		File filePath = new File(file);
		FileInputStream input;
		try {
			input = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(input); 
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	 }
	 
	public String getValue(String key) {
		return prop.getProperty(key);
		}
	 }