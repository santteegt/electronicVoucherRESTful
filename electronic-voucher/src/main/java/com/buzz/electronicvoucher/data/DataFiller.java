package com.buzz.electronicvoucher.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;

public abstract class DataFiller {
	
	protected Map <String, Object> parameters;
	
	protected String reportName;
	
	public void setParameters(Map <String, Object> parameters, String reporName) {
		this.parameters = parameters;
		this.reportName = reporName;
		
	}
	
	public String getReportName(){
		return this.reportName;
	}
	
	public abstract void fillData(Object pk, Object voucher)throws Exception;
	
	public abstract void save()throws Exception;
	
	public abstract String fillReportData(Document doc, ConcurrentHashMap<String, Object> reportParameters)throws Exception;

}
