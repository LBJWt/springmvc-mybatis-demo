package com.smd.demo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 构造easyui的datagrid组件需要的对象，该对象转为json格式之后可以被datagrid对象读取
 * 
 * @param <T>
 *            datagrid表格列表中的对象类型
 */
public class Datagrid<T> {

	private int total;// 总数
	private List<T> rows;// 表格要显示的对象列表

	public Datagrid() {
		super();
		this.total = 0;
		this.rows = new ArrayList<T>();
	}

	public Datagrid(int total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Datagrid [total=" + total + ", rows=" + rows + "]";
	}

}
