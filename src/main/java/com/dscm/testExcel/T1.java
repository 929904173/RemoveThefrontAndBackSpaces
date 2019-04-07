package com.dscm.testExcel;

import java.util.HashMap;
import java.util.Map;

public class T1 {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("加藤惠", "16");
		map.put("八雲紫", "17");
		map.put("穹妹", "15");
		for(Map.Entry<String, String> entry: map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

}
