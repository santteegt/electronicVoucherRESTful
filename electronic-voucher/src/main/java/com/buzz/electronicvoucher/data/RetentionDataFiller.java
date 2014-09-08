package com.buzz.electronicvoucher.data;

import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RetentionDataFiller implements DataFiller {
	
	private String reportName="comprobanteRetencion";

	public void fillData(Object pk, Object voucher) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void save() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String fillReportData(Document document,
			ConcurrentHashMap<String, Object> reportParameters)
			throws Exception {
		NodeList nodeinfoRetencion = document
				.getElementsByTagName("infoCompRetencion");
		for (int s = 0; s < nodeinfoRetencion.getLength(); s++) {
			Node primerNodo = nodeinfoRetencion.item(s);
			String fechaEmision;
			String contribuyenteEspecial;
			String obligadoContabilidad;
			String razonSocialSujetoRetenido;
			String identificacionSujetoRetenido;
			String periodoFiscal;
			
			Element primerElemento = (Element) primerNodo;
			

			contribuyenteEspecial = primerElemento
					.getElementsByTagName("contribuyenteEspecial").item(0)
					.getChildNodes().item(0).getNodeValue();

			obligadoContabilidad = primerElemento
					.getElementsByTagName("obligadoContabilidad").item(0)
					.getChildNodes().item(0).getNodeValue();

			razonSocialSujetoRetenido = primerElemento
					.getElementsByTagName("razonSocialSujetoRetenido").item(0)
					.getChildNodes().item(0).getNodeValue();

			identificacionSujetoRetenido = primerElemento
					.getElementsByTagName("identificacionSujetoRetenido").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			fechaEmision = primerElemento
					.getElementsByTagName("fechaEmision").item(0)
					.getChildNodes().item(0).getNodeValue();

			periodoFiscal = primerElemento
					.getElementsByTagName("periodoFiscal").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			reportParameters.put("CONT_ESPECIAL", 
					contribuyenteEspecial);
			reportParameters.put("LLEVA_CONTABILIDAD", 
					obligadoContabilidad);
			reportParameters.put("RS_COMPRADOR", 
					razonSocialSujetoRetenido);
			reportParameters.put("RUC_COMPRADOR", 
					identificacionSujetoRetenido);
			reportParameters.put("FECHA_EMISION", fechaEmision);
			reportParameters.put("GUIA", "");
			reportParameters.put("EJERCICIO_FISCAL", periodoFiscal);
		}
		return this.reportName;
		
	}

}
