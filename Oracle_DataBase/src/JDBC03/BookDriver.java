package JDBC03;

import java.util.ArrayList;
import java.util.Scanner;

public class BookDriver {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("\n*** 메뉴선택 ***");
		System.out.printf("1. 프로그램 종료. ");
		System.out.printf("  2. 데이터 추가");
		System.out.printf("  3. 데이터 열람");
		System.out.printf("  4. 데이터 수정");
		System.out.println("  5. 데이터 삭제");
		System.out.print(">> 메뉴 선택 : ");
		String choice = sc.nextLine();
		switch(choice) {
		case "2" :
			insertData();
			break;
		case "3" :
			selectData();
			break;
		case "4":
			updateData();
			break;
		case "5":
			deleteData();
			break;
		}
	}
	public static void deleteData() {
		BookDao bdao = new BookDao();
		Scanner sc=new Scanner(System.in);
		
		System.out.print("삭제할 도서의 번호를 입력하세요 : ");
		String booknum = sc.nextLine();
		
		int result = bdao.delete(booknum);
		if(result==1)	System.out.println("삭제 성공");
		else System.out.println("삭제 실패");
	}
	
	public static void insertData() {
		BookDto bdto = new BookDto();
		Scanner sc=new Scanner(System.in);
		// String subject = sc.nextLine();
		// bdto.setBooknum(subject);
		// -> bdto.setSubject(sc.nextLine());
		System.out.print("제목을 입력하세요 : ");
		bdto.setSubject(sc.nextLine());
		System.out.print("출판년도를 입력하세요 : ");
		bdto.setMakeyear(Integer.parseInt(sc.nextLine()));
		System.out.print("입고가격을 입력하세요 : ");
		bdto.setInprice(Integer.parseInt(sc.nextLine()));
		System.out.print("출고가격을 입력하세요 : ");
		bdto.setOutprice(Integer.parseInt(sc.nextLine()));
		System.out.print("등급을 입력하세요 : ");
		bdto.setGrade(sc.nextLine());
		
		BookDao bdao = new BookDao();
		int result =	bdao.insert(bdto);
		if(result==1) System.out.println("레코드 추가 성공");
		else System.out.println("레코드 추가 실패");
	}
	
	public static void selectData() {
		// 데이터의 열람
		// 1. 데이터의 열람은 데이터베이스에서 레코드 목록을 조회하고 그 결과를 전달받아 
		// 화면에 표시하는 역할을 합니다.
		// 2. 데이터베이스 레코드 목록을 조회하는 역할은 다른 클래스 객체의 메서드를 실행시켜
		// 전달받습니다
		// 3. 클래스의 이름은 BookDao 이고 그 안의 멤버메서드 중 select()메서드를 이용합니다
		BookDao bdao = new BookDao();
		// 4. 리턴값은 한 개의 레코드 데이터를 담을 수 있는 BookDto 클래스의 객체에 담기고, 
		// 	   그 객체들은 데이터베이스 테이블에 들어있는 레코드 수 만큼 ArrayList에 담겨서 리턴됩니다.	
		//     리턴값의 자료형 : ArrayList<BookDto>
		ArrayList<BookDto> list = null;
		list = bdao.select();
		// list안에는 BookDto형태의 객체들이 데이터베이스 테이블의 레코드 갯수만큼 들어있습니다.
		System.out.println("도서번호\t제목\t출판년도\t입고가격\t출고가격\t등급");
		System.out.println("-------------------------------------------------------------");
		for(BookDto dto:list) {	//리스트에서 꺼낸 하나의 객체는 BookDto형, 반복은 갯수만큼
			System.out.printf("%s\t%s\t%d\t%d\t%d\t%s\n", dto.getBooknum(), 
					dto.getSubject(), dto.getMakeyear(), dto.getInprice(), dto.getOutprice(), dto.getGrade());
		}
		
	}
	
	public static void updateData() {
		BookDao bdao = new BookDao();
		BookDto bdto = new BookDto();
		Scanner sc=new Scanner(System.in);
		// 수정할 사항이 없는 필드(null - 입력하지 않음)는 수정하지 않음
		// 수정할 값을 입력한 필드만 수정
		String in;
		
		System.out.print("수정할 도서의 도서번호를 선택하세요(필수) : ");
		bdto.setBooknum(sc.nextLine());
		System.out.print("제목을 입력하세요(수정하지 않으려면 Enter) : ");
		bdto.setSubject(sc.nextLine());
		System.out.print("출판년도를 입력하세요(수정하지 않으려면 Enter) : ");
		// in에 입력받은 내용이 ""라면(아무것도 입력하지 않고 엔터 시) 0으로, 아니면 입력된 값으로 진행
		if((in=sc.nextLine()).equals("")) bdto.setMakeyear(0);
		else bdto.setMakeyear(Integer.parseInt(in));
		System.out.print("입고가격을 입력하세요(수정하지 않으려면 Enter) : ");
		if((in=sc.nextLine()).equals("")) bdto.setInprice(0);
		else bdto.setInprice(Integer.parseInt(in));
		System.out.print("출고가격을 입력하세요(수정하지 않으려면 Enter) : ");
		if((in=sc.nextLine()).equals("")) bdto.setOutprice(0);
		else bdto.setOutprice(Integer.parseInt(in));
		System.out.print("등급을 입력하세요(수정하지 않으려면 Enter) : ");
		// 입력값이 없으면 null, 있으면 그 값으로
		bdto.setGrade(sc.nextLine());
		int result = bdao.update(bdto);
		if(result==1) System.out.println("레코드 수정 성공");
		else System.out.println("레코드 수정 실패");
	}
}
