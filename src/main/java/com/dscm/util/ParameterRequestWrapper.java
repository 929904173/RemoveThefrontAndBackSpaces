package com.dscm.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class ParameterRequestWrapper  extends HttpServletRequestWrapper {

	private Map<String , String[]> params = new HashMap<String, String[]>();      
	
	public ParameterRequestWrapper(HttpServletRequest request) {
		super(request);
		Map<String, String[]> requestMap=request.getParameterMap();  
        System.out.println("转化前参数："+JSON.toJSONString(requestMap));  
        this.params.putAll(requestMap);  
        this.modifyParameterValues();  
        System.out.println("转化后参数："+JSON.toJSONString(params));  
	}

	 @Override  
     public ServletInputStream getInputStream() throws IOException {  
           //非json类型，直接返回  
           if(!super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){  
               return super.getInputStream();  
           }  
           //为空，直接返回  
           String json = IOUtils.toString(super.getInputStream(), "utf-8");  
           if (StringUtils.isEmpty(json)) {  
               return super.getInputStream();  
           }  
           System.out.println("转化前参数："+json);  
           Map<String,Object> map=StringJsonUtils.jsonStringToMap(json);  
           System.out.println("转化后参数："+JSON.toJSONString(map));  
           ByteArrayInputStream bis = new ByteArrayInputStream(JSON.toJSONString(map).getBytes("utf-8"));  
           return new MyServletInputStream(bis);  
     }  
	 
	private void modifyParameterValues() {
		 Set<String> set =params.keySet();      
         Iterator<String> it=set.iterator();      
         while(it.hasNext()){      
            String key= (String) it.next();      
            String[] values = params.get(key);      
            values[0] = values[0].trim();      
            params.put(key, values);      
          }      
	}

	  @Override      
      public String getParameter(String name) {  
          String[]values = params.get(name);      
          if(values == null || values.length == 0) {      
              return null;      
          }      
          return values[0];      
      }    
	  public String[] getParameterValues(String name) {//同上      
          return params.get(name);      
     }  
	  
	  class MyServletInputStream extends  ServletInputStream{  
	        private ByteArrayInputStream bis;  
	        public MyServletInputStream(ByteArrayInputStream bis){  
	            this.bis=bis;  
	        }  
	        @Override  
	        public boolean isFinished() {  
	            return true;  
	        }  
	  
	        @Override  
	        public boolean isReady() {  
	            return true;  
	        }  
	  
	        @Override  
	        public void setReadListener(ReadListener listener) {  
	              
	        }  
	        @Override  
	        public int read() throws IOException {  
	             return bis.read();  
	        }  
	      }  
}
