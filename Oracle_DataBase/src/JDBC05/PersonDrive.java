package JDBC05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonDrive {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n*** 메뉴 선택***");
		System.out.printf("1. 프로그램 종료, 2. 회원정보추가 3. 회원정보열람 4. 회원정보수정 5. 회원정보삭제\t|\t메뉴선택 : ");
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
			System.out.printf("1. 프로그램 종료, 2. 회원정보추가 3. 회원정보열람 4. 회원정보수정 5. 회원정보삭제\t|\t메뉴선택 : ");
			choice = sc.nextLine();
		}
	}

	
	private static void insertData() {
		PersonDto pdto = new PersonDto();
		Scanner sc=new Scanner(System.in);
		System.out.print("성명을 입력하세요 : ");
		pdto.setPersonname(sc.nextLine());
		System.out.print("생년월일을 입력하세요(yy/mm/dd) : ");
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		java.util.Date uDate = null;
		while(true) {
			try {
				uDate = sdf.parse(sc.nextLine());
				break;
			}catch(ParseException e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요(필수 yy/mm/dd)");
			}
		}
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		pdto.setBirth(sDate);
		
		System.out.print("전화번호를 입력하세요(000-0000-0000) : ");
		pdto.setPhone(sc.nextLine());
		System.out.print("구매포인트를 입력하세요 : ");
		pdto.setBpoint(Integer.parseInt(sc.nextLine()));
		System.out.print("나이를 입력하세요 : ");
		pdto.setAge(Integer.parseInt(sc.nextLine()));
		System.out.print("성별을 입력하세요 : ");
		pdto.setGender(sc.nextLine());
		
		PersonDao pdao = new PersonDao();
		int result = pdao.insert(pdto);
		if(result==1) System.out.println("데이터 삽입 성공");
		else System.out.println("데이터 삽입 실패");
		
	}

	private static void selectData() {
		PersonDao pdao = new PersonDao();
		ArrayList<PersonDto> list = null;
		list = pdao.select();
		System.out.println("회원번호\t회원성명\t전화번호\t생년월일\t가입일\t구매포인트\t나이\t성별");
		System.out.println("-----------------------------------------------------------------------------");
		for(PersonDto dto : list) {
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%d\t%d\t%s\n", dto.getPersonnum(), 
				dto.getPersonname(), dto.getPhone(),dto.getBirth(), dto.getEnterdate(), dto.getBpoint(), dto.getAge(), dto.getGender());
		}
	}

	private static void updateData() {
		PersonDao pdao = new PersonDao();
		PersonDto pdto = new PersonDto();
		Scanner sc=new Scanner(System.in);

		String in;
		System.out.print("수정할 회원의 번호를 입력하세요(필수) : ");
		if((in=sc.nextLine()).equals("")) 	pdto.setPersonnum(null);
		else pdto.setPersonnum(in);
		
		System.out.print("회원의 수정된 성명을 입력하세요(선택) : ");
		pdto.setPersonname(sc.nextLine());
		
		System.out.print("회원의 수정된 전화번호를 입력하세요(선택) : ");
		pdto.setPhone(sc.nextLine());
		
		System.out.print("회원의 수정된 생년월일을 입력하세요(선택) : ");
		if((in=sc.nextLine()).equals("")) 	pdto.setBirth(null);
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd");
			java.util.Date uDate = null;
			java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		}
		
		System.out.print("회원의 수정된 구매포인트를 입력하세요(선택) : ");
		if((in=sc.nextLine()).equals("")) 	pdto.setBpoint(0);
		else pdto.setBpoint(Integer.parseInt(in));
		System.out.print("회원의 수정된 나이를 입력하세요(선택) : ");
		if((in=sc.nextLine()).equals("")) 	pdto.setAge(0);
		else pdto.setAge(Integer.parseInt(in));
		
		System.out.print("회원의 수정된 성별을 입력하세요(선택) : ");
		pdto.setGender(sc.nextLine());
		
		int result = pdao.update(pdto);
		if(result==1) System.out.println("레코드 수정 성공");
		else System.out.println("레코드 수정 실패");
		
	}

	private static void deleteData() {
		PersonDao pdao = new PersonDao();
		PersonDto pdto = new PersonDto();
		Scanner sc=new Scanner(System.in);
		String in;
		System.out.print("삭제할 회원번호를 입력하세요 : ");
		String personnum = null;
		if(!(in=sc.nextLine()).equals(""))  personnum = in;
		int result = pdao.delete(personnum);
		if(result==1) System.out.println("삭제 성공");
		else System.out.println("삭제 실패");
	}	
	

}
