package com.xh.networkclient.bean;



public class FileRes extends FSCommonRes {

	private FileDTO fileDTO;

	public FileRes() {
		super();
	}

	public FileRes(FileDTO fileDTO) {
		super();
		this.fileDTO = fileDTO;
	}

	public FileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}

	@Override
	public String toString() {
		return "FileRes [fileDTO=" + fileDTO + "]";
	}

}
