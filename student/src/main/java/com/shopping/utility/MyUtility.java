package com.shopping.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shopping.controller.SuperController;
import com.shopping.sport.SportController;
import com.shopping.transport.VehicleController;

public class MyUtility {
	
	// 상품 수정 시, 과거에 업로드 했던 이미지를 웹 서버에서 삭제합니다.
	public static void deleteOldImageFile(String webPath, MultipartRequest mr) {
		
		// 지워야할 파일리스트
		String[] deleteImage =
			{
					mr.getParameter("deleteImage01"),
					mr.getParameter("deleteImage02"),
					mr.getParameter("deleteImage03")
			};
		
		// 리스트에서 파일을 하나씩 선택한다.
		for(String delFile : deleteImage) {
			if(delFile != null) {
				String deleteFile = webPath + "/" + delFile;
				File target = new File(deleteFile);
				
				// 파일이 삭제되면 콘솔창에 성공메시지 프린트
				// File 클래스의 delete() 메서드는 불리언을 리턴한다.
				if(target.delete()) {
					System.out.println(deleteFile + "file delete success");
				}
			}
		}
	}
	
	// sport.txt 파일을 읽어서 프로퍼티로 바꿔주는 메소드
	private static Properties getPropertiesData(String webFullPathName) {
		// 스트림을 이용하여 프로퍼티 목록을 반환해줍니다.
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(webFullPathName);
			
			prop = new Properties();
			// load하면 자동으로 프로퍼티로 만들어줌
			// 텍스트파일의 형식이 프로퍼티 형식으로 되어있음
			prop.load(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {fis.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		System.out.println("prop.toString()");
		System.out.println(prop);
		return prop;
	}

	public static Map<String, VehicleController> getTransportationMap(String webFullPathName) {
		// 운송 수단 텍스트 파일을 이용하여 각각 동적으로 객체를 생성합니다.
		// 이 항목들은 Map 구조에 담아서 반환합니다.
		Map<String, VehicleController> map = new HashMap<String, VehicleController>();
		
		Properties prop = getPropertiesData(webFullPathName);
		
		Enumeration<Object> keys = prop.keys();
		while(keys.hasMoreElements()) {
			String command = keys.nextElement().toString();
			String className = prop.getProperty(command);
			System.out.println(command + "/" + className);
			
			try {
				Class<?> handleClass = Class.forName(className);
				VehicleController instance = (VehicleController)handleClass.newInstance();
				map.put(command, instance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	public static Map<String, SuperController> getTodolistMap(String filename) {
		// Todolist 텍스트 파일을 이용하여 각각 동적으로 객체를 생성합니다.
		// 이 항목들은 Map 구조에 담아서 반환합니다.
		Map<String, SuperController> map = new HashMap<String, SuperController>();
		
		Properties prop = getPropertiesData(filename);
		
		Enumeration<Object> keys = prop.keys();
		while(keys.hasMoreElements()) {
			String command = keys.nextElement().toString();
			String className = prop.getProperty(command);
			System.out.println(command + "/" + className);
			
			try {
				Class<?> handleClass = Class.forName(className);
				SuperController instance = (SuperController)handleClass.newInstance();
				map.put(command, instance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}


	public static Map<String, String> getSettingMap(String webSettingName) {
		// webSettingName 파일을 이용하여 자바의 Map 형식으로 반환해줍니다.
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = null;
		prop = getPropertiesData(webSettingName);
		
		Enumeration<Object> keys = prop.keys();
		while(keys.hasMoreElements()) {
			String key = keys.nextElement().toString();
			String value = prop.getProperty(key);


	         try {
	             map.put(key, new String(value.getBytes("ISO-8859-1"), "UTF-8")) ;
	          } catch (UnsupportedEncodingException e) {
	             e.printStackTrace();
	          }
			
		}
		
		return map;
	}
	

	public static Map<String, SportController> getSportMap(String webFullPathName) {
		// sport.txt 텍스트 파일을 이용하여 각각 동적으로 객체를 생성합니다.
		// 이 항목들은 Map 구조에 담아서 반환합니다.
		Map<String, SportController> map = new HashMap<String, SportController>();
		
		// getPropertiesData 메소드를 이용해서 프로퍼티 가져오기
		Properties prop = getPropertiesData(webFullPathName);
		
		Enumeration<Object> keys = prop.keys();
		while(keys.hasMoreElements()) {
			String command = keys.nextElement().toString();
			String className = prop.getProperty(command);
			System.out.println(command + "/" + className);
			
			try {
				Class<?> handleClass = Class.forName(className);
				SportController instance = (SportController)handleClass.newInstance();
				map.put(command, instance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	// 이미지 업로드에 필요한 멀티 파트 객체를 생성하여 반환합니다.
	public static MultipartRequest getMultipartRequest(HttpServletRequest request, String uploadPath) {
		MultipartRequest mr = null;
		int maxPostSize = 10 * 1024 * 1024;
		String ENCODING = "UTF-8";
		
		try {
			mr = new MultipartRequest(
					request, 
					uploadPath,
					maxPostSize,
					ENCODING,
					new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return mr;
	}

	// 현재 시각을 문자열로 반환
	public static String getCurrnetTime() {
		String pattern = "yyyy년 MM월 dd일 hh시 ";
		String date = new SimpleDateFormat(pattern).format(new Date());
		
		return date;
	}

}
