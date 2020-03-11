package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.vo.BookVO;

public class BookDAO {
	
	private Connection con;
	
	public BookDAO(Connection con) {
		this.con = con;
	}
	
	public List<String> select(String keyword) throws Exception {

		ArrayList<String> list = new ArrayList<String>();
		String sql = "select btitle from book where btitle like ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%" + keyword + "%");
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			list.add(rs.getString("btitle"));
		}
		
		rs.close();
		pstmt.close();
		
		return list;
	}

	public List<BookVO> selectBooks(String keyword) throws Exception {
		List<BookVO> list = new ArrayList<BookVO>();
		String sql = "select btitle,bimgurl,bauthor,bprice from book where btitle like ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%" + keyword + "%");
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			BookVO book = new BookVO(rs.getString("bimgurl"), 
					rs.getString("btitle"),
					rs.getString("bauthor"),
					rs.getString("bprice"));
			list.add(book);
		}
		
		rs.close();
		pstmt.close();
		
		return list;
	}
	
}
