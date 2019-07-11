package tern.block.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import tern.block.core.dto.Account;
import tern.block.core.dto.BlockBody;
import tern.block.core.dto.BlockHeader;
import tern.block.core.utils.FileStoreUtil;
import tern.block.core.utils.FileUtil;
import tern.block.pojo.Block;
import tern.block.util.BlockUtil;
import tern.block.util.CipherRSA;
import tern.block.util.Encrypt;







/**
 * @author Wind~. 
 * @time 2019/04/19
 * @Version 1.0
 * @title 公私钥对生成
 * */
@Controller
public class KeysController extends PublicBaseController{
    
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private Encrypt encrypt;
	
	@Autowired
	private BlockUtil blockUtil;
	
	
	/**
	 * 本地生成公私钥
	 * */
	@GetMapping("/createKeys")
	@ResponseBody
	public boolean createKeys(@RequestParam("nodeName") String nodeName)
	{   
		if(nodeName == null)
		{   
			LOG.info("节点名称异常,无法本地生成公私钥对文件");
			return false;
		}
        try {
        	//先创建一个公钥文件
        	Map<String, Object> map = CipherRSA.generateKeyPair();
			if(!fileUtil.creatTxtFile(nodeName+"PubKey"))
		    {
				LOG.info(nodeName+"公钥创建失败");
				return false;
			}
			if(!fileUtil.writeTxtFile(CipherRSA.getPublicKey(map)))
			{   
				LOG.info(nodeName+"公钥写入失败");
				return false;
			}
		    //在创建一个私钥文件
			if(!fileUtil.creatTxtFile(nodeName+"PriKey"))
			{   
				LOG.info(nodeName+"私钥创建失败");
				return false;
			}
			if(!fileUtil.writeTxtFile(CipherRSA.getPrivateKey(map)))
			{   
				LOG.info(nodeName+"私钥写入失败");
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        LOG.info(nodeName+"--公私钥本地成功生成");
        return true;
	}
	
	/**
	 * 本地账本生成
	 * */
	@GetMapping("/createAccountBook")
	@ResponseBody
	public String createAccountBook(@RequestParam("nodeEmail") String nodeEmail)
	{
	   String result = null;
	   if(nodeEmail == null)
	   {
		   result = "账本创建失败";
	   }
	   /**
	    * 1.本地创建账本信息
	    * 2.给此账本创建第一笔空交易
	    * */
	   try {
		if(fileUtil.creatTxtFile(nodeEmail+"_AccountBook"))
		{
			 result = nodeEmail+"账本已生成";
			 /**
			  * 1.生成区块
			  * 2.序列化为JSON
			  * 3.写入账本内
			  * */
			 Block firstBlock = new Block();
			 /**
			  * 1.生成区块头
			  * 2.生成区块体
			  * 3.生成hash 创世交易hash为null
			  * */
			 BlockHeader blockHeader = new BlockHeader();
			 blockUtil.createBlockHeader(blockHeader);
			 BlockBody blockBody = new BlockBody();
			 blockUtil.createBlockBody(blockBody);
			 blockUtil.createBlock(firstBlock, blockHeader, blockBody);
			 
			 /**
			  * 创建root账本
			  * param digestOrigin 创世交易的数字签名
			  * key   digestOrigin
			  * value firstBlock
			  * */
			 Account account = new Account();
			 account.setAccountName(nodeEmail);
			 
			 List<Map<String, Object>> rootAccount = new ArrayList<>();
			 Map<String, Object> originBlock = new HashMap<>();
			 String digestOrigin = encrypt.getSHA256("originBlock");
			 originBlock.put(digestOrigin, firstBlock);
			 rootAccount.add(originBlock);
			 account.setAccount(rootAccount);
			 /**
			  * JSON序列化 系统账本
			  * 每个节点第一次创建账本,都调用此方法
			  * */
			 FileStoreUtil.appendToTargetFileByGuava(nodeEmail+"_AccountBook",JSON.toJSONString(account)); 
			 result = "创世交易块已写入账本";
		}
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	   }
	   return result;
	}
	
	/**
	 * 区块数字签名
	 * */
	@PostMapping("/encryptBlock")
	@ResponseBody
	public String designBlockEncrypt(@RequestBody String transcation)
	{   
		String result = null;
		if(transcation == null)
		{   
			result = "加密信息为空,无法生成签名";
			LOG.info("加密信息为空,无法生成签名");
			return result;
		}
		if(encrypt.getSHA256(transcation) == null)
		{   
			result = "区块SHA256签名发生错误";
			LOG.info("区块SHA256签名发生错误");
			return result;
		}   
			result = "区块数字签名完成";
			LOG.info("区块数字签名完成");
			return result;
		
	}
	
}
