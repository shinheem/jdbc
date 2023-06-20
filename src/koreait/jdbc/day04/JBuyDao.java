package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koreait.jdbc.day02.OracleUtility;

public class JBuyDao {

	//트랜잭션을 처리하는 예시(질의어(SQL)를 이용하여 데이터베이스를 접근 하는 것을 의미) : auto commit을 해제하고 직접 commit합니다.*
	// try catch를 직접하세요. throws 아닙니다.
	public int insertMany(List<JBuy> carts) {
		Connection conn = OracleUtility.getConnection();
		String sql = "insert into j_buy \n" + "values (jbuy_seq.nextval, ?, ?, ?, sysdate)";
		
		int count = 0;
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);			//auto commit 설정 - false
			ps = conn.prepareStatement(sql);
			for(JBuy b : carts) {
				ps.setString(1, b.getCustomid());
				ps.setString(2, b.getPcode());
				ps.setInt(3, b.getQuantity());
				count += ps.executeUpdate();
			}
			conn.commit();		//커밋
		} catch (SQLException e) {
			System.out.println("장바구니 상품 구매하기 예외 : " + e.getMessage());
			System.out.println("장바구니 상품 구매를 취소합니다.");
			try {
				conn.rollback();	//롤백
			}catch (SQLException e1){
			}
		}
		return count;
		}
	//	select * from mypage_buy where customid = 'twice';
	public List<MyPageBuy> mypageBuy(String customid) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from mypage_buy where custom_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, customid);	// ? 에 들어갈 main에서 받아올 인자값 
		ResultSet rs = ps.executeQuery();
		
		List<MyPageBuy> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new MyPageBuy(rs.getDate(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5), rs.getInt(6),rs.getLong(7)));
			// 
		}
		return list;
	}
	// select sum(total) from mypage_buy where customid = 'twice';
	public long myMoney(String customid) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select sum(total) from mypage_buy where custom_id = ?";
		//함수 조회하는 select는 항상 결과행이 1개, 컬럼도 1개
		
		PreparedStatement ps = conn.prepareStatement(sql); // ==> PreparedStatement는 sql문을 실행하기 위해 미리 준비가 돼있는 wrapper(묶여있는)클래스
		ps.setString(1, customid);	// 
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		long total = rs.getLong(1);// select를 sum(total)로 합쳤을 때 정수값이 나오기 때문에 리턴값을 long으로 지정
		
		return total;
	}
	public int insert(JBuy buy) {
		
		return 1;
	}
	public JBuy selectOneTest(int buyseq) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from j_buy where buy_seq = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, buyseq);
		JBuy buy = null;
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			buy = new JBuy(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5));
		}
		return buy;
	}
	}