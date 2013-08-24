package org.viaggi.xmltv.grabber.tvguide;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.viaggi.xmltv.grabber.objects.Channel;
import org.viaggi.xmltv.grabber.objects.Guide;
import org.viaggi.xmltv.grabber.objects.Program;
import org.xml.sax.SAXException;

public class GuideWriter {

	static Logger log = Logger.getLogger(GuideWriter.class.getName());

	private Guide guide;
	
	public GuideWriter(Guide newGuide) {
		guide = newGuide;
	}
		
	public void writeData(File outputFile) throws IOException, ParseException, SAXException {
				
		StringBuilder sb = new StringBuilder();
		log.info("Starting Writing XML Data");
		
		sb.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>").append("\n");
		sb.append("<!DOCTYPE tv SYSTEM \"xmltv.dtd\">").append("\n");
		sb.append("<tv source-info-url=\"http://www.schedulesdirect.org/\" source-info-name=\"Schedules Direct\" generator-info-name=\"XMLTV/$Id: tv_grab_na_dd.in,v 1.70 2008/03/03 15:21:41 rmeden Exp $\" generator-info-url=\"http://www.xmltv.org/\">").append("\n");
		
		sb.append(getChannelXML(guide.getChannels())).append("\n");
		sb.append(getProgramXML(guide.getPrograms())).append("\n");
		
		sb.append("</tv>");
		
		FileUtils.writeStringToFile(outputFile, sb.toString());

		log.info("Done Writing XML Data");

	}
	
	private boolean validateXML(File file2validate) throws SAXException, IOException {
	
		SchemaFactory schemaFactory = SchemaFactory
			    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("/home/cviaggi/Downloads/xmltv-0.5.63/xmltv.dtd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(file2validate));
			
			return true;
	}
	
	private String getChannelXML(HashSet<Channel> channels) {
		
		StringBuilder sb = new StringBuilder();
		
		for(Channel channel : channels) {
			if(sb.length() > 0) {
				sb.append("\n");
			}
			
			sb.append("<channel id=\"").append(channel.getId()).append("\">").append("\n");
			
			for(String name : channel.getDisplayNames()) {
				sb.append("<display-name>").append(name).append("</display-name>").append("\n");
			}
			
			sb.append("<icon src=\"").append(channel.getIcon()).append("\" />").append("\n");
			sb.append("</channel>");
		}
		
		return sb.toString();
	}

	private String getProgramXML(HashSet<Program> programs) throws ParseException {
				
		StringBuilder sb = new StringBuilder();
		
		for(Program program : programs) {
			if(sb.length() > 0) {
				sb.append("\n");
			}
			
			sb.append("<programme start=\"").append(program.getStartTime()).append("\" stop=\"").append(program.getEndTime()).append("\" channel=\"").append(program.getChannel().getId()).append("\"");			
			
			sb.append(">").append("\n");
			sb.append("<title lang=\"en\"><![CDATA[").append(program.getProgramTitle()).append("]]></title>").append("\n");
			sb.append("<url><![CDATA[").append(program.getUrl().toString()).append("]]></url>").append("\n");
			sb.append("<sub-title>").append(program.getSubTitle()).append("</sub-title>").append("\n");
			sb.append("<desc>").append(program.getDescription()).append("</desc>").append("\n");
			sb.append("<icon>").append(program.getIcon()).append("</icon>").append("\n");
			
			if(program.isNew()) {
				sb.append("<new />").append("\n");
			} else if(program.isRepeat()) {
				sb.append("<previously-shown />");
			}

			if(program.isCC()) {
				sb.append("<subtitles type=\"onscreen\" />").append("\n");
			}
			
			for(String cat : program.getAllCategories()) {
				sb.append("<category lang=\"en\">").append(cat).append("</category>").append("\n");				
			}
			sb.append("<episode-num system=\"dd_progid\">").append(program.getProgramId()).append("</episode-num>").append("\n");
			
			if(program.getRating().length() > 0) {
				sb.append("<rating system=\"VCHIP\">").append("\n");
				sb.append("<value>").append(program.getRating()).append("</value>").append("\n");
				sb.append("</rating>").append("\n");
			}
			
			sb.append("</programme>");
		}
		
		return sb.toString();
	}

}
