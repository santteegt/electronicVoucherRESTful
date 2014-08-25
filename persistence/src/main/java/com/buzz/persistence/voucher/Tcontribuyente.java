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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buzz
 */
@Entity
@Table(name="TCONTRIBUYENTE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"identificacion"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcontribuyente.findAll", query = "SELECT t FROM Tcontribuyente t"),
    @NamedQuery(name = "Tcontribuyente.findByCcontribuyente", query = "SELECT t FROM Tcontribuyente t WHERE t.tcontribuyentePK.ccontribuyente = :ccontribuyente"),
    @NamedQuery(name = "Tcontribuyente.findByFhasta", query = "SELECT t FROM Tcontribuyente t WHERE t.tcontribuyentePK.fhasta = :fhasta"),
    @NamedQuery(name = "Tcontribuyente.findByFdesde", query = "SELECT t FROM Tcontribuyente t WHERE t.fdesde = :fdesde"),
    @NamedQuery(name = "Tcontribuyente.findByIdentificacion", query = "SELECT t FROM Tcontribuyente t WHERE t.identificacion = :identificacion"),
    @NamedQuery(name = "Tcontribuyente.findByRazonSocial", query = "SELECT t FROM Tcontribuyente t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "Tcontribuyente.findByNombreComercial", query = "SELECT t FROM Tcontribuyente t WHERE t.nombreComercial = :nombreComercial")})
public class Tcontribuyente implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcontribuyentePK tcontribuyentePK;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdesde;
    @Basic(optional = false)
    @Column(nullable = false, length = 13)
    private String identificacion;
    @Basic(optional = false)
    @Column(name = "razon_social", nullable = false, length = 300)
    private String razonSocial;
    @Column(name = "nombre_comercial", length = 300)
    private String nombreComercial;
    @Lob
    private byte[] logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccontribuyenteFk")
    private List<Tusuarioid> tusuarioidList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcontribuyente")
    private List<Tcomprobante> tcomprobanteList;
    @JoinColumn(name = "ctipocontribuyente_fk", referencedColumnName = "ctipocontribuyente", nullable = false)
    @ManyToOne(optional = false)
    private Ttipocontribuyente ctipocontribuyenteFk;

    public Tcontribuyente() {
    }

    public Tcontribuyente(TcontribuyentePK tcontribuyentePK) {
        this.tcontribuyentePK = tcontribuyentePK;
    }

    public Tcontribuyente(TcontribuyentePK tcontribuyentePK, Date fdesde, String identificacion, String razonSocial) {
        this.tcontribuyentePK = tcontribuyentePK;
        this.fdesde = fdesde;
        this.identificacion = identificacion;
        this.razonSocial = razonSocial;
    }

    public Tcontribuyente(int ccontribuyente, Date fhasta) {
        this.tcontribuyentePK = new TcontribuyentePK(ccontribuyente, fhasta);
    }

    public TcontribuyentePK getTcontribuyentePK() {
        return tcontribuyentePK;
    }

    public void setTcontribuyentePK(TcontribuyentePK tcontribuyentePK) {
        this.tcontribuyentePK = tcontribuyentePK;
    }

    public Date getFdesde() {
        return fdesde;
    }

    public void setFdesde(Date fdesde) {
        this.fdesde = fdesde;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @XmlTransient
    public List<Tusuarioid> getTusuarioidList() {
        return tusuarioidList;
    }

    public void setTusuarioidList(List<Tusuarioid> tusuarioidList) {
        this.tusuarioidList = tusuarioidList;
    }

    @XmlTransient
    public List<Tcomprobante> getTcomprobanteList() {
        return tcomprobanteList;
    }

    public void setTcomprobanteList(List<Tcomprobante> tcomprobanteList) {
        this.tcomprobanteList = tcomprobanteList;
    }

    public Ttipocontribuyente getCtipocontribuyenteFk() {
        return ctipocontribuyenteFk;
    }

    public void setCtipocontribuyenteFk(Ttipocontribuyente ctipocontribuyenteFk) {
        this.ctipocontribuyenteFk = ctipocontribuyenteFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcontribuyentePK != null ? tcontribuyentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcontribuyente)) {
            return false;
        }
        Tcontribuyente other = (Tcontribuyente) object;
        if ((this.tcontribuyentePK == null && other.tcontribuyentePK != null) || (this.tcontribuyentePK != null && !this.tcontribuyentePK.equals(other.tcontribuyentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcontribuyente[ tcontribuyentePK=" + tcontribuyentePK + " ]";
    }
    
}
