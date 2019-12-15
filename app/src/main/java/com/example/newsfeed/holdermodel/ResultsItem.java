package com.example.newsfeed.holdermodel;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResultsItem{




	@SerializedName("column")
	private Object column;

	@SerializedName("section")
	private String section;

	@SerializedName("abstract")
	private String jsonMemberAbstract;

	@SerializedName("source")
	private String source;

	@SerializedName("asset_id")
	private long assetId;

	@SerializedName("media")
	private List<MediaItem> media;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private String title;



	@SerializedName("url")
	private String url;

	@SerializedName("adx_keywords")
	private String adxKeywords;



	@SerializedName("id")
	private long id;

	@SerializedName("byline")
	private String byline;

	@SerializedName("published_date")
	private String publishedDate;

	@SerializedName("views")
	private int views;





	public void setColumn(Object column){
		this.column = column;
	}

	public Object getColumn(){
		return column;
	}

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setJsonMemberAbstract(String jsonMemberAbstract){
		this.jsonMemberAbstract = jsonMemberAbstract;
	}

	public String getJsonMemberAbstract(){
		return jsonMemberAbstract;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setAssetId(long assetId){
		this.assetId = assetId;
	}

	public long getAssetId(){
		return assetId;
	}

	public void setMedia(List<MediaItem> media){
		this.media = media;
	}

	public List<MediaItem> getMedia(){
		return media;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}



	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setAdxKeywords(String adxKeywords){
		this.adxKeywords = adxKeywords;
	}

	public String getAdxKeywords(){
		return adxKeywords;
	}


	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setByline(String byline){
		this.byline = byline;
	}

	public String getByline(){
		return byline;
	}

	public void setPublishedDate(String publishedDate){
		this.publishedDate = publishedDate;
	}

	public String getPublishedDate(){
		return publishedDate;
	}

	public void setViews(int views){
		this.views = views;
	}

	public int getViews(){
		return views;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" +

			",column = '" + column + '\'' + 
			",section = '" + section + '\'' + 
			",abstract = '" + jsonMemberAbstract + '\'' + 
			",source = '" + source + '\'' + 
			",asset_id = '" + assetId + '\'' + 
			",media = '" + media + '\'' + 
			",type = '" + type + '\'' + 
			",title = '" + title + '\'' +
			",url = '" + url + '\'' + 
			",adx_keywords = '" + adxKeywords + '\'' +
			",id = '" + id + '\'' + 
			",byline = '" + byline + '\'' + 
			",published_date = '" + publishedDate + '\'' + 
			",views = '" + views + '\'' + 
			"}";
		}
}