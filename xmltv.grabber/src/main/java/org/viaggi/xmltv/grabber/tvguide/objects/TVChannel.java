package org.viaggi.xmltv.grabber.tvguide.objects;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.viaggi.xmltv.grabber.objects.Channel;

public class TVChannel implements Channel {

	private String channelNumber;
	private String callLetters;
	private String networkId;
	
	public TVChannel(String channel, String letters, String network) {
		this.channelNumber = channel;
		this.callLetters = letters;
		this.networkId = network;
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Channel#getId()
	 */
	public String getId() {
		return ID_PREFIX + "." + callLetters + "." + channelNumber;
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Channel#getIcon()
	 */
	public String getIcon() {
		
		//TODO::Get a icon for each channel
		return "file://C:\\Perl\\site/share/xmltv/icons/KERA.gif";
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Channel#getDisplayNames()
	 */
	public String[] getDisplayNames() {
		String[] displayNames = new String[5];
		
		//Go from specific to less so (notice the index is descending)
		displayNames[4] = channelNumber;
		displayNames[3] = callLetters;
		displayNames[2] = channelNumber + " " + callLetters;
		displayNames[1] = channelNumber + " " + networkId;
		displayNames[0] = channelNumber + " " + callLetters + " " + networkId;
		
		return displayNames;
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Channel#printString()
	 */
	public String printString() {
		
		  return new ToStringBuilder(this).
			       append("callLetters", callLetters).
			       append("channelNumber", channelNumber).
			       append("networkId", networkId).
			       toString();
	}


	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Channel#hashCode()
	 */
	public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
        		// if deriving: appendSuper(super.hashCode()).
        append(channelNumber).
        append(networkId).
        append(callLetters).
        toHashCode();
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Channel#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof TVChannel))
            return false;

        TVChannel rhs = (TVChannel) obj;
	        return new EqualsBuilder().
	            // if deriving: appendSuper(super.equals(obj)).
	            append(channelNumber, rhs.channelNumber).
	            append(networkId, rhs.networkId).
	            append(callLetters, rhs.callLetters).
	            isEquals();
	}

}
