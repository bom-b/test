package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.dao.MemberDao;
import com.shopping.utility.MyUtility;
import com.shopping.utility.Paging;
import com.shopping.model.bean.Board;
import com.shopping.model.bean.Member;
import com.shopping.model.bean.Product;

public class MemberDao extends SuperDao{
	
	public Member getDataByPK(String id) {
		Member bean = new Member(id, "홍길동", "abc123", "male", "2023-08-02", "미혼", 100, "역삼", "kim9", "탁구,축구,");
				
		return bean;
	}
	
	public List<Member> getDataList(){
		List<Member> lists= new ArrayList<Member>();
	
		lists.add(new Member("hong", "홍길동", "abc123", "male", "2023-08-02", "미혼", 1000, "역삼", "kim9", "탁구,축구,"));
		lists.add(new Member("park", "박영희", "abc123", "female", "2023-08-02", "결혼", 1050, "역삼", "kim9", "축구,"));
		lists.add(new Member("kang", "강우연", "abc123", "male", "2023-08-02", "미혼", 2100, "역삼", "yoon", "탁구,축구,"));
		lists.add(new Member("choi", "최영", "abc123", "male", "2023-08-02", "이혼", 3100, "역삼", "kim9", "농구"));
		lists.add(new Member("kim9", "김구", "abc123", "male", "2023-08-02", "이혼", 2100, "역삼", "", "농구"));
		
		return lists;
	}

	public Member getDataByPK(String id, String password) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// ?를 placeholder라고 합니다. 치환되어야할 영역.
		
		String sql = "SELECT * FROM members ";
		sql += "WHERE id = ? AND password = ?";
		
		conn = super.getConnection(); // 단계 2
		pstmt = conn.prepareStatement(sql); // 단계 3
		
		// 여기서 ?를 치환해야됨
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		
		rs = pstmt.executeQuery(); // 단계 4-1
			
		Member bean = null;
		// 1건이 조회됨
		if(rs.next()) {
			bean = getBeanData(rs);
		} 
		
		// 단계 5
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	private Member getBeanData(ResultSet rs) throws Exception {
		// ResultSet의 데이터를 읽어서 Bean에 기록한다음, 반환해줍니다.
		Member bean = new Member();
		
		bean.setId(rs.getString("id"));
		bean.setName(rs.getString("name"));
		bean.setPassword(rs.getString("password"));
		bean.setGender(rs.getString("gender"));
		
		bean.setBirth(String.valueOf(rs.getDate("birth")));
		//getDate() : 뒤에 시분초 잘라냄
		
		bean.setMarriage(rs.getString("marriage"));
		bean.setSalary(rs.getInt("salary"));
		bean.setAddress(rs.getString("address"));
		bean.setManager(rs.getString("manager"));
		bean.setMpoint(rs.getInt("mpoint"));
		
		return bean;
	}

	// 데이터 베이스에 멤버 정보 추가 (Bean 객체 정보를 이용하여 데이터베이스에 추가합니다.)
	public int InsertData(Member bean) throws Exception{
		//데이터가 잘 넘어 왔는지 확인하는 디버깅코드
		System.out.println(bean);
		
		int cnt = -1;
		
		// 삽입하는 sql 문장
		String sql = " insert into members(id, name, password, gender, birth, marriage, salary, address, manager)";
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
		
		conn = super.getConnection();
		
		// 자동 커밋 끄기
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getPassword());
		pstmt.setString(4, bean.getGender());
		pstmt.setString(5, bean.getBirth());
		pstmt.setString(6, bean.getMarriage());
		pstmt.setInt(7, bean.getSalary());
		pstmt.setString(8, bean.getAddress());
		pstmt.setString(9, bean.getManager());
		
		//실행
		//성공했다면 cnt는 1이 된다.
		cnt = pstmt.executeUpdate();
		// 커밋
		conn.commit();
		
		// 닫기
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	// 회원 목록을 이름순으로 정렬하여 반환한다.
	public List<Member> selectAll() throws Exception {
		PreparedStatement pstmt = null;
		// rs : 컴퓨터 메모리에 놓여있는 n행 n열의 테이블같은것
		ResultSet rs = null;
		
		String sql = " SELECT * FROM members order by name desc";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		List<Member> lists = new ArrayList<Member>();
		
		while(rs.next()) {
			Member bean = new Member();
			bean = getBeanData(rs);
			lists.add(bean);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	// 기본키 정보를 이용하여 Bean 객체를 구한다.
	public Member getDataByPrimarykey(String id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM members where id=?";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		Member bean = null;
		
		if (rs.next()) {
			bean = getBeanData(rs);
		}	
			
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}
	
	// 테이블의 총 행 개수를 구합니다.
	public int GetTotalRecordCount() throws Exception{
		String sql = " select count(*) as cnt from members " ;
		
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
	public List<Member> selectAll(Paging pageInfo) throws Exception{
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select ID, NAME, PASSWORD, GENDER, BIRTH, MARRIAGE, SALARY, ADDRESS, MANAGER " ;
		sql += " from (select ID, NAME, PASSWORD, GENDER, BIRTH, MARRIAGE, SALARY, ADDRESS, MANAGER, rank() over(order by ID) as ranking " ;
		sql += " from members) " ;
		sql += " where ranking between ? and ? " ;
		
		conn = super.getConnection();
		
		pstmt = conn.prepareStatement(sql) ;
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		rs = pstmt.executeQuery() ;
		
		List<Member> lists = new ArrayList<Member>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs)) ;
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	// 아이디에 해당하는 데이터 삭제
	public int deleteData(String id) throws Exception {
		
		String sql ="";
		
		int cnt = 0;
		
		Member bean = this.getDataByPrimarykey(id);
		String remark = MyUtility.getCurrnetTime() + bean.getName() + "(아이디 :" + id + ")님이 탈퇴를 하셨습니다.";
		
		PreparedStatement pstmt = null;
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		// step 01 : 게시물 테이블의 remark 컬럼 업데이트
		sql = " update boards set remark = ? where id = ? ";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, remark);
		pstmt.setString(2, id);
		
		cnt = pstmt.executeUpdate();
		if (pstmt != null) {pstmt.close();} 
		
		// step 02 : 주문 테이블의 remark 컬럼 업데이트
		sql = " update orders set remark = ? where id = ? ";
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, remark);
		pstmt.setString(2, id);
		
		cnt = pstmt.executeUpdate();
		if (pstmt != null) {pstmt.close();} 
		
		// step 03 : 회원 테이블의 id 행을 삭제
		sql = " delete from members where id = ? ";		
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		
		cnt = pstmt.executeUpdate();
		
		
		conn.commit();
		
		if (pstmt != null) {pstmt.close();} 
		if (conn != null) {conn.close();} 
		
		return cnt;
	}

	
}
