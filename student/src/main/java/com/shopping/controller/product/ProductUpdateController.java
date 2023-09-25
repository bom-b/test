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

public class ProductUpdateController extends SuperClass {
	
	private final String PREFIX = "product/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		// Categories 테이블에서 상품 카테고리 목록을 읽어서 request에 바인딩합니다.
		CategoryDao c_dao = new CategoryDao();
		List<Category> lists = null;
		
		// 상품 수정시 넘어 오는 상품 번호를 챙긴다.
		Integer pnum = Integer.parseInt(request.getParameter("pnum"));
		
		// 다오에서 pnum에 해당하는 데이터를 가져오고 그 가져온 데이터를 빈으로 만든다.
		ProductDao p_dao = new ProductDao();
		Product bean = p_dao.GetDataByPK(pnum);
		
		try {
			lists = c_dao.GetCategoryList("product", "select");
			request.setAttribute("categories", lists);
			request.setAttribute("bean", bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		super.gotoPage(PREFIX + "prUpdateForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		// 오브젝트로 가져온 mr을 강등해야됨
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Product bean = new Product();
		bean.setPnum(super.getNumberData(mr.getParameter("pnum"))); // 상품 수정 시엔 상품 번호를 반드시 챙겨야함
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
			cnt = dao.UpdateData(bean);
			
			if(cnt == -1) {
				// 등록 실패
				super.gotoPage(PREFIX + "prUpdateForm.jsp");
				
			} else {
				// 등록 성공
				String message = "성공적으로 상품이 수정되었습니다.";
				super.setPostiveAlertMessage(message);
				
				// 현재 진행 중인 페이지로 이동하기 위하여 페이징관련 파라미터도 넘겨주어야한다.
				String gotopage = super.getUrlInfomation("prList");
				gotopage += "&pageNumber=" + mr.getParameter("pageNumber");
				gotopage += "&pageSize=" + mr.getParameter("pageSize");
				gotopage += "&mode=" + mr.getParameter("mode");
				gotopage += "&keyword=" + mr.getParameter("keyword");
				response.sendRedirect(gotopage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
