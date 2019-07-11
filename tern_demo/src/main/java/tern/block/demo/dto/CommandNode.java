package tern.block.demo.dto;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;


/**
 * 普通节点
 * */
@Alias("CommandNode")
public class CommandNode implements Serializable{
   
	
	private static final long serialVersionUID = 1L;
	
	private  Integer cusId;
    private  String cusName;
    private  String cusSex;
    private  String cusPassword;
    private  String cusEmail;
    private  String cusTelphone;
    private  String cusPubKey;
    private  String cusPriKey;
    private  Integer cusNocues;
    private  Integer cusCompetence;
    private  Integer cusState;
    private  Integer cusBlockNum;
    
    
    
	

	public CommandNode(Integer cusId, String cusName, String cusSex, String cusPassword, String cusEmail,
			String cusTelphone, String cusPubKey, String cusPriKey, Integer cusNocues, Integer cusCompetence,
			Integer cusState, Integer cusBlockNum) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusSex = cusSex;
		this.cusPassword = cusPassword;
		this.cusEmail = cusEmail;
		this.cusTelphone = cusTelphone;
		this.cusPubKey = cusPubKey;
		this.cusPriKey = cusPriKey;
		this.cusNocues = cusNocues;
		this.cusCompetence = cusCompetence;
		this.cusState = cusState;
		this.cusBlockNum = cusBlockNum;
	}

	public CommandNode(String cusName, String cusSex, String cusPassword, String cusEmail, String cusTelphone,
			String cusPubKey, String cusPriKey, Integer cusNocues,Integer cusState,Integer cusBlockNum) {
		super();
		this.cusName = cusName;
		this.cusSex = cusSex;
		this.cusPassword = cusPassword;
		this.cusEmail = cusEmail;
		this.cusTelphone = cusTelphone;
		this.cusPubKey = cusPubKey;
		this.cusPriKey = cusPriKey;
		this.cusNocues = cusNocues;
		this.cusState = cusState;
		this.cusBlockNum = cusBlockNum;
	}
	
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusSex() {
		return cusSex;
	}
	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}
	public String getCusPassword() {
		return cusPassword;
	}
	public void setCusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getCusTelphone() {
		return cusTelphone;
	}
	public void setCusTelphone(String cusTelphone) {
		this.cusTelphone = cusTelphone;
	}
	public String getCusPubKey() {
		return cusPubKey;
	}
	public void setCusPubKey(String cusPubKey) {
		this.cusPubKey = cusPubKey;
	}
	public String getCusPriKey() {
		return cusPriKey;
	}
	public void setCusPriKey(String cusPriKey) {
		this.cusPriKey = cusPriKey;
	}
	public Integer getCusNocues() {
		return cusNocues;
	}
	public void setCusNocues(Integer cusNocues) {
		this.cusNocues = cusNocues;
	}
	public Integer getCusCompetence() {
		return cusCompetence;
	}
	public void setCusCompetence(Integer cusCompetence) {
		this.cusCompetence = cusCompetence;
	}

	public Integer getCusState() {
		return cusState;
	}

	public void setCusState(Integer cusState) {
		this.cusState = cusState;
	}

	public Integer getCusBlockNum() {
		return cusBlockNum;
	}

	public void setCusBlockNum(Integer cusBlockNum) {
		this.cusBlockNum = cusBlockNum;
	}
	
	
	 
    
    
}
