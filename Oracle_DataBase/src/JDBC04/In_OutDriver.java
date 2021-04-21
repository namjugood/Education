package JDBC04;

import java.util.ArrayList;
import java.util.Scanner;

public class In_OutDriver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n*** 메뉴 선택***");
		System.out.printf("1. 프로그램 종료, 2. 데이터 추가 3. 데이터 열람 4. 데이터 수정 5. 데이터 삭제");
		System.out.print(">>메뉴선택 : ");
		String choice = sc.nextLine();
		while(!choice.equals("1")) {
			switch(choice) {
			case "2":
				insertData();
				break;
			case "3":
				selectData();
				break;
			case "4":
				updateData();
				break;
			case "5":
				deleteData();
				break;
			}
			System.out.println("\n*** 메뉴 선택***");
			System.out.printf("1. 프로그램 종료, 2. 데이터 추가 3. 데이터 열람 4. 데이터 수정 5. 데이터 삭제");
			System.out.print(">>메뉴선택 : ");
			choice = sc.nextLine();
		}
	}

	private static void insertData() {
		// 날짜는 오늘날짜, 순번 : 오늘 날짜에서 가장 큰 순번 +1 -> 임의의 숫자로 입력하되
		// 날짜 + 순번이 중복되지 않게 입력
		In_OutDto idto = new In_OutDto();
		Scanner sc=new Scanner(System.in);
		System.out.print("순번을 입력하세요 : ");
		idto.setIndexk(Integer.parseInt(sc.nextLine()));
		System.out.print("도서번호를 입력하세요 : ");
		idto.setBooknum(sc.nextLine());
		System.out.print("회원번호를 입력하세요 : ");
		idto.setPersonnum(sc.nextLine());
		System.out.print("할인금액을 입력하세요 : ");
		idto.setDiscount(Integer.parseInt(sc.nextLine()));
		
		In_OutDao idao = new In_OutDao();
		int result = idao.insert(idto);
		if(result==1) System.out.println("데이터 삽입 성공");
		else System.out.println("데이터 삽입 실패");
		
	}

	private static void deleteData() {
		// TODO Auto-generated method stub
		
	}

	private static void updateData() {
		// TODO Auto-generated method stub
		
	}

	private static void selectData() {
		In_OutDao idao=new In_OutDao();
		ArrayList<In_OutDto> list = null;
		list = idao.select();
		System.out.println("대여일자\t순번\t도서번호\t회원번호\t할인금액");
		System.out.println("--------------------------------------------------------");
		for(In_OutDto dto : list) {	//리스트에서 꺼낸 하나의 객체는 BookDto형이며, 반복은 갯수만큼
		System.out.printf("%s\t%d\t%s\t%s\t%d\n", dto.getOut_date(), 
				dto.getIndexk(), dto.getBooknum(), dto.getPersonnum(), dto.getDiscount());
		}
	}
}
