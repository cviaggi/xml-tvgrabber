package org.viaggi.xmltv.grabber.tvguide.parsing;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.viaggi.xmltv.grabber.tvguide.Constants;
import org.viaggi.xmltv.grabber.tvguide.objects.TVChannel;
import org.viaggi.xmltv.grabber.tvguide.objects.TVProgram;

public class GuideParser {

	  static Logger log = Logger.getLogger(GuideParser.class.getName());

	private String timezoneOffset;
	
	private String payload;
	private GuideHeader guideHeader;
	
	private HashSet<TVProgram> programs;
	private HashSet<TVChannel> channels;
	
	public GuideParser() {
		timezoneOffset = "-0800";
	}
	
	public String getPayload() {
		return payload;
	}
	
	public void parse(String payload) throws Exception {
		this.payload = payload;
		
		String lines[] = payload.split("\\r?\\n");
		
		if(lines.length == 0) {
			throw new Exception("Parsing failed, no lines returned");
		}
		
		guideHeader = new GuideHeader(lines[0]);
		programs = new HashSet<TVProgram>();
		channels = new HashSet<TVChannel>();
				
		for(int i = 1; i < lines.length; i++) {
			GuideLine gl = new GuideLine(lines[i]);
			TVProgram program = gl.getProgram();

			//Setting the URL element
			program.setUrl(buildUri(program.getProgramId(), program.getObjectId()));
			
			log.debug(program.printString());
			
			channels.add(program.getChannel());
			programs.add(program);
		}
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(GuideParser.class).append("@").append(System.identityHashCode(this)).append("\n");
		sb.append("[").append(guideHeader.toString()).append("]").append("\n");
		
		for(TVProgram line : programs) {
			sb.append(line.printString()).append("\n");
		}

		return sb.toString();
	}
	
	public HashSet<TVChannel> getChannels() {
		return channels;
		
	}
	
	public void getPrograms(HashSet<TVProgram> programs) {		
		programs.addAll(this.programs);		
	}
	
	public HashSet<TVProgram> getPrograms() {
		return this.programs;
	}

	private URI buildUri(String programId, String tvOid) throws URISyntaxException {
		
 		URIBuilder builder = new URIBuilder();
 		builder.setScheme(Constants.URL_SCHEME).setHost(Constants.URL_HOST).setPath(Constants.URL_PATH_PROGRAM_INFO)
 		    .setParameter(Constants.QUERY_PARAM_QR, programId) 	
 		    .setParameter(Constants.QUERY_PARAM_TVOID, tvOid);
 		return builder.build();

	}

}
