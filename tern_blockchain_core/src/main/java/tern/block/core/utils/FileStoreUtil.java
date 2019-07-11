package tern.block.core.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import com.google.common.base.Charsets;
import com.google.common.io.Files;

import tern.block.core.dto.SimpleMerkleTree;

public class FileStoreUtil {
   
	//定义区块链文件大小
	private static final int FILE_SIZE = 1024 ; //单位KB
	//定义账本位置
	private static String path = "D:/";
	
	/**
	 * 将文件内容写入目标文件 :Guava方式
	 * */
	
	public static void writeIntoTargetFile(String targetFileName , String content)
	{
		String target = path + targetFileName + ".txt";
		File newFile = new File(target);
		
		try {
			Files.write(content.getBytes(), newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将文件内容向后追加写入目标文件:FileWriter 方式
	 * */
	public static void appendToTargetFile(String targetFileName ,String content)
	{    
		String target = path + targetFileName + ".txt";
		try {
			FileWriter writer = new FileWriter(target,true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将文件内容向后追加写入目标文件:Guava方式
	 * */
	public static void appendToTargetFileByGuava(String targetFileName,String content)
	{   
		String target = path + targetFileName + ".txt";
		File file = new File(target);
		try {
			Files.append(content, file, Charsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 模拟区块链内容写入文件
	 * */
	public static void writeIntoFile(String content)
	{
		/**
		 * 步骤1 查看当前目录下是否有真正写入的loging文件,有则继续使用,无则创建
		 * */
		try{
			//String target = path + targetFileName + ".txt";
			File root = new File(".//");
			//获取当前文件下的所有文件
			File[] files = root.listFiles();
			if(files == null)
			{
				//如果根目录下没有任何文件则创建新文件
				String targetFileName = ".//blockchain-"+System.currentTimeMillis()+"loging";
				appendToTargetFileByGuava(targetFileName, content);
				return;
			}
			//如果根目录下有文件则寻找是否存在后缀为loging的文件
			boolean has = false;
			for(File file : files)
			{
				String name = file.getName();
				if(name.endsWith(".loging")&&name.startsWith("blockchain-"))
				{
					//有则继续写入
					System.out.println(file.getPath());
					appendToTargetFileByGuava(file.getPath(), content);
					has = true;
					
					//写入后如果文件大小超过固定大小,则将loging后缀转为log后缀
					if(file.length() >= FILE_SIZE)
					{
						String logPath = file.getPath().replaceAll("loging", "log");
						File log = new File(logPath);
					    Files.copy(file,log);
						//删除已经写满的loging文件
					    file.delete();
		
					}
				}
			}
			//无则创建新文件
			if(!has)
			{
				String targetFileName = ".//blockchain-"+System.currentTimeMillis()+".loging";
				appendToTargetFileByGuava(targetFileName, content);
				return;
			}
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}	
	
	//模拟区块链内容写入
	public static void writeIntoBlockFile()
	{
		List<String> list = new ArrayList<>();
		list.add("AI");
		list.add("BlockChain");
		list.add("BrainScience");
		for(int i=0;i<20;i++)
		{
			list.add(gengerateVCode(6));
			writeIntoFile(SimpleMerkleTree.getTreeNodeHash(list)+"\n");
		}
	}
	//根据length长度生成数字符串
	public static String gengerateVCode(int length)
	{
		  Long vCode = new Double((Math.random()+1)*Math.pow(10, length-1)).longValue();
		  return vCode.toString();
	}
	public static void main(String[] args)
	{
		writeIntoBlockFile();
	}
}
