package com.test.service;

import static common.DBTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.test.dao.BookDAO;
import com.test.vo.BookVO;

public class BookService {

	// Business Logic(Transaction)
	public List<String> getBooksTitle(String keyword) {
		// 처리를 포함한 로직처리
		Connection con = getConnection();
		List<String> list = null;
		BookDAO dao = new BookDAO(con);
		try {
			list = dao.select(keyword);
			if( list != null ) {
				commit(con);
			} else {
				rollback(con);
			}			
		} catch(Exception e) {
			rollback(con);
			System.out.println(e);
		} finally {
			close(con);
		}
		
		return list;
	}

	public List<BookVO> getBooks(String keyword) {

		Connection con = getConnection();
		List<BookVO> list = null;
		BookDAO dao = new BookDAO(con);
		try {
			list = dao.selectBooks(keyword);
			if( list != null ) {
				commit(con);
			} else {
				rollback(con);
			}			
		} catch(Exception e) {
			rollback(con);
			System.out.println(e);
		} finally {
			close(con);
		}
		
		return list;

	}
	
}
