package com.shopping.model.bean;

public class Board {
	private int no;
	private String id;
	private String password;
	private String subject;
	private String contents;
	private int readhit;
	private String regdate;

	// 차후 답글과 관련된 변수
	private Integer depth; // 글의 깊이
	private Integer groupno; // 그룹 번호
	private Integer orderno; // 순서 번호
	
	private int likes; // 좋아요
	private int hates; // 싫어요

	public Board() {
	}

	public Board(int no, String id, String password, String subject, String contents, int readhit, String regdate, Integer depth) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.subject = subject;
		this.contents = contents;
		this.readhit = readhit;
		this.regdate = regdate;
		this.depth = depth;
		
	}
	
	
	@Override
	public String toString() {
		return "Board [no=" + no + ", id=" + id + ", password=" + password + ", subject=" + subject + ", contents="
				+ contents + ", readhit=" + readhit + ", regdate=" + regdate + ", depth=" + depth + ", groupno="
				+ groupno + ", orderno=" + orderno + ", likes=" + likes + ", hates=" + hates + "]";
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getReadhit() {
		return readhit;
	}

	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	// 답글관련
	public Integer getGroupno() {
		return groupno;
	}

	public void setGroupno(Integer groupno) {
		this.groupno = groupno;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getHates() {
		return hates;
	}

	public void setHates(int hates) {
		this.hates = hates;
	}

	

}
