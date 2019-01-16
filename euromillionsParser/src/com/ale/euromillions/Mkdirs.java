package com.ale.euromillions;

import java.io.File;

public class Mkdirs {

	public static void main(String[] args) {
		String toCreate = "C:\\DONNEES\\HubeOne\\DMPR\\recette_RV3\\FUM-BORD\\BORD\\0000030001\\out\\v4";
		File f = new File(toCreate);
		
		if (!f.exists()) {
			System.out.println("Creating folder '" + toCreate + "'...");
			f.mkdirs();
			System.out.println("...OK!");
		}
	}

}
