package com.tencent.qqlive.jcehelper.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;


public class ReflectTest {

	static void l(String s) {
		System.out.println(s);
	}

	static boolean isBasicType(String type) {
		return ("java.lang.String".equals(type) 
			|| "int".equals(type) 
			|| "long".equals(type));
	}
	
	static boolean isVectorType(String type) {
		return "java.util.List".equals(type);
	}

	/**
	 * 获取对象类型的简介描述
	 * @param type
	 * @return
	 */
	static String cut(String type){
		String rlt = null;
		if("java.lang.String".equals(type)){
			rlt = "String";
		}else if("java.lang.Integer".equals(type)){
			rlt = "Integer";
		}else if("java.lang.Long".equals(type)){
			rlt = "Long";
		}else{
			rlt = type;
		}
			
			
		return rlt;
	}
	
	static void simpleOutput(Field f, Object o, String prefix) {
		try {
			StringBuilder sbud = new StringBuilder();
			sbud.append(prefix + f.getName())
				.append("\t[" + f.get(o).toString())
				.append("]\t(" + cut(f.getType().getName()) + ")");
			l(sbud.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void reflect(Object o, String prefix) {
		Field[] fileds = o.getClass().getDeclaredFields();
		for (Field f : fileds) {
			try {
				String type = f.getType().getName();
				// 如果字段值为空或基础类型，就不用迭代了
				if (isBasicType(type) || null == f.get(o)) {
					simpleOutput(f, o, prefix);
				} else if(isVectorType(type)){
					List list = (List)f.get(o);
					l(f.getName() + "是个集合 : " + type + ", size : " + list.size());
					showVector((List)f.get(o), "\t");
				}else {
					// 继续迭代
					l(f.getName() + "是个对象 : " + type);
					reflect(f.get(o), "\t");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void showVector(List list, String prefix){
		int i = 0;
		for(Object object : list){
			if(0==i){
				l(prefix + "元素类型 : " + object.getClass().getName());
			}
			l(prefix + "[" + (i+1) + "]:");
			i++;
			reflect(object, prefix);
		}
	}

	public static void main(String[] args) {
		ClassA a1 = new ClassA();
		a1.fieldAA = "11";
		a1.fieldAB = 12;
		a1.fieldAC = 13;

		ClassA a2 = new ClassA();
		a2.fieldAA = "21";
		a2.fieldAB = 22;
		a2.fieldAC = 23;

		ClassA a3 = new ClassA();
		a3.fieldAA = "31";
		a3.fieldAB = 32;
		a3.fieldAC = 33;

		ClassB b1 = new ClassB();
		b1.fieldBA = "51";
		b1.fieldBB = 52;
		b1.fielBC = a1;

		List<ClassA> list = new ArrayList<ClassA>();
		list.add(a2);
		list.add(a3);
		b1.fieldBD = list;

		//reflect(b1,"");
		Gson gson = new Gson();
		String json = gson.toJson(b1);
		l(json);
		
		ClassB cb = gson.fromJson(str, ClassB.class);
		l(cb.toString());
	}
	
	static String str = "{\"fieldBA\":\"51\",\"fieldBB\":52,\"fielBC\":{\"fieldAA\":\"11\",\"fieldAB\":12,\"fieldAC\":13},\"fieldBD\":[{\"fieldAA\":\"21\",\"fieldAB\":22,\"fieldAC\":23},{\"fieldAA\":\"31\",\"fieldAB\":32,\"fieldAC\":33}]}";

}
