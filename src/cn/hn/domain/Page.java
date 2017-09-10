package cn.hn.domain;

import java.util.List;

public class Page {

	private int totalpage;//remember total number of pages
	private int pagesize = 2;//size of single page
	
	private int totalrecord; //remember total number of record
	private int pagenum; //remember current page number
	
	private int startindex; //remember start index in the database
	private List<Book> list; //remember page data
	
	private int startPage;
	private int endPage;
	
	public Page(int pagenum,int totalrecord){
		this.pagenum = pagenum;
		this.totalrecord = totalrecord;
		
		this.totalpage = (this.totalrecord + this.pagesize - 1)/this.pagesize;
		this.startindex = (this.pagenum - 1)*this.pagesize;
		
		if(this.totalpage < 10){
			this.startPage = 1;
			this.endPage = this.totalpage;
		}else{
			this.startPage = this.pagenum -4;
			this.endPage = this.pagenum + 5;
			
			if(this.startPage < 1){
				this.startPage = 1;
				this.endPage = 10;
			}
			if(this.endPage > this.totalpage){
				this.endPage = this.totalpage;
				this.startPage = this.totalpage - 9;
			}
		}
		
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
