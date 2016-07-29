package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.ProductVO;
import com.model2.mvc.service.domain.PurchaseVO;
import com.model2.mvc.service.domain.UserVO;

public class PurchaseDAO {

	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "insert into TRANSACTION values (seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, sysdate, ?)";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		pst.setString(2, purchaseVO.getBuyer().getUserId());
		pst.setString(3, purchaseVO.getPaymentOption());
		pst.setString(4, purchaseVO.getReceiverName());
		pst.setString(5, purchaseVO.getReceiverPhone());
		pst.setString(6, purchaseVO.getDivyAddr());
		pst.setString(7, purchaseVO.getDivyRequest());
		pst.setString(8, "1");
		pst.setString(9, purchaseVO.getDivyDate());
		pst.executeQuery();

		con.close();
	}

	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql ="SELECT prod_no, buyer_id, tran_status_code, tran_no, receiver_phone "
					+ "FROM transaction WHERE buyer_id = ?";

		PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		pst.setString(1, buyerId);

		ResultSet rs = pst.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				UserVO userVO = new UserVO();
				ProductVO productVO = new ProductVO();
				PurchaseVO purchaseVO = new PurchaseVO();

				userVO.setUserId(rs.getString(2));

				productVO.setProdNo(rs.getInt(1));

				purchaseVO.setBuyer(userVO);
				purchaseVO.setPurchaseProd(productVO);
				purchaseVO.setTranCode(rs.getString(3));
				purchaseVO.setTranNo(rs.getInt(4));
				purchaseVO.setReceiverPhone(rs.getString(5));

				list.add(purchaseVO);
				if (!rs.next())
					break;
			}
		}

		System.out.println("list.size() : " + list.size());
		map.put("list", list);
		System.out.println("map().size() : " + map.size());

		con.close();

		return map;
	}

	public PurchaseVO findPurchaseVO(int tranNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "SELECT tran_no, buyer_id, payment_option, receiver_name, receiver_phone, "
				+ "demailaddr, dlvy_request, dlvy_date, order_data, prod_no FROM transaction " + "WHERE tran_no=?";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, tranNo);

		ResultSet rs = pst.executeQuery();

		PurchaseVO purchaseVO = null;
		UserVO userVO = null;
		ProductVO productVO = null;

		while (rs.next()) {
			userVO = new UserVO();
			purchaseVO = new PurchaseVO();
			productVO = new ProductVO();

			userVO.setUserId(rs.getString(2));

			productVO.setProdNo(rs.getInt(10));

			purchaseVO.setBuyer(userVO);
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setTranNo(rs.getInt(1));
			purchaseVO.setPaymentOption(rs.getString(3));
			purchaseVO.setReceiverName(rs.getString(4));
			purchaseVO.setReceiverPhone(rs.getString(5));
			purchaseVO.setDivyAddr(rs.getString(6));
			purchaseVO.setDivyRequest(rs.getString(7));
			purchaseVO.setDivyDate(rs.getString(8));
			purchaseVO.setOrderDate(rs.getDate(9));

		}
		con.close();

		return purchaseVO;
	}

	public PurchaseVO findPurchaseVO2(int prodNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "SELECT tran_no, buyer_id, payment_option, receiver_name, receiver_phone, "
				+ "demailaddr, dlvy_request, dlvy_date, order_data, prod_no FROM transaction " + "WHERE prod_no=?";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, prodNo);

		ResultSet rs = pst.executeQuery();

		PurchaseVO purchaseVO = null;
		UserVO userVO = null;
		ProductVO productVO = null;

		while (rs.next()) {
			userVO = new UserVO();
			purchaseVO = new PurchaseVO();
			productVO = new ProductVO();

			userVO.setUserId(rs.getString(2));

			productVO.setProdNo(rs.getInt(10));

			purchaseVO.setBuyer(userVO);
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setTranNo(rs.getInt(1));
			purchaseVO.setPaymentOption(rs.getString(3));
			purchaseVO.setReceiverName(rs.getString(4));
			purchaseVO.setReceiverPhone(rs.getString(5));
			purchaseVO.setDivyAddr(rs.getString(6));
			purchaseVO.setDivyRequest(rs.getString(7));
			purchaseVO.setDivyDate(rs.getString(8));
			purchaseVO.setOrderDate(rs.getDate(9));

		}
		con.close();

		return purchaseVO;
	}

	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "UPDATE transaction set buyer_id=?, payment_option=?, receiver_name=?, receiver_phone=?, "
				+ "demailaddr=?, dlvy_request=?, dlvy_date=? WHERE tran_no=?";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, purchaseVO.getBuyer().getUserId());
		pst.setString(2, purchaseVO.getPaymentOption());
		pst.setString(3, purchaseVO.getReceiverName());
		pst.setString(4, purchaseVO.getReceiverPhone());
		pst.setString(5, purchaseVO.getDivyAddr());
		pst.setString(6, purchaseVO.getDivyRequest());
		pst.setString(7, purchaseVO.getDivyDate());
		pst.setInt(8, purchaseVO.getTranNo());
		pst.executeQuery();

		con.close();
	}

	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "UPDATE transaction set tran_status_code=? WHERE tran_no=?";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, purchaseVO.getTranCode());
		pst.setInt(2, purchaseVO.getTranNo());
		pst.executeQuery();

		con.close();
	}
}
