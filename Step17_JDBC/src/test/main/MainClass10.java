package test.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import test.dto.MemberDto;

public class MainClass10 {
	public static void main(String[] args) {
		// 수정할 회원의 정보
		int num=1;
		String name="호빵";
		String addr="독산동";
		
		//MemberDto 객체를 생성해서 
		MemberDto dto=new MemberDto();
		//수정할 회원의 정보를 담고 
		dto.setNum(num);
		dto.setName(name);
		dto.setAddr(addr);;
		//update() 메소드의 매개변수에 전달해서 회원 정보가 수정되도록 한다.
		update(dto);
	}
	
	//회원 한명의 정보를 수정하는 메소드 
	public static void update(MemberDto dto) {
		//Member 객체에 담긴 정보를 이용해서 회원 정보를 수정하도록 해보세요.
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
			String sql="UPDATE member"
					+ " SET name=?, addr=?"
					+ " WHERE num=?";
			//미완성의 sql 문을 전달하면서 PreparedStatement 객체의 참조값 얻어내기
			pstmt=conn.prepareStatement(sql);
			//PreparedStatement 객체의 메소드를 이용해서 미완성인 sql 문을 완성시키기(? 에 값 바인딩하기)
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			//sql 문 실행하기
			pstmt.executeUpdate();
			System.out.println("회원 정보를 수정했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}				
	}
}
