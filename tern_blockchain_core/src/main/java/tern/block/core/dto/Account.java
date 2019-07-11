package tern.block.core.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author WindC.~
 * @time 2019/04/26
 * @title 账本类型对象
 * @version 1.0
 * */

public class Account implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账本设计基本思想
	 * 1.账本整体结构为List<Map<String,Object>>
	 * 2.List 为账本
	 * 3.Map存储交易区块
	 * 4.每个账本,需要不同的名字
	 * 5.账本需要保持数据一致性,但对象不一致
	 * 即 每个节点的账本都是独立的,它们具有普遍性,没有所谓的中心账本
	 * 为了保持账本数据一致性,需要对加入账本池的账本进行解析,并定义定时调度,对其进行链长分析,以此保证所有账本信息的一致性
	 * 6.账本的创世交易块由系统生成
	 * 7.账本不存在为空的情景,每个账本都具有至少唯一的交易块
	 * 8.账本存储的交易类型,暂时初定为Object类型,避免由于Block的体系未定,而影响开发效率
	 * */
	private String accountName;
	private List<Map<String,Object>> account;
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public List<Map<String, Object>> getAccount() {
		return account;
	}
	public void setAccount(List<Map<String, Object>> account) {
		this.account = account;
	}
	public Account(String accountName, List<Map<String, Object>> account) {
		super();
		this.accountName = accountName;
		this.account = account;
	}
	public Account() {
		super();
	} 
	
	
}
