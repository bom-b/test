package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Board;
import com.shopping.model.bean.Member;
import com.shopping.model.bean.Product;
import com.shopping.utility.Paging;

public class BoardDao extends SuperDao {
	
	public Board getDataByPK(int no) {
		Board bean = new Board(no, "hong", "abc123", "자바", "자바너무어려워", 11, "2023-08-02", 0);
			
		return bean;
	}
	
	public List<Board> getDataList(){
		List<Board> lists = new ArrayList<Board>();
		
		lists.add(new Board(1, "hong", "abc123", "자바", "자바너무어려워", 11, "2023-08-02", 0));
		lists.add(new Board(2, "kong", "abc", "자바", "자바너무어려워", 2, "2023-08-02", 0));
		lists.add(new Board(3, "song", "123", "자바", "자바너무어려워", 131, "2023-08-02", 1));
		lists.add(new Board(4, "tong", "abc123", "자바", "자바너무어려워", 41, "2023-08-02", 1));
		lists.add(new Board(5, "dong", "ab", "자바", "자바너무어려워", 121, "2023-08-02", 2));
		lists.add(new Board(6, "bong", "a3", "자바", "자바너무어려워", 321, "2023-08-02", 2));
		lists.add(new Board(7, "sang", "ab23", "자바", "자바너무어려워", 112, "2023-08-02", 2));
		
		return lists;
	}
	// 게시물 bean 데이터를 이용하여 등록합니다.
	public int InsertData(Board bean) throws Exception {
		// 데이터가 잘 넘어 오는지 보는 디버깅코드
		System.out.println(bean);
		
		int cnt = -1;
		
		// 삽입하는 sql 문장
		String sql = " insert into boards(no, id, password, subject, content, regdate, groupno, orderno, depth)";
		sql += " values(seqboard.nextval, ?, ?, ?, ?, ?, seqboard.currval, default, default)";
		
		PreparedStatement pstmt = null;
		
		conn = super.getConnection();
		
		// 자동 커밋 끄기
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getPassword());
		pstmt.setString(3, bean.getSubject());
		pstmt.setString(4, bean.getContents());
		pstmt.setString(5, bean.getRegdate());
		
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

	// 게시물 목록을 게시물 번호 역순 정렬하여 반환한다.
	public List<Board> selectAll() throws Exception {
		PreparedStatement pstmt = null;
		// rs : 컴퓨터 메모리에 놓여있는 n행 n열의 테이블같은것
		ResultSet rs = null;
		
		String sql = " SELECT * FROM boards order by no desc";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		List<Board> lists = new ArrayList<Board>();
		
		while(rs.next()) {
			Board bean = new Board();
			bean = getBeanData(rs);
			lists.add(bean);

		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}
	
	// 테이블의 총 행 개수를 구합니다.
	public int GetTotalRecordCount() throws Exception{
		String sql = " select count(*) as cnt from boards " ;
		
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
	
	// 검색어를 받아서 테이블의 총 행 개수를 구합니다. 
	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
		System.out.print("검색할 필드명 (칼럼명) : " + mode);
		System.out.println(" / 검색할 키워드 : " + keyword);
		
		
		String sql = " select count(*) as cnt from boards " ;
		
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
	public List<Board> selectAll(Paging pageInfo) throws Exception {

		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select no, id, password, subject, content, readhit, regdate, remark, groupno, orderno, depth, likes, hates " ;
		
		// 답글시스템 이전
		// sql += " from (select no, id, password, subject, content, readhit, regdate, remark, groupno, orderno, depth, rank() over(order by no desc) as ranking " ;
		
		sql += " from (select no, id, password, subject, content, readhit, regdate, remark, groupno, orderno, depth, likes, hates, rank() over(order by groupno desc, orderno asc) as ranking " ;
		sql += " from boards " ;
		
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
		
		List<Board> lists = new ArrayList<Board>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs)) ;
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	// ResultSet 정보를 bean으로 만들어서 반환해 줍니다.
	private Board getBeanData(ResultSet rs) throws Exception {
		Board bean = new Board();
		
		bean.setNo(rs.getInt("no"));
		bean.setId(rs.getString("id"));
		bean.setPassword(rs.getString("password"));
		bean.setSubject(rs.getString("subject"));
		bean.setContents(rs.getString("content"));
		bean.setReadhit(rs.getInt("readhit"));
		bean.setRegdate(String.valueOf(rs.getDate("regdate")));
		bean.setDepth(rs.getInt("depth"));
		bean.setGroupno(rs.getInt("groupno"));
		bean.setOrderno(rs.getInt("orderno"));
		bean.setLikes(rs.getInt("likes"));
		bean.setHates(rs.getInt("hates"));
		
		return bean;
	}
	
	// 기본키 정보를 이용하여 Bean 객체를 구한다.
	public Board getDataByPrimarykey(String no) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM boards where no=?";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, no);
		
		rs = pstmt.executeQuery();
		
		Board bean = null;
		
		if (rs.next()) {
			bean = getBeanData(rs);
		}	
			
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	// 게시물 번호를 입력하여 해당 게시물에 대한 bean 객체를 반환해줍니다.
	public Board GetDataByPK(Integer no) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select * from boards where no = ? ";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		// 치환
		pstmt.setInt(1, no);
		
		rs = pstmt.executeQuery();
		
		Board bean = null;
		
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
	public int updateData(Board bean) throws Exception {
		System.out.println(bean);
		int cnt = -1;
		
		// 수정하는 sql 문장
		String sql = " update boards set id = ?, password = ?, subject = ?, content = ?, regdate = ?, groupno = ?, orderno = ?, depth = ? ";
		sql +=  " where no = ? " ; 
		
		PreparedStatement pstmt = null;
		
		conn = super.getConnection();
		
		// 자동 커밋 끄기
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getPassword());
		pstmt.setString(3, bean.getSubject());
		pstmt.setString(4, bean.getContents());
		pstmt.setString(5, bean.getRegdate());
		pstmt.setInt(6, bean.getGroupno());
		pstmt.setInt(7, bean.getOrderno());
		pstmt.setInt(8, bean.getDepth());
		pstmt.setInt(9, bean.getNo());
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
	
	// 해당 그룹 번호의 데이터 행 개수를 반환해줍니다.

	public Integer GetTotalRecordCount(int groupno) throws Exception {
	    System.out.println("검토할 그룹 번호 : " + groupno);
	    
	    int cnt = -1;
	    
	    PreparedStatement pstmt = null;
	    
	    String sql = "SELECT count(*) AS cnt FROM boards WHERE groupno = ?";
	    
	    conn = super.getConnection();
	    conn.setAutoCommit(false);
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, groupno);
	        
	        rs = pstmt.executeQuery(); // executeQuery로 변경
	        
	        if (rs.next()) {
	            cnt = rs.getInt("cnt");
	        }
	        
	        conn.commit();
	    } catch (SQLException e) {
	        // 예외 처리 필요
	        e.printStackTrace();
	        throw e; // 예외를 다시 던지거나 적절한 방식으로 처리해야 합니다.
	    } finally {
	        // 리소스 정리
	        if (rs != null) {
	            rs.close();
	        }
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	    
	    return cnt;
	}

	// 게시물 bean 데이터를 이용하여 답글을 작성합니다.
	public int ReplyData(Board bean, Integer orderno) throws Exception {
		// 데이터가 잘 넘어 오는지 보는 디버깅코드
		System.out.println(bean);
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		// step1 : 동일한 그룹 번호에 대하여 orderno 컬럼의 숫자를 1씩 증가시켜야 한다.
		String sql1 = " update boards set orderno = orderno + 1 ";
		sql1 += " where groupno = ? and orderno > ? " ;
		
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, bean.getGroupno());
		pstmt.setInt(2, orderno);
		cnt = pstmt.executeUpdate();
		
		// Step2 : 게시물 bean 데이터를 이용하여 답글을 작성합니다.
		pstmt = null;
		String sql2 = " insert into boards(no, id, password, subject, content, regdate, groupno, orderno, depth)";
		sql2 += " values(seqboard.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		pstmt = conn.prepareStatement(sql2);
		
		// ? 치환
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getPassword());
		pstmt.setString(3, bean.getSubject());
		pstmt.setString(4, bean.getContents());
		pstmt.setString(5, bean.getRegdate());
		pstmt.setInt(6, bean.getGroupno());
		pstmt.setInt(7, bean.getOrderno());
		pstmt.setInt(8, bean.getDepth());
		
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
	
	// 게시물의 pk와 이모티콘 종류의 값을 받아서 업데이트

	public int UpdateEmoticon(int no, String columnName) throws Exception {
		int cnt = -1;
		
		String sql = " update boards set " + columnName + "=" + columnName + " +1 ";
		sql += " where no = ? ";
		
		PreparedStatement pstmt = null;
		conn = super.getConnection();
		
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환
		pstmt.setInt(1, no);
		
		//실행
		//성공했다면 cnt는 1이 된다.
		cnt = pstmt.executeUpdate();
		// 커밋
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}


	

	
}
