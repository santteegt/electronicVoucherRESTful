package com.buzz.electronicvoucher.data;

import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BillDataFiller extends DataFiller {

	private String reportName = "factura";
	
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
		String ivas = "0";
		reportParameters.put("IVA_0", ivas);
		reportParameters.put("IVA_12", ivas);
		reportParameters.put("NO_OBJETO_IVA", ivas);
		NodeList nodeinfoFactura = document
				.getElementsByTagName("infoFactura");
		NodeList nodetotalImpuesto = document
				.getElementsByTagName("totalImpuesto");
		
		for (int s = 0; s < nodeinfoFactura.getLength(); s++) {
			Node primerNodo = nodeinfoFactura.item(s);
			String fechaEmision;
			String contribuyenteEspecial;
			String obligadoContabilidad;
			String razonSocialComprador;
			String identificacionComprador;
			String propina;
			String importeTotal;
			String totalSinImpuestos;
			String totalDescuento;
			
			Element primerElemento = (Element) primerNodo;
			fechaEmision = primerElemento
					.getElementsByTagName("fechaEmision").item(0)
					.getChildNodes().item(0).getNodeValue();

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

			propina = primerElemento
					.getElementsByTagName("propina").item(0)
					.getChildNodes().item(0).getNodeValue();

			importeTotal = primerElemento
					.getElementsByTagName("importeTotal").item(0)
					.getChildNodes().item(0).getNodeValue();

			totalSinImpuestos = primerElemento
					.getElementsByTagName("totalSinImpuestos").item(0)
					.getChildNodes().item(0).getNodeValue();

			totalDescuento = primerElemento
					.getElementsByTagName("totalDescuento").item(0)
					.getChildNodes().item(0).getNodeValue();

			
			reportParameters.put("FECHA_EMISION", fechaEmision);
			reportParameters.put("CONT_ESPECIAL", 
					contribuyenteEspecial);
			reportParameters.put("LLEVA_CONTABILIDAD", 
					obligadoContabilidad);
			reportParameters.put("RS_COMPRADOR", 
					razonSocialComprador);
			reportParameters.put("RUC_COMPRADOR", 
					identificacionComprador);
			reportParameters.put("PROPINA", propina);
			reportParameters.put("SUBTOTAL", totalSinImpuestos);
			reportParameters.put("TOTAL_DESCUENTO", 
					totalDescuento);
			reportParameters.put("VALOR_TOTAL", importeTotal);
		}
		for (int s = 0; s < nodetotalImpuesto.getLength(); s++) {
			Node primerNodo = nodetotalImpuesto.item(s);
			String codigo;
			String valor;
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
		return this.reportName;
	}

}
