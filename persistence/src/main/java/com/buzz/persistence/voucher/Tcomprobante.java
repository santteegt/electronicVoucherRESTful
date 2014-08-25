/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buzz
 */
@Entity
@Table(name="TCOMPROBANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcomprobante.findAll", query = "SELECT t FROM Tcomprobante t"),
    @NamedQuery(name = "Tcomprobante.findByCcontribuyenteFk", query = "SELECT t FROM Tcomprobante t WHERE t.tcomprobantePK.ccontribuyenteFk = :ccontribuyenteFk"),
    @NamedQuery(name = "Tcomprobante.findByIdpeticion", query = "SELECT t FROM Tcomprobante t WHERE t.tcomprobantePK.idpeticion = :idpeticion"),
    @NamedQuery(name = "Tcomprobante.findByAmbiente", query = "SELECT t FROM Tcomprobante t WHERE t.ambiente = :ambiente"),
    @NamedQuery(name = "Tcomprobante.findByTipoEmision", query = "SELECT t FROM Tcomprobante t WHERE t.tipoEmision = :tipoEmision"),
    @NamedQuery(name = "Tcomprobante.findByRazonSocial", query = "SELECT t FROM Tcomprobante t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "Tcomprobante.findByNombreComercial", query = "SELECT t FROM Tcomprobante t WHERE t.nombreComercial = :nombreComercial"),
    @NamedQuery(name = "Tcomprobante.findByRuc", query = "SELECT t FROM Tcomprobante t WHERE t.ruc = :ruc"),
    @NamedQuery(name = "Tcomprobante.findByClaveAcceso", query = "SELECT t FROM Tcomprobante t WHERE t.claveAcceso = :claveAcceso"),
    @NamedQuery(name = "Tcomprobante.findByCodigoDocumento", query = "SELECT t FROM Tcomprobante t WHERE t.codigoDocumento = :codigoDocumento"),
    @NamedQuery(name = "Tcomprobante.findByEstablecimiento", query = "SELECT t FROM Tcomprobante t WHERE t.establecimiento = :establecimiento"),
    @NamedQuery(name = "Tcomprobante.findByPuntoEmision", query = "SELECT t FROM Tcomprobante t WHERE t.puntoEmision = :puntoEmision"),
    @NamedQuery(name = "Tcomprobante.findBySecuencial", query = "SELECT t FROM Tcomprobante t WHERE t.secuencial = :secuencial"),
    @NamedQuery(name = "Tcomprobante.findByDireccionMatriz", query = "SELECT t FROM Tcomprobante t WHERE t.direccionMatriz = :direccionMatriz")})
public class Tcomprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcomprobantePK tcomprobantePK;
    @Basic(optional = false)
    @Column(nullable = false, length = 1)
    private String ambiente;
    @Basic(optional = false)
    @Column(name = "tipo_emision", nullable = false, length = 1)
    private String tipoEmision;
    @Basic(optional = false)
    @Column(name = "razon_social", nullable = false, length = 300)
    private String razonSocial;
    @Column(name = "nombre_comercial", length = 300)
    private String nombreComercial;
    @Basic(optional = false)
    @Column(nullable = false, length = 13)
    private String ruc;
    @Basic(optional = false)
    @Column(name = "clave_acceso", nullable = false, length = 49)
    private String claveAcceso;
    @Basic(optional = false)
    @Column(name = "codigo_documento", nullable = false, length = 2)
    private String codigoDocumento;
    @Basic(optional = false)
    @Column(nullable = false, length = 3)
    private String establecimiento;
    @Basic(optional = false)
    @Column(name = "punto_emision", nullable = false, length = 3)
    private String puntoEmision;
    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String secuencial;
    @Basic(optional = false)
    @Column(name = "direccion_matriz", nullable = false, length = 300)
    private String direccionMatriz;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcomprobante")
    private List<Tcabeceranotacreditodebito> tcabeceranotacreditodebitoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcomprobante")
    private List<Tinformacionadicionalcomprobante> tinformacionadicionalcomprobanteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcomprobante")
    private List<Tcabeceraguiaremision> tcabeceraguiaremisionList;
    @JoinColumns({
     @JoinColumn(name = "ccontribuyente_fk", referencedColumnName = "ccontribuyente", nullable = false, insertable = false, updatable = false),
     @JoinColumn(name = "fhasta", referencedColumnName = "fhasta", nullable = false, insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private Tcontribuyente tcontribuyente;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tcomprobante")
    private Tsesioncomprobante tsesioncomprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcomprobante")
    private List<Tcabecerafactura> tcabecerafacturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcomprobante")
    private List<Tcabeceraretencion> tcabeceraretencionList;

    public Tcomprobante() {
    }

    public Tcomprobante(TcomprobantePK tcomprobantePK) {
        this.tcomprobantePK = tcomprobantePK;
    }

    public Tcomprobante(TcomprobantePK tcomprobantePK, String ambiente, String tipoEmision, String razonSocial, String ruc, String claveAcceso, String codigoDocumento, String establecimiento, String puntoEmision, String secuencial, String direccionMatriz) {
        this.tcomprobantePK = tcomprobantePK;
        this.ambiente = ambiente;
        this.tipoEmision = tipoEmision;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.claveAcceso = claveAcceso;
        this.codigoDocumento = codigoDocumento;
        this.establecimiento = establecimiento;
        this.puntoEmision = puntoEmision;
        this.secuencial = secuencial;
        this.direccionMatriz = direccionMatriz;
    }

    public Tcomprobante(int ccontribuyenteFk, String idpeticion) {
        this.tcomprobantePK = new TcomprobantePK(ccontribuyenteFk, idpeticion);
    }

    public TcomprobantePK getTcomprobantePK() {
        return tcomprobantePK;
    }

    public void setTcomprobantePK(TcomprobantePK tcomprobantePK) {
        this.tcomprobantePK = tcomprobantePK;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getTipoEmision() {
        return tipoEmision;
    }

    public void setTipoEmision(String tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getPuntoEmision() {
        return puntoEmision;
    }

    public void setPuntoEmision(String puntoEmision) {
        this.puntoEmision = puntoEmision;
    }

    public String getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    public String getDireccionMatriz() {
        return direccionMatriz;
    }

    public void setDireccionMatriz(String direccionMatriz) {
        this.direccionMatriz = direccionMatriz;
    }

    @XmlTransient
    public List<Tcabeceranotacreditodebito> getTcabeceranotacreditodebitoList() {
        return tcabeceranotacreditodebitoList;
    }

    public void setTcabeceranotacreditodebitoList(List<Tcabeceranotacreditodebito> tcabeceranotacreditodebitoList) {
        this.tcabeceranotacreditodebitoList = tcabeceranotacreditodebitoList;
    }

    @XmlTransient
    public List<Tinformacionadicionalcomprobante> getTinformacionadicionalcomprobanteList() {
        return tinformacionadicionalcomprobanteList;
    }

    public void setTinformacionadicionalcomprobanteList(List<Tinformacionadicionalcomprobante> tinformacionadicionalcomprobanteList) {
        this.tinformacionadicionalcomprobanteList = tinformacionadicionalcomprobanteList;
    }

    @XmlTransient
    public List<Tcabeceraguiaremision> getTcabeceraguiaremisionList() {
        return tcabeceraguiaremisionList;
    }

    public void setTcabeceraguiaremisionList(List<Tcabeceraguiaremision> tcabeceraguiaremisionList) {
        this.tcabeceraguiaremisionList = tcabeceraguiaremisionList;
    }

    public Tcontribuyente getTcontribuyente() {
        return tcontribuyente;
    }

    public void setTcontribuyente(Tcontribuyente tcontribuyente) {
        this.tcontribuyente = tcontribuyente;
    }

    public Tsesioncomprobante getTsesioncomprobante() {
        return tsesioncomprobante;
    }

    public void setTsesioncomprobante(Tsesioncomprobante tsesioncomprobante) {
        this.tsesioncomprobante = tsesioncomprobante;
    }

    @XmlTransient
    public List<Tcabecerafactura> getTcabecerafacturaList() {
        return tcabecerafacturaList;
    }

    public void setTcabecerafacturaList(List<Tcabecerafactura> tcabecerafacturaList) {
        this.tcabecerafacturaList = tcabecerafacturaList;
    }

    @XmlTransient
    public List<Tcabeceraretencion> getTcabeceraretencionList() {
        return tcabeceraretencionList;
    }

    public void setTcabeceraretencionList(List<Tcabeceraretencion> tcabeceraretencionList) {
        this.tcabeceraretencionList = tcabeceraretencionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcomprobantePK != null ? tcomprobantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcomprobante)) {
            return false;
        }
        Tcomprobante other = (Tcomprobante) object;
        if ((this.tcomprobantePK == null && other.tcomprobantePK != null) || (this.tcomprobantePK != null && !this.tcomprobantePK.equals(other.tcomprobantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcomprobante[ tcomprobantePK=" + tcomprobantePK + " ]";
    }
    
}
