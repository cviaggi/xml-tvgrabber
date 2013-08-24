package org.viaggi.xmltv.grabber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.viaggi.xmltv.grabber.tvguide.Constants;
import org.viaggi.xmltv.grabber.tvguide.GuideHelper;
import org.viaggi.xmltv.grabber.tvguide.GuideScrapper;

/**
 * Hello world!
 *
 */
public class Grabber extends Constants
{
	static Logger log = Logger.getLogger(Grabber.class.getName());

	private static Properties props;
	
	private final String USER_AGENT = "Mozilla/5.0";
		
    public static void main( String[] args ) throws FileNotFoundException, IOException, URISyntaxException
    {

    	//If help is asked for then print help and exit
    	if((args != null) && (args.length > 0)) {
    		if(args[0].contains("-help")) {
    			printHelp();
    			System.exit(0);
    		}
    		if(args[0].contains("--help")) {
    			printHelp();
    			System.exit(0);
    		}
    		if(args[0].contains("-h")) {
    			printHelp();
    			System.exit(0);
    		}
    		if(args[0].contains("?")) {
    			printHelp();
    			System.exit(0);
    		}
    	}

    	//If the -l is used then print a list of region ids and stop
    	if((args != null) && (args.length > 0)) {
    		if(args[0].contains("-l")) {
    			Validate.notNull(args[1], "Using the -l requires a zipcode after the argument");
    			Validate.notEmpty(args[1], "Using the -l requires a zipcode after the argument");
    			
    			GuideHelper.printHelp(args[1]);
    			System.exit(0);
    		}
    	}

    	//Assume we are parsing, this is the default
    	StopWatch watch = new StopWatch();
    	watch.start();
    	
    	props = new Properties();
    	
    	//Read the property file if passed in
    	if((args != null) && (args.length > 0)){
        
	    	File file = new File(args[0]);
	    	Validate.isTrue(file.exists(), "The file `" + args[0] + "` could not be located");
	    	props.load(new FileInputStream(file));
    	} else {
    		
    		//If not passed in then search the classpath
    		Grabber grabber = new Grabber();
    		InputStream in = grabber.getClass().getClassLoader().getResourceAsStream(PROPERTY_DEFAULT_FILE_NAME);
    		
    		//If found in classpath use that, if not search local directory
    		if(in != null) {
    	    	props.load(in);
    		} else {
    			File file = new File(PROPERTY_DEFAULT_FILE_NAME);
    			
    			if(file.exists()) {
    		    	props.load(new FileInputStream(file));    				
    			} 
    		}
    	}
    	
    	//Collect all the properties
    	String outputDir = props.getProperty(PROPERTY_OUTPUT_DIRECTORY, PROPERTY_OUTPUT_DIRECTORY_DEFAULT);
    	String outputFile = props.getProperty(PROPERTY_OUTPUT_FILE, PROPERTY_OUTPUT_FILE_DEFAULT);
    	String parseHours = props.getProperty(PROPERTY_PARSE_HOURS, PROPERTY_PARSE_HOURS_DEFAULT);
    	String zipcode = props.getProperty(PROPERTY_ZIPCODE, PROPERTY_ZIPCODE_DEFAULT);
    	String regionid = props.getProperty(PROPERTY_REGION_ID, PROPERTY_REGION_ID_DEFAULT);
    	
    	log.info("Starting Download ---------------");
    	log.info("Parameters: ");
    	log.info("  Output Directory: " + outputDir);
    	log.info("  Output File Name: " + outputFile);
    	log.info("  Scrape Hours Ahead: " + parseHours);
    	log.info("  Region Id: " + regionid);
    	log.info("  Zipcode: " + zipcode);
    	
    	File dir = new File(outputDir);
    	if(!dir.exists()) {
    		Validate.isTrue(dir.mkdirs(), "Output directory `" + outputDir + "` does not exist and could not be created");
    	}
    	
    	File guide = new File(outputDir + File.separator + outputFile);    	
    	int parseHoursInt = Integer.parseInt(parseHours);
    	
        GuideScrapper gs = new GuideScrapper();
        
        try {
            gs.scrapeHoursAhead(parseHoursInt, guide, regionid, zipcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        watch.stop();
        
        log.info("Total Run Time: " + watch);
        
    }
    
    public static void printHelp() {
    	System.out.println("GRABBER                         GRABBER Manual");
    	System.out.println("");
    	System.out.println("NAME");
    	System.out.println("       Grabber -- scrape TV Guide data from the web");
    	System.out.println("");
    	System.out.println("SYNOPSIS");
    	System.out.println("       java -jar xmltv.grabberjar [file]");
    	System.out.println("       java -jar xmltv.grabberjar -l [zipcode]");
    	System.out.println("       java -jar xmltv.grabberjar -help");
    	System.out.println("");
    	System.out.println("DESCRIPTION");
    	System.out.println("       Grabber is a TV Guide data scraper that will scrape the information from the TVGuide.com website. Additional");
    	System.out.println("       configuration can be set in the file that you specify via the command line. If you don't specify a file then");
    	System.out.println("       a set of defaults will be used.");
    	System.out.println("");
    	System.out.println("       This program will attempt to generate a log4j log file in /tmp/logs directory.");
    	System.out.println("");
    	System.out.println("Property File");
    	System.out.println("       The file passed in is a standard property file of key/value pairs. The keys are listed below with their");
    	System.out.println("       defaults.");
    	System.out.println("");
    	System.out.println("       Property File property elements with their defaults include: ");
    	System.out.println("       -- guide.output.dir=/tmp");
    	System.out.println("       -- guide.output.file=tv-guide.xml");
    	System.out.println("       -- guide.scraper.retrieval.hours=24");
    	System.out.println("       -- guide.id.region=20552.268435456");
    	System.out.println("       -- guide.id.zipcode=94041");
    	System.out.println("");
    	System.out.println("Looking Up Region Id");
    	System.out.println("       The program offers a way to discover your region id by zipcode lookup. If you use the `-l` option and pass ");
    	System.out.println("       in a zipcode you the system will return a list of providers and region id that goes with each provider. Once ");
    	System.out.println("       you have this value, you can then use that in your property file.");
    	System.out.println("");
    	System.out.println("OPTIONS");
    	System.out.println("       file         the property file to use (path may be necessary)");
    	System.out.println("       -l zipcode   Forces the system to list the region ids availble by the zipcode");
    	System.out.println("");
    	System.out.println("------------------------------------------------------");
    }
 	
}
