package com.puppy.common.vo;

public class ExtraServiceVO {
	private int s_no;
	private String s_name;
	private String s_explain;
	private String s_status;
	private int s_price;
	private int c_no;
	
	public ExtraServiceVO(int s_no, String s_name, String s_explain, String s_status, int s_price, int c_no) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_explain = s_explain;
		this.s_status = s_status;
		this.s_price = s_price;
		this.c_no = c_no;
	}

	public ExtraServiceVO() {
		super();
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_explain() {
		return s_explain;
	}

	public void setS_explain(String s_explain) {
		this.s_explain = s_explain;
	}

	public String getS_status() {
		return s_status;
	}

	public void setS_status(String s_status) {
		this.s_status = s_status;
	}

	public int getS_price() {
		return s_price;
	}

	public void setS_price(int s_price) {
		this.s_price = s_price;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	
	
}
