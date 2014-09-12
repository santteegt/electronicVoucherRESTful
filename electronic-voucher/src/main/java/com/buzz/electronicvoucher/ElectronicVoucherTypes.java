package com.buzz.electronicvoucher;

public enum ElectronicVoucherTypes {
	
	BILL("FACTURA","01","com.buzz.electronicvoucher.data.BillDataFiller"),
	RETENTION("COMPROBANTE RETENCION","07","com.buzz.electronicvoucher.data.RetentionDataFiller");
	
	private String name;
	
	private String code;
	
	private String dataFillerClass;
	
	private ElectronicVoucherTypes(String name, String code, String dataFillerClass) {
		this.name = name;
		this.code = code;
		this.dataFillerClass = dataFillerClass;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getdataFillerClass(){
		return this.dataFillerClass;
	}

}
