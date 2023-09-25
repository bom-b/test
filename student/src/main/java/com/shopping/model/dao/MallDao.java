package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shopping.model.bean.Member;
import com.shopping.model.bean.Order;
import com.shopping.model.bean.WishList;
import com.shopping.model.mall.CartItem;

public class MallDao extends SuperDao {

	// payer : 계산을 하는 사람 (포인트 적립 대상자),
	// cartList : 카트에 담겨있는 상품번호와 구매수량
	public void Calculate(Member payer, Map<Integer, Integer> cartList) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = -1;
		
		String sql = "";
		
		conn = super.getConnection();
		conn.setAutoCommit(false);

		//step1 : 주문 테이블(Orders)에 매출 1건 입력을 해야됨
		sql = " insert into orders(oid, id, orderdate) ";
		sql += " values(seqoid.nextval, ?, sysdate) ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, payer.getId());
		
		pstmt.executeUpdate();
		pstmt = null;
		
		//step2 : step1에서 방금 들어간 송장번호 읽기
		sql = " select max(oid) as invoice from orders ";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		int invoice = 0;
		if (rs.next()) {
			invoice = rs.getInt("invoice");
		}
		pstmt = null;
		
		int totalPoint = 0; // 총 적립 포인트
		
		//반복문을 사용하여 
		for(Integer pnum : cartList.keySet()) {
			
			//step3 : 주문 상세 테이블(OrderDetails)에 각 데이터 추가하기
			sql = " insert into orderDetails(odid, oid, pnum, qty) ";
			sql += " values(seqodid.nextval, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, invoice); // 송장번호
			pstmt.setInt(2, pnum); // 상품번호
			pstmt.setInt(3, cartList.get(pnum)); // 구매 수량
			
			pstmt.executeUpdate();
			pstmt = null;
			
			//step4 : 각 상품들의 재고 수량 뺄샘하기
			sql = " update products set stock = stock - ? ";
			sql += " where pnum = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cartList.get(pnum)); // 구매 수량
			pstmt.setInt(2, pnum); // 상품번호
			
			pstmt.executeUpdate();
			pstmt = null;
			
			// 각 상품들의 적립포인트를 구해줍니다.
			int point = cartList.get(pnum) * new ProductDao().GetMileagePoint(pnum);
			totalPoint += point;
		}	
		
		 
		
		//step5 : 해당회원의 적립 포인트 변경
		sql = " update members set mpoint = mpoint + ? ";
		sql += " where id = ? ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, totalPoint); // 적립할 포인트
		pstmt.setString(2, payer.getId()); // 회원아이디
		
		pstmt.executeUpdate();
		pstmt = null;
		
		// step 탈출!
		conn.commit();
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		
		
	}

	// 과거 쇼핑 내역을 최신 날짜 순으로 정렬하여 반환
	public List<Order> GetHistory(String id) throws Exception {
		String sql = " select * from orders where id =? order by orderdate desc ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		// 쇼핑내역을 담을 리스트
		List<Order> orderlist = new ArrayList<Order>();
		
		while(rs.next()) {
			
			orderlist.add(this.makeOrderBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return orderlist;
		
	}
	
	// rs에서 가져온 것을 bean으로 만든다.
	private Order makeOrderBean(ResultSet rs) throws Exception {
		Order bean = new Order();
		
		bean.setId(rs.getString("id"));
		bean.setOid(rs.getInt("oid"));
		bean.setOrderdate(String.valueOf(rs.getString("orderdate")));
		
		return bean;
	}

	// 해당 송장번호에 대한 주문 정보를 반환해줍니다.
	public Order getDetailHistory(int oid) throws Exception {
		
		String sql = " select * from orders where oid = ? ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, oid);
		
		ResultSet rs = pstmt.executeQuery();
		
		Order bean = null;
		
		if(rs.next()) {
			bean = this.makeOrderBean(rs);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}
	
	// 송장번호에 대한 세상사내역을 컬렉션 형태로 반환합니다.

	public List<CartItem> showDetail(int oid) throws Exception {
		
		String sql = " select p.pnum, p.name pname, od.qty, p.price, p.point, p.image01 " ;
		sql += " from (orders o inner join orderdetails od " ;
		sql += " on o.oid = od.oid) inner join products p  " ;
		sql += " on od.pnum = p.pnum and o.oid = ?  " ;
		sql += " order by od.odid desc " ;
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, oid);
		
		ResultSet rs = pstmt.executeQuery();
		
		
		List<CartItem> lists = new ArrayList<CartItem>();
		
		while(rs.next()) {
			lists.add(this.makeCartItemBean(rs));
			
			
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	// 
	private CartItem makeCartItemBean(ResultSet rs) throws Exception {
		
		CartItem item = new CartItem();
		
		// item.setId(rs.getString("id"));
		item.setImage01(rs.getString("image01"));
		item.setPname(rs.getString("pname"));
		
		item.setPnum(rs.getInt("pnum"));
		item.setPoint(rs.getInt("point"));
		item.setPrice(rs.getInt("price"));
		item.setQty(rs.getInt("qty"));
		
		return item;
	}

	// 로그인한 사람의 찜 목록을 데이터베이스에 추가합니다.
	public void insertWishList(String id, Map<Integer, Integer> whishList) throws Exception{
		
		String sql = "" ;
		int cnt = -1;
		
		PreparedStatement pstmt = null;
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		
		// step01 : 과거 나의 찜 정보가 있으면 일단 삭제합니다.
		sql = " delete from wishlist where id = ? " ;
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		cnt = pstmt.executeUpdate();
		
		if(pstmt != null) {pstmt.close();}
		
		// step02 : 현재 세션 정보를 반복하여 테이블에 추가합니다.
		sql = " insert into wishlist (id, pnum, qty) " ;
		sql += " values(?, ? ,?) "; 	
		
		pstmt = conn.prepareStatement(sql) ; 
		
		for (Integer pnum : whishList.keySet()) {
			pstmt.setString(1, id);
			pstmt.setInt(2, pnum);
			pstmt.setInt(3, whishList.get(pnum));
			cnt = pstmt.executeUpdate();	
		}
		
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
	 
	}

	
	// 나의 wishlist 항목을 컬렉션으로 반환합니다.
	public List<WishList> getWishList(String id) throws Exception {
		
		String sql = " select * from wishlist where id = ? " ;
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		List<WishList> lists = new ArrayList<WishList>();
		
		while(rs.next()) {
			lists.add(this.makeWishListBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	// rs에서 가져온 데이터 bean에 넣기
	private WishList makeWishListBean(ResultSet rs) throws Exception {
		WishList bean = new WishList();
		bean.setId(rs.getString("id"));
		bean.setPnum(rs.getInt("pnum"));
		bean.setQty(rs.getInt("qty"));
		
		return bean;
	}
	
	
}
