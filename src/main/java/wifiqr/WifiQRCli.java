package wifiqr;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class WifiQRCli {

	public static void main(String[] args) {
		Options o = new Options();
		o.addOption(Option.builder("s").hasArg().required().desc("* Network SSID").argName("ssid").longOpt("ssid").build());
		o.addOption(Option.builder("p").hasArg().required().desc("* WPA Password").argName("password").longOpt("password").build());
		o.addOption(Option.builder().type(Boolean.class).desc("Disable logo").argName("nologo").longOpt("nologo").build());
		o.addOption(Option.builder("o").hasArg().type(File.class).desc("Output file (default is output.png)").argName("output").longOpt("output").build());
		
		DefaultParser parser = new DefaultParser();
		try {
			CommandLine cli = parser.parse(o, args);
			String ssid = cli.getOptionValue("ssid");
			String password = cli.getOptionValue("password");
			String wifiInfos = String.format("WIFI:S:%s;T:WPA;P:%s;;", ssid, password);
			File out;
			if(cli.hasOption("output")){
				out = (File) cli.getParsedOptionValue("output");
			}else{
				out = new File("output.png");
			}
			boolean logo = !cli.hasOption("nologo");
			System.out.printf("Generating qr code with info '%s' into %s with logo : %s%n", wifiInfos, out, logo);
			try {
				WifiQR.geneateQrCode(wifiInfos, logo, out.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ParseException e) {
			HelpFormatter help = new HelpFormatter();
			help.printHelp("wifi.bat", o);
			System.out.println("* are required arguments");
		}
	}

}
