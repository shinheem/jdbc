package koreait.jdbc.day04;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {

	public static void main(String[] args) {
		System.out.println(":::::::::::::: 김땡땡 쇼핑몰에 오신걸 환영합니다. :::::::::");
		System.out.println("<<상품 소개>>");
		JProductDao pdao = new JProductDao();
		try {
			List<JProduct> products = pdao.selectAll();
			for(JProduct product : products) {
				System.out.println(product);
			}
		} catch (SQLException e) {
			System.out.println("상품소개 예외 : " + e.getMessage());
		}

		
		System.out.println("\n<< 편리한 상품 구매를 위해 검색하기 >>");
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 입력 >>>");
		String pname = sc.nextLine();
		try {
			List<JProduct> products = pdao.selectByPname(pname);
			for(JProduct product : products) {
				System.out.println(product);
			}
		} catch (SQLException e) {
			System.out.println("상품검색 예외 : " + e.getMessage());
	}
		CustomerDao cdao = new CustomerDao();
		JCustomer customer = null;
		boolean isLogin = false;
		String custom_ID = null;
		while(!isLogin) {
		System.out.println("상품 구매를 위해 로그인 하기");
		System.out.println("간편 로그인 = 사용자 id입력 (로그인 취소(0000)) >> ");
		custom_ID = sc.nextLine();
		if(custom_ID.equals("0000")) {
			System.out.println("로그인을 취소합니다.");
		break;
		}
		try {
			
			customer = cdao.logIn(custom_ID);
			if(customer != null) {
				System.out.println(customer.getName() + " 고객님 환영합니다.!!!");
				isLogin = true;
			}else {
				System.out.println("입력하신 고객 ID는 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		} catch (Exception e) {
			System.out.println("간편 로그인 예외 : " + e.getMessage());
		}
		}
		// 장바구니 담기는 로그인 상태인 경우만 실행하기
		JBuyDao bdao = new JBuyDao();
		List<JBuy> carts = new ArrayList<>();
		if(isLogin) {
			while(true) {
				System.out.println("\n장바구니에 담기 합니다. 그만하려면 상품코드 0000 입력하세요.");
				System.out.println("상품코드를 입력하세요.");
				String pcode = sc.nextLine();
				if(pcode.equals("0000")) break;
				System.out.println("구매할 수량을 입력하세요. >>> ");
				int quantity = Integer.parseInt(sc.nextLine());
				carts.add(new JBuy(0, custom_ID, pcode, quantity, null));
				
				System.out.println("장바구니에 담긴 상품을 결제하시겠습니까? (y/Y)");
				if(sc.nextLine().toLowerCase().equals("y")) break;
			}
			
		//	System.out.println("장바구니 확인 : " + carts);
			System.out.println("마이페이지 - 장바구니");
			for(JBuy b : carts) {
				System.out.println("pcode :" + b.getPcode() + ",수량 :" + b.getQuantity());
			}
			//dao에서 carts를 전달받아 list만큼 반복하는 insert 실행하기
			int count = bdao.insertMany(carts);
			if(count != 0)
				System.out.println("예외발생. 결제 취소");
			else
			System.out.println("\n" + "결제를 완료했습니다. 현재까지 " +customer.getName() + "회원님의 구매 내역 입니다.");
			
			try {
				List<MyPageBuy> buys = bdao.mypageBuy(custom_ID);
				//
				for(MyPageBuy b : buys) {
					System.out.println(String.format("%15s %20s %5d %10d %16s", 
							b.getBuy_date(),
							b.getQuantity(),
							b.getPrice(),
							b.getTotal()));
				}
				DecimalFormat df = new DecimalFormat("###,###,###");
				long total = bdao.myMoney(custom_ID);
				System.out.println("총 구매금액 : " + df.format(total));
				
			} catch (SQLException e) {
				System.out.println("구매내역 예외 : " + e.getMessage());
			}
			
			//5번에 장바구니 담긴 상품이 j_buy 테이블에 1) 정상 저장 , 2) 잘못된 수량 rollback 까지 되는지 확인
			//6. 마이 페이지 - 구매 내역 보기. 총 구매 금액을 출력해주기 -> sql 테스트 해보고 메소드 작성 시도하기
		}else {
			System.out.println("로그인을 취소했습니다. 프로그램을 종료합니다.");
			}
		sc.close();
		}
	}