package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.ProductVO;
import com.model2.mvc.service.domain.PurchaseVO;

public class ProductDAO {

	public void insertProduct(ProductVO productVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "insert into PRODUCT values (seq_product_prod_no.nextval, ?, ?, ?, ?, ?, sysdate)";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, productVO.getProdName());
		pst.setString(2, productVO.getProdDetail());
		pst.setString(3, productVO.getManuDate());
		pst.setInt(4, productVO.getPrice());
		pst.setString(5, productVO.getFileName());
		pst.executeQuery();

		con.close();
	}

	public ProductVO findProduct(int prodNo) throws SQLException {
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM product WHERE prod_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;

		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt(1));
			productVO.setProdName(rs.getString(2));
			productVO.setProdDetail(rs.getString(3));
			productVO.setManuDate(rs.getString(4));
			productVO.setPrice(rs.getInt(5));
			productVO.setFileName(rs.getString(6));
			productVO.setRegDate(rs.getDate(7));

		}

		con.close();

		return productVO;
	}

	public Map<String, Object> getProductList(SearchVO searchVO) throws SQLException {
		Connection con = DBUtil.getConnection();
		String sqlAnd = "";
		String sqlWhere = "";
		String priceSort = "";
		if (searchVO.getSearchCondition() != null ) {
			if (searchVO.getSearchCondition().equals("0")) {
				sqlAnd += " AND p.PROD_NO LIKE '" + searchVO.getSearchKeyword() + "%' ";
				sqlWhere += " WHERE PROD_NO LIKE '" + searchVO.getSearchKeyword() + "%' ";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sqlAnd += " AND p.PROD_NAME LIKE '" + searchVO.getSearchKeyword() + "%' ";
				sqlWhere += " WHERE PROD_NAME LIKE '" + searchVO.getSearchKeyword() + "%' ";
			} 
			if (searchVO.getPriceRange().equals("2") && searchVO.getPriceRange() != null && searchVO.getMinPrice() != "" && searchVO.getMaxPrice() != "") {				 
				sqlAnd += " AND p.PRICE BETWEEN " + searchVO.getMinPrice() + " AND " + searchVO.getMaxPrice() + " ";
				sqlWhere += " AND PRICE BETWEEN " + searchVO.getMinPrice() + " AND " + searchVO.getMaxPrice() + " ";
			}
		}
		
		if (searchVO.getPriceSort() != null){
			if (searchVO.getPriceSort().equals("asc")){
				priceSort = " price "+searchVO.getPriceSort();
			}else if(searchVO.getPriceSort().equals("desc")){
				priceSort = " price "+searchVO.getPriceSort();
			}else{
				priceSort = "PROD_NO";
			}
		}
		
		String sql = " SELECT vt1.*, vt2.count " 
				+ " FROM (SELECT rownum rn, vt.* "
					  + " FROM (SELECT p.prod_no, prod_name, price, p.reg_date, NVL(tran_status_code, 0) tran_status_code "
					  		+ " FROM product p, transaction t " 
					  		+ " WHERE p.prod_no=t.prod_no(+) "+sqlAnd
					  		+ " ORDER BY "+priceSort+") vt) vt1, (SELECT count(*) count from product "+sqlWhere+") vt2"
				+ " WHERE vt1.rn BETWEEN ? AND ? order by vt1.rn";
		System.out.println(sql);

		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, (searchVO.getPage() * searchVO.getPageUnit()) - (searchVO.getPageUnit() - 1));
		stmt.setInt(2, (searchVO.getPage() * searchVO.getPageUnit()));

		ResultSet rs = stmt.executeQuery();

		Map<String, Object> map = new HashMap<String, Object>();

		// rs.absolute(searchVO.getPage() * searchVO.getPageUnit() -
		// searchVO.getPageUnit() + 1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		int total = 0;
		while (rs.next()) {

			total = rs.getInt("count");
			PurchaseVO purchaseVO = new PurchaseVO();
			ProductVO vo = new ProductVO();

			vo.setProdNo(rs.getInt(2));
			vo.setProdName(rs.getString(3));
			vo.setPrice(rs.getInt(4));
			vo.setRegDate(rs.getDate(5));

			purchaseVO.setPurchaseProd(vo);
			purchaseVO.setTranCode(rs.getString(6));

			list.add(purchaseVO);
		}
		
		System.out.println("로우의 수:" + total);
		map.put("count", new Integer(total));
		System.out.println("list.size() : " + list.size());
		map.put("list", list);
		System.out.println("map().size() : " + map.size());

		con.close();

		return map;
	}

	public void updateProduct(ProductVO productVO) throws SQLException {

		Connection con = DBUtil.getConnection();

		String sql = "update PRODUCT set PROD_DETAIL=?,PROD_NAME=?,MANUFACTURE_DAY=?,PRICE=?, "
				+ "IMAGE_FILE=? where PROD_NO=?";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, productVO.getProdDetail());
		pst.setString(2, productVO.getProdName());
		pst.setString(3, productVO.getManuDate());
		pst.setInt(4, productVO.getPrice());
		pst.setString(5, productVO.getFileName());
		pst.setInt(6, productVO.getProdNo());
		pst.executeQuery();

		con.close();

	}

}
