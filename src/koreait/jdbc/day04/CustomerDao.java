package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day02.OracleUtility;
import koreait.jdbc.day03.StudentDto;

public class CustomerDao {

	//1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
	public JCustomer logIn(String customid) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from J_CUSTOM where custom_ID = ?";	//PK조회 : 결과 행 0 또는 1개
		PreparedStatement ps = conn.prepareStatement(sql);	// Statement : 준비된 SQL, Prepared : SQL이 미리 준비된 컴파일
		//PreparedStatement는 Statement 인터페이스와 비교할 수 있습니다.
		//Statement 인터페이스 : SQL 실행에 필요한 데이터를 동시에 포함시켜서 컴파일을 합니다.
		
		ps.setString(1, customid); //준비된 SQL에 파라미터 전달
		ResultSet rs = ps.executeQuery(); //select 쿼리 실행
		JCustomer temp = null;
		if(rs.next()) {
			temp =  new JCustomer(rs.getString(1),rs.getString(2),rs.getString(3), rs.getInt(4), rs.getDate(5));
		}
			
		ps.close();
		conn.close();
		
		return temp;
	}
}