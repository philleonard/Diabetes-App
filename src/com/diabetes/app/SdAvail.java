package com.diabetes.app;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class SdAvail {

	public boolean check() {
		
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state))
		    return true;
		else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
		    return false;
		else 
		    return false;
	}

	public boolean injectionDataCheck() {
		
	File sdcard = new File(Environment.getExternalStorageDirectory().toString());
	if (sdcard.isDirectory()) {
		File diabetes_folder = new File(sdcard + "/.Diabetes_Health_Tracker_Data");
		if (!diabetes_folder.isDirectory()) {
			diabetes_folder.mkdir();
		}
		File injectionData = new File(diabetes_folder + "/injection_data.csv");
		if (!injectionData.isFile()) {
			try {
				injectionData.createNewFile();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	return false;
	}
	
}
