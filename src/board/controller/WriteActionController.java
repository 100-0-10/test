package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.command.BoardCommand;
import board.dao.BoardDAO;


// AbstractCommandController 를 상속받는 이유 : 
// ModelAndView handle의 매개 변수가 Controller interface과 다르기 때문
public class WriteActionController extends AbstractCommandController {
	
	BoardDAO dao; // BoardDAO dao = new BoardDAO();
	
	
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao()호출됨(dao) : " + dao);
	}



	// 매개변수를 알기 쉽게 변환
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		// BoardCommand 는 상속받은 부모의 class이다.
		BoardCommand data = (BoardCommand) command;
		String author = data.getAuthor();
		String content = data.getContent();
		String title = data.getTitle();
		
		dao.write(author, title, content); // dao.write(data);
			
		// response.sendRedirect("list.jsp");
		ModelAndView mav = new ModelAndView("list");
		// mav.setViewName("list); 위에 ModelAndView()안에 넣음으로서 생략이 가능하다
		
		return mav;
		
		// 또는
		// return new ModelAndView("redirect:/list.do");
		// 위의 주석 문을 한줄로 처리 가능하다.
	}

}
