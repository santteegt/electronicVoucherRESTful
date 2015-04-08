package com.buzz.electronicvoucher.data;

import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GuideRemissionDataFiller extends DataFiller {
	
	private String reportName = "guiaRemisionFinal";

	@Override
	public String getReportName(){
		return this.reportName;
	}
	
	public void fillData(Object pk, Object voucher) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String fillReportData(Document document,
			ConcurrentHashMap<String, Object> reportParameters)
			throws Exception {
		NodeList nodeinfoNotaCredito = document
				.getElementsByTagName("infoGuiaRemision");
		for (int s = 0; s < nodeinfoNotaCredito.getLength(); s++) {
			Node primerNodo = nodeinfoNotaCredito.item(s);
			String fechaEmision;
			String contribuyenteEspecial;
			String obligadoContabilidad;
			String razonSocialComprador;
			String identificacionComprador;
			String numDocModificado;
			
			Element primerElemento = (Element) primerNodo;
			

			contribuyenteEspecial = primerElemento
					.getElementsByTagName("contribuyenteEspecial").item(0)
					.getChildNodes().item(0).getNodeValue();

			obligadoContabilidad = primerElemento
					.getElementsByTagName("obligadoContabilidad").item(0)
					.getChildNodes().item(0).getNodeValue();

			razonSocialComprador = primerElemento
					.getElementsByTagName("razonSocialTransportista").item(0)
					.getChildNodes().item(0).getNodeValue();

			identificacionComprador = primerElemento
					.getElementsByTagName("rucTransportista").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			fechaEmision = primerElemento
					.getElementsByTagName("fechaIniTransporte").item(0)
					.getChildNodes().item(0).getNodeValue();

			numDocModificado = primerElemento
					.getElementsByTagName("dirPartida").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			reportParameters.put("CONT_ESPECIAL", 
					contribuyenteEspecial);
			reportParameters.put("LLEVA_CONTABILIDAD", 
					obligadoContabilidad);
			reportParameters.put("RS_COMPRADOR", 
					razonSocialComprador);
			reportParameters.put("RUC_COMPRADOR", 
					identificacionComprador);
			reportParameters.put("FECHA_EMISION", fechaEmision);
			reportParameters.put("GUIA", "");
			reportParameters.put("EJERCICIO_FISCAL", numDocModificado);
		}
		return this.reportName;
	}

}
