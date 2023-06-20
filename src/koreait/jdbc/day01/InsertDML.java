package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// 학생 성적처리 프로그램 중에 새로운 학생을 등록(입력) 하는 기능을 만들어 봅시다.(테이블에 insert 실행)
public class InsertDML  {

	public static void main(String[] args) {
	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String passwrod = "0419";
		
		
		try (
			Connection conn = DriverManager.getConnection(url, user, passwrod);
			){
			
			
			
			System.out.println("연결 상태 = " + conn);
			if(conn != null)
				System.out.println("오라클 데이터베이스 연결 성공!!");
			
			//db연결 완료 후에 sql 실행하기.
			
			//insert SQL 작성 : 제약조건(기본키 stuno) 위반 되지 않는 값으로 입력하기.
			String sql = "insert into TBL_STUDENT values ('2023002','김땡이',17,'경기도')";		//insert SQL 작성
			
			//preparedStatement 객체를 생성하면서 실행할 sql을 설정합니다.
			//preparedStatement 객체는 Connection 객체 메소드로 만듭니다. Connection 구현객체는 dbms종류에 따라
			//생성되고 preparedStatement 객체도 그에 따라 구현 객체가 결정됩니다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 
			
			
			pstmt.execute();	//preparedStatement 객체로 execute 하면 SQL이 실행됩니다.
			pstmt.close();
			
			System.out.println("정상적으로 새로운 학생이 입력되었습니다.");
			
			
		}catch (Exception e) {	// 모든 Exeption 처리합니다.
			System.out.println("오류메세지 = " + e.getMessage());
			//e.printStackTrace(); 			
			}
			}
		}
/*
 * Statement 인터페이스는 SQL쿼리 처리와 솬련된 방법을 정의합니다. 
 * 객체는 SQL 쿼리문을 데이터베이스에 전송합니다. Connection 객체를 통해서 만듭니다.
 *
 * PreparedStatement는 Statement의 자식 인터페이스.
 * 특징은 SQL을 먼저 컴파일하고 SQL실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식입니다.
 *
 */
