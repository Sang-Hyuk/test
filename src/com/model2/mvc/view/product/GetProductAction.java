package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.ProductVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		// int tranCode = Integer.parseInt(request.getParameter("trancode"));

		ProductService service = new ProductServiceImpl();
		ProductVO vo = service.getProduct(prodNo);

		request.setAttribute("vo", vo);

		Cookie cookie = null;
		String history = null;
		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			history = cookie.getValue();
			if (cookie.getName().equals("history")) {
				if (!(history.contains("" + vo.getProdNo()))) {
					history += "," + vo.getProdNo();
				}
			}
		}

		if (cookie.getName().equals("history")) {
			cookie = new Cookie("history", history);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
		} else {
			cookie = new Cookie("history", "" + vo.getProdNo());
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		if (request.getParameter("menu").equals("manage")) {
			return "forward:/product/productUpdate.jsp?";
		} else {
			return "forward:/product/productSearch.jsp?trancode=0";
		}
	}

}
