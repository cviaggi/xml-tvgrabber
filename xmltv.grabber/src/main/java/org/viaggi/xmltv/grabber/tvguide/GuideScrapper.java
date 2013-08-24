package org.viaggi.xmltv.grabber.tvguide;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.viaggi.xmltv.grabber.objects.Guide;
import org.viaggi.xmltv.grabber.objects.Program;
import org.viaggi.xmltv.grabber.tvguide.DateHelper.TIME_INCREMENT;
import org.viaggi.xmltv.grabber.tvguide.objects.TVGuide;
import org.viaggi.xmltv.grabber.tvguide.options.Format;
import org.viaggi.xmltv.grabber.tvguide.options.Genre;
import org.viaggi.xmltv.grabber.tvguide.options.HighDefinitionFlag;
import org.viaggi.xmltv.grabber.tvguide.options.Music;
import org.viaggi.xmltv.grabber.tvguide.options.PayPerView;
import org.viaggi.xmltv.grabber.tvguide.options.TwentFourHourProgramming;
import org.viaggi.xmltv.grabber.tvguide.parsing.GuideDetail;
import org.xml.sax.SAXException;

public class GuideScrapper {
	
	static Logger log = Logger.getLogger(GuideScrapper.class.getName());
	
	private Guide guide;
	
	public GuideScrapper() {
		guide = new TVGuide();
	}

	private URI buildUri(String year, String month, String day, String hour) throws URISyntaxException {
		
		//TODO::Move all these to constants
 		URIBuilder builder = new URIBuilder();
 		builder.setScheme(Constants.URL_SCHEME).setHost(Constants.URL_HOST).setPath(Constants.URL_PATH)
 		    .setParameter("fmt", Format.NORMAL.getValue()) 	//format of the output
 		    .setParameter("srvid", Constants.QUERY_PARAM_REGION_IDENTIFIER)  //set programming region to return
 		    .setParameter("gridmins", Constants.TWO_HOUR_TIME_BLOCK_IN_MINUTES) //2 hours in minutes
 		    .setParameter("gridyr", year)				//grid year
		    .setParameter("gridmo", month)				//grid month
 		    .setParameter("griddy", day)				//grid day
 		    .setParameter("gridhr", hour)				//grid hour
 		    .setParameter("chanrow", Constants.QUERY_PARAM_CHANNEL_ROWS)
 		    .setParameter("genre", Genre.ALL_CHANNELS.getValue()) //genre to output
 		    .setParameter("favchan", Constants.QUERY_PARAM_FAVORITE_CHANNELS_ONLY)
 		    .setParameter("magic", Constants.QUERY_PARAM_MAGIC_SETTING)
		    .setParameter("magictype", Constants.QUERY_PARAM_MAGIC_TYPE_SETTING)
 		    .setParameter("zip", Constants.QUERY_PARAM_ZIP_OUTPUT)
 		    .setParameter("music", Music.DISPLAY.getValue()) //display music channels 
 		    .setParameter("ppv", PayPerView.DO_NOT_DISPLAY.getValue()) //display ppv channels
 		    .setParameter("24hr", TwentFourHourProgramming.DISPLAY.getValue()) //display 24 hour programming channels 
 		    .setParameter("HDTVOnly", HighDefinitionFlag.ALL_CHANNELS.getValue()) //HDTV channels only
 		    .setParameter("block", Constants.QUERY_PARAM_BLOCK);
 		return builder.build();

	}

	public void scrapeHoursAhead(int totalHoursAhead, File outputFile) throws ClientProtocolException, URISyntaxException, IOException, java.text.ParseException, SAXException {

		int totalIncrements = totalHoursAhead / TIME_INCREMENT.TWO_HOURS.getValue();

		//Get a date object
		DateHelper dh = new DateHelper();
		int i;
		
		log.info("Starting Retrieval of Raw Data");
		System.out.print("M");
		
		//Iterate a total depending upon the time increment
		for(i = 0 ; i < totalIncrements; i++) {
			
			System.out.print(".");
			scrape(dh.getYear(), dh.getMonth(), dh.getDay(), dh.getHour());
			dh.incrementDate(TIME_INCREMENT.TWO_HOURS);
		}
		log.info("Raw Data Done `" + i + "` Passes Made");
		System.out.println("");
		
		scrapeProgramInfo();
		
		GuideWriter gw = new GuideWriter(guide);
		gw.writeData(outputFile);
				
	}
	
	private void scrapeProgramInfo() throws ClientProtocolException, IOException {
		
		HashSet<Program> programs = guide.getPrograms();
		int total = 0;
		
		log.info("Starting Retrieval of Detail Data");
		System.out.print("D");
		
		for(Program program : programs) {
			total++;
			System.out.print(".");
			
	 		if(log.isDebugEnabled()) {
	 			log.debug("Detail URL: " + program.getUrl().toString());
	 		}
	 		
	 		DefaultHttpClient httpclient = new DefaultHttpClient();
	 		HttpGet httpGet = new HttpGet(program.getUrl());

	 		HttpResponse response = httpclient.execute(httpGet);

	 		try {
	 			
	 		    HttpEntity entity = response.getEntity();
	 		    
	 		    GuideDetail detail = new GuideDetail(EntityUtils.toString(entity));
	 		    
	 		    program.setDescription(detail.get(GuideDetail.FIELD_NAME.EPISODE_DESCRIPTION));
	 		    program.setSubTitle(detail.getSubTitle());
	 		    program.setIcon(detail.getIcon());
	 		    
	 		    EntityUtils.consume(entity);
	 		    
	 		} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	 		    httpGet.releaseConnection();
	 		}

		}
		log.info("Detail Data Done `" + total + "` Passes Made");
		
	}
	
	public void scrape(String year, String month, String day, String hour) throws URISyntaxException, ClientProtocolException, IOException {
 			
 		URI uri = buildUri(year, month, day, hour);
 		
 		if(log.isDebugEnabled()) {
 			log.debug("URL: " + uri.toString());
 		}
 		
 		DefaultHttpClient httpclient = new DefaultHttpClient();
 		HttpGet httpGet = new HttpGet(uri);
 		
 		HttpResponse response = httpclient.execute(httpGet);

 		try {
 			
 		    HttpEntity entity = response.getEntity();

 		    String rawData = EntityUtils.toString(entity);
 		    
 		    guide.addRawData(rawData);
 		  
 		    EntityUtils.consume(entity);
 		    
 		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
 		    httpGet.releaseConnection();
 		}
	}
}
