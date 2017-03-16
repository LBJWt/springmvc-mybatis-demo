package com.smd.demo.model;

/**
 * 功能描述：实体model的父类，提供分页功能
 */
public class BaseModel {

	private Integer page = 1;// 页数，第几页
	
	private Integer rows =10;// 每页有几条数据
	
	private Integer startRow = 1;// 当前页的第一条

	private String sort;// 要排序的字段
	
	private String order;// 排序顺序，desc降序，asc升序

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStartRow() {
		return startRow;
	}
	
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	/** 设置开始行，用于分页显示 */
	public void setStartRow() {
		this.startRow = (page-1) * rows;
	}
	
}
