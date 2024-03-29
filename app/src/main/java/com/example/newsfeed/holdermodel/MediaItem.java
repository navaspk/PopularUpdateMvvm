package com.example.newsfeed.holdermodel;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class MediaItem{

	@SerializedName("copyright")
	private String copyright;

	@SerializedName("media-metadata")
	private List<MediaMetadataItem> mediaMetadata;

	@SerializedName("subtype")
	private String subtype;

	@SerializedName("caption")
	private String caption;

	@SerializedName("type")
	private String type;

	@SerializedName("approved_for_syndication")
	private int approvedForSyndication;

	public void setCopyright(String copyright){
		this.copyright = copyright;
	}

	public String getCopyright(){
		return copyright;
	}

	public void setMediaMetadata(List<MediaMetadataItem> mediaMetadata){
		this.mediaMetadata = mediaMetadata;
	}

	public List<MediaMetadataItem> getMediaMetadata(){
		return mediaMetadata;
	}

	public void setSubtype(String subtype){
		this.subtype = subtype;
	}

	public String getSubtype(){
		return subtype;
	}

	public void setCaption(String caption){
		this.caption = caption;
	}

	public String getCaption(){
		return caption;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setApprovedForSyndication(int approvedForSyndication){
		this.approvedForSyndication = approvedForSyndication;
	}

	public int getApprovedForSyndication(){
		return approvedForSyndication;
	}

	@Override
 	public String toString(){
		return 
			"MediaItem{" + 
			"copyright = '" + copyright + '\'' + 
			",media-metadata = '" + mediaMetadata + '\'' + 
			",subtype = '" + subtype + '\'' + 
			",caption = '" + caption + '\'' + 
			",type = '" + type + '\'' + 
			",approved_for_syndication = '" + approvedForSyndication + '\'' + 
			"}";
		}
}