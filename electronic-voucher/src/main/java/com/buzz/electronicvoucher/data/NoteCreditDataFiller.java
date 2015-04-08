package com.buzz.electronicvoucher.data;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NoteCreditDataFiller extends DataFiller {
	
	private String reportName = "notaCreditoFinal";
	
	@Override
	public String getReportName(){
		return this.reportName;
	}
	
	public void fillData(Object pk, Object voucher) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void save() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String fillReportData(Document document,
			ConcurrentHashMap<String, Object> reportParameters)
			throws Exception {
		String iva0 = "0";
		String iva12 = "0";
		String ivas = "0";
		reportParameters.put("IVA_0", iva0);
		reportParameters.put("IVA_12", iva12);
		reportParameters.put("NO_OBJETO_IVA", ivas);
		NodeList nodeinfoNotaCredito = document
				.getElementsByTagName("infoNotaCredito");
		NodeList nodetotalImpuesto = document
				.getElementsByTagName("totalImpuesto");
		NodeList nodeImpuesto = document
				.getElementsByTagName("impuesto");
		String valor="0";
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
					.getElementsByTagName("razonSocialComprador").item(0)
					.getChildNodes().item(0).getNodeValue();

			identificacionComprador = primerElemento
					.getElementsByTagName("identificacionComprador").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			fechaEmision = primerElemento
					.getElementsByTagName("fechaEmision").item(0)
					.getChildNodes().item(0).getNodeValue();

			numDocModificado = primerElemento
					.getElementsByTagName("numDocModificado").item(0)
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
		reportParameters.put("ICE", valor);
		reportParameters.put("IVA", valor);
		for (int s = 0; s < nodetotalImpuesto.getLength(); s++) {
			Node primerNodo = nodetotalImpuesto.item(s);
			String codigo;
			Element primerElemento = (Element) primerNodo;
			codigo = primerElemento
					.getElementsByTagName("codigo").item(0)
					.getChildNodes().item(0).getNodeValue();

			valor = primerElemento
					.getElementsByTagName("valor").item(0)
					.getChildNodes().item(0).getNodeValue();

			if ("3".equals(codigo)) {
				reportParameters.put("ICE", valor);
			} else {
				reportParameters.put("IVA", valor);
			}
		}
		BigDecimal total=BigDecimal.ZERO;
		for (int s = 0; s < nodeImpuesto.getLength(); s++) {
			Node primerNodo = nodeImpuesto.item(s);
			String codigo;
			String totivas;
			Element primerElemento = (Element) primerNodo;
			codigo = primerElemento
					.getElementsByTagName("codigo").item(0)
					.getChildNodes().item(0).getNodeValue();

			totivas = primerElemento
					.getElementsByTagName("baseImponible").item(0)
					.getChildNodes().item(0).getNodeValue();
			total=new BigDecimal(totivas);			
			if ("0".equals(codigo)) {
				iva0=(total.add(new BigDecimal(iva0))).toString();
			} else {
				iva12=(total.add(new BigDecimal(iva12))).toString();
			}
		}
		reportParameters.put("IVA_0", iva0);
		reportParameters.put("IVA_12", iva12);
		return this.reportName;

	}

}
