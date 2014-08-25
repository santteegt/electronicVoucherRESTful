/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buzz
 */
@Entity
@Table(name= "TDETALLERETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetalleretencion.findAll", query = "SELECT t FROM Tdetalleretencion t"),
    @NamedQuery(name = "Tdetalleretencion.findByCdretencion", query = "SELECT t FROM Tdetalleretencion t WHERE t.cdretencion = :cdretencion"),
    @NamedQuery(name = "Tdetalleretencion.findByCodigoRetencion", query = "SELECT t FROM Tdetalleretencion t WHERE t.codigoRetencion = :codigoRetencion"),
    @NamedQuery(name = "Tdetalleretencion.findByBaseImponible", query = "SELECT t FROM Tdetalleretencion t WHERE t.baseImponible = :baseImponible"),
    @NamedQuery(name = "Tdetalleretencion.findByPorcentajeRetener", query = "SELECT t FROM Tdetalleretencion t WHERE t.porcentajeRetener = :porcentajeRetener"),
    @NamedQuery(name = "Tdetalleretencion.findByValorRetenido", query = "SELECT t FROM Tdetalleretencion t WHERE t.valorRetenido = :valorRetenido"),
    @NamedQuery(name = "Tdetalleretencion.findByCodDocSustento", query = "SELECT t FROM Tdetalleretencion t WHERE t.codDocSustento = :codDocSustento"),
    @NamedQuery(name = "Tdetalleretencion.findByNumDocSustento", query = "SELECT t FROM Tdetalleretencion t WHERE t.numDocSustento = :numDocSustento"),
    @NamedQuery(name = "Tdetalleretencion.findByFechaEmisionDocSustento", query = "SELECT t FROM Tdetalleretencion t WHERE t.fechaEmisionDocSustento = :fechaEmisionDocSustento")})
public class Tdetalleretencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer cdretencion;
    @Basic(optional = false)
    @Column(name = "codigo_retencion", nullable = false, length = 5)
    private String codigoRetencion;
    @Basic(optional = false)
    @Column(name = "base_imponible", nullable = false)
    private float baseImponible;
    @Basic(optional = false)
    @Column(name = "porcentaje_retener", nullable = false)
    private float porcentajeRetener;
    @Basic(optional = false)
    @Column(name = "valor_retenido", nullable = false)
    private float valorRetenido;
    @Basic(optional = false)
    @Column(name = "cod_doc_sustento", nullable = false, length = 2)
    private String codDocSustento;
    @Column(name = "num_doc_sustento", length = 17)
    private String numDocSustento;
    @Column(name = "fecha_emision_doc_sustento")
    @Temporal(TemporalType.DATE)
    private Date fechaEmisionDocSustento;
    @JoinColumn(name = "ccretencion_fk", referencedColumnName = "ccretencion", nullable = false)
    @ManyToOne(optional = false)
    private Tcabeceraretencion ccretencionFk;

    public Tdetalleretencion() {
    }

    public Tdetalleretencion(Integer cdretencion) {
        this.cdretencion = cdretencion;
    }

    public Tdetalleretencion(Integer cdretencion, String codigoRetencion, float baseImponible, float porcentajeRetener, float valorRetenido, String codDocSustento) {
        this.cdretencion = cdretencion;
        this.codigoRetencion = codigoRetencion;
        this.baseImponible = baseImponible;
        this.porcentajeRetener = porcentajeRetener;
        this.valorRetenido = valorRetenido;
        this.codDocSustento = codDocSustento;
    }

    public Integer getCdretencion() {
        return cdretencion;
    }

    public void setCdretencion(Integer cdretencion) {
        this.cdretencion = cdretencion;
    }

    public String getCodigoRetencion() {
        return codigoRetencion;
    }

    public void setCodigoRetencion(String codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    public float getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(float baseImponible) {
        this.baseImponible = baseImponible;
    }

    public float getPorcentajeRetener() {
        return porcentajeRetener;
    }

    public void setPorcentajeRetener(float porcentajeRetener) {
        this.porcentajeRetener = porcentajeRetener;
    }

    public float getValorRetenido() {
        return valorRetenido;
    }

    public void setValorRetenido(float valorRetenido) {
        this.valorRetenido = valorRetenido;
    }

    public String getCodDocSustento() {
        return codDocSustento;
    }

    public void setCodDocSustento(String codDocSustento) {
        this.codDocSustento = codDocSustento;
    }

    public String getNumDocSustento() {
        return numDocSustento;
    }

    public void setNumDocSustento(String numDocSustento) {
        this.numDocSustento = numDocSustento;
    }

    public Date getFechaEmisionDocSustento() {
        return fechaEmisionDocSustento;
    }

    public void setFechaEmisionDocSustento(Date fechaEmisionDocSustento) {
        this.fechaEmisionDocSustento = fechaEmisionDocSustento;
    }

    public Tcabeceraretencion getCcretencionFk() {
        return ccretencionFk;
    }

    public void setCcretencionFk(Tcabeceraretencion ccretencionFk) {
        this.ccretencionFk = ccretencionFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdretencion != null ? cdretencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetalleretencion)) {
            return false;
        }
        Tdetalleretencion other = (Tdetalleretencion) object;
        if ((this.cdretencion == null && other.cdretencion != null) || (this.cdretencion != null && !this.cdretencion.equals(other.cdretencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetalleretencion[ cdretencion=" + cdretencion + " ]";
    }
    
}
