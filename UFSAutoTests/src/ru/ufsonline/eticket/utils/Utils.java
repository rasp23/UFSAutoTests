package ru.ufsonline.eticket.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	public static void sleep(long millis) {
		sleep(millis, null);
	}

	public static void sleep(long millis, String info) {
		long minimum = 3000L;
		long current = 0L;
		long n = millis / minimum;
		long modulo = millis % minimum;
		String str = "";
		String output = "Sleep: POS PER%";
		try {
			System.out.println("Sleep for " + millis + " ms."
					+ (info != null ? " FOR: " + info : ""));
			if (millis > minimum) {
				str = "[";
				for (int i = 0; i < n; i++)
					str += "-";
				if (modulo > 0)
					str += "-";
				str += "]";
				System.out.println(output.replace("POS", str).replace("PER",
						"0"));
				while (current + modulo < millis) {
					Thread.sleep(minimum);
					current += minimum;
					str = str.replaceFirst("-", "|");
					System.out.println(output.replace("POS", str).replace(
							"PER", String.valueOf(current * 100 / millis)));
				}
			}
			Thread.sleep(modulo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getList(String data, String separator) {
	    if (data.equals(""))
	        return new ArrayList<String>();
		String[] str = data.split(separator, -1);
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			lst.add(str[i].trim());
		}
		return lst;
	}

	public static List<String> getList(String data) {
		if ("null".equalsIgnoreCase(data) || "".equalsIgnoreCase(data)) {
			return new ArrayList<String>();
		}
		return getList(data, ",");
	}
}