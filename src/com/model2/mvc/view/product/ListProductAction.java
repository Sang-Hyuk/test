package com.model2.mvc.view.product;

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
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("[ListProductAction]start.......");
		SearchVO searchVO = new SearchVO();
		PageVO pageVO = null;
		
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("user");

		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page") != "") {
			page = Integer.parseInt(request.getParameter("page"));
		}
		searchVO.setPage(page);
		searchVO.setMinPrice(request.getParameter("minPrice")!=null ? request.getParameter("minPrice") : "");
		searchVO.setMaxPrice(request.getParameter("maxPrice")!=null ? request.getParameter("maxPrice") : "");
		searchVO.setPriceRange(request.getParameter("priceRange"));
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		searchVO.setPriceSort(request.getParameter("priceSort")!=null ? request.getParameter("priceSort") : "");
		String pageUnit = getServletContext().getInitParameter("pageUnit");
		String pageSize = getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service = new ProductServiceImpl();
		Map<String, Object> map = service.getProductList(searchVO);
		
		ArrayList<PurchaseVO> list = null;
		if (map != null) {
			pageVO = new PageVO(page, Integer.parseInt(pageUnit), Integer.parseInt(pageSize), 
							   ((Integer) map.get("count")).intValue());
			list = (ArrayList<PurchaseVO>) map.get("list");
		}

		request.setAttribute("list", list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("userVO", userVO);
		System.out.println(pageVO);
		System.out.println(searchVO);
		request.setAttribute("pageVO", pageVO);
		request.setAttribute("menu", request.getParameter("menu"));
		System.out.println("[ListProductAction end.......]");

		if(request.getParameter("menu").equals("manage")){
		return "forward:/product/productList.jsp?menu=manage";
		}else{
		return "forward:/product/productList.jsp?menu=search";
		}
	}

}
