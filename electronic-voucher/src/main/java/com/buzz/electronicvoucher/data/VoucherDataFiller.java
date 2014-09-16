package com.buzz.electronicvoucher.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.w3c.dom.Document;

import com.buzz.electronicvoucher.ElectronicVoucherStatusTypes;
import com.buzz.electronicvoucher.schema.v1_1_0.InfoTributaria;
import com.buzz.persistence.util.BeanUtils;
import com.buzz.persistence.util.JPAServerSession;
import com.buzz.persistence.util.JPASession;
import com.buzz.persistence.voucher.Tcomprobante;
import com.buzz.persistence.voucher.TcomprobantePK;
import com.buzz.persistence.voucher.Tsesioncomprobante;
import com.buzz.persistence.voucher.TsesioncomprobantePK;
import com.buzz.tools.TokenCreator;

public class VoucherDataFiller extends DataFiller {
	
	private Tsesioncomprobante sesionVoucher;
	private Tcomprobante voucherBean;
	
	private String sessionToken;
	
	private String status;
	
	private JSONObject jsonResponse;

	public void fillData(Object pContributor, Object pVoucher) throws Exception {
		this.sessionToken = (String)super.parameters.get("sessionToken");
		status = (String)super.parameters.get("status");
		jsonResponse = (JSONObject)super.parameters.get("jsonResponse");
		
		InfoTributaria infoTributaria = (InfoTributaria)pVoucher.getClass()
    			.getDeclaredMethod("getInfoTributaria").invoke(pVoucher);
		
		TsesioncomprobantePK pk = new TsesioncomprobantePK((Integer)pContributor,
				String.valueOf(System.currentTimeMillis()));
		this.sesionVoucher = new Tsesioncomprobante(pk);
		sesionVoucher.setCsesionFk(sessionToken);
		sesionVoucher.setMensajeEntrada("null");
		sesionVoucher.setMensajeSalida(jsonResponse.toString());
		sesionVoucher.setXmlComprobante((String)parameters.get("signedVoucher"));
		sesionVoucher.setResultado(status);
		sesionVoucher.setCodigosError(ElectronicVoucherStatusTypes.AUTHORIZED
				.getStatus().equals(status) ? null:((JSONObject)jsonResponse.get("mensajes")).toString());
		
		
		/*TcomprobantePK voucherPk = new TcomprobantePK((Integer)pContributor, sesionToken );
		this.voucherBean = new Tcomprobante(voucherPk);
		
		BeanUtils.copyFields(infoTributaria, this.voucherBean);*/
		this.save();
	}

	public void save() throws Exception {
		//if(voucherBean != null) {
			//JPAServerSession.saveOrUpdate(voucherBean);
			JPASession.saveOrUpdate(sesionVoucher);
			//JPASession.saveOrUpdate(voucherBean);
		//}
		
	}

	public String fillReportData(Document doc,
			ConcurrentHashMap<String, Object> reportParameters)
			throws Exception {
		return null;
	}

}
