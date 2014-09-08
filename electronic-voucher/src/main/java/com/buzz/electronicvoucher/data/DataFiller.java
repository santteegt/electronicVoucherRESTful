package com.buzz.electronicvoucher.data;

import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;

public interface DataFiller {
	
	public void fillData(Object pk, Object voucher)throws Exception;
	
	public void save()throws Exception;
	
	public String fillReportData(Document doc, ConcurrentHashMap<String, Object> reportParameters)throws Exception;

}
