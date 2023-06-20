package koreait.jdbc.day04;

import java.sql.Date;
import koreait.jdbc.day02.OracleUtility;
import koreait.jdbc.day03.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString
//DTO : JBuy, JProduct
public class JCustomer {
	private String custom_id;
	private String name;
	private String email;
	private int age;
	private Date regDate;
}
