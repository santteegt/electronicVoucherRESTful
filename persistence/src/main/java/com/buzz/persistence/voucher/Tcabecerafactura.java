/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author karina
 */
@Entity
@Table(name = "TCABECERAFACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcabecerafactura.findAll", query = "SELECT t FROM Tcabecerafactura t"),
    @NamedQuery(name = "Tcabecerafactura.findByCcfactura", query = "SELECT t FROM Tcabecerafactura t WHERE t.ccfactura = :ccfactura"),
    @NamedQuery(name = "Tcabecerafactura.findByFechaEmision", query = "SELECT t FROM Tcabecerafactura t WHERE t.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Tcabecerafactura.findByDireccionEstablecimiento", query = "SELECT t FROM Tcabecerafactura t WHERE t.direccionEstablecimiento = :direccionEstablecimiento"),
    @NamedQuery(name = "Tcabecerafactura.findByContribuyenteEspecial", query = "SELECT t FROM Tcabecerafactura t WHERE t.contribuyenteEspecial = :contribuyenteEspecial"),
    @NamedQuery(name = "Tcabecerafactura.findByObligadoContabilidad", query = "SELECT t FROM Tcabecerafactura t WHERE t.obligadoContabilidad = :obligadoContabilidad"),
    @NamedQuery(name = "Tcabecerafactura.findByTipoIdComprador", query = "SELECT t FROM Tcabecerafactura t WHERE t.tipoIdComprador = :tipoIdComprador"),
    @NamedQuery(name = "Tcabecerafactura.findByGuiaRemision", query = "SELECT t FROM Tcabecerafactura t WHERE t.guiaRemision = :guiaRemision"),
    @NamedQuery(name = "Tcabecerafactura.findByRazonSocialComprador", query = "SELECT t FROM Tcabecerafactura t WHERE t.razonSocialComprador = :razonSocialComprador"),
    @NamedQuery(name = "Tcabecerafactura.findByIdentificacionComprador", query = "SELECT t FROM Tcabecerafactura t WHERE t.identificacionComprador = :identificacionComprador"),
    @NamedQuery(name = "Tcabecerafactura.findByTotalSinImpuestos", query = "SELECT t FROM Tcabecerafactura t WHERE t.totalSinImpuestos = :totalSinImpuestos"),
    @NamedQuery(name = "Tcabecerafactura.findByTotalDescuentos", query = "SELECT t FROM Tcabecerafactura t WHERE t.totalDescuentos = :totalDescuentos"),
    @NamedQuery(name = "Tcabecerafactura.findByPropina", query = "SELECT t FROM Tcabecerafactura t WHERE t.propina = :propina"),
    @NamedQuery(name = "Tcabecerafactura.findByImporteTotal", query = "SELECT t FROM Tcabecerafactura t WHERE t.importeTotal = :importeTotal"),
    @NamedQuery(name = "Tcabecerafactura.findByMoneda", query = "SELECT t FROM Tcabecerafactura t WHERE t.moneda = :moneda")})
public class Tcabecerafactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ccfactura", nullable = false)
    private Integer ccfactura;
    @Basic(optional = false)
    @Column(name = "fecha_emision", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Column(name = "direccion_establecimiento", length = 300)
    private String direccionEstablecimiento;
    @Column(name = "contribuyente_especial", length = 5)
    private String contribuyenteEspecial;
    @Column(name = "obligado_contabilidad", length = 2)
    private String obligadoContabilidad;
    @Basic(optional = false)
    @Column(name = "tipo_id_comprador", nullable = false, length = 2)
    private String tipoIdComprador;
    @Column(name = "guia_remision", length = 17)
    private String guiaRemision;
    @Basic(optional = false)
    @Column(name = "razon_social_comprador", nullable = false, length = 300)
    private String razonSocialComprador;
    @Basic(optional = false)
    @Column(name = "identificacion_comprador", nullable = false, length = 13)
    private String identificacionComprador;
    @Basic(optional = false)
    @Column(name = "total_sin_impuestos", nullable = false)
    private float totalSinImpuestos;
    @Basic(optional = false)
    @Column(name = "total_descuentos", nullable = false)
    private float totalDescuentos;
    @Basic(optional = false)
    @Column(name = "propina", nullable = false)
    private float propina;
    @Basic(optional = false)
    @Column(name = "importe_total", nullable = false)
    private float importeTotal;
    @Column(name = "moneda", length = 15)
    private String moneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccfacturaFk")
    private List<Tcabecerafacturaimpuesto> tcabecerafacturaimpuestoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccfacturaFk")
    private List<Tretencionfactura> tretencionfacturaList;
    @JoinColumns({
        @JoinColumn(name = "ccontribuyente_fk2", referencedColumnName = "ccontribuyente_fk", nullable = false),
        @JoinColumn(name = "idpeticion_fk", referencedColumnName = "idpeticion", nullable = false)})
    @ManyToOne(optional = false)
    private Tcomprobante tcomprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccfacturaFk")
    private List<Tdetallefactura> tdetallefacturaList;

    public Tcabecerafactura() {
    }

    public Tcabecerafactura(Integer ccfactura) {
        this.ccfactura = ccfactura;
    }

    public Tcabecerafactura(Integer ccfactura, Date fechaEmision, String tipoIdComprador, String razonSocialComprador, String identificacionComprador, float totalSinImpuestos, float totalDescuentos, float propina, float importeTotal) {
        this.ccfactura = ccfactura;
        this.fechaEmision = fechaEmision;
        this.tipoIdComprador = tipoIdComprador;
        this.razonSocialComprador = razonSocialComprador;
        this.identificacionComprador = identificacionComprador;
        this.totalSinImpuestos = totalSinImpuestos;
        this.totalDescuentos = totalDescuentos;
        this.propina = propina;
        this.importeTotal = importeTotal;
    }

    public Integer getCcfactura() {
        return ccfactura;
    }

    public void setCcfactura(Integer ccfactura) {
        this.ccfactura = ccfactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDireccionEstablecimiento() {
        return direccionEstablecimiento;
    }

    public void setDireccionEstablecimiento(String direccionEstablecimiento) {
        this.direccionEstablecimiento = direccionEstablecimiento;
    }

    public String getContribuyenteEspecial() {
        return contribuyenteEspecial;
    }

    public void setContribuyenteEspecial(String contribuyenteEspecial) {
        this.contribuyenteEspecial = contribuyenteEspecial;
    }

    public String getObligadoContabilidad() {
        return obligadoContabilidad;
    }

    public void setObligadoContabilidad(String obligadoContabilidad) {
        this.obligadoContabilidad = obligadoContabilidad;
    }

    public String getTipoIdComprador() {
        return tipoIdComprador;
    }

    public void setTipoIdComprador(String tipoIdComprador) {
        this.tipoIdComprador = tipoIdComprador;
    }

    public String getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public String getRazonSocialComprador() {
        return razonSocialComprador;
    }

    public void setRazonSocialComprador(String razonSocialComprador) {
        this.razonSocialComprador = razonSocialComprador;
    }

    public String getIdentificacionComprador() {
        return identificacionComprador;
    }

    public void setIdentificacionComprador(String identificacionComprador) {
        this.identificacionComprador = identificacionComprador;
    }

    public float getTotalSinImpuestos() {
        return totalSinImpuestos;
    }

    public void setTotalSinImpuestos(float totalSinImpuestos) {
        this.totalSinImpuestos = totalSinImpuestos;
    }

    public float getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(float totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public float getPropina() {
        return propina;
    }

    public void setPropina(float propina) {
        this.propina = propina;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @XmlTransient
    public List<Tcabecerafacturaimpuesto> getTcabecerafacturaimpuestoList() {
        return tcabecerafacturaimpuestoList;
    }

    public void setTcabecerafacturaimpuestoList(List<Tcabecerafacturaimpuesto> tcabecerafacturaimpuestoList) {
        this.tcabecerafacturaimpuestoList = tcabecerafacturaimpuestoList;
    }

    @XmlTransient
    public List<Tretencionfactura> getTretencionfacturaList() {
        return tretencionfacturaList;
    }

    public void setTretencionfacturaList(List<Tretencionfactura> tretencionfacturaList) {
        this.tretencionfacturaList = tretencionfacturaList;
    }

    public Tcomprobante getTcomprobante() {
        return tcomprobante;
    }

    public void setTcomprobante(Tcomprobante tcomprobante) {
        this.tcomprobante = tcomprobante;
    }

    @XmlTransient
    public List<Tdetallefactura> getTdetallefacturaList() {
        return tdetallefacturaList;
    }

    public void setTdetallefacturaList(List<Tdetallefactura> tdetallefacturaList) {
        this.tdetallefacturaList = tdetallefacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccfactura != null ? ccfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcabecerafactura)) {
            return false;
        }
        Tcabecerafactura other = (Tcabecerafactura) object;
        if ((this.ccfactura == null && other.ccfactura != null) || (this.ccfactura != null && !this.ccfactura.equals(other.ccfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcabecerafactura[ ccfactura=" + ccfactura + " ]";
    }
    
}
