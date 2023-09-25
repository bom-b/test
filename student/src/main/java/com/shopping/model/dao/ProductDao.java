package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.shopping.model.bean.Board;
import com.shopping.model.bean.Product;
import com.shopping.model.mall.CartItem;
import com.shopping.utility.MyUtility;
import com.shopping.utility.Paging;

public class ProductDao extends SuperDao {
	
	public Product getDataByPK02(int pnum) {
		// 해당 상품 번호에 맞는 상품 bean을 반환합니다.
		if(pnum ==1) {
			return new Product(1, "바게트", "파리", "french_baguette_01.png", "french_baguette_02.png", "french_baguette_03.png", 10, 25000, "beverage", "이 상품은 매우 시원하고 맛이 좋습니다.", 2, "2023-08-02");
		} else if(pnum ==2) {
			return new Product(2, "포도쥬스", "델몬트", "juice02.png", "juice01.png", null, 10, 5000, "beverage", "사과쥬스", 2, "2023-08-02");
		} else {
			return new Product(3, "오렌지쥬스", "델몬트", "juice01.png", null, null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02");
		}
	}
	
	public Product getDataByPK(int pnum) {
		Product bean = new Product(pnum, "사과쥬스", "델몬트", "juice01.png", null, null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02");
			
		return bean;
	}
	
	public List<Product> getDatalist(){
		List<Product> lists = new ArrayList<Product>();
		
		lists.add(new Product(1, "바게트", "파리", "french_baguette_01.png", "french_baguette_02.png", "french_baguette_03.png", 10, 2000, "beverage", "이 상품은 매우 시원하고 맛이 좋습니다.", 2, "2023-08-02"));
		lists.add(new Product(2, "포도쥬스", "델몬트", "juice02.png", "juice01.png", null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02"));
		lists.add(new Product(3, "오렌지쥬스", "델몬트", "juice01.png", null, null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02"));
		lists.add(new Product(4, "사이다", "델몬트", "milk01.jpg", null, null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02"));
		lists.add(new Product(5, "콜라", "델몬트", "milk02.jpg", null, null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02"));
		lists.add(new Product(6, "바나나", "델몬트", "juice01.png", "whitewine01.png", null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02"));
		lists.add(new Product(7, "딸기", "델몬트", "juice01.png", null, null, 10, 2000, "beverage", "사과쥬스", 2, "2023-08-02"));
		
		return lists;
	}

	// 상품 목록을 pnum 순으로 정렬하여 반환한다.
	public List<Product> selectAll() throws Exception {
		PreparedStatement pstmt = null;
		// rs : 컴퓨터 메모리에 놓여있는 n행 n열의 테이블같은것
		ResultSet rs = null;
		
		String sql = " SELECT * FROM products order by pnum";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		List<Product> lists = new ArrayList<Product>();
		
		while(rs.next()) {
			Product bean = new Product();
			bean = getBeanData(rs);
			lists.add(bean);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}
	
	// ResultSet 정보를 bean으로 만들어서 반환해 줍니다.
	private Product getBeanData(ResultSet rs) throws Exception {
		Product bean = new Product();
		
		bean.setPnum(rs.getInt("pnum"));
		bean.setStock(rs.getInt("stock"));
		bean.setPrice(rs.getInt("price"));
		bean.setPoint(rs.getInt("point"));
		
		bean.setName(rs.getString("NAME"));
		bean.setCompany(rs.getString("COMPANY"));
		bean.setImage01(rs.getString("IMAGE01"));
		bean.setImage02(rs.getString("IMAGE02"));
		bean.setImage03(rs.getString("IMAGE03"));
		bean.setCategory(rs.getString("CATEGORY"));
		bean.setContents(rs.getString("CONTENTS"));

		bean.setInputdate(String.valueOf(rs.getDate("INPUTDATE")));
		
		
		return bean;
	}
	
	// 기입한 상품 정보를 이용하여 데이터 베이스에 추가합니다.
	public int InsertData(Product bean) throws Exception {
		// 로그
		System.out.println("상품등록 빈 :\n" + bean);
		
		PreparedStatement pstmt = null;
		String sql = " insert into products(pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate)";
		sql += " values(seqprod.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int cnt = -1;
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		
		// 물음표 치환
		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getCompany());
		pstmt.setString(3, bean.getImage01());
		pstmt.setString(4, bean.getImage02());
		pstmt.setString(5, bean.getImage03());
		pstmt.setInt(6, bean.getStock());
		pstmt.setInt(7, bean.getPrice());
		pstmt.setString(8, bean.getCategory());
		pstmt.setString(9, bean.getContents());
		pstmt.setInt(10, bean.getPoint());
		pstmt.setString(11, bean.getInputdate());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	// 테이블의 총 행 개수를 구합니다.
	public int GetTotalRecordCount() throws Exception{
		String sql = " select count(*) as cnt from products " ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection() ;
		pstmt = conn.prepareStatement(sql) ;
		
		rs = pstmt.executeQuery() ; 
		
		int cnt = -1 ;
		
		if(rs.next()) {
			cnt = rs.getInt("cnt") ;
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}
	
	// (검색 조건이 들어간) 테이블의 총 행 개수를 구합니다.
	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.print("검색할 필드명 (칼럼명) : " + mode);
		System.out.println(" / 검색할 키워드 : " + keyword);
		
		
		String sql = " select count(*) as cnt from products " ;
	
		if (mode == null || mode.equals("all")) {
			// 전체 모드, 또는 입력값이 안들어왔을경우
			
		} else {
			// 전체 모드가 아니라면,
			sql += " where " + mode + " like '%" + keyword + "%' " ;
		}
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection() ;
		pstmt = conn.prepareStatement(sql) ;
		
		rs = pstmt.executeQuery() ; 
		
		int cnt = -1 ;
		
		if(rs.next()) {
			cnt = rs.getInt("cnt") ;
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}

	// 페이징 클래스의 객체를 매개변수로 받아서 조건에 맞는 목록 반환하기
	// TopN 구문을 사용하여 페이징 처리된 게시물 목록을 반환하는 것임
	public List<Product> selectAll(Paging pageInfo) throws Exception{
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select PNUM, NAME, COMPANY, IMAGE01, IMAGE02, IMAGE03, STOCK, PRICE, CATEGORY, CONTENTS, POINT, INPUTDATE " ;
		sql += " from (select PNUM, NAME, COMPANY, IMAGE01, IMAGE02, IMAGE03, STOCK, PRICE, CATEGORY, CONTENTS, POINT, INPUTDATE, rank() over(order by PNUM desc) as ranking " ;
		sql += " from products "; 
		
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		if (mode == null || mode.equals("all")) {
			// 전체 모드, 또는 입력값이 안들어왔을경우

		} else {
			// 전체 모드가 아니라면,
			sql += " where " + mode + " like '%" + keyword + "%' " ;
		}
		
		sql += " ) " ;
		sql += " where ranking between ? and ? " ;
		
		conn = super.getConnection();
		
		pstmt = conn.prepareStatement(sql) ;
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		rs = pstmt.executeQuery() ;
		
		List<Product> lists = new ArrayList<Product>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs)) ;
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	// 상품 번호를 입력하여 해당 상품에 대한 bean 객체를 반환해줍니다.
	public Product GetDataByPK(Integer pnum) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select * from products where pnum = ? ";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		// 치환
		pstmt.setInt(1, pnum);
		
		rs = pstmt.executeQuery();
		
		Product bean = null;
		
		// 존재할때만 객체 생성을 하는게 좋다. 처음엔 null로 지정하는게 좋음
		if(rs.next()) {
			bean = getBeanData(rs);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	// 업데이트
	public int UpdateData(Product bean) throws Exception {
		System.out.println("상품등록 빈 :\n" + bean);
		
		PreparedStatement pstmt = null;
	    String sql = " update products set name = ?, company = ?, image01 = ?, image02 = ?, image03 = ?, stock = ?, price = ?, category = ?, contents = ?, point = ?, inputdate = ?" ;
	    sql += " where pnum = ? " ; 
	
		int cnt = -1;
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		
		// 물음표 치환
		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getCompany());
		pstmt.setString(3, bean.getImage01());
		pstmt.setString(4, bean.getImage02());
		pstmt.setString(5, bean.getImage03());
		pstmt.setInt(6, bean.getStock());
		pstmt.setInt(7, bean.getPrice());
		pstmt.setString(8, bean.getCategory());
		pstmt.setString(9, bean.getContents());
		pstmt.setInt(10, bean.getPoint());
		pstmt.setString(11, bean.getInputdate());
		pstmt.setInt(12, bean.getPnum());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	
	public CartItem GetCartItem(Integer pnum, Integer qty) throws Exception {
		Product bean = this.GetDataByPK(pnum); // 상품 정보
		
		CartItem item = new CartItem(); // 상품 정보 + 구매 수량 + 로그인 아이디
		
		item.setId(null); // 위시리스트용 테이블과 관련 있음(지금은 의미 없음)
		item.setImage01(bean.getImage01());
		item.setPname(bean.getName());
		item.setPnum(pnum);
		item.setPoint(bean.getPoint());
		item.setPrice(bean.getPrice());
		item.setQty(qty);
		
		return item;
	}

	public int GetMileagePoint(Integer pnum) throws Exception {
		int point = 0;
		
		String sql = " select point from products ";
		sql += " where pnum = ? ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 치환
		pstmt.setInt(1, pnum);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			point = rs.getInt("point");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		
		return point;
	}

	// 상품 번호를 이용하여 해당 상품을 삭제합니다.
	public int DeleteDate(int pnum) throws Exception{
		
		String sql = "";
		int cnt = 0;
		
		Product bean = this.GetDataByPK(pnum);
		String remark = MyUtility.getCurrnetTime() + bean.getName() + "(상품번호 : " + pnum + "번) 상품이 삭제 되었습니다.";
		
		PreparedStatement pstmt = null;
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		// step01 : 주문 상세 테이블의 remark(비고) 컬럼에 히스토리 남기기
		sql = " update orderdetails set remark = ? where pnum = ?  ";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, remark);
		pstmt.setInt(2, pnum);
		
		cnt = pstmt.executeUpdate();
		if (pstmt != null) {pstmt.close();} 
		
		// step02 : 상품 테이블에서 해당 상품 번호와 관련된 행 삭제하기
		sql = " delete from products where pnum = ? ";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, pnum);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if (pstmt != null) {pstmt.close();} 
		if (conn != null) {conn.close();} 
		
		return cnt;
	}

	
}
