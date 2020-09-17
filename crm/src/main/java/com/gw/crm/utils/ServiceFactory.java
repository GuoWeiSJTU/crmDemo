package com.gw.crm.utils;

public class ServiceFactory {
	
	public static Object getService(Object service){
		
		return new com.gw.crm.utils.TransactionInvocationHandler(service).getProxy();
		
	}
	
}
