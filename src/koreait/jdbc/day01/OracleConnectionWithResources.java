package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnectionWithResources  {

	public static void main(String[] args) {
	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String passwrod = "0419";
		
		
		try (//자원해제 close가 필요한 객체 생성과 변수 선언하기
			Connection conn = DriverManager.getConnection(url, user, passwrod);
			){// 2개 이상의 구문을 작성할 수 있습니다.
			
			//현재 버전에서는 DriverManager가 알아서 실행하므로 생략 가능
			//Class.forName(driver);
			
			
			
			System.out.println("연결 상태 = " + conn);
			if(conn != null)
				System.out.println("오라클 데이터베이스 연결 성공!!");
			else
				System.out.println("오라클 데이터베이스 연결 실패!!");
			
		}catch (Exception e) {	
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 password가 잘못됐습니다.");
			System.out.println("오류메세지 = " + e.getMessage());
			e.printStackTrace(); 			
			}
		
		//conn.close()를 명시적으로 실행할 필요가 없습니다. 자동 close
			}
		}
