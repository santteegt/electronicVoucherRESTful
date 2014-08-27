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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buzz
 */
@Entity
@Table(name = "TTIPOCONTRIBUYENTE", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ttipocontribuyente.findAll", query = "SELECT t FROM Ttipocontribuyente t"),
    @NamedQuery(name = "Ttipocontribuyente.findByCtipocontribuyente", query = "SELECT t FROM Ttipocontribuyente t WHERE t.ctipocontribuyente = :ctipocontribuyente"),
    @NamedQuery(name = "Ttipocontribuyente.findByDescripcion", query = "SELECT t FROM Ttipocontribuyente t WHERE t.descripcion = :descripcion")})
public class Ttipocontribuyente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ctipocontribuyente", nullable = false)
    private Integer ctipocontribuyente;
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ctipocontribuyenteFk", fetch = FetchType.LAZY )
    private List<Tcontribuyente> tcontribuyenteList;
    public Ttipocontribuyente() {
    }

    public Ttipocontribuyente(Integer ctipocontribuyente) {
        this.ctipocontribuyente = ctipocontribuyente;
    }

    public Integer getCtipocontribuyente() {
        return ctipocontribuyente;
    }

    public void setCtipocontribuyente(Integer ctipocontribuyente) {
        this.ctipocontribuyente = ctipocontribuyente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @XmlTransient
    public List<Tcontribuyente> getTcontribuyenteList() {
        return tcontribuyenteList;
    }

    public void setTcontribuyenteList(List<Tcontribuyente> tcontribuyenteList) {
        this.tcontribuyenteList = tcontribuyenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctipocontribuyente != null ? ctipocontribuyente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ttipocontribuyente)) {
            return false;
        }
        Ttipocontribuyente other = (Ttipocontribuyente) object;
        if ((this.ctipocontribuyente == null && other.ctipocontribuyente != null) || (this.ctipocontribuyente != null && !this.ctipocontribuyente.equals(other.ctipocontribuyente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Ttipocontribuyente[ ctipocontribuyente=" + ctipocontribuyente + " ]";
    }
    
}
