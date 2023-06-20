package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

public class JProductDao {

	
	//2. 상품 목록 보기
	public List<JProduct> selectAll() throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from J_PRODUCT";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<JProduct> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new JProduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		ps.close();
		conn.close();
		return list;
	}
	
	//3. 상품명으로 검색하기
	public List<JProduct> selectByPname(String pname) throws SQLException {
		//pname은 사용자가 입력한 검색어
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from J_PRODUCT where pname like '%' || ? || '%'"; 
		//like는 유사 비교. % 기호 사용(자바에서는 %가 포맷의 기호이기 때문에 문자형으로 묶어서 ||연결 연산자 사용
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pname);
		ResultSet rs = ps.executeQuery();
		
		List<JProduct> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new JProduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
	}
		ps.close();
		conn.close();
		return list;
	}
}
