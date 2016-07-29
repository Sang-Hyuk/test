package com.model2.mvc.view.purchase;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.PageVO;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.PurchaseVO;
import com.model2.mvc.service.domain.UserVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		SearchVO searchVO = new SearchVO();
		PageVO pageVO = new PageVO();
		
		int page = 1;
		if(request.getParameter("page") != null && request.getParameter("page") != ""){
			page=Integer.parseInt(request.getParameter("page"));
		}
		searchVO.setPage(page);
		String pageUnit=getServletContext().getInitParameter("pageUnit");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		Map<String,Object> map=purchaseService.getPurchaseList(searchVO, 
												((UserVO)session.getAttribute("user")).getUserId());
		
		ArrayList<PurchaseVO> list=null;
		if(map != null){
			pageVO = new PageVO(page, Integer.parseInt(pageUnit), Integer.parseInt(getServletContext().getInitParameter("pageSize")), 
					   ((Integer) map.get("count")).intValue());
			list=(ArrayList<PurchaseVO>)map.get("list");
		}
		System.out.println(map);
		System.out.println(list);
		System.out.println(pageVO);
		System.out.println(searchVO);
		request.setAttribute("list", list);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("pageVO", pageVO);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
