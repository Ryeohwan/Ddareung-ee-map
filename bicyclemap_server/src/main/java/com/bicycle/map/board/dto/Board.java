package com.bicycle.map.board.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Board{
	
	// board는 넣지 않을 수도 있기 때문에 널체크를 하지 않았습니다.
	
	private int code,originNo,groupOrd,groupLayer;
	private String title,content,writer,fileName;
	private Date reg_datetime;
	private MultipartFile file;
	private List<FileInfoDto> fileInfos;
	public Board() {
		super();
	}
	public Board(int code, int originNo, int groupOrd, int groupLayer, String title, String content, String writer,
			Date reg_datetime, MultipartFile file, List<FileInfoDto> fileInfos) {
		setCode(code);
		setContent(content);
		setFile(file);
		setFileInfos(fileInfos);
		setGroupLayer(groupLayer);
		setGroupOrd(groupOrd);
		setOriginNo(originNo);
		setReg_datetime(reg_datetime);
		setTitle(title);
		setWriter(writer);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getOriginNo() {
		return originNo;
	}
	public void setOriginNo(int originNo) {
		this.originNo = originNo;
	}
	public int getGroupOrd() {
		return groupOrd;
	}
	public void setGroupOrd(int groupOrd) {
		this.groupOrd = groupOrd;
	}
	public int getGroupLayer() {
		return groupLayer;
	}
	public void setGroupLayer(int groupLayer) {
		this.groupLayer = groupLayer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getReg_datetime() {
		return reg_datetime;
	}
	public void setReg_datetime(Date reg_datetime) {
		this.reg_datetime = reg_datetime;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}