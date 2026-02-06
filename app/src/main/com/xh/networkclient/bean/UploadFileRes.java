package com.xh.networkclient.bean;


public class UploadFileRes extends FSCommonRes {

	private UploadFileDTO uploadFileDTO;

	public UploadFileRes() {
		super();
	}

	public UploadFileRes(UploadFileDTO uploadFileDTO) {
		super();
		this.uploadFileDTO = uploadFileDTO;
	}

	public UploadFileDTO getUploadFileDTO() {
		return uploadFileDTO;
	}

	public void setUploadFileDTO(UploadFileDTO uploadFileDTO) {
		this.uploadFileDTO = uploadFileDTO;
	}

	@Override
	public String toString() {
		return "UploadFileRes [uploadFileDTO=" + uploadFileDTO + "]";
	}

}
