package com.buzz.electronicvoucher;

public enum ElectronicVoucherTypes {
	
	BILL("FACTURA","01","com.buzz.electronicvoucher.data.BillDataFiller"),
	CREDITNOTE("NOTA DE CREDITO","04","com.buzz.electronicvoucher.data.NoteCreditDataFiller"),
	DEBITNOTE("NOTA DE DEBITO","05","com.buzz.electronicvoucher.data.NoteDebitDataFiller"),
	GUIDEREMISSION("GUIA REMISION","06","com.buzz.electronicvoucher.data.GuideRemissionDataFiller"),
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
