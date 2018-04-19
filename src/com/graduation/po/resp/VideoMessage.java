package com.graduation.po.resp;

public class VideoMessage extends BaseMessage {
	// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;

	private String Title;

	private String Description;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
