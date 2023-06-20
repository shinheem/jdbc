package koreait.jdbc.day03;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoTest {

	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		
		System.out.println("\n::::::::1. insert 테스트::::::::");
		System.out.println("2023009 김땡구 17 강남구 - 데이터 입력");
		StudentDto dto = new StudentDto("2023009","김땡구", 17,"강남구");
		try {
			int count = dao.insert(dto);	// dao.insert(dto) == dao에서 insert를 가져와 인자를 넣은 dto를 대입
			System.out.println("학생 등록" + count + "건 입력 성공");
			System.out.println("입력 결과 조회 : " + dao.selectOne(dto.getStuno()));
		} catch (SQLException e) {
			System.out.println("예외 - " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("\n::::::::2. update 테스트::::::::");
		System.out.println("2023009 김땡구를 16 용산구 - 데이터 수정");
		dto = new StudentDto("2023009","김땡구", 16,"용산구");
		try {
			int count = dao.update(dto);
			System.out.println("학생 정보 수정" + count + "건 입력 성공");
			System.out.println("수정 결과 조회 : " + dao.selectOne(dto.getStuno()));
		} catch (SQLException e) {
			System.out.println("예외 - " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("\n::::::::3. delete 테스트::::::::");
		System.out.println("2023009 - 데이터 삭제");
		try {
			int count = dao.delete("2023009");
			System.out.println("학생 정보 삭제" + count + "건 입력 성공");
			System.out.println("삭제 결과 조회 : " + dao.selectOne(dto.getStuno()));
		} catch (SQLException e) {
			System.out.println("예외 - " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("\n::::::::4. selectAll::::::::");
		System.out.println("학생 테이블의 모든 데이터 조회하여 출력합니다.");
		try {
			List<StudentDto> list = dao.selectAll();
			for(StudentDto key : list) {
				System.out.println(key);
			}
		} catch (Exception e) {
			System.out.println("에러 - " + e.getMessage());
		}
	}
}
