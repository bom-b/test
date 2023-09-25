package com.shopping.myservlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.shopping.controller.SuperController;
import com.shopping.utility.MyUtility;


@WebServlet(
		urlPatterns = { "/Shopping" }, 
		initParams = { 
				@WebInitParam(name = "txtSetting", value = "/WEB-INF/setting.txt"), 
				@WebInitParam(name = "todolist", value = "/WEB-INF/todolist.txt")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 초기화 파라미터 관련 변수
	private String txtSetting = null;
	private String todolist = null;
	
	// setting.txt 파일을 위한 Map
	private Map<String, String> settingMap = null;
	
	// todolist.txt 파일을 위한 Map
	private Map<String, SuperController> todolistMap = null;
	
	// imageUploadWebPath 변수 : 실제 이미지가 업로드 되는 경로
	private String imageUploadWebPath;
	
    public FrontController() {}

	public void init(ServletConfig config) throws ServletException {
		// 변수 txtSetting는 파라미터에 있는 "txtSetting"의 밸류(상대경로)임
		this.txtSetting = config.getInitParameter("txtSetting");
		System.out.println("txtSetting is [" + this.txtSetting + "]");
		
		// 변수 todolist는 파라미터에 있는 "todolist"의 밸류(상대경로)임
		this.todolist = config.getInitParameter("todolist");
		System.out.println("todolist is [" + this.todolist + "]");
		
		// 
		ServletContext application = config.getServletContext();
		
		// txtSetting 절대경로 구하기
		String txtSettingFile = application.getRealPath(txtSetting);
		System.out.println("txtSettingFile is [" + txtSettingFile + "]");
		
		// todolist 절대경로 구하기
		String todolistFile = application.getRealPath(todolist);
		System.out.println("todolistFile is [" + todolistFile + "]");
		
		// txtSetting 절대경로를 통해 map 만들기
		this.settingMap = MyUtility.getSettingMap(txtSettingFile);
		
		// 사이즈 보기
		System.out.println("setting file element size = [" + settingMap.size() + "]");
		
		// settingMap 바인딩 작업하기
		application.setAttribute("map", this.settingMap);
		
		// 이미지 업로드 경로를 변수에 저장합니다. 
		// in setting.txt파일 내의 uploadPath=upload 항목 참조
		String imsiPath = settingMap.get("uploadPath");
		if(imsiPath==null) {imsiPath = "image";}
		
		// 업로드 패스의 절대경로 구하기 
		// imageUploadWebPath 변수 : 실제 이미지가 업로드 되는 경로
		imageUploadWebPath = application.getRealPath(imsiPath);
		System.out.println("imageUploadWebPath is [" + imageUploadWebPath + "]");
		
		// todolist 절대경로를 통해 map 만들기
		this.todolistMap = MyUtility.getTodolistMap(todolistFile);
		
		// 사이즈 보기
		System.out.println("todolist file element size = [" + todolistMap.size() + "]");
		
		
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 컨트롤러 분기를 위한 핵심 파라미터
		String command = request.getParameter("command");
		
		if(command == null) {
			// 파일이 보내지면 커맨드가 널로 인식이 된다고 함
			// 파일업로드가 됐다고 인식이 됨
			System.out.println("file upload event invoked");
			
			// 파일을 다루려면 MultipartRequest 사용해야됨
			MultipartRequest mr = MyUtility.getMultipartRequest(request, imageUploadWebPath);
			
			if(mr != null) {
				command = mr.getParameter("command");
				
				// 커맨드가 prUpdate라면 파일삭제
				if(command.equals("prUpdate")) {
					MyUtility.deleteOldImageFile(imageUploadWebPath, mr);	
				}
				
				// file upload object binding in request scope.
				// 파일 업로드 오브젝트를 리퀘스트 스코프에 바인딩한다.
				request.setAttribute("mr", mr);
			} else {
				System.out.println("MultipartRequest object is null");
			}
		} 
		
		System.out.println("command is [" + command + "]");
		
		// todolistMap에서 커맨드에 해당하는 밸류를 controller에 할당하기
		SuperController controller = this.todolistMap.get(command);
		if(controller != null) {
			String method = request.getMethod();
			
			// get post 분기
			try {
				if(method.equalsIgnoreCase("get")) {
					System.out.println(this.getClass() + "get method called");
					controller.doGet(request, response);
				} else {
					System.out.println(this.getClass() + "post method called");
					controller.doPost(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("requset command is not found");
		}
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

}
