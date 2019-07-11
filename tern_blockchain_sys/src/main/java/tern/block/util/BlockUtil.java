package tern.block.util;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import tern.block.core.dto.BlockBody;
import tern.block.core.dto.BlockHeader;
import tern.block.pojo.Block;


/**
 * 生成区块的测试方法
 * */
@Component
public class BlockUtil {
	
	 /**
	  * 以下为创世交易区块的快速生成
	  * */
	
	 /**
	  * 生成区块头
	  * */
     public BlockHeader createBlockHeader(BlockHeader blockHeader)
     {
		 blockHeader.setVersion(0);
		 blockHeader.setHashPreviousBlock(null);
		 blockHeader.setHashMerkleRoot(null);
		 blockHeader.setPublicKey(null);
		 blockHeader.setNumber(0);
		 Date time = new Date();
		 blockHeader.setTimeStamp(time.getTime());
		 blockHeader.setNonce(1);
		 return blockHeader;
     }
     
     /**
      * 生成区块体
      * */
     public  BlockBody createBlockBody(BlockBody blockBody)
     {
    	 blockBody.setOrderInfos(new ArrayList<>());
    	 return blockBody;
     }
     
     /**
      * 生成区块
      * */
     public  Block createBlock(Block block,BlockHeader blockHeader,BlockBody blockBody)
     {
    	 block.setBlockHeader(blockHeader);
    	 block.setBlockBody(blockBody);
    	 block.setBlockHash(null);
    	 return block;
     }
}
