package com.xh.networkclient.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Chenyz
 * 
 */
public class FileDTO implements Serializable {

	private static final long serialVersionUID = -1995887501435219261L;

	/**
	 * 文件id
	 */
	private String fileId;

	/**
	 * 文件路径
	 */
	private String[] url;

	public FileDTO() {
		super();
	}

	public FileDTO(String fileId, String[] url) {
		super();
		this.fileId = fileId;
		this.url = url;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "FileDTO [fileId=" + fileId + ", url=" + Arrays.toString(url)
				+ "]";
	}

}
