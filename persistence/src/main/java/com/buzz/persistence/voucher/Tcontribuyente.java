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
 * @author karina
 */
@Entity
@Table(name = "TCONTRIBUYENTE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"identificacion"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcontribuyente.findAll", query = "SELECT t FROM Tcontribuyente t"),
    @NamedQuery(name = "Tcontribuyente.findByCcontribuyente", query = "SELECT t FROM Tcontribuyente t WHERE t.ccontribuyente = :ccontribuyente"),
    @NamedQuery(name = "Tcontribuyente.findByFhasta", query = "SELECT t FROM Tcontribuyente t WHERE t.fhasta = :fhasta"),
    @NamedQuery(name = "Tcontribuyente.findByFdesde", query = "SELECT t FROM Tcontribuyente t WHERE t.fdesde = :fdesde"),
    @NamedQuery(name = "Tcontribuyente.findByIdentificacion", query = "SELECT t FROM Tcontribuyente t WHERE t.identificacion = :identificacion"),
    @NamedQuery(name = "Tcontribuyente.findByRazonSocial", query = "SELECT t FROM Tcontribuyente t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "Tcontribuyente.findByNombreComercial", query = "SELECT t FROM Tcontribuyente t WHERE t.nombreComercial = :nombreComercial")})
public class Tcontribuyente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ccontribuyente", nullable = false)
    private Integer ccontribuyente;
    @Basic(optional = false)
    @Column(name = "fhasta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhasta;
    @Basic(optional = false)
    @Column(name = "fdesde", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdesde;
    @Basic(optional = false)
    @Column(name = "identificacion", nullable = false, length = 13)
    private String identificacion;
    @Basic(optional = false)
    @Column(name = "razon_social", nullable = false, length = 300)
    private String razonSocial;
    @Column(name = "nombre_comercial", length = 300)
    private String nombreComercial;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @JoinColumn(name = "ctipocontribuyente_fk", referencedColumnName = "ctipocontribuyente", nullable = false)
    @ManyToOne(optional = false)
    private Ttipocontribuyente ctipocontribuyenteFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcontribuyente")
    private List<Tcomprobante> tcomprobanteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccontribuyenteFk")
    private List<Tusuarioid> tusuarioidList;

    public Tcontribuyente() {
    }

    public Tcontribuyente(Integer ccontribuyente) {
        this.ccontribuyente = ccontribuyente;
    }

    public Tcontribuyente(Integer ccontribuyente, Date fhasta, Date fdesde, String identificacion, String razonSocial) {
        this.ccontribuyente = ccontribuyente;
        this.fhasta = fhasta;
        this.fdesde = fdesde;
        this.identificacion = identificacion;
        this.razonSocial = razonSocial;
    }

    public Integer getCcontribuyente() {
        return ccontribuyente;
    }

    public void setCcontribuyente(Integer ccontribuyente) {
        this.ccontribuyente = ccontribuyente;
    }

    public Date getFhasta() {
        return fhasta;
    }

    public void setFhasta(Date fhasta) {
        this.fhasta = fhasta;
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

    public Ttipocontribuyente getCtipocontribuyenteFk() {
        return ctipocontribuyenteFk;
    }

    public void setCtipocontribuyenteFk(Ttipocontribuyente ctipocontribuyenteFk) {
        this.ctipocontribuyenteFk = ctipocontribuyenteFk;
    }

    @XmlTransient
    public List<Tcomprobante> getTcomprobanteList() {
        return tcomprobanteList;
    }

    public void setTcomprobanteList(List<Tcomprobante> tcomprobanteList) {
        this.tcomprobanteList = tcomprobanteList;
    }

    @XmlTransient
    public List<Tusuarioid> getTusuarioidList() {
        return tusuarioidList;
    }

    public void setTusuarioidList(List<Tusuarioid> tusuarioidList) {
        this.tusuarioidList = tusuarioidList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccontribuyente != null ? ccontribuyente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcontribuyente)) {
            return false;
        }
        Tcontribuyente other = (Tcontribuyente) object;
        if ((this.ccontribuyente == null && other.ccontribuyente != null) || (this.ccontribuyente != null && !this.ccontribuyente.equals(other.ccontribuyente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcontribuyente[ ccontribuyente=" + ccontribuyente + " ]";
    }
    
}
