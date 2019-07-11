package tern.block.core.dto;

import java.util.ArrayList;
import java.util.List;

import tern.block.core.utils.Encrypt;

/**
 * 简化版MerkleTree
 * */
public class SimpleMerkleTree {
   
	//按Merkle树思想计算根节点哈希值
	public static String getTreeNodeHash(List<String> hashsList)
	{
		if(hashsList == null || hashsList.size() == 0)
		{
			return null;
		}
		
		while(hashsList.size() != 1)
		{
			hashsList = getMerkleNodeList(hashsList);
		}
		
		//最终只剩下根节点
		return hashsList.get(0);
	}
	
	//按Merkle 树思想计算根节点哈希值
	public static List<String> getMerkleNodeList(List<String> contentList)
	{
		List<String> merkleNodeList = new ArrayList<String>();
		
		if(contentList == null || contentList.size() ==0 )
		{
			return merkleNodeList;
		}
		
		int index = 0;
	    int length = contentList.size();
	    
	    while(index < length)
	    {
	    	//获取左孩子节点数据
	    	String left = contentList.get(index++);
	    	
	    	//获取右孩子节点数据
	    	String right = "";
	    	if(index < length)
	    	{
	    		right = contentList.get(index++);
	    	}
	    	
	    	//计算左右孩子节点的父结点的哈希值
	    	String sha2HexValue = Encrypt.getSHA256Simple(left + right);
	    	merkleNodeList.add(sha2HexValue);
	    }
	    return merkleNodeList;
	}
	
}
