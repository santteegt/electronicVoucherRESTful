/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author buzz
 */
@Entity
@Table(name = "TCABECERARETENCION", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcabeceraretencion.findAll", query = "SELECT t FROM Tcabeceraretencion t"),
    @NamedQuery(name = "Tcabeceraretencion.findByCcretencion", query = "SELECT t FROM Tcabeceraretencion t WHERE t.ccretencion = :ccretencion"),
    @NamedQuery(name = "Tcabeceraretencion.findByFechaEmision", query = "SELECT t FROM Tcabeceraretencion t WHERE t.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Tcabeceraretencion.findByDireccionEstablecimiento", query = "SELECT t FROM Tcabeceraretencion t WHERE t.direccionEstablecimiento = :direccionEstablecimiento"),
    @NamedQuery(name = "Tcabeceraretencion.findByContribuyenteEspecial", query = "SELECT t FROM Tcabeceraretencion t WHERE t.contribuyenteEspecial = :contribuyenteEspecial"),
    @NamedQuery(name = "Tcabeceraretencion.findByObligadoContabilidad", query = "SELECT t FROM Tcabeceraretencion t WHERE t.obligadoContabilidad = :obligadoContabilidad"),
    @NamedQuery(name = "Tcabeceraretencion.findByTipoIdSujetoretenido", query = "SELECT t FROM Tcabeceraretencion t WHERE t.tipoIdSujetoretenido = :tipoIdSujetoretenido"),
    @NamedQuery(name = "Tcabeceraretencion.findByRazonSocialSujetoretenido", query = "SELECT t FROM Tcabeceraretencion t WHERE t.razonSocialSujetoretenido = :razonSocialSujetoretenido"),
    @NamedQuery(name = "Tcabeceraretencion.findByIdentificacionSujetoretenido", query = "SELECT t FROM Tcabeceraretencion t WHERE t.identificacionSujetoretenido = :identificacionSujetoretenido"),
    @NamedQuery(name = "Tcabeceraretencion.findByPeriodoFiscal", query = "SELECT t FROM Tcabeceraretencion t WHERE t.periodoFiscal = :periodoFiscal")})
public class Tcabeceraretencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ccretencion", nullable = false)
    private Integer ccretencion;
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
    @Column(name = "tipo_id_sujetoretenido", nullable = false, length = 2)
    private String tipoIdSujetoretenido;
    @Basic(optional = false)
    @Column(name = "razon_social_sujetoretenido", nullable = false, length = 300)
    private String razonSocialSujetoretenido;
    @Basic(optional = false)
    @Column(name = "identificacion_sujetoretenido", nullable = false, length = 13)
    private String identificacionSujetoretenido;
    @Basic(optional = false)
    @Column(name = "periodo_fiscal", nullable = false, length = 7)
    private String periodoFiscal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccretencionFk")
    private List<Tdetalleretencion> tdetalleretencionList;
    @JoinColumns({
        @JoinColumn(name = "ccontribuyente_fk2", referencedColumnName = "ccontribuyente_fk", nullable = false),
        @JoinColumn(name = "idpeticion_fk", referencedColumnName = "idpeticion", nullable = false)})
    @ManyToOne(optional = false)
    private Tcomprobante tcomprobante;

    public Tcabeceraretencion() {
    }

    public Tcabeceraretencion(Integer ccretencion) {
        this.ccretencion = ccretencion;
    }

    public Tcabeceraretencion(Integer ccretencion, Date fechaEmision, String tipoIdSujetoretenido, String razonSocialSujetoretenido, String identificacionSujetoretenido, String periodoFiscal) {
        this.ccretencion = ccretencion;
        this.fechaEmision = fechaEmision;
        this.tipoIdSujetoretenido = tipoIdSujetoretenido;
        this.razonSocialSujetoretenido = razonSocialSujetoretenido;
        this.identificacionSujetoretenido = identificacionSujetoretenido;
        this.periodoFiscal = periodoFiscal;
    }

    public Integer getCcretencion() {
        return ccretencion;
    }

    public void setCcretencion(Integer ccretencion) {
        this.ccretencion = ccretencion;
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

    public String getTipoIdSujetoretenido() {
        return tipoIdSujetoretenido;
    }

    public void setTipoIdSujetoretenido(String tipoIdSujetoretenido) {
        this.tipoIdSujetoretenido = tipoIdSujetoretenido;
    }

    public String getRazonSocialSujetoretenido() {
        return razonSocialSujetoretenido;
    }

    public void setRazonSocialSujetoretenido(String razonSocialSujetoretenido) {
        this.razonSocialSujetoretenido = razonSocialSujetoretenido;
    }

    public String getIdentificacionSujetoretenido() {
        return identificacionSujetoretenido;
    }

    public void setIdentificacionSujetoretenido(String identificacionSujetoretenido) {
        this.identificacionSujetoretenido = identificacionSujetoretenido;
    }

    public String getPeriodoFiscal() {
        return periodoFiscal;
    }

    public void setPeriodoFiscal(String periodoFiscal) {
        this.periodoFiscal = periodoFiscal;
    }

    @XmlTransient
    public List<Tdetalleretencion> getTdetalleretencionList() {
        return tdetalleretencionList;
    }

    public void setTdetalleretencionList(List<Tdetalleretencion> tdetalleretencionList) {
        this.tdetalleretencionList = tdetalleretencionList;
    }

    public Tcomprobante getTcomprobante() {
        return tcomprobante;
    }

    public void setTcomprobante(Tcomprobante tcomprobante) {
        this.tcomprobante = tcomprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccretencion != null ? ccretencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcabeceraretencion)) {
            return false;
        }
        Tcabeceraretencion other = (Tcabeceraretencion) object;
        if ((this.ccretencion == null && other.ccretencion != null) || (this.ccretencion != null && !this.ccretencion.equals(other.ccretencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcabeceraretencion[ ccretencion=" + ccretencion + " ]";
    }
    
}
