//package com.lyy.eu.util;
//
//import java.io.IOException;
//
//import java.lang.reflect.Method;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.servlet.ServletException;
//import javax.servlet.jsp.PageContext;
//
//import com.jspsmart.upload.File;
//import com.jspsmart.upload.Files;
//import com.jspsmart.upload.Request;
//import com.jspsmart.upload.SmartUpload;
//import com.jspsmart.upload.SmartUploadException;
//
///**
// * 文件上传的工具类
// * @author lh
// *
// */
//public class FileUploadUtil {
//
//	public static String uploadPath = "../images"; //文件默认的上传路�?
//	private static final String ALLOWEDLIST = "gif,jpg,png"; // 允许上传的文件类�?
//	private static final String DENIEDLIST = "exe, bat, jsp"; // 不允许上传的文件类型
//	private static final int MAXFILESIZE = 10*1024*1024; //单个上传文件的最大�??
//	private static final int TOTALMAXFILESIZE = 1000*1024*1024; //总上传文件最大�??
//
//	/**
//	 * 文件上传
//	 * @param pageContext
//	 * @return
//	 * @throws ServletException
//	 * @throws IOException
//	 * @throws SQLException
//	 * @throws SmartUploadException
//	 */
//	@SuppressWarnings("unchecked")
//	public Map<String, String> uploads(PageContext pageContext) throws ServletException, IOException,
//	SQLException, SmartUploadException{
//		Map<String, String> map = new HashMap<String, String>();
//		SmartUpload su = this.getSmartUpload(pageContext);
//
//		Request rq = su.getRequest();
//
//
////		//初始化上传组�?
////		su.initialize(pageContext);
////		//设置参数
////		su.setAllowedFilesList(ALLOWEDLIST);
////		su.setDeniedFilesList(DENIEDLIST);
////		su.setMaxFileSize(MAXFILESIZE);
////		su.setTotalMaxFileSize(TOTALMAXFILESIZE);
////		//设置上传数据的编码集
////		su.setCharset("UTF-8");
////		su.upload();
////
////		//获取上传的参数信息，非文件参�? 此时的Request是jspsmartupload中的
////		Request rq = su.getRequest();
//		//获取�?有表单控件的名字
//		Enumeration<String> enus = rq.getParameterNames();
//		//单个控件名称 name属�??
//		String name = null;
//		while(enus.hasMoreElements()) {
//			name = enus.nextElement();
//			map.put(name, rq.getParameter(name));
//		}
//		//return map;  //此时获取的所有的普�?�文本信�?
//
//		//处理上传的文�?
//		Files files = su.getFiles();
//		//如果无文件，则直接放回文本信�?
//		if( files == null || files.getCount() <= 0) {
//			return map;
//		}
//		//有就循环取出用户上传的文�?
//		Collection<File> fls = files.getCollection();
//		//TODO 文件存储服务器的详细位置
//	    //获取保存文件夹的绝对路径 -> tomcat在服务器的绝对路�?  C...\webapps\项目名\
//		String basePath = pageContext.getServletContext().getRealPath("/");
//		//System.out.println(basePath);
//
//		String fileName = null; //上传后的文件名称
//		String filedName = null; //文件控件�?
//		String filePath = null; //文件保存路径
//
//		String pathStr = ""; //多文件拼接路�?
//		String temp = null; //网页文件中文件上传的控件
//
//		for(File fl : fls) {
//			if( !fl.isMissing() ) {//文件存在
//
//				temp = fl.getFieldName(); //photos files 页面多个文件域控�?
//				if( StringUtil.checkNull(filedName) ) { //如果这个变量为空，说明这是第�?次要上传的文�?
//					filedName = temp;
//				}else { //否则这是其他的文件域控件
//					if( !temp.equals(filedName) ) {
//						map.put(filedName, pathStr);
//						pathStr = ""; //初始�? 	清空上一个上传路�? 准备放下�?个文件上传路�?
//						filedName = temp;
//					}
//				}
//
//				//为了避免命名重复覆盖，自定义文件�?
//				filedName = fl.getFieldName();
//				fileName = uploadPath + "/" + new Date().getTime() + "_" + fl.getFileName();
//
//				if( StringUtil.checkNull(pathStr) ) { //说明这是第一个要上传的文�?
//					pathStr = fileName;
//				}else {
//					pathStr += ";" + fileName; //多图片字符串拼接
//				}
//
//				//存储在服务器�?  自定义路�? + 自定义名�?
//				filePath = basePath + fileName;
//				//存储文件
//				fl.saveAs(filePath, SmartUpload.SAVE_PHYSICAL);
//			}
//		}
//		map.put(filedName, pathStr);
//		return map;
//
//
//
//
//	}
//
//	private SmartUpload getSmartUpload(PageContext pageContext) throws ServletException, IOException, SQLException, SmartUploadException {
//		SmartUpload su = new SmartUpload();
//		//初始化上传组�?
//		su.initialize(pageContext);
//		//设置参数
//		su.setAllowedFilesList(ALLOWEDLIST);
//		su.setDeniedFilesList(DENIEDLIST);
//		su.setMaxFileSize(MAXFILESIZE);
//		su.setTotalMaxFileSize(TOTALMAXFILESIZE);
//		//设置上传数据的编码集
//		su.setCharset("UTF-8");
//		su.upload();
//
//		return su;
//	}
//
//	/**
//	 * 基于对象的文件上�?
//	 *
//	 * @param pageContext
//	 * @return
//	 */
//	public <T> T uploads(Class<T> cls, PageContext pageContext) throws Exception {
//		T t =cls.newInstance();
//
//		//获取给定类中的所有setter方法
//		Method[] methods  = cls.getMethods();
//
//		//存储�?有是setter方法，以方法名为键，对应的方法对象为�?
//		Map<String, Method> setters = new HashMap<String, Method>();
//
//		String methodName = null;
//		for(Method method : methods) {
//			methodName = method.getName();
//			if(methodName.startsWith("set")) {
//				setters.put(methodName, method);
//			}
//		}
//
//		Map<String, String> map = this.uploads(pageContext);//获取请求中的�?有参�?
//
//		//循环�?有的参数�? 找到这个参数注入时对应的setter方法
//		String name = null;
//		Method method = null;
//		Class<?>[] types = null;
//		Class<?> type = null;
//		String val = null;
//
//		t = cls.newInstance();
//
//		for(Entry<String, String> entry : map.entrySet()) {
//			val = entry.getValue();
//			name = entry.getKey();
//
//			if(val == null || "".equals(val)) {
//				continue;
//			}
//
//			methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
//			method = setters.getOrDefault(methodName, null);
//			if(method == null) {
//				continue;
//			}
//
//			types = method.getParameterTypes();
//			if(types == null || types.length<=0) {
//				continue;
//			}
//
//			type = types[0];
//
//			if(Integer.TYPE == type || Integer.class == type ) {
//				method.invoke(t, Integer.parseInt(val));
//			}else if (Float.TYPE == type || Float.class == type) {
//				method.invoke(t, Float.parseFloat(val));
//			}else if(Double.TYPE == type || Double.class == type) {
//				method.invoke(t, Double.parseDouble(val));
//			}else {
//				method.invoke(t, val);
//			}
//
//		}
//		return t;
//	}
//
//}
