package org.viaggi.xmltv.grabber.tvguide.objects;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.viaggi.xmltv.grabber.objects.Program;

public class TVProgram implements Program {

	private String programTitle;
	private String category;
	private String blockCount;
	private String subCategory;
	private String sourceType;
	private String attributes;
	private String programId;
	private String sourceId;
	private String startTime;
	private String duration;
	private String objectId;
	private String rating;
	private String description;
	private String subTitle;
	private String icon;
	
	private URI url;
	private TVChannel channel;
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#isNew()
	 */
	public boolean isNew() {
		
		int value = Integer.parseInt(attributes);
		
		return ((value & 4) == 4);

	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#isRepeat()
	 */
	public boolean isRepeat() {
		
		int value = Integer.parseInt(attributes);
		
		return ((value & 2) == 2);

	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#isCC()
	 */
	public boolean isCC() {
		
		int value = Integer.parseInt(attributes);
		
		return ((value & 8) == 8);

	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getAllCategories()
	 */
	public String[] getAllCategories() {
		
		List<String> cats = new ArrayList<String>();
		int categoryInteger = Integer.parseInt(category);
		
		if((categoryInteger & 256) == 256) {
			cats.add("News");
		}
		if((categoryInteger & 64) == 64) {
			cats.add("Movies");
		}
		if((categoryInteger & 2) == 2) {
			cats.add("Family");
		}
		if((categoryInteger & 1024) == 1024) {
			cats.add("Sports");
		}
				
		return cats.toArray(new String[cats.size()]);
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getEndTime()
	 */
	public String getEndTime() throws ParseException {
		//TODO::Fix me
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date date = format.parse(startTime);
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		
		int dur = Integer.parseInt(duration);
		
		cal.add(Calendar.MINUTE, dur);

		return format.format(cal.getTime());
	}
		
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getProgramTitle()
	 */
	public String getProgramTitle() {
		return programTitle.replace("&", "&amp;");
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setProgramTitle(java.lang.String)
	 */
	public void setProgramTitle(String programTitle) {
		this.programTitle = programTitle;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getCategory()
	 */
	public String getCategory() {
		return category;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setCategory(java.lang.String)
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getBlockCount()
	 */
	public String getBlockCount() {
		return blockCount;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setBlockCount(java.lang.String)
	 */
	public void setBlockCount(String blockCount) {
		this.blockCount = blockCount;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getSubCategory()
	 */
	public String getSubCategory() {
		return subCategory;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setSubCategory(java.lang.String)
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getSourceType()
	 */
	public String getSourceType() {
		return sourceType;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setSourceType(java.lang.String)
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getAttributes()
	 */
	public String getAttributes() {
		return attributes;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setAttributes(java.lang.String)
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getProgramId()
	 */
	public String getProgramId() {
		return programId;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setProgramId(java.lang.String)
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getSourceId()
	 */
	public String getSourceId() {
		return sourceId;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setSourceId(java.lang.String)
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getStartTime()
	 */
	public String getStartTime() {
		return startTime;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setStartTime(java.lang.String)
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getDuration()
	 */
	public String getDuration() {
		return duration;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setDuration(java.lang.String)
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getObjectId()
	 */
	public String getObjectId() {
		return objectId;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setObjectId(java.lang.String)
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getRating()
	 */
	public String getRating() {
		return rating;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setRating(java.lang.String)
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#hashCode()
	 */
	public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
        		// if deriving: appendSuper(super.hashCode()).
        append(programId).
        append(sourceId).
        append(startTime).
        toHashCode();
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof TVProgram))
            return false;

        TVProgram rhs = (TVProgram) obj;
	        return new EqualsBuilder().
	            // if deriving: appendSuper(super.equals(obj)).
	            append(programId, rhs.programId).
	            append(sourceId, rhs.sourceId).
	            append(startTime, rhs.startTime).
	            isEquals();
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getChannel()
	 */
	public TVChannel getChannel() {
		return channel;
	}
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setChannel(org.viaggi.xmltv.grabber.tvguide.xml.TVChannel)
	 */
	public void setChannel(TVChannel channel) {
		this.channel = channel;
	}
	
	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#printString()
	 */
	public String printString() {
		
		  return new ToStringBuilder(this).
			       append("attributes", attributes).
			       append("blockCount", blockCount).
			       append("category", category).
			       append("duration", duration).
			       append("objectId", objectId).
			       append("programId", programId).
			       append("programTitle", programTitle).
			       append("rating", rating).
			       append("sourceId", sourceId).
			       append("sourceType", sourceType).
			       append("startTime", startTime).
			       append("subCategory", subCategory).
			       append("description", description).
			       append("subTitle", subTitle).
			       append("icon", icon).
			       append("url", url.toString()).
			       append("channel", channel.printString()).
			       toString();
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getUrl()
	 */
	public URI getUrl() {
		return url;
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setUrl(java.net.URI)
	 */
	public void setUrl(URI url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getSubTitle()
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setSubTitle(java.lang.String)
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#getIcon()
	 */
	public String getIcon() {
		return icon;
	}

	/* (non-Javadoc)
	 * @see org.viaggi.xmltv.grabber.tvguide.xml.Program#setIcon(java.lang.String)
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
