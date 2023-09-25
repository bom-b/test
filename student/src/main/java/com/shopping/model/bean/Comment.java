package com.shopping.model.bean;

public class Comment {
	
	private int cnum;
	private int no;
	private String id;
	private String content;
	private String regdate;
	
	public Comment() {}
	
	@Override
	public String toString() {
		return "Comment [cnum=" + cnum + ", no=" + no + ", id=" + id + ", content=" + content + ", refgdate=" + regdate
				+ "]";
	}
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
