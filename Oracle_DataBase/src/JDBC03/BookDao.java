package JDBC03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookDao {
	// Database access object
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
//	insert문-----------------------
	public int insert(BookDto bdto) {
		int result = 0;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "scott", "tiger");
			String sql = "insert into booklist values(booklist_seq.nextVal,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  bdto.getSubject());
			pstmt.setInt(2, bdto.getMakeyear());
			pstmt.setInt(3, bdto.getInprice());
			pstmt.setInt(4, bdto.getOutprice());
			pstmt.setString(5,  bdto.getGrade());
			result = pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				if(rs != null) rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
//	select 문------
	public ArrayList<BookDto> select() {
		ArrayList<BookDto> list = new ArrayList<BookDto>();
		// 데이터베이스 테이블의 조회가 여기서 이루어 집니다
		// 결과는 list 변수에 담기고, 리턴됩니다/
			
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "scott", "tiger");
			String sql = "select * from booklist";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
				
			while(rs.next()){
				BookDto bdto = new BookDto();
				/*String a = rs.getString("booknum");
				String b = rs.getString("subject");
				int c = rs.getInt("makeyear");
				int d = rs.getInt("inprice");
				int e = rs.getInt("outprice");
				String f = rs.getString("grade"); */
				bdto.setBooknum(rs.getString("booknum"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setMakeyear(rs.getInt("makeyear"));
				bdto.setInprice(rs.getInt("inprice"));
				bdto.setOutprice(rs.getInt("outprice"));
				bdto.setGrade(rs.getString("grade"));
				list.add(bdto);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	update문----------
	public int update(BookDto bdto) {
		int result = 0;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "scott", "tiger");
			String sql = "update booklist set";
			boolean first = true;		// sql문에 추가되는 필드명이 처음 추가되는 지 아닌지 판단
			// 처음 추가되는 것은(,) 없이 추가되고 두 번째 이후 추가되는 필드는 (,)를 앞에 찍고 추가	
			// 위 사항은 전달된 값이 있을 때에 실행되므로 그 부분부터 확인해야 합니다.
			if(!bdto.getSubject().equals("")) {
				// subject는 전달값이 ""가 아닐 경우, 반드시 처음 추가되는 필드이므로 (,)는 신경쓰지
				// 않습니다.
				sql = sql+"subject = ?";
				first = false;	// 처음 필드가 추가되었으므로 first를 false로 만들고 이후 내용에 적용합니다.
			}
			if(bdto.getMakeyear() != 0) {
				if(first) {
					sql = sql + " makeyear = ?";
					first=false;
				}else {
					sql=sql + ", makeyear = ?";
				}
			}
			if(bdto.getInprice() != 0) {
				if(first) {
					sql = sql + " inprice = ?";
					first=false;
				}else {
					sql=sql + ", inprice = ?";
				}
			}
			if(bdto.getOutprice() != 0) {
				if(first) {
					sql = sql + " Outprice = ?";
					first=false;
				}else {
					sql=sql + ", Outprice = ?";
				}
			}
			if(!bdto.getGrade().equals("")) {
				if(first) {
					sql = sql + " grade = ?";
					first=false;
				}else {
					sql=sql + ", grade = ?";
				}
			}
			// sql = sql+"subject = ?";
			// sql = sql + ", makeyear = ?";
			sql = sql + " where booknum=?";
			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			
			int cnt = 1;
			first = true;
			if(!bdto.getSubject().equals("")) {
				pstmt.setString(1,  bdto.getSubject());	first = false;
			}
			if(bdto.getMakeyear() !=0) {
				if(first) {pstmt.setInt(1,  bdto.getMakeyear()); first=false;
				}else
					pstmt.setInt(++cnt, bdto.getMakeyear());
			}
			if(bdto.getInprice() !=0) {
				if(first) {pstmt.setInt(1,  bdto.getInprice()); first=false;
				}else
					pstmt.setInt(++cnt, bdto.getInprice());
			}
			if(bdto.getOutprice() !=0) {
				if(first) {pstmt.setInt(1,  bdto.getOutprice()); first=false;
				}else
					pstmt.setInt(++cnt, bdto.getOutprice());
			}
			if(!bdto.getGrade().equals("")) {
				if(first) {pstmt.setString(1,  bdto.getGrade());	first = false;
				}else pstmt.setString(++cnt, bdto.getGrade());
			}	
			pstmt.setNString(++cnt,  bdto.getBooknum());
			result = pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				if(rs != null) rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//delete문-----------------------	
	public int delete(String booknum) {
		int result = 0;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "scott", "tiger");
			Scanner sc = new Scanner(System.in);
			String sql = "delete from booklist where booknum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  booknum);
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				if(rs != null) rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
}
