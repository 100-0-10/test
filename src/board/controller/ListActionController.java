package board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;


// Controller�� ��ӹ޴� ���� : ��û�� �޾Ƽ� ó���ϱ� ����
public class ListActionController implements Controller {
	
	BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	}



	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		System.out.println("ListACtionController 호출됨!");
		
		ArrayList list = dao.list();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list"); // list.jsp
		
		// request.setAttribute("list", list);
		mav.addObject("list", list);
		
		
		return mav;
	}

}
