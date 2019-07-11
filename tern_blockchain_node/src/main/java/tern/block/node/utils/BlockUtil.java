package tern.block.node.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import tern.block.core.dto.Block;
import tern.block.core.dto.BlockBody;
import tern.block.core.dto.BlockHeader;
import tern.block.core.dto.OrderInfo;



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
      * Hash 为交易信息+时间戳的SHA256加密字符串
      * */
     public  Block createBlock(Block block,BlockHeader blockHeader,BlockBody blockBody)
     {
    	 block.setBlockHeader(blockHeader);
    	 block.setBlockBody(blockBody);
    	 block.setBlockHash(null);
    	 return block;
     }
     
     public  BlockBody createBlockBody(BlockBody blockBody,OrderInfo order)
     {   
    	 List<OrderInfo> infos = new ArrayList<>();
    	 infos.add(order);
    	 blockBody.setOrderInfos(infos);
    	 return blockBody;
     }
     
     /**
      * 生成区块
      * Hash 为交易信息+时间戳的SHA256加密字符串
      * */
     public  Block createBlock(Block block,BlockHeader blockHeader,BlockBody blockBody,String hash)
     {
    	 block.setBlockHeader(blockHeader);
    	 block.setBlockBody(blockBody);
    	 block.setBlockHash(hash);
    	 return block;
     }
}
