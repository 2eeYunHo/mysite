package com.douzone.mysite.vo;

public class BoardVo {
	private String title;
	private String contents;
	private Long no;
	private String reg_date;
	private Long hit;
	private Long group_no;
	private Long order_no;
	private Long depth;
	private Long user_no;
	private String userName;
	private String gender;
	private String email;
	private String password;
	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public Long getGroup_no() {
		return group_no;
	}

	public void setGroup_no(Long group_no) {
		this.group_no = group_no;
	}

	public Long getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}

	public Long getDepth() {
		return depth;
	}

	public void setDepth(Long depth) {
		this.depth = depth;
	}

	public Long getUser_no() {
		return user_no;
	}

	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "BoardVo [title=" + title + ", contents=" + contents + ", no=" + no + ", reg_date=" + reg_date + ", hit="
				+ hit + ", group_no=" + group_no + ", order_no=" + order_no + ", depth=" + depth + ", user_no="
				+ user_no + ", userName=" + userName + ", gender=" + gender + ", email=" + email + ", password="
				+ password + ", message=" + message + "]";
	}

}