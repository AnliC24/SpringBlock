package tern.block.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: blockChainIdea
 * @Date: 2019/1/23 2:52
 * @Author: windC~
 * @Description: 日期格式处理
 */
public class DateUtil {
     public static void main(String[] args)
     {
    	// SimpleDateFormat date=new SimpleDateFormat("yy-MM-dd HH:mm");
    	 String sql="2018-07-01 07:10";
    	 java.sql.Date date=java.sql.Date.valueOf(sql);
			System.out.println(date);
     }
	/**
	 * ����ת�ַ��� �ȴ�������/ʱ���ʽ����? new SimpleDateFormat(pattern)���ٵ��ø�ʽ�����format������
	 * 
	 * @param date
	 *            ����
	 * @param pattern
	 *            ���ڸ�ʽ
	 * @return ������Ӧ��ʽ�������ַ�����Ϣ
	 */
	public static String dateToString(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * �ַ���ת���� �ȴ�������/ʱ���ʽ����? new SimpleDateFormat(pattern)���ٵ��ø�ʽ�����parse������
	 * 
	 * @param dateStr
	 *            �����ַ���
	 * @param pattern
	 *            ���ڸ�ʽ
	 * @return ��������
	 */
	public static Date stringToDate(String dateStr, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = (Date) df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * ����ת�ַ���
	 * 
	 * @param date
	 *            ����
	 * @return ���ع̶���ʽ�������ַ�����Ϣ
	 */
	public static String dateToString(Date date) {
		/* 1. ����dateToString(Date date, "yyyy-MM-dd HH:mm:ss") */   
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * �ַ���ת����
	 * 
	 * @param dateStr
	 *            �����ַ���
	 * @return ����Ӧ��ʽ�ַ���ת��Ϊ����
	 */
	public static Date stringToDate(String dateStr) {
		/* 1. ����stringToDate(String dateStr, "yyyy-MM-dd HH:mm:ss")�� */
		return stringToDate(dateStr, "yyyy-MM-dd HH-mm-ss");

	}
	
	/**
	 * �ַ���תSqlDate����
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static java.sql.Date toSqlDate(String dateStr, String pattern) {
		return new java.sql.Date(stringToDate(dateStr, pattern).getTime());
	}

	/**
	 * �ַ���תTimeStamp����
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static java.sql.Timestamp StringtoTimestamp(String dateStr, String pattern) {
		SimpleDateFormat sp = new SimpleDateFormat(pattern);
		Date utildate = null;
		java.sql.Timestamp timestampdate = null;
		try {
			utildate = sp.parse(dateStr);
			timestampdate = new java.sql.Timestamp(utildate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestampdate;	
	}
}
