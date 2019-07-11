package tern.block.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import tern.block.core.dto.BlockBody;
import tern.block.core.dto.BlockHeader;




/**
 * @author WindC.~
 * @time 2019/04/19
 * @version 1.0
 * @title 交易区块
 * */


@Alias("Block")
public class Block implements Serializable{


	private static final long serialVersionUID = 1L;
	
	/**
	 * 交易区块头
	 * */
	private BlockHeader blockHeader;
	
	
	/**
	 * 交易区块体
	 * */
	private BlockBody blockBody;
	
	/**
	 * 交易区块哈希
	 * */
	private String blockHash;

	public BlockHeader getBlockHeader() {
		return blockHeader;
	}

	public void setBlockHeader(BlockHeader blockHeader) {
		this.blockHeader = blockHeader;
	}

	public BlockBody getBlockBody() {
		return blockBody;
	}

	public void setBlockBody(BlockBody blockBody) {
		this.blockBody = blockBody;
	}

	public String getBlockHash() {
		return blockHash;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}
	
	
	
	
	
   
}
