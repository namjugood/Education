package com.board.util;

public class Paging {
	private int page =1;		// 현재 페이지(get)
	private int totalCount;	// 모든 게시물의 갯수 (get)
	private int beginPage;	// 표시될 시작페이지 번호(1 or 11)
	private int endPage;		// 표시될 끝 페이지 번호(10 or 20)
	private int displayRow =10;	// 한 페이지에 몇 개의 행을 표시할 지
	private int displayPage =10;	// 한 페이지에 몇 개의 페이지 롤을 표시할 지
	private boolean prev;	// prev버튼이 보여질 것인지 아닌지(true / false)
	private boolean next;	// next버튼이 보여질 것인지 아닌지(true / false)
	private int startNum;	// 현재페이지의 시작 게시물 번호(1페이지라면, 1번부터 10번의 1번)
	private int endNum;		// 현제페이지의 끝 게시물 번호(2페이지라면, 11번부터 20번의 20번)
	// 1 2 3 4 [5] 6 7 8 9 10 [next]  => prev=false
	// [prev] 11 12 13 14 [15] 16 17 18 19 20 [next]
	// [prev] 21 22 23 24 [25] 26 27 28 29 30 [next]
	// [prev] 101 102 103 104 [105] 106 107 108 109 110  => next=false
	
	private void paging() {
		// endPage : 표시될 끝페이지 번호 10 or 20
		// endPage =((int)(Math.ceil(page / (double)displayPage))) * displayPage;
		double temp = page/(double)displayPage;	
		// 1/10 -> 0.1		|	9/10 -> 0.9	|	11/10 -> 1.1	|	25/10 -> 2.5
		temp=Math.ceil(temp);
		// 0.1 -> 1.0		|	0.9 -> 1.0	|	1.1 -> 2.0	|	2.5 -> 3.0
		endPage=((int)temp) * displayPage;
		// 1.0 -> 10		|	1.0 -> 10	|	2.0 -> 20	|	3.0 -> 30
		beginPage = endPage - displayPage + 1;
		System.out.println("beginPage : "+ beginPage + ", endPage : "+ endPage);
		
		int totalPage = (int)Math.ceil(totalCount / (double)displayRow);
		// 총 게시물 수를 한 화면에 표시할 게시물 수로 나눠서 총 페이지 갯수를 계산
		// 107/10 -> 10.7 -> 11.0 -> 11
		// 75/10 -> 7.5 -> 8
		
		if(totalPage<endPage) {	// 총 페이지가 현재 endPage보다 작다면
			endPage = totalPage;	// endPage를 총 페이지 수로 대체하고
			next=false;					// 다음 버튼은 표시하지 않는 것으로 설정
		}else {
			next=true;
		}
		
		prev = (beginPage==1) ? false : true;	// 시작페이지가 1인 경우만 표시안함
		
		startNum = (page-1)*displayRow+1;	
		// 현재 화면의 시작 게시물 번호 1page : 1, 2page : 11, 3page : 21, 4page : 31
		endNum = page*displayRow;			// 현재 화면의 끝 게시물 번호 10, 20, 30, 40
		
		System.out.println("beginPage : "+ beginPage + ", endPage : "+ endPage);
		System.out.println("startNum : "+ startNum + ", endNum : " + endNum);
		
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		paging();	// paging() 메서드의 호출 - setTotalCount 메서드가 외부에서 호출되면 자동실행
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	
	
}
