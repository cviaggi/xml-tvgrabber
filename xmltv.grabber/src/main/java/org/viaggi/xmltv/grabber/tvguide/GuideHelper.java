package org.viaggi.xmltv.grabber.tvguide;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.viaggi.xmltv.grabber.tvguide.parsing.GuideOptions;

public class GuideHelper {

	static Logger log = Logger.getLogger(GuideHelper.class.getName());

	private static URI buildUri(String zipcode) throws URISyntaxException {
		
		//TODO::Move all these to constants
 		URIBuilder builder = new URIBuilder();
 		builder.setScheme(Constants.URL_SCHEME).setHost(Constants.URL_HOST).setPath(Constants.URL_PATH_REGION_RESOLVER)
 		    .setParameter("zipcode", zipcode); 	//zipcode to find
 		return builder.build();
	}
	
	public static void printHelp(String zipcode) throws ClientProtocolException, IOException, URISyntaxException {
		List<GuideOptions> options = getOptions(zipcode);
		printOptions(options, zipcode);		
	}
	
	public static void printOptions(List<GuideOptions> options, String zipcode) {
		
    	System.out.println("GRABBER                         Guide Region Ids");
    	System.out.println("");
    	System.out.println("DESCRIPTION");
    	System.out.println("       Below is a list of regions and their identifying information for you to choose from.");
    	System.out.println("       These are all the options found for the zipcode given and are valid ids for the property file.");
    	System.out.println("       To use one of these items, copy the region id into the property file for the value of the key ");
    	System.out.println("       `guide.id.region`.");
    	System.out.println("");
    	System.out.println("ZIPCODE: " + zipcode);
    	System.out.println("");
    	System.out.println("GUIDE REGIONS");
    	System.out.println("-------------------------------------------------------------------------------------");
    	System.out.println("guide.id.region     service type        provider name");
    	System.out.println("-------------------------------------------------------------------------------------");
    	
    	for(GuideOptions option : options) {
        	System.out.println(option.getTableLine());
    	}
    	System.out.println("-------------------------------------------------------------------------------------");

	}
	
	public static List<GuideOptions> getOptions(String zipcode) throws ClientProtocolException, IOException, URISyntaxException {
		
 		DefaultHttpClient httpclient = new DefaultHttpClient();
 		HttpGet httpGet = new HttpGet(buildUri(zipcode));

 		HttpResponse response = httpclient.execute(httpGet);
 		List<GuideOptions> options = null;
 		
 		try {
 			
 		    HttpEntity entity = response.getEntity();

 		    String guideChoices = EntityUtils.toString(entity);
 		    options = parseOptions(guideChoices);
 		    
 		    EntityUtils.consume(entity);
 		    
 		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
 		    httpGet.releaseConnection();
 		}
 		
 		return options;
	}
	
	public static List<GuideOptions> parseOptions(String payload) throws Exception {
		
		String lines[] = payload.split("\\r?\\n");
		
		if(lines.length == 0) {
			throw new Exception("Parsing failed, no lines returned");
		}
		
		List<GuideOptions> options = new ArrayList<GuideOptions>();
		
		for(int i = 1; i < lines.length; i++) {
			
			GuideOptions option = new GuideOptions(lines[i]);
						
			log.debug(option.toString());
			
			options.add(option);

		}
		
		return options;
	}
}
