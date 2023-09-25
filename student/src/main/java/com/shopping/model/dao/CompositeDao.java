package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Combo01;
import com.shopping.model.bean.Combo02;
import com.shopping.model.bean.Combo03;
import com.shopping.model.bean.Combo04;
import com.shopping.model.bean.Combo05;

public class CompositeDao extends SuperDao {

	
	public List<Combo01> view01() throws Exception {
		
		String sql = " select m.name, b.subject, b.content, b.regdate\r\n "
				+ " from members m inner join boards b \r\n "
				+ " on m.id = b.id\r\n "
				+ " order by m.name ";
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql) ;
		rs = pstmt.executeQuery() ;
		
		List<Combo01> lists = new ArrayList<Combo01>();
		
		while(rs.next()) {
			Combo01 bean = new Combo01();
			bean.setName(rs.getString("name"));
			bean.setSubject(rs.getString("subject"));
			bean.setContent(rs.getString("content"));
			bean.setRegdate(String.valueOf(rs.getString("regdate")));
			
			lists.add(bean);
		}
		
		
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	public List<Combo02> view02() throws Exception {
		
		String sql = " select m.name, count(*) as cnt ";
		sql += " from members m inner join boards b ";
		sql += " on m.id = b.id ";
		sql += " group by m.name ";
		sql += " order by m.name ";
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql) ;
		rs = pstmt.executeQuery() ;
		
		List<Combo02> lists = new ArrayList<Combo02>();
		
		while(rs.next()) {
			Combo02 bean = new Combo02();
			bean.setName(rs.getString("name"));
			bean.setCnt(rs.getInt("cnt"));
			lists.add(bean);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	public List<Combo03> view03() throws Exception {

		String sql = " select m.name mname, p.name pname, o.orderdate, od.qty, p.price, od.qty * p.price as amount ";
		sql += " from ((members m inner join orders o ";
		sql += " on m.id=o.id) inner join orderdetails od ";
		sql += " on o.oid=od.oid) inner join products p ";
		sql += " on od.pnum=p.pnum ";
		sql += " order by p.name desc, m.name asc ";
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql) ;
		rs = pstmt.executeQuery() ;
		
		List<Combo03> lists = new ArrayList<Combo03>();
		
		while(rs.next()) {
			Combo03 bean = new Combo03();
			bean.setMname(rs.getString("mname"));
			bean.setPname(rs.getString("pname"));
			bean.setOrderdate(rs.getString("orderdate"));
			bean.setQty(rs.getInt("qty"));
			bean.setPrice(rs.getInt("price"));
			bean.setAmount(rs.getInt("amount"));
			
			lists.add(bean);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	public List<Combo04> view04() throws Exception {
		
		String sql = " select m.id, sum(od.qty * p.price) as sumtotal ";
		sql += " from ((members m inner join orders o ";
		sql += " on m.id=o.id) inner join orderdetails od ";
		sql += " on o.oid=od.oid) inner join products p ";
		sql += " on od.pnum=p.pnum ";
		sql += " group by m.id  ";
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql) ;
		rs = pstmt.executeQuery() ;
		
		List<Combo04> lists = new ArrayList<Combo04>();
		
		while(rs.next()) {
			Combo04 bean = new Combo04();
			bean.setId(rs.getString("id"));
			bean.setSumtotal(rs.getInt("sumtotal"));
			lists.add(bean);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}
	
	public List<Combo05> View05() throws Exception{		
		String sql = " select m.id, count(m.id) as cnt ";
		sql += " from members m left outer join orders o";
		sql += " on m.id=o.id";
		sql += " group by m.id";
		sql += " order by cnt desc, m.id asc";
		
		List<Combo05> lists = new ArrayList<Combo05>() ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn=super.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery() ;
		
		while(rs.next()) {
			Combo05 bean = new Combo05() ;
			
			bean.setCnt(rs.getInt("cnt"));
			bean.setId(rs.getString("id"));
			
			lists.add(bean) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
	}	
	
}
