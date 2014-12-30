package com.feature.business.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.util.Constants;

public class Utils {
	public static Properties getProperties(ServletContext context) {
		Properties properties = null;

		String path = Utils.getCommonPath() + "index_items.properties";// 这里就是我的properties文件

		try {
			FileInputStream input = new FileInputStream(path);
			properties = new Properties();
			properties.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return properties;
	}

	public static String getItem(Properties properties, String id) {
		return properties.getProperty("item_" + id);
	}
	
	public static Properties getCustomerProperties(ServletContext context, String customerId) {
		Properties properties = null;

		String path = Utils.getCommonPath() + Constants.PATH_CUSTOMERS + File.separator + customerId + ".properties";

		try {
			FileInputStream input = new FileInputStream(path);
			properties = new Properties();
			properties.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return properties;
	}
	
	public static String getCustomerSingleProperty(ServletContext context, String customerId, String key) {
		Properties properties = getCustomerProperties(context, customerId);
		String rlt = null;
		if(null!=properties){
			rlt = properties.getProperty(key);
		}
		
		return rlt;
	}
	
   public static Properties getPropertiesByFileName(ServletContext context, String fileName)
   {
	    Properties properties = null;
		String path = Utils.getCommonPath() + Constants.PATH_CUSTOMERS + File.separator + fileName + ".properties";

		try {
			FileInputStream input = new FileInputStream(path);
			properties = new Properties();
			properties.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return properties;
   }
   
   public static Map<String,String> getAllPropertiesValue(ServletContext context, String fileName)
   {
	   Map<String,String> resultMap = new HashMap<String,String>();
	   
	   Properties pro = getPropertiesByFileName(context, fileName);
	   if(pro==null){
		   return null;
	   }
	   try {  
           Enumeration en = pro.keys();  
           
           while(en.hasMoreElements()){  
               String key = en.nextElement().toString();  
               String value = pro.getProperty(key);  
               resultMap.put(key, value);  
           } 
	   }
	   catch(Exception ex) {
    	   ex.printStackTrace();
       }
           
	   
	   return resultMap;
   }
   
   public static String getPropertiesValueByKey(ServletContext context, String key,String fileName)
   {
	   Properties properties = getPropertiesByFileName(context, fileName);
		String rlt = null;
		
		if(null!=properties){
			rlt = properties.getProperty(key);
		}
		
		return rlt;
   }
	
	
	public static void main(String[] args) {
		String aString = "123][456";

		String[] array = aString.split("\\]\\[");
		System.out.println(array[0]);

	}

	public static void writeProp(ServletContext context, String subPath, String name,
			Map<String, String> map) {
		String path = Utils.getCommonPath() + subPath + File.separator + name + ".properties";

		Properties properties = new Properties();
		try {
			File file = new File(path);  
            if (!file.exists()){
            	file.createNewFile();
            }  
                
            InputStream fis = new FileInputStream(file);  
            properties.load(fis);  
            fis.close();
			
			OutputStream outputStream = new FileOutputStream(file);

			for(String key : map.keySet()){
				String value = map.get(key);
				if(!com.tencent.qqlive.jcehelper.util.Utils.isValid(value)){
					value = "";
				}
				properties.setProperty(key, value);
			}
			
			properties.store(outputStream, null);  
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deletePropObject(ServletContext context, String subPath, String name,
			String key) {
		String path = Utils.getCommonPath() + subPath + File.separator + name + ".properties";

		Properties properties = new Properties();
		try {
			File file = new File(path);  
            if (!file.exists()){
            	file.createNewFile();
            }  
                
            InputStream fis = new FileInputStream(file);  
            properties.load(fis);  
            fis.close();
			
			OutputStream outputStream = new FileOutputStream(file);

//			for(String key : map.keySet()){
//				String value = map.get(key);
//				if(!isValid(value)){
//					value = "";
//				}
//				properties.setProperty(key, value);
//			}
			
			properties.remove(key);
			properties.store(outputStream, null);  
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeCustomerProp(ServletContext context,String name,Map<String, String> map) {
		writeProp(context, Constants.PATH_CUSTOMERS, name,map);
	}

	public static void writeProperties(ServletContext context,String name,Map<String, String> map) {
		writeProp(context, Constants.PATH_CUSTOMERS, name,map);
	}
	
	public static void deletePropertiesValue(ServletContext context,String name,String key){
		deletePropObject(context, Constants.PATH_CUSTOMERS, name,key);
	}
	
	public static List<String> toList(String str){
		List<String> list = new ArrayList<String>();

		if(com.tencent.qqlive.jcehelper.util.Utils.isValid(str)){
			String[] array = str.split(",");
			if(null!=array && array.length>0){
				for(String singleStr : array){
					if(!com.tencent.qqlive.jcehelper.util.Utils.isValid(singleStr) ||list.contains(str)){
						continue;
					}
					
					list.add(singleStr);
				}
			}
		}
		
		return list;
	}
	
	public static String join(List<String> list){
		StringBuilder sbud = new StringBuilder();
		
		if(null!=list && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(com.tencent.qqlive.jcehelper.util.Utils.isValid(list.get(i))){
					sbud.append(list.get(i));
				}
				
				if(i<(list.size()-1)){
					sbud.append(",");
				}
			}
		}
		
		return sbud.toString();
	}
	
	public static void Log(String str){
		System.out.println(str);
	}
	
	public static List<CommonParamBean> split2CPBList(String raw){
		List<CommonParamBean> list = new ArrayList<CommonParamBean>();
		
		if(!com.tencent.qqlive.jcehelper.util.Utils.isValid(raw)){
			return list;
		}
		
		String tempStr = raw;
		
		tempStr = tempStr.substring(1);
		tempStr = tempStr.substring(0,tempStr.length()-1);
		
		String[] array = tempStr.split("\\]\\[");
		if(null!=array && array.length>0){
			
			for(String str : array){
				String[] singleArray = str.split(":");
				if(null!=singleArray && singleArray.length>1){
					CommonParamBean cpb = new CommonParamBean();
					cpb.setParam0(singleArray[0]);
					cpb.setParam1(singleArray[1]);
					list.add(cpb);
				}
			}
		}
		
		return list;
	}
	
	public static CommonParamBean split2OrderBean(String raw){
		CommonParamBean paraBean = new CommonParamBean();
		
		if(!com.tencent.qqlive.jcehelper.util.Utils.isValid(raw)){
			return null;
		}
		
		String tempStr = raw;
		
		tempStr = tempStr.substring(1);
		tempStr = tempStr.substring(0,tempStr.length()-1);
		
		String[] array = tempStr.split("\\]\\[");
		if(null!=array && array.length>0){
			
			for(String str : array){
				String[] singleArray = str.split(",");
				if(null!=singleArray && singleArray.length>1){
					paraBean.setParam1(singleArray[0]);
					paraBean.setParam2(singleArray[1]);
					paraBean.setParam3(singleArray[2]);
				}
			}
		}
		
		return paraBean;
	}
	
	public static CommonParamBean split2OrderBean1(String raw){
		CommonParamBean paraBean = new CommonParamBean();
		
		if(!com.tencent.qqlive.jcehelper.util.Utils.isValid(raw)){
			return null;
		}
		
		String tempStr = raw;
		
		tempStr = tempStr.substring(1);
		tempStr = tempStr.substring(0,tempStr.length()-1);
		
		String[] array = tempStr.split("\\]\\[");
		
		if(null!=array && array.length>0){
			if(array.length==5){
				paraBean.setParam1(array[0]);
				paraBean.setParam2(array[1]);
				paraBean.setParam3(array[2]);
				paraBean.setParam4(array[3]);
				paraBean.setParam5(array[4]);
			}
			else if(array.length==4){
				paraBean.setParam1(array[0]);
				paraBean.setParam2(array[1]);
				paraBean.setParam3(array[2]);
				paraBean.setParam4(array[3]);
			}
			else if(array.length==3){
				paraBean.setParam1(array[0]);
				paraBean.setParam2(array[1]);
				paraBean.setParam3(array[2]);
			}
			else if(array.length==2){
				paraBean.setParam1(array[0]);
				paraBean.setParam2(array[1]);
			}
			else if(array.length==1){
				paraBean.setParam1(array[0]);
			}
			
		}
		
		return paraBean;
	}
	
	public static String getCommonPath(){
		return Utils.class.getResource("/").getFile() + File.separator;
	}
	
}
