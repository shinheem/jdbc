package koreait.jdbc.day04;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@AllArgsConstructor
public class JBuy {		//구매와 관련된 CRUD 실행 SQL. DAO : JCustomerDao, JProductDao
// 메소드 이름은 insert, update, delete, select, selectByPname 등등으로 이름을 작성하세요.
	private int buySeq;
	private String customid;
	private String pcode;
	private int quantity;
	private Date buyDate;
}
//필드값이 모두 같으면 equals로 true가 되도록 하고싶다.
//-> equals 와 hashcode를 재정의 해야 합니다.(불변객체)
//=> vo 입니다. vo는 테스트 케이스에서 객체를 비교할 때 사용할 수 있습니다.
//	 assertEquals 비교.