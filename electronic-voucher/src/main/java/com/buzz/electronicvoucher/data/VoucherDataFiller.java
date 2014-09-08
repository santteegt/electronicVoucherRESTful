package com.buzz.electronicvoucher.data;

import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;

import com.buzz.electronicvoucher.schema.v1_1_0.InfoTributaria;
import com.buzz.persistence.util.BeanUtils;
import com.buzz.persistence.util.JPAServerSession;
import com.buzz.persistence.util.JPASession;
import com.buzz.persistence.voucher.Tcomprobante;
import com.buzz.persistence.voucher.TcomprobantePK;
import com.buzz.tools.TokenCreator;

public class VoucherDataFiller implements DataFiller {
	
	private Tcomprobante voucherBean;
	
	protected final String token = TokenCreator.createToken();

	public void fillData(Object pContributor, Object pVoucher) throws Exception {
		InfoTributaria infoTributaria = (InfoTributaria)pVoucher.getClass()
    			.getDeclaredMethod("getInfoTributaria").invoke(pVoucher);
		TcomprobantePK voucherPk = new TcomprobantePK((Integer)pContributor, token );
		this.voucherBean = new Tcomprobante(voucherPk);
		
		BeanUtils.copyFields(infoTributaria, this.voucherBean);
		this.save();
	}

	public void save() throws Exception {
		if(voucherBean != null) {
			//JPAServerSession.saveOrUpdate(voucherBean);
			JPASession.saveOrUpdate(voucherBean);
		}
		
	}

	public String fillReportData(Document doc,
			ConcurrentHashMap<String, Object> reportParameters)
			throws Exception {
		return null;
	}

}
