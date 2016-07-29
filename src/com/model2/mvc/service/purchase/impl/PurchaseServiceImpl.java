package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.PurchaseVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDAO purchaseDAO;	
	
	public PurchaseServiceImpl() {
		purchaseDAO = new PurchaseDAO();
	}
	
	@Override
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.insertPurchase(purchaseVO);
	}

	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		return purchaseDAO.findPurchaseVO(tranNo);
	}

	@Override
	public PurchaseVO getPurchase2(int prodNo) throws Exception {
		return purchaseDAO.findPurchaseVO2(prodNo);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		return purchaseDAO.getPurchaseList(searchVO, buyerId);
		
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.updatePurchase(purchaseVO);

	}

	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.updateTranCode(purchaseVO);

	}

}
