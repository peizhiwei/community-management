package com.peizhiwei.community.util;

import java.util.List;

public class Pager<T> {
	private int page;//��ҳ��ʼҳ
	private int size;//ÿҳ��¼��
	private List<T> rows;//���صļ�¼����
	private long total;//�ܼ�¼����
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
}
