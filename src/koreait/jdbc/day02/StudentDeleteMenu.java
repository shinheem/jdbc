package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		System.out.println(":::::::::::::::::::::::::::학생 정보 삭제 메뉴입니다.::::::::::::::::::::::::");
		System.out.println("<< 지정된 학번의 정보를 삭제할 수 있습니다. >>");
		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			//학생정보 수정
			updateStudent(conn);
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}
	}

	//반복 없이 1개만 수정하게 하세요.
	//@SuppressWarnings("resource")	//리소스와 관련된 경고는 표시하지 않게 해주세요.
	private static void updateStudent(Connection connection) throws Exception {
		Scanner sc = new Scanner(System.in);
		// 4개의 입력값 그리고 파라미터 값 변수는 클래스를 정의해서 변경할 예정입니다.
		String stuno;
		String sql = "delete from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
				System.out.println("학생번호 입력시 0000입력은 삭제 취소 입니다.");
				System.out.print("삭제할 학번을 입력하세요 >>> ");
				stuno = sc.nextLine();

				if (stuno.equals("0000")) {
					System.out.println("학생 정보 삭제를 취소합니다.");
					sc.close();
					return;		//리턴에 값이 없을 때는 단순하게 메서드 종료로 실행됩니다.
				}
				//매개변수 전달과 실행을 모아놓기 => 이후에 메소드를 따로 정의할 예정.
 		   try {
 			   
				ps.setString(1, stuno);
				//ps.execute();	// insert, update, delete, select 모두 실행
				int count = ps.executeUpdate();		//*리턴값은 반영된 행의 개수 -> 새로운 메소드 써보기
							//insert, update, delete를 실행합니다.
				System.out.println("학생 정보 삭제" + count + "건이 완료되었습니다.");
				System.out.println("삭제를 성공했습니다.!");
			} // try
			catch (SQLException e) {
				System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요");
			}catch (NumberFormatException e) {
				System.out.println("나이 입력이 잘못되었습니다. 정수값을 입력해주세요.");
			}
			System.out.println("------------------------------------------------");
		
		ps.close();
		sc.close();
		
		System.out.println("입력한 학생정보 총 건이 성공적으로 삭제가 완료되었습니다.");
	}
	}