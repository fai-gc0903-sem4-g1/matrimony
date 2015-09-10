package com.test;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
//		List<String> strs=new ArrayList<String>();
//		strs.add("PHONG LE 14V/100");
//		strs.add("HONG THUY TINH 3V/1");
		for(String a:args){
			System.out.println(a);
		}
//		StringBuilder rao=new StringBuilder();
//		rao.append("BAN>>>>>>>>>>>>>>>>>>>");
//		for(String s:strs){
//			int len=34-s.length();
//			rao.append(s);
//			for(int i=0;i<len;i++){
//				rao.append(">");
//			}
//		}
//		rao.append("(xh)(xh)(xh)(xh)(xh)(xh)(xh)(xh)(xh)(xh)(xh)");
//		System.out.println(rao.toString());
//		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(rao.toString()), null);
	}
}
