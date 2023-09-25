package com.shopping.model.mall;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

// CartManager 클래스를 위한 한시적 테스트용 클래스
public class CartTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		CartManager manager = new CartManager(); // 카트관리자 객체
		CartMap cartmap = new CartMap();
		int pnum = 0, qty = 0;
		
		System.out.println("어떤 품목들이 있나요?");
		Map<Integer, CartItem> itemList = cartmap.GetItemList();
		
		for(Integer key : itemList.keySet()) {
			CartItem item = itemList.get(key);
			System.out.println(item);
		}
		
		System.out.println("-----------------");
		
		while (true) {
			System.out.println("\n1.카트 목록, 2.추가, 3.삭제, 4.수정, 5.품목 개수 확인, 6.카트 비우기, 0.종료");
			int menu = scan.nextInt();
			
			switch (menu) {
			case 1: 
				System.out.println("구매 수량 정보");
				Map<Integer, Integer> cartList = manager.GetAllList();
				System.out.println(cartList);
				
				break;
			
			case 2: 
				System.out.println("추가할 상품 번호 입력: ");
				pnum = scan.nextInt();
				
				System.out.println("구매 수량 입력: ");
				qty = scan.nextInt();
				
				manager.AddCart(pnum, qty);
				break;
						
			case 3: 
				System.out.println("삭제할 상품 번호 입력: ");
				pnum = scan.nextInt();
				
				manager.DeleteCart(pnum);
				break;
			
			case 4: 
				System.out.println("이전 개수를 덮어쓰기 합니다.");
				System.out.println("수정할 상품번호 입력 : ");
				pnum = scan.nextInt();
				
				System.out.println("구매 수량 입력: ");
				qty = scan.nextInt();
				
				manager.EditCart(pnum, qty);
				break;
			
			case 5: 
				int size = manager.getCartItemSize();
				System.out.println("구매할 품목 개수" + size);
				break;
				
			case 6: 
				System.out.println("모든 품목을 카트에서 비웁니다.");
				manager.RemoveAllCart();
				break;
			
			case 0: 
				System.out.println("프로그램 종료");
				System.exit(0); // 정상종료되었음을 운영체제에게 통보하기
				break;
			
			default:
				
			}
		}
	}
}
