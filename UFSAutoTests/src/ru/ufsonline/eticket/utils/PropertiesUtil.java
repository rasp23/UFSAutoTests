package ru.ufsonline.eticket.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SuppressWarnings("serial")
public class PropertiesUtil extends Properties {
	
	private InputStream in = null;
	
	public PropertiesUtil(String pathToProperties) {				
		try {
			in = new FileInputStream(new File(pathToProperties));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
		
		try {
			this.load(in);
		} catch (IOException e) {
			e.printStackTrace();			
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();				
			}
		}	
	}
}
