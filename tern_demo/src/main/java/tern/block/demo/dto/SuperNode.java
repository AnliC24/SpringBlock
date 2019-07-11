package tern.block.demo.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 超级节点
 * */

@Alias("SuperNode")
public class SuperNode implements Serializable{

	
	    private static final long serialVersionUID = 1L;
	
	    private Integer sysId;
	    private String sysName;
	    private String sysSex;
	    private String sysPassword;
	    private String sysEmail;
	    private String sysTelphone;
	    private Date sysRegistryTime;
	    private String sysPubKey;
	    private String sysPriKey;
	    private Integer sysCompetence;
	    private Integer sysState;
	    private Integer sysBlockNum;
	    
	    
	    


		public SuperNode(Integer sysId, String sysName, String sysSex, String sysPassword, String sysEmail,
				String sysTelphone, Date sysRegistryTime, String sysPubKey, String sysPriKey, Integer sysCompetence,
				Integer sysState, Integer sysBlockNum) {
			super();
			this.sysId = sysId;
			this.sysName = sysName;
			this.sysSex = sysSex;
			this.sysPassword = sysPassword;
			this.sysEmail = sysEmail;
			this.sysTelphone = sysTelphone;
			this.sysRegistryTime = sysRegistryTime;
			this.sysPubKey = sysPubKey;
			this.sysPriKey = sysPriKey;
			this.sysCompetence = sysCompetence;
			this.sysState = sysState;
			this.sysBlockNum = sysBlockNum;
		}


		public SuperNode(String sysName, String sysSex, String sysPassword, String sysEmail, String sysTelphone,
				Date sysRegistryTime, String sysPubKey, String sysPriKey, Integer sysCompetence, Integer sysState,Integer  sysBlockNum) {
			super();
			this.sysName = sysName;
			this.sysSex = sysSex;
			this.sysPassword = sysPassword;
			this.sysEmail = sysEmail;
			this.sysTelphone = sysTelphone;
			this.sysRegistryTime = sysRegistryTime;
			this.sysPubKey = sysPubKey;
			this.sysPriKey = sysPriKey;
			this.sysCompetence = sysCompetence;
			this.sysState = sysState;
			this.sysBlockNum = sysBlockNum;
		}


		public Integer getSysId() {
			return sysId;
		}


		public void setSysId(Integer sysId) {
			this.sysId = sysId;
		}


		public String getSysName() {
			return sysName;
		}


		public void setSysName(String sysName) {
			this.sysName = sysName;
		}


		public String getSysSex() {
			return sysSex;
		}


		public void setSysSex(String sysSex) {
			this.sysSex = sysSex;
		}


		public String getSysPassword() {
			return sysPassword;
		}


		public void setSysPassword(String sysPassword) {
			this.sysPassword = sysPassword;
		}


		public String getSysEmail() {
			return sysEmail;
		}


		public void setSysEmail(String sysEmail) {
			this.sysEmail = sysEmail;
		}


		public String getSysTelphone() {
			return sysTelphone;
		}


		public void setSysTelphone(String sysTelphone) {
			this.sysTelphone = sysTelphone;
		}


		public Date getSysRegistryTime() {
			return sysRegistryTime;
		}


		public void setSysRegistryTime(Date sysRegistryTime) {
			this.sysRegistryTime = sysRegistryTime;
		}


		public String getSysPubKey() {
			return sysPubKey;
		}


		public void setSysPubKey(String sysPubKey) {
			this.sysPubKey = sysPubKey;
		}


		public String getSysPriKey() {
			return sysPriKey;
		}


		public void setSysPriKey(String sysPriKey) {
			this.sysPriKey = sysPriKey;
		}


		public Integer getSysCompetence() {
			return sysCompetence;
		}


		public void setSysCompetence(Integer sysCompetence) {
			this.sysCompetence = sysCompetence;
		}


		public Integer getSysState() {
			return sysState;
		}


		public void setSysState(Integer sysState) {
			this.sysState = sysState;
		}


		public Integer getSysBlockNum() {
			return sysBlockNum;
		}


		public void setSysBlockNum(Integer sysBlockNum) {
			this.sysBlockNum = sysBlockNum;
		}
   
     
}
