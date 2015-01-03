package com.tencent.qqlive.jcehelper.bean;

import java.io.Serializable;

import com.tencent.qqlive.jcehelper.util.Utils;

public class CommonParamBean implements Serializable, Cloneable, Comparable<CommonParamBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String param0;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;
	private String param9;
	private String param10;
	private String param11;
	public String getParam10() {
		return param10;
	}
	public void setParam10(String param10) {
		this.param10 = param10;
	}
	public String getParam11() {
		return param11;
	}
	public void setParam11(String param11) {
		this.param11 = param11;
	}
	public String getParam12() {
		return param12;
	}
	public void setParam12(String param12) {
		this.param12 = param12;
	}
	public String getParam13() {
		return param13;
	}
	public void setParam13(String param13) {
		this.param13 = param13;
	}
	public String getParam14() {
		return param14;
	}
	public void setParam14(String param14) {
		this.param14 = param14;
	}
	public String getParam15() {
		return param15;
	}
	public void setParam15(String param15) {
		this.param15 = param15;
	}
	private String param12;
	private String param13;
	private String param14;
	private String param15;
	public String getParam7() {
		return param7;
	}
	public void setParam7(String param7) {
		this.param7 = param7;
	}
	public String getParam8() {
		return param8;
	}
	public void setParam8(String param8) {
		this.param8 = param8;
	}
	public String getParam9() {
		return param9;
	}
	public void setParam9(String param9) {
		this.param9 = param9;
	}
	public String getParam6() {
		return param6;
	}
	public void setParam6(String param6) {
		this.param6 = param6;
	}
	public String getParam0() {
		return param0;
	}
	public void setParam0(String param0) {
		this.param0 = param0;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getParam4() {
		return param4;
	}
	public void setParam4(String param4) {
		this.param4 = param4;
	}
	public String getParam5() {
		return param5;
	}
	public void setParam5(String param5) {
		this.param5 = param5;
	}
	
	@Override
	public Object clone() {  
		CommonParamBean o = null;  
        try {  
            o = (CommonParamBean) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }  
	
	@Override
	public String toString(){
		return "param0[" + param0 + "]\n"
			 + "param1[" + param1 + "]\n"
			 + "param2[" + param2 + "]\n"
			 + "param3[" + param3 + "]\n"
			 + "param4[" + param4 + "]\n"
			 + "param5[" + param5 + "]\n"
			 + "param6[" + param6 + "]\n"
			 + "param7[" + param7 + "]\n"
			 + "param8[" + param8 + "]\n"
			 + "param9[" + param9 + "]";
	}
	@Override
	public int compareTo(CommonParamBean o) {
		int rlt = 0;
		
		if(null==o || !Utils.isValidInt(o.param0)){
			rlt = -1;
		}else{
			if(!Utils.isValidInt(param0)){
				rlt = 1;
			}else{
				if(Integer.valueOf(param0)>Integer.valueOf(o.param0)){
					rlt = 1;
				}else{
					rlt = -1;
				}
			}
		}
		
		return rlt;
	}
}
