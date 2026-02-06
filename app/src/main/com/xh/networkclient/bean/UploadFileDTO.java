package com.xh.networkclient.bean;

import java.io.Serializable;

/**
 * @author Chenyz
 *
 */
public class UploadFileDTO implements Serializable {

	private static final long serialVersionUID = 4528484731036424038L;
	/**
	 * 文件id
	 */
	private String fileId;

	public UploadFileDTO() {
		super();
	}

	public UploadFileDTO(String fileId) {
		super();
		this.fileId = fileId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "UploadFileDTO [fileId=" + fileId + "]";
	}

}
