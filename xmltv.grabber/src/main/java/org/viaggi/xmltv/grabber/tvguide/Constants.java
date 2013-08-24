package org.viaggi.xmltv.grabber.tvguide;

/**
 * Holds constants that are used for the query parameters
 * 
 * @author cviaggi
 *
 */
public class Constants {

	public static final String URL_SCHEME = "http";
	public static final String URL_HOST = "www.tvguide.com";
	public static final String URL_PATH = "/listings/data/ajaxcache.ashx";
	public static final String URL_PATH_REGION_RESOLVER = "/listings/data/localizebyzip.ashx";
	
	public static final String QUERY_PARAM_REGION_IDENTIFIER = "20552.268435456";   //srvid=20552.268435456  seems to identify the region, this should be "San Francisco Bay Area Broadcast (San Fran-Oak-Sj) [OTA Broadcast]	OTA"

	public static final String QUERY_PARAM_CHANNEL_ROWS = "1";	//??? number of channels per row
	public static final String QUERY_PARAM_FAVORITE_CHANNELS_ONLY = "false"; //??? display only favorite channels
	
	public static final String QUERY_PARAM_MAGIC_SETTING = "0";  //??? turn magic on/off
	public static final String QUERY_PARAM_MAGIC_TYPE_SETTING = "0"; //??? magic type
	
	public static final String QUERY_PARAM_ZIP_CODE = "94041"; //zipcode
	public static final String QUERY_PARAM_BLOCK = "5"; //??? block channels in groups of 5

	public static final String TWO_HOUR_TIME_BLOCK_IN_MINUTES = "120"; //2 hours as specified in minutes
	
	public static final String QUERY_PARAM_QR = "Qr";
	public static final String QUERY_PARAM_TVOID = "tvoid";
	public static final String QUERY_PARAM_FLAGS = "flags=C";
	public static final String QUERY_PATH_VERSION = "v2=1";
	public static final String URL_PATH_PROGRAM_INFO = "/listings/data/closeup.aspx";
	
	
	public static String PROPERTY_DEFAULT_FILE_NAME = "guide.properties";
	public static String PROPERTY_OUTPUT_DIRECTORY = "guide.output.dir";
	public static String PROPERTY_OUTPUT_DIRECTORY_DEFAULT = "/tmp";
	public static String PROPERTY_OUTPUT_FILE = "guide.output.file";
	public static String PROPERTY_OUTPUT_FILE_DEFAULT = "tv-guide.xml";
	public static String PROPERTY_PARSE_HOURS = "guide.scraper.retrieval.hours";
	public static String PROPERTY_PARSE_HOURS_DEFAULT = "24";

	public static String PROPERTY_REGION_ID = "guide.id.region";
	public static String PROPERTY_REGION_ID_DEFAULT = Constants.QUERY_PARAM_REGION_IDENTIFIER;
	public static String PROPERTY_ZIPCODE = "guide.id.zipcode";
	public static String PROPERTY_ZIPCODE_DEFAULT = Constants.QUERY_PARAM_ZIP_CODE;

}
