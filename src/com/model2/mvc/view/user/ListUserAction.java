package com.model2.mvc.view.user;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.PageVO;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.UserVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class ListUserAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[ListUserAction]start.......");
		SearchVO searchVO = new SearchVO();
		PageVO pageVO = null;

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println(page);
		}
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		System.out.println(request.getParameter("searchCondition"));
		System.out.println(request.getParameter("searchKeyword"));
		String pageUnit = getServletContext().getInitParameter("pageUnit");
		String pageSize = getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));

		UserService service = new UserServiceImpl();
		Map<String, Object> map = service.getUserList(searchVO);

		ArrayList<UserVO> list = null;
		if (map != null) {
			pageVO = new PageVO(page, Integer.parseInt(pageUnit), Integer.parseInt(pageSize), 
							   ((Integer) map.get("count")).intValue());
			list = (ArrayList<UserVO>) map.get("list");
		}

		request.setAttribute("list", list);
		request.setAttribute("searchVO", searchVO);
		System.out.println(pageVO);
		System.out.println(searchVO);
		request.setAttribute("pageVO", pageVO);

		System.out.println("[ListUserActionend.......]");

		return "forward:/user/listUser.jsp";
	}
}