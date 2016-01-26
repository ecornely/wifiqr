package wifiqr.demo;

import wifiqr.WifiQR;

/**
 * Information about the WIFI: format found :
 * https://github.com/zxing/zxing/commit/58fefb095c9564dcbd0dbf4d2f1f90cffede10c8
 * */
public class DemoUsage {

	public static void main(String[] args) throws Exception{
		String wifiInfos = "WIFI:S:mynetwork;T:WPA;P:mypassword;;";
		String outputFilename="out/mynetwork.png";
		WifiQR.geneateQrCode(wifiInfos, true, outputFilename);
		
	}
	
	

}
