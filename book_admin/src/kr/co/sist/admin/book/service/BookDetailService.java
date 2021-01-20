package kr.co.sist.admin.book.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.exceptions.PersistenceException;

import kr.co.sist.admin.book.dao.AdminBookDAO;
import kr.co.sist.admin.book.domain.BookDetailDomain;
import kr.co.sist.admin.book.vo.BookModifyVO;

public class BookDetailService {
	/**
	 * Method : ������ �߰��ϴ� ��
	 * �ۼ��� : ��ȿ��
	 * �����̷� : 2020-10-08
	 * @param bmVO
	 */
	public int addBook(BookModifyVO bmVO) {
		AdminBookDAO abDAO = AdminBookDAO.getInstance();
		int cnt = 0;
		try {
			cnt = abDAO.insertBook(bmVO);
		} catch (PersistenceException e) {
			e.printStackTrace();
			cnt = 0;
		}
		return cnt;
	}
	
	/**
	 * Method : ���� ������ �����ϴ� ��
	 * �ۼ��� : ��ȿ��
	 * �����̷� : 2020-10-08
	 * @param bmVO
	 * @return
	 */
	public int changeBook(BookModifyVO bmVO) {
		int result = 0;
		AdminBookDAO abDAO = AdminBookDAO.getInstance();
		result = abDAO.updateBook(bmVO);
		return result;
	}
	
	/**
	 * Method : �ش� ������ �������� ��� ��
	 * �ۼ��� : ��ȿ��
	 * �����̷� : 2020-10-08
	 * @param book_isbn
	 * @return
	 */
	public BookDetailDomain selectBookDetail(String book_isbn) {
		BookDetailDomain bdd = null;
		AdminBookDAO abDAO = AdminBookDAO.getInstance();
		bdd = abDAO.selectBookDetail(book_isbn);
		
		return bdd;
	}
}