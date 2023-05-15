package test.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import test.dto.MemberDto;

public class MainClass09 {
	public static void main(String[] args) {
		String name="에이콘";
		String addr="강남역";
		
		//아래의 insert() 메소드를 활용해서 위의 회원정보가 DB 에 저장되도록 해 보세요.
		MemberDto dto1=new MemberDto();
		dto1.setName(name);
		dto1.setAddr(addr);
		
		MainClass09.insert(dto1);
	}
	
	public static void insert(MemberDto dto) {
		//인자로 전달된 MemberDto 객체에 담긴 내용을 이용해서 DB 에 회원 정보가 저장되도록 해 보세요.
		//mem 에 담겨진 정보를 이용해서 회원 한명의 정보를 DB 에 저장되도록 해보세요.
		//DB 연결객체를 담을 지역 변수 만들기
		Connection conn=null;
		try {
			//오라클 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//접속할 DB 의 정보 @아이피주소:port번호:db이름
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			//계정 비밀번호를 이용해서 Connection 객체의 참조값 얻어오기
			conn=DriverManager.getConnection(url, "scott", "tiger");
			//예외가 발생하지 않고 여기까지 실행순서가 내려오면 접속 성공이다.
			System.out.println("Oracle DB 접속 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//sql 문을 대신 실행해줄 객체의 참조값을 담을 지역변수 미리 만들기
		PreparedStatement pstmt=null;
		try {
			//실행할 미완성의 sql 문
			String sql="INSERT INTO member"
					+ " (num, name, addr)"
					+ " VALUES( member_seq.NEXTVAL , ?, ?)";
			//미완성의 sql 문을 전달하면서 PreparedStatement 객체의 참조값 얻어내기
			pstmt=conn.prepareStatement(sql);
			//PreparedStatement 객체의 메소드를 이용해서 미완성인 sql 문을 완성시키기(? 에 값 바인딩하기)
			pstmt.setString(1, dto.getName()); //1번째 ? 에 문자열 바인딩
			pstmt.setString(2, dto.getAddr()); //2번째 ? 에 문자열 바인딩
			//sql 문 실행하기
			pstmt.executeUpdate();
			System.out.println("회원 정보를 저장했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
