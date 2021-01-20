package kr.co.sist.user.book.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.user.book.service.BookService;
import kr.co.sist.user.book.vo.BookCountryVO;
import kr.co.sist.user.book.vo.SearchBookVO;
import kr.co.sist.user.pagination.PageVO;
import kr.co.sist.user.pagination.PaginationService;
import kr.co.sist.user.pagination.TotalCntVO;

@Controller
public class BookController {
  
	@RequestMapping(value = "/book.do", method = RequestMethod.GET)
	public String selectBookMain() {
		
		return "book/book_main";
	}
	
	@RequestMapping(value = "/booklist.do", method = RequestMethod.GET)
	public String selectListMain() {
		
		return "book/bookCoun_main";
	}
	
	
	@RequestMapping(value = "/bookbest.do", method = RequestMethod.GET)
	public String selectBestMain() {
		
		return "book/bookBest_main";
	}
	
	@RequestMapping(value = "/booknew.do", method = RequestMethod.GET)
	public String selectNewMain() {
		
		return "book/bookNew_main";
	}
	
	
	/**
	 * ����Ʈ������ �����ִ� �κ�. ����Ʈ �ؼ� ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bookList_best.do", method = RequestMethod.GET)
	public String selectBestBook(BookCountryVO bcVO, Model model) {
//		System.out.println("params>>>"  + bcVO);
		BookService bs = new BookService();
		model.addAttribute("book_list_best", bs.bookBestList(bcVO));
		
		return "book/bookList_best";
	}
	
	
	/**
	 * ����, �ؿ� ���� ����� �����ִ� �κ�. ����Ʈ �ؼ� ��
	 * 
	 * @param model
	 * @param bcVO
	 * @return
	 */
	@RequestMapping(value = "/bookList_country.do", method = RequestMethod.GET)
	public String selectKorBook(BookCountryVO bcVO, TotalCntVO tcVO, @RequestParam(defaultValue = "1") int current_page,
			Model model) {

		PaginationService ps = new PaginationService();

		bcVO.setStart_num(ps.startNum(current_page));
		bcVO.setEnd_num(ps.endNum(current_page));

		BookService bs = new BookService();
		PageVO pVO = ps.calcPaging(current_page, tcVO);

		model.addAttribute("book_list", bs.bookList(bcVO));
		model.addAttribute("paging", pVO);

//		System.out.println("current Page=====================>" + pVO.getCurrent_page());
//		System.out.println("start Page=====================>" + pVO.getStart_page());
//		System.out.println("end Page=====================>" + pVO.getEnd_page());
//		System.out.println("pre Page=====================>" + pVO.getPre_page());
//		System.out.println("next Page=====================>" + pVO.getNext_page());
//		System.out.println("total Page=====================>" + pVO.getTotal_page());

		return "book/bookList_coun";
	}
	
	
	@RequestMapping(value = "/bookList_new.do", method = RequestMethod.GET)
	public String newBook(BookCountryVO bcVO, Model model) {
		
		BookService bs = new BookService();
		model.addAttribute("book_list_new", bs.bookNewList(bcVO));
		
		return "book/bookList_new";
	}
	
	
	 
  	@RequestMapping(value = "/book_search_result.do", method = GET)
	  public String bookSearchResult(SearchBookVO sbVO, TotalCntVO tcVO, @RequestParam(defaultValue = "1") int current_page, String bookCate, String bookCateDetail, @RequestParam(defaultValue = "0")String price1, @RequestParam(defaultValue = "0")String price2, String searchType,String searchkeyword, Model model) {//SearchBookVO sbVO�� ������ price(int ������ ������)
	  
  		PaginationService ps = new PaginationService();
  		
  		//SearchBookVO sbVO = new SearchBookVO();
  		sbVO.setStart_num(ps.startNum(current_page));
  		sbVO.setEnd_num(ps.endNum(current_page));
  		
  		if(bookCate != null) {
  			sbVO.setBookCate(bookCate);
  		}
  		if(bookCateDetail != null) {
  			sbVO.setBookCateDetail(bookCateDetail);
  		}
  		if( !"0".equals(price1)) { 
  			sbVO.setBook_price1(Integer.parseInt(price1));
  		}
  		if( !"0".equals(price2)) {
  			sbVO.setBook_price2(Integer.parseInt(price2));
  		}
  		if(searchkeyword != null) {
  			sbVO.setSearchType(searchType);
  			sbVO.setSearchKeyword(searchkeyword);
  		}
  		
		BookService bs = new BookService();
		PageVO pVO = ps.calcPagingSearch(current_page, tcVO);

  		model.addAttribute("search_book_result",bs.selectSearchBook(sbVO));
  	 	model.addAttribute("paging", pVO);
  	 	
		System.out.println("current Page=====================>" + pVO.getCurrent_page());
		System.out.println("start Page=====================>" + pVO.getStart_page());
		System.out.println("end Page=====================>" + pVO.getEnd_page());
		System.out.println("pre Page=====================>" + pVO.getPre_page());
		System.out.println("next Page=====================>" + pVO.getNext_page());
		System.out.println("total Page=====================>" + pVO.getTotal_page());
  	 	
	     return "book_search/user_book_search_frm"; // result.jsp�� ���� �����ʰ� �켱 frm���� �ٽ� ����
	   }
	
  	
	
  	@RequestMapping(value = "/book_detail.do", method = RequestMethod.GET)
  	public String bookDetail(String book_isbn, Model model) {
  		
  		BookService bs = new BookService();
  		model.addAttribute("book_detail",bs.detailBook(book_isbn));
  		
  		return "book_detail/user_book_detail";
  		
  	}
	
	
}
