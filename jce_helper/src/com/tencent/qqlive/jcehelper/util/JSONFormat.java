package com.tencent.qqlive.jcehelper.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSONFormat {
 
    public static void main(String[] args) throws Exception {
        String str = "{\"fieldBA\":\"51\",\"fieldBB\":52,\"fielBC\":{\"fieldAA\":\"11\",\"fieldAB\":12,\"fieldAC\":13},\"fieldBD\":[{\"fieldAA\":\"21\",\"fieldAB\":22,\"fieldAC\":23},{\"fieldAA\":\"31\",\"fieldAB\":32,\"fieldAC\":33}]}";
        System.out.println(jsonFormatter(str));
    }
    
    static final String STR_NEW_LINE = "<br>\n";
    public static final String STR_SPACE = "<span class=\"space_class\"/>";
    
    /*
    public static String jsonFormatter4Html(String uglyJSONString){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJSONString);
		String tempJson = gson.toJson(je);
		String rlt = tempJson;
		
		if(!Utils.isEmpty(tempJson)){
			StringBuilder sbud = new StringBuilder();
			boolean isNewLine = false;
			for(int i=0;i<tempJson.length();i++){
				String append = null;
				char c = tempJson.charAt(i);
				if('\n'==c){
					isNewLine = true;
					append = STR_NEW_LINE;
				}else if(0x20==c){
					append = isNewLine ? STR_SPACE : (""+c);
				}else{
					isNewLine = false;
					append = "" + c;
				}
				
				sbud.append(append);
			}
			
			rlt = sbud.toString();
		}
		
		
		return rlt;
	}
    */
    
    public static String jsonFormatter(String uglyJSONString){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJSONString);
		return gson.toJson(je);
	}
    
    public static String htmlFormat(String tempJson){
    	String rlt = tempJson;
		if(!Utils.isEmpty(tempJson)){
			StringBuilder sbud = new StringBuilder();
			boolean isNewLine = false;
			for(int i=0;i<tempJson.length();i++){
				String append = null;
				char c = tempJson.charAt(i);
				if('\n'==c){
					isNewLine = true;
					append = STR_NEW_LINE;
				}else if(0x20==c){
					append = isNewLine ? STR_SPACE : (""+c);
				}else{
					isNewLine = false;
					append = "" + c;
				}
				
				sbud.append(append);
			}
			
			rlt = sbud.toString();
		}
		
		
		return rlt;
	}
    
    
    
}
