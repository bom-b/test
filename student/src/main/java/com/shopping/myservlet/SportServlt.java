package com.shopping.myservlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.sport.SportController;
import com.shopping.utility.MyUtility;


@WebServlet(urlPatterns = {"/Sport"}, initParams = {
		@WebInitParam(name = "txtSport", value="/WEB-INF/sport.txt"),
		@WebInitParam(name = "txtSetting2", value="/WEB-INF/setting2.txt")
})
public class SportServlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // 컨트롤러 모음
	private String txtSport = null;
	// 정적 문자열 셋팅2 모음
	private String txtSetting2 = null;
	
	
	// map for "txtSport.txt"
	private Map<String, SportController> sportMap = null;
	
	// map for "txtSport.txt"
	private Map<String, String> setting2Map = null;
	
	private ServletContext application = null;
	

    public SportServlt() {
        super();

    }


	public void init(ServletConfig config) throws ServletException {
		System.out.println(this.getClass() + "init() called");
		
		// 파람에 설정한 주소로 sport.txt파일 경로 가져오기
		this.txtSport = config.getInitParameter("txtSport");
    	System.out.println("txtTransportation is [" + txtSport + "]");
    	
    	// 파람에 설정한 주소로 setting2.txt파일 경로 가져오기
    	this.txtSetting2 = config.getInitParameter("txtSetting2");
    	System.out.println("txtSetting2 is [" + txtSetting2 + "]");
    	
    	this.application = config.getServletContext();
    	
    	// 웹 풀 패스 네임 얻기
    	String webFullPathName = application.getRealPath(txtSport);
    	System.out.println("webFullPathName is [" + webFullPathName + "]");
    	
    	// 셋팅2의 풀 패스 네임 얻기
    	String websetting2PathName = application.getRealPath(txtSetting2);
    	System.out.println("websetting2PathName is [" + websetting2PathName + "]");
    	
    	// 스포츠 맵 만들기 (유틸의 메소드를 통해서)
    	sportMap = MyUtility.getSportMap(webFullPathName);
    	
    	// 셋팅 2 맵 만들기 
    	setting2Map = MyUtility.getSettingMap(websetting2PathName);
    	
    	// 어플리케이션 스코프에 저장하기
    	application.setAttribute("map2", setting2Map);
    	
    	

	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		System.out.println("command id [" + command + "]");
		
		SportController controller = sportMap.get(command);
		if(controller != null) {
			controller.play();
		} else {
			System.out.println("requset command is not found");
		}
		
		String gotopage = "example/sportTo.jsp";
		RequestDispatcher dispatcher =  request.getRequestDispatcher(gotopage);
		dispatcher.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}