package JDBC03;

//클래스의 용도 : 자료 전송용 객체(DataBase Transfer Object) 생성
//객체 하나로 자료 하나를 담아 전송용으로 사용합니다. 이와 같은 클래스를 Java Bean이라고 합니다.



public class BookDto {
	private String booknum;
	private String subject;
	private int makeyear;
	private int inprice;
	private int outprice;
	private String grade;
	
	public String getBooknum() {
		return booknum;
	}
	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getMakeyear() {
		return makeyear;
	}
	public void setMakeyear(int makeyear) {
		this.makeyear = makeyear;
	}
	public int getInprice() {
		return inprice;
	}
	public void setInprice(int inprice) {
		this.inprice = inprice;
	}
	public int getOutprice() {
		return outprice;
	}
	public void setOutprice(int outprice) {
		this.outprice = outprice;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

}
