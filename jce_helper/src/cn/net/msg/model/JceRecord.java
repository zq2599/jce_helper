package cn.net.msg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component("jecRecord")
@Entity
@Table(name="jce_record")
public class JceRecord{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="business_id")
	private String businessId;
	@Column(name="receive_time")
	private long receiveTime;
	@Column(name="request_time")
	private long requestTime;
	@Column(name="response_time")
	private long responseTime;
	@Column(name="guid")
	private String guid;
	@Column(name="cmd")
	private int cmd;
	@Column(name="struct_name")
	private String structName;
	@Column(name="param0")
	private String param0;
	@Column(name="param1")
	private String param1;
	@Column(name="param2")
	private String param2;
	@Column(name="param3")
	private String param3;
	@Column(name="param4")
	private String param4;
	@Column(name="param5")
	private String param5;
	@Column(name="param6")
	private String param6;
	@Column(name="param7")
	private String param7;
	@Column(name="param8")
	private String param8;
	@Column(name="param9")
	private String param9;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public long getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}
	public long getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public String getStructName() {
		return structName;
	}
	public void setStructName(String structName) {
		this.structName = structName;
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
	public String getParam6() {
		return param6;
	}
	public void setParam6(String param6) {
		this.param6 = param6;
	}
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
}


