package com.shopping.myservlet;

import java.io.IOException;
import java.util.Iterator;

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

import com.shopping.model.bean.Hello;

/**
 * Servlet implementation class HelloServelet
 */
@WebServlet(
		urlPatterns = { "/hello" }, 
		initParams = { 
				@WebInitParam(name = "company", value = "it 교육 센터", description = "강남에 있습니다"), 
				@WebInitParam(name = "address", value = "강남구 역삼동")
		})
public class HelloServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String company;
	private String address;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("서블릿이 초기화 됩니다.");
		this.company = config.getInitParameter("company");
		this.address = config.getInitParameter("address");
		
		System.out.println("company : " + this.company);
		System.out.println("address : " + this.address);
		
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
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass() + " doPost");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Integer age = Integer.parseInt(request.getParameter("age"));
		String[] hobbies = request.getParameterValues("hobby");
		//doGet(request, response);
		
		String hobby = "";
		if(hobbies == null) {
			
		}else {
			for (int i = 0; i < hobbies.length; i++) {
				hobby += hobbies[i] + ",";
			}
		}
		
		System.out.println(id + name + age + hobby);
		
		// 개별 변수를 바인딩
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("hobby", hobby);
		
		// bean 객체를 이용한 바인딩
		Hello bean = new Hello();
		bean.setId(id);
		bean.setName(name);
		bean.setAge(age);
		bean.setHobby(hobby);
		request.setAttribute("bean", bean);
		
		// 세션에 바인딩하기
		HttpSession session =  request.getSession();
		session.setAttribute("company", company);
		session.setAttribute("address", address);
		
		String gotopage = "example/servletTo01.jsp";
		
		// 리다이렉션 방식
		// 이 방식은 request 객체를 다시 만들기 때문에 이전 바인딩 정보가 휘발됩니다.
		// response.sendRedirect(gotopage);
		
		//포워딩 방식
		RequestDispatcher dispatcher =  request.getRequestDispatcher(gotopage);
		dispatcher.forward(request, response);
		
		
	}

}
