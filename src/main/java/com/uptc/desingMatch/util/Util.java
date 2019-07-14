package com.uptc.desingMatch.util;

import java.io.File;

public class Util {
	
	public static void createImg(String path) {
		File index = new File(path);
		if (!index.exists()) {
		    index.mkdir();
		} 
	}

}
