package tern.block.core.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;


/**
 * 区块头
 * */

@Alias("BlockHeader")
public class BlockHeader implements Serializable{


	private static final long serialVersionUID = 1L;
	
	/**
	 * 版本号
	 * */
	private int version;
	
	/**
	 * 上一个区块的哈希
	 * */
	private String hashPreviousBlock;
	
	/**
	 * Merkle 树根节点哈希值
	 * */
	private String hashMerkleRoot;
	
	/**
	 * 生成该区块的公钥
	 * */
	private String publicKey;
	
	/**
	 * 区块的序号
	 * */
	private int number;
	
	/**
	 * 时间戳
	 * */
	private long timeStamp;
	
	/**
	 * 32位随机数
	 * */
	private long nonce;
	
	/**
	 * 该区块里每条交易信息的哈希集合,按顺序来,通过该哈希集合能算出根节点哈希值
	 * */
    private List<String> hashList;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getHashPreviousBlock() {
		return hashPreviousBlock;
	}

	public void setHashPreviousBlock(String hashPreviousBlock) {
		this.hashPreviousBlock = hashPreviousBlock;
	}

	public String getHashMerkleRoot() {
		return hashMerkleRoot;
	}

	public void setHashMerkleRoot(String hashMerkleRoot) {
		this.hashMerkleRoot = hashMerkleRoot;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getNonce() {
		return nonce;
	}

	public void setNonce(long nonce) {
		this.nonce = nonce;
	}

	public List<String> getHashList() {
		return hashList;
	}

	public void setHashList(List<String> hashList) {
		this.hashList = hashList;
	}
    

}
