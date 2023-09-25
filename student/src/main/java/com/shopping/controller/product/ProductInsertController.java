package com.shopping.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Category;
import com.shopping.model.bean.Product;
import com.shopping.model.dao.CategoryDao;
import com.shopping.model.dao.ProductDao;

public class ProductInsertController extends SuperClass {
	
	private final String PREFIX = "product/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		// Categories 테이블에서 상품 카테고리 목록을 읽어서 request에 바인딩합니다.
		CategoryDao dao = new CategoryDao();
		List<Category> lists = null;
		
		try {
			lists = dao.GetCategoryList("product", "select");
			request.setAttribute("categories", lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		super.gotoPage(PREFIX + "prInsertForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		// 오브젝트로 가져온 mr을 강등해야됨
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Product bean = new Product();
		//bean.setPnum(null); //상품번호는 시퀀스가 처리해줌
		bean.setName(mr.getParameter("name"));
		bean.setCompany(mr.getParameter("company"));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		bean.setImage03(mr.getFilesystemName("image03"));
		
		bean.setStock(super.getNumberData(mr.getParameter("stock")));
		bean.setPrice(super.getNumberData(mr.getParameter("price")));
		bean.setCategory(mr.getParameter("category"));
		bean.setContents(mr.getParameter("contents"));
		bean.setPoint(super.getNumberData(mr.getParameter("point")));
		bean.setInputdate(mr.getParameter("inputdate"));
		
		ProductDao dao = new ProductDao();
		
		int cnt = -1;
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt == -1) {
				// 등록 실패
				super.gotoPage(PREFIX + "prInsertForm.jsp");
				
			} else {
				// 등록 성공
				String message = "성공적으로 상품이 등록되었습니다.";
				super.setPostiveAlertMessage(message);
				
				new ProductListController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
