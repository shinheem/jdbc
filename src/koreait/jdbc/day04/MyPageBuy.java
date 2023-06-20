package koreait.jdbc.day04;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@Getter @AllArgsConstructor @Builder @ToString
public class MyPageBuy {
   private Date buy_date;
   private String customid;
   private String pcode;
   private String pname;
   private int quantity;
   private int price;
   private long total;
}


