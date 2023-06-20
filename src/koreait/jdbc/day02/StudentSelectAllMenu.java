package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentSelectAllMenu {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println("::::::::::::::::::학생을 학번으로 조회하는 메뉴:::::::::::::::::::::::");
		selectStudent(conn);
		
		OracleUtility.close(conn);
	}

	private static void selectStudent(Connection conn) {
		String sql = "select * from TBL_SCORE";
		
		try(
				PreparedStatement ps = conn.prepareStatement(sql);
			){
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
				System.out.print("stuno : " + rs.getString(1) + "\t\t");
				System.out.print("name : " + rs.getString(4) + "\t");
				System.out.print("age : " + rs.getInt(3) + "\t");
				System.out.println("subject : " + rs.getString(2) + "\t");
				}
			} catch (Exception e) {
				System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 -" + e.getMessage());
				//결과 집합을 모두 소모했음 -> 조회 결과가 없는데 rs.getXXXX 메소드 실행할 때의 예외 메시지.
		}
	}
}

