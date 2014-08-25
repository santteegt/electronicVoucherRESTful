package com.buzz.tools;

import net.sf.jasperreports.engine.JRExporter;

public enum ReportTypes {
    PDF("net.sf.jasperreports.engine.export.JRPdfExporter", "application/pdf", "pdf"), CSV(
            "net.sf.jasperreports.engine.export.JRCsvExporter", "text/csv", "csv"), HTML(
            "net.sf.jasperreports.engine.export.JRHtmlExporter", "text/html", "html"), RTF(
            "net.sf.jasperreports.engine.export.JRRtfExporter", "application/rtf", "rtf"), TEXT(
            "net.sf.jasperreports.engine.export.JRRtfExporter", "application/rtf", "rtf"), TXT(
            "it.businesslogic.ireport.export.JRTxtExporter", "text/plain", "txt"), TEXT1(
            "net.sf.jasperreports.engine.export.JRTextExporter", "text/plain", "txt"), XLS(
            "net.sf.jasperreports.engine.export.JRXlsExporter", "application/x-excel", "xls"), XML(
            "net.sf.jasperreports.engine.export.JRXmlExporter", "text/xml", "xml");
    private String reportExporter;

    private String contentType;

    private String extension;

    private ReportTypes(String pReportExporter, String pContentType, String pExtension) {
        this.reportExporter = pReportExporter;
        this.contentType = pContentType;
        this.extension = pExtension;

    }

    public String getContentType() {
        return this.contentType;
    }

    public JRExporter getExporter() throws Exception {
            return (JRExporter) Class.forName(this.reportExporter).newInstance();
    }

    public String getExtension() {
        return this.extension;
    }
}

