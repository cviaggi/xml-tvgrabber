package org.viaggi.xmltv.grabber.tvguide.objects;

import java.util.HashSet;

import org.viaggi.xmltv.grabber.objects.Channel;
import org.viaggi.xmltv.grabber.objects.Guide;
import org.viaggi.xmltv.grabber.objects.Program;
import org.viaggi.xmltv.grabber.tvguide.parsing.GuideParser;

public class TVGuide implements Guide {

	private HashSet<Program> programs;
	private HashSet<Channel> channels;
	
	public TVGuide() {
		programs = new HashSet<Program>();
		channels = new HashSet<Channel>();
	}
	
	public void addRawData(String rawData) throws Exception {
		parseRawData(rawData);
	}

	public HashSet<Program> getPrograms() {
		return programs;
	}

	public HashSet<Channel> getChannels() {
		return channels;
	}

	private void parseRawData(String rawData) throws Exception {
		
		GuideParser gp = new GuideParser();
		gp.parse(rawData);
		
		programs.addAll(gp.getPrograms());
		channels.addAll(gp.getChannels());
	}
}
