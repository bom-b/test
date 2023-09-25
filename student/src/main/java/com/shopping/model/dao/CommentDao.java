package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Comment;

public class CommentDao extends SuperDao {

	
	// 해당 게시물 번호에 달려 있는 댓글 목록을 반환해줍니다.
	public List<Comment> getDateByPk(int no) throws Exception {
		
		String sql = " select * from comments ";
		sql += " where no = ? order by cnum asc ";
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection();
		
		pstmt = conn.prepareStatement(sql) ;
		pstmt.setInt(1, no);
		
		rs = pstmt.executeQuery() ;
		
		List<Comment> lists = new ArrayList<Comment>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	private Comment getBeanData(ResultSet rs) throws Exception{
		Comment bean = new Comment();
		
		bean.setCnum(rs.getInt("cnum"));
		bean.setContent(rs.getString("content"));
		bean.setId(rs.getString("id"));
		bean.setNo(rs.getInt("no"));
		bean.setRegdate(rs.getString("regdate"));
		// 날짜만 나오게 하고싶다면, 
		// bean.setRegdate(String.valueOf(rs.getString("regdate")));
		
		return bean;
	}

	// 넘겨진 댓글 정보를 데이터 베이스에 추가합니다.
	public int InsertData(Comment bean) throws Exception {
		//데이터가 잘 넘어 왔는지 확인하는 디버깅코드
		System.out.println(bean);
		
		int cnt = -1;
		
		// 삽입하는 sql 문장
		String sql = " insert into comments(cnum, no, id, content, regdate)";
		sql += " values(seqcomm.nextval, ?, ?, ?, sysdate)";
		
		PreparedStatement pstmt = null;
		conn = super.getConnection();
		
		// 자동 커밋 끄기
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환
		pstmt.setInt(1, bean.getNo());
		pstmt.setString(2, bean.getId());
		pstmt.setString(3, bean.getContent());

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

	// 댓글 번호 cnum을 삭제합니다.
	public int DeleteData(int cnum) throws Exception {
		
		PreparedStatement pstmt = null ;
		String sql = " delete from comments where cnum = ? " ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql) ;
				
		int cnt = -1 ;
		pstmt.setInt(1, cnum);
		
		cnt = pstmt.executeUpdate() ;
		conn.commit();
		
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return cnt ;
	}
	
	
}
