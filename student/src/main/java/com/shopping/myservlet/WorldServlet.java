package com.shopping.myservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.model.bean.World;

/**
 * Servlet implementation class WorldServlet
 */
@WebServlet(
		urlPatterns = { "/world" }, 
		initParams = { 
				@WebInitParam(name = "tel", value = "0212345678", description = "전화번호"), 
				@WebInitParam(name = "fax", value = "0211112222", description = "팩스번호")
		})
public class WorldServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private String tel;
    private String fax;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("서블릿이 초기화 됩니다.");
		tel = config.getInitParameter("tel");
		fax = config.getInitParameter("fax");
		
		System.out.println("tel : " + tel);
		System.out.println("fax : " + fax);
		
		ServletContext application =  config.getServletContext();
		String hello = "여러분 안녕하세요.";
		application.setAttribute("hello", hello);
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		if(method.equalsIgnoreCase("post")) {
			this.doPost(request, response);
		}else {
			this.doGet(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass() + " doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass() + " doPost");
		String pnum = request.getParameter("pnum");
		String name = request.getParameter("name");
		String company = request.getParameter("company");
		String price = request.getParameter("price");
		
		// 개별 변수 바인딩
		// 리퀘스트 스코프에 데이터를 담는다.
		request.setAttribute("pnum", pnum);
		request.setAttribute("name", name);
		request.setAttribute("company", company);
		request.setAttribute("price", price);
		
		// bean 객체를 이용한 바인딩
		World bean = new World();
		bean.setPnum(pnum);
		bean.setName(name);
		bean.setCompany(company);
		bean.setPrice(price);
		request.setAttribute("bean", bean);
		
		// 세션에 바인딩하기
		HttpSession session = request.getSession();
		session.setAttribute("tel", tel);
		session.setAttribute("fax", fax);
		
		String gotopage = "example/servletTo02.jsp";
		
		//포워딩 방식
		RequestDispatcher dispatcher =  request.getRequestDispatcher(gotopage);
		dispatcher.forward(request, response);
		
		
		System.out.println(pnum + name + company + price);
	}

}
