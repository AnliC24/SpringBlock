package tern.block.demo.controller;



import java.security.InvalidKeyException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tern.block.core.dto.Node;
import tern.block.core.orderDTO.Order;
import tern.block.core.orderDTO.RecevieOrder;
import tern.block.core.orderDTO.SendOrder;
import tern.block.demo.client.BlockVaildClient;
import tern.block.demo.client.NodeLoadClient;
import tern.block.demo.dto.OrderDTO;
import tern.block.demo.dto.VaildBlockInfo;
import tern.block.demo.dto.VaildOrder;
import tern.block.demo.runner.blockSysKeysInit;
import tern.block.demo.service.RegistryNodeService;
import tern.block.demo.serviceImpl.LoginRedisServiceImpl;
import tern.block.demo.serviceImpl.NodeLoadServiceImpl;
import tern.block.demo.serviceImpl.OrderServiceImpl;
import tern.block.demo.util.CipherRSA;
import tern.block.demo.util.DateUtil;



/**
 * 节点下单模块
 * */

@Controller
@RequestMapping("/order")
public class OrderController extends PublicController{
    
	@Autowired
	private blockSysKeysInit blockSysKeysInit;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private RegistryNodeService registryNodeService;
	
	@Autowired
	private NodeLoadServiceImpl nodeLoadServiceImpl;
	
	@Autowired
	private LoginRedisServiceImpl loginRedisServiceImpl;
	
	@Autowired
	private NodeLoadClient nodeLoadClient;
	
	@Autowired
	private BlockVaildClient blockVaildClient;
	
	/**
	 * 将前端加密的数据存进数据库nodeOrder表内
	 * 共有2部分密文
	 * 1.寄件人信息
	 * 2.收件人信息
	 * */
	/**
	 * 进行校验
//	 * */
//	@PostMapping("/savaOrder")
//	@ResponseBody
//	public  ModelAndView saveNodeOrder(@Validated  @ModelAttribute RecevieOrder recevieOrder, @ModelAttribute SendOrder sendOrder , BindingResult bindingResult)
//	{
//		
//		ModelAndView model=new ModelAndView();
//		/**
//		 * 如果有错误信息,则返回提示
//		 * */
//		if(bindingResult.hasErrors())
//		{
//			  model.setViewName("order");
//	          //model.addObject("error", true);
//	          model.addObject("errorMsg",bindingResult.getAllErrors());
//	          return model;
//		}else{
//			/**
//			 * 判断节点是否存在,根据填写的注册邮箱获取其id,查询redis,是否存在。
//			 * */
//			String email = sendOrder.getSendNodeEmail();
//			if(email == null)
//			{
//				model.setViewName("order");
//				model.addObject("errorMsg","注册邮箱信息不能为空..");
//				
//				return model;
//			}
//			/**
//			 * 验证邮箱是否存在
//			 * */
//            if(checkMail(email))
//            {
//                //不存在
//                model.setViewName("order");
//                model.addObject("errorMsg","该节点不属于联盟节点,无法使用联盟权利...,请更换节点,或者注册该节点为联盟节点..");
//                return model;
//            }
//            
//            /**
//             * 获取节点id
//             * */
//            Map<String, Object> nodeInfo = new HashMap<>();
//            nodeInfo.put("loginEmail", email.toString().trim());
//            Node node = nodeLoadServiceImpl.loginNode(nodeInfo);
//       
//            /**
//			 * 无错误信息,则将寄件人信息和收件人信息加密处理
//			 * 0.获取系统公钥加密
//			 * 1.JSON化寄件人信息
//			 * 2.JSON化收件人信息
//			 * 3.加密处理json序列化的信息
//			 * */
//             String sendOrderEncrypt = null;
//			 String receiveOrderEncrypt = null;
//			 
//			 /**
//			  * 获取系统公钥
//			  * */
//			 String sysPubkey = blockSysKeysInit.getSysPubKey();
//			 Log.info("系统公钥为:"+sysPubkey);
//            
//            /**
//             * redis 查询 ,该节点是否已经登录
//             * */
//            boolean state = loginRedisServiceImpl.checkLoginNodeRedis("node"+node.getNodeId());
//            if(state)
//            {
//            	//已经登录,可以进行交易
//            	//加密处理
//            	//loginRedisServiceImpl.getNode("node"+node.getNodeId());
//            	 try {
//    				 /**
//    				  * 寄件人信息加密 
//    				  * */
//    				byte[] preStr = JSON.toJSONString(sendOrder).getBytes();
//    				Log.info("加密长度:"+preStr.length);
//    				if(preStr.length>117)
//    				{
//    					byte[] enBytes =  CipherRSA.segmentencrypt(JSON.toJSONString(sendOrder).getBytes(),sysPubkey);
//    					sendOrderEncrypt = new String(enBytes,"gb2312");
//    					Log.info("加密后的寄件人信息:"+enBytes);
//    				}else{
//    					Cipher cipher=Cipher.getInstance("RSA");
//    					cipher.init(Cipher.ENCRYPT_MODE, CipherRSA.getPublicRSAKey(sysPubkey));
//    					byte[] enBytes = cipher.doFinal(preStr);
//    					sendOrderEncrypt = new String(enBytes,"gb2312");
//    					Log.info("加密后的寄件人信息:"+enBytes);
//    				}
//    				
//    				/**
//    				 * 收件人信息加密
//    				 * */			
//    				byte[] receiveStr = JSON.toJSONString(recevieOrder).getBytes();
//    				Log.info("加密长度:"+receiveStr.length);
//    				if(receiveStr.length>117)
//    				{
//    					byte[] enBytes =  CipherRSA.segmentencrypt(JSON.toJSONString(sendOrder).getBytes(),sysPubkey);
//    					receiveOrderEncrypt = new String(enBytes,"gb2312");
//    					Log.info("加密后的寄件人信息:"+enBytes);
//    				}else{
//    					Cipher cipher=Cipher.getInstance("RSA");
//    					cipher.init(Cipher.ENCRYPT_MODE, CipherRSA.getPublicRSAKey(sysPubkey));
//    					byte[] enBytes = cipher.doFinal(receiveStr);
//    					receiveOrderEncrypt = new String(enBytes,"gb2312");
//    					Log.info("加密后的寄件人信息:"+enBytes);
//    				}
//    				
//    			} catch (NoSuchAlgorithmException e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    				Log.info("下单失败");
//    			} catch (NoSuchPaddingException e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    				Log.info("下单失败");
//    			} catch (InvalidKeyException e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    				Log.info("下单失败");
//    			} catch (Exception e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    				Log.info("下单失败");
//    			}
//            	 
//            	 Order order = new Order();
//    			 order.setSendId(node.getNodeId());
//    			 order.setOrderInfoSend(sendOrderEncrypt);
//    			 order.setOrderInfoRecevie(receiveOrderEncrypt);
//    			 order.setOrderInfoTime(DateUtil.dateToString(new Date()));
//    			 order.setOrderIdState(0);
//    			 order.setOrderIdVaild(0);
//    			 
//    			 /**
//    			  * 持久化,表单信息存入数据库
//    			  * */
//    			boolean falg =  saveOrder(order);
//    			if(falg)
//    			{
//    				/**
//    				 * true 订单保存成功
//    				 * 邮件通知寄件人
//    				 * */
//    				 model.setViewName("index");
//    				 nodeLoadClient.sendNodeOptionMail(sendOrder.getSendNodeEmail(), DateUtil.dateToString(new Date())+"下单成功");
//    				 return model;
//    			}else{
//    				Log.info("订单存储失败...");
//    				model.addObject("errorMsg","下单失败,请重新下单...");
//    				//邮件通知寄件人
//    			    nodeLoadClient.sendNodeOptionMail(sendOrder.getSendNodeEmail(), DateUtil.dateToString(new Date())+"下单失败.");
//    			} 
//            }
//            else
//            {
//            	//未登录
//            	model.setViewName("login");
//                model.addObject("errorMsg","请先登录后,在进行节点交易...");
//                return model;
//            }
//		}
//		model.setViewName("order");
//		return model;
//	}
	
	/**
     * 检查智能节点邮箱是否已经被注册
     * */
    public boolean checkMail(String email){
        String checkResult = registryNodeService.checkMail(email);
        if(email == null || email == "")
        {
        	return false;
        }
        if(checkResult != null)
        {
            return false;
        }
        return true;
    }
    
    /**
     * 持久化订单信息
     * */
    public boolean saveOrder(OrderDTO order)
    {
    	return orderServiceImpl.orderSave(order);
    }
     
    
    public static void main(String[] args) {
		/**
		 * 做个简单中文加密测试一下
		 * */
    	String sysPubkey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDU5Ky3TAPojcAEHeZwmnvPypGAWvuR8ijk4p2z"+
                           "M9bDKNK1sUg1Pg89msldNnlBJ71+lTdM8XtN9qoEa8o8kb6GlhFEm3n5Gv4Tm7CT0r4yoxpD1atq"+
                           "8+XRfKJWxoubLi3WDA2sr/yi+jixDxv3FmPA7xtiRWMBG307YUYThFWGCQIDAQAB";
    	String preStr = "我是小猪猪0";
    	
		try {
			byte[] bt =CipherRSA.decryptBase64(preStr);
			Cipher cipher=Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, CipherRSA.getPublicRSAKey(sysPubkey));
			byte[] enBytes = cipher.doFinal(bt);
			String sendOrderEncrypt = new String(enBytes);
			System.out.println("加密后的数据为:"+sendOrderEncrypt);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**
     * 订单解密
     * 1.显示订单信息
     * */
    @PostMapping("/queryOrder")
    @ResponseBody
    public Map<String,Object> decryptOrder(@RequestParam("page") String page)
    {
    	/**
    	 * 1.获取订单信息
    	 * 2.获取私钥
    	 * 3.私钥解密订单信息
    	 * */
//    	int id = 1;
//    	Order order = orderServiceImpl.getOrderById(id);
    	/**
    	 * 获取订单信息
    	 * */
    	
    	Map<String,Object> allOrder=new HashMap<>();
    	
   	    int pageNum = Integer.parseInt(page);

        PageHelper.startPage(pageNum,5);
        PageInfo<Order> OrderInfo = new PageInfo<>(orderServiceImpl.getOrderAll());

        allOrder.put("allOrderInfo",OrderInfo.getList());
        allOrder.put("flag",OrderInfo.getSize());
        allOrder.put("totalPages",OrderInfo.getPages());
        allOrder.put("currentPage",OrderInfo.getPageNum());
   	
   	
      	return allOrder;
    }
    
    
    /**
     * 下单信息
     * */
    @PostMapping("/savaOrder")
	@ResponseBody
	public  ModelAndView saveNodeOrder(@Validated  @ModelAttribute RecevieOrder recevieOrder, @ModelAttribute SendOrder sendOrder , BindingResult bindingResult)
	{
    	ModelAndView model=new ModelAndView();
    	/**
//		 * 如果有错误信息,则返回提示
//		 * */
		if(bindingResult.hasErrors())
		{
			  model.setViewName("order");
	          //model.addObject("error", true);
	          model.addObject("errorMsg",bindingResult.getAllErrors());
	          return model;
		}else{
			/**
//			 * 判断节点是否存在,根据填写的注册邮箱获取其id,查询redis,是否存在。
//			 * */
			String email = sendOrder.getSendNodeEmail();
			if(email == null)
			{
				model.setViewName("order");
				model.addObject("errorMsg","注册邮箱信息不能为空..");
				
				return model;
			}
			/**
			 * 验证邮箱是否存在
			 * */
            if(checkMail(email))
            {
                //不存在
                model.setViewName("order");
                model.addObject("errorMsg","该节点不属于联盟节点,无法使用联盟权利...,请更换节点,或者注册该节点为联盟节点..");
                return model;
            }
            
            /**
             * 获取节点id
             * */
            Map<String, Object> nodeInfo = new HashMap<>();
            nodeInfo.put("loginEmail", email.toString().trim());
            Node node = nodeLoadServiceImpl.loginNode(nodeInfo);
            
            /**
             * 判断节点是否已经登录
             * */
            boolean state = loginRedisServiceImpl.checkLoginNodeRedis("node"+node.getNodeId());
            
            
             OrderDTO order = new OrderDTO();
			 order.setSendId(node.getNodeId());
			 order.setOrderInfoSend(JSON.toJSONString(sendOrder));
			 order.setOrderInfoRecevie(JSON.toJSONString(recevieOrder));
			 order.setOrderInfoTime(DateUtil.dateToString(new Date()));
			 order.setOrderIdState(0);
			 order.setOrderIdVaild(0);
            
            /**
//			  * 持久化,表单信息存入数据库
//			  * */
			boolean falg =  saveOrder(order);
			if(falg)
			{
				/**
				 * true 订单保存成功
				 * 邮件通知寄件人
				 * */
				 model.setViewName("index");
				 nodeLoadClient.sendNodeOptionMail(sendOrder.getSendNodeEmail(), DateUtil.dateToString(new Date())+"下单成功");
				 return model;
			}else{
				Log.info("订单存储失败...");
				model.addObject("errorMsg","下单失败,请重新下单...");
				//邮件通知寄件人
			    nodeLoadClient.sendNodeOptionMail(sendOrder.getSendNodeEmail(), DateUtil.dateToString(new Date())+"下单失败.");
			} 
		}
		model.setViewName("order");
		return model;
	}
    
    /**
     * 查询节点信息
     * */
    @PostMapping("/queryAllNodeInfo")
    @ResponseBody
    public Map<String,Object>  getAllNode(@RequestParam("page") String page)
    {
    	Log.info("查询节点信息");
    	Map<String,Object> allNode=new HashMap<>();
    	
    	 int pageNum = Integer.parseInt(page);

         PageHelper.startPage(pageNum,5);
         PageInfo<Node> nodeInfo = new PageInfo<>(nodeLoadServiceImpl.getAllNode());

         allNode.put("allNodeInfo",nodeInfo.getList());
         allNode.put("flag",nodeInfo.getSize());
         allNode.put("totalPages",nodeInfo.getPages());
         allNode.put("currentPage",nodeInfo.getPageNum());
    	
    	
    	return allNode;
    }
    
    /**
     * 订单模块
     * */
    @GetMapping("/validOrder")
    public ModelAndView product()
    {
    	ModelAndView modelAndView = new ModelAndView("product");
    	
    	/**
    	 * 获取订单信息
    	 * */
    	return modelAndView;
    }
    
    /**
     * 在线节点解析
     * */
    @PostMapping("/getOnlineNode")
    @ResponseBody
    public Map<String,Object> getNodeOnline()
    {
    	Map<String,Object> allNodeOnline=new HashMap<>();
    	/**
    	 * 1.reids查询出所有的key = Node节点信息
    	 * 2.把对象存入Map,返回
    	 * 
    	 * 1.批量查询key = ndoe + *
    	 * */
    	allNodeOnline = loginRedisServiceImpl.getNodeOnline("node");
    	if("".equals(allNodeOnline) || allNodeOnline == null)
    	{   
    		Log.info("无在线节点");
    		return allNodeOnline;
    	}
    	return  allNodeOnline;
    }
    
    /**
     * 发起新交易
     * /addProduct
     * sId 交易节点id
     * 
     * */
    @GetMapping("/addProduct")
    @Transactional
    public ModelAndView getAddProduct(@RequestParam String id,@RequestParam String sId)
    {   
    	ModelAndView modelAndView = new ModelAndView();
    	/**
    	 * 获取到id进行更改订单状态
    	 * */
    	if("".equals(id) || id ==  null)
    	{
    		Log.info("交易流水号为空,无法完全确认,请重新验证..");
    		modelAndView.setViewName("product");
    		return modelAndView;
    	}
    	Map<String, Object> orderInfo = new HashMap<>();
    	orderInfo.put("state", 1);
    	orderInfo.put("id", id);
    	boolean falg = orderServiceImpl.updateOrderState(orderInfo);
    	String email;
    	if(falg)
    	{
    		Log.info(id+"订单确认成功");
    		/**
    		 * 通知节点,订单确认成功
    		 * 1.获取交易id对象注册邮箱
    		 * 2.向其发送通知邮件
    		 * */
    	     email = orderServiceImpl.getEmailByNodeId(sId);
    		 nodeLoadClient.sendNodeOptionMail(email, DateUtil.dateToString(new Date())+"交易已被超级节点确认...等待链节点验证...");
    	}else{
    		Log.info(id+"订单状态更新失败...");
    		modelAndView.setViewName("product");
    		return modelAndView;
    	}
    	modelAndView.setViewName("addProduct");
    	/**
    	 * 把交易信息查询出来,并返回给视图
    	 * 1.接收方的邮箱
    	 * 2.发送方的邮箱
    	 * 3.交易内容
    	 * 4.发起时间 -- 现在时间
    	 * */
    	Order orderDTO = orderServiceImpl.getOrderById(Integer.parseInt(id));
    	RecevieOrder receiveInfo = JSON.parseObject(orderDTO.getOrderInfoRecevie(), RecevieOrder.class);
    	/**
    	 * 交易内容
    	 * */
    	SendOrder sendInfo = JSON.parseObject(orderDTO.getOrderInfoSend(), SendOrder.class);
    	
    	VaildOrder order = new VaildOrder();
    	order.setSendEmail(email);
    	order.setTime(DateUtil.dateToString(new Date()));
    	order.setReceiveEmail(receiveInfo.getReceiveNodeEmail());
    	order.setInfo(sendInfo.getSendNodeProduct());
    	
    	modelAndView.addObject("vaildOrder", order);
    	return modelAndView;
    }
    
    
    /**
     * 链交易验证
     * */
    @PostMapping("/vaildOrderByChain")
    public ModelAndView vaildOrderByBlockChainNode(@Validated  @ModelAttribute VaildOrder vaildOrder, BindingResult bindingResult)
    {
    	ModelAndView modelAndView = new ModelAndView();
    	/**
    	 * 1.vaildOrder验证
    	 * 2.判断当前在线节点数量,大于1个节点即可验证
    	 * 3.将vaildOrder打包成块,然后发送至rabbitmq上面
    	 * 4.在线节点消费此区块,并进行验证,验证成功返回"验证成功"
    	 * 5.系统收集返回结果,进行判断
    	 * 6.若此时返回的结果集成功大于验证节点数的一半,系统即可将区块写入系统账本,写入成功后,全网通知,webSocket通知
    	 * 7.邮件通知交易发起者,交易验证成功
    	 * */
    	if(bindingResult.hasErrors())
		{
    		  modelAndView.setViewName("superIndex");
	          //model.addObject("error", true);
    		  modelAndView.addObject("errorMsg",bindingResult.getAllErrors());
	          return modelAndView;
		}else{
			/**
			 * 解析在线节点
			 * */
			Map<String,Object> allNodeOnline=new HashMap<>();
	    	/**
	    	 * 1.reids查询出所有的key = Node节点信息
	    	 * 2.把对象存入Map,返回
	    	 * 
	    	 * 1.批量查询key = ndoe + *
	    	 * */
	    	allNodeOnline = loginRedisServiceImpl.getNodeOnline("node");
	    	if("".equals(allNodeOnline) || allNodeOnline == null)
	    	{   
	    		 Log.info("解析在线节点出错");
	    		 modelAndView.setViewName("superIndex");
	    		 return modelAndView;
	    	}
	    	//判断在线节点个数
	    	if(allNodeOnline.size()>1)
	    	{
	    		//打包成块
	    		/**
	    		 * 封装信息,JSON化处理数据,fegin调用接口
	    		 * 1.寄件人信息
	    		 * 2.收件人信息
	    		 * */
	    		VaildBlockInfo vaildBlockInfo = new VaildBlockInfo();
	    		vaildBlockInfo.setReceiveInfo(vaildOrder.getReceiveEmail());
	    		vaildBlockInfo.setSendInfo(vaildOrder.getSendEmail());
	    		blockVaildClient.vaildBlockOrder(vaildOrder.getReceiveEmail(),vaildOrder.getSendEmail());
	    		
	    		/**
	    		 * 获取验证结果集
	    		 * 进行判断
	    		 * 如果节点不同意此次交易,则告知节点交易失败,交易订单状态改为3,并更改其vaild验证节点个数.此属性为验证此次交易的节点个数
	    		 * */
	    		
	    	}	
	    	else{
	    		modelAndView.setViewName("superIndex");
	    		Log.info("在线节点个数不足..无法进行链验证..");
	    		modelAndView.addObject("message", "在线节点个数不足..无法进行链验证..");
	    		return modelAndView;
	    	}
		}
    	modelAndView.setViewName("superIndex");
		return modelAndView;
    }
    
    
}
