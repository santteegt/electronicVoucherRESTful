/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buzz
 */
@Entity
@Table(name= "TDETALLENOTADEBITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetallenotadebito.findAll", query = "SELECT t FROM Tdetallenotadebito t"),
    @NamedQuery(name = "Tdetallenotadebito.findByCdndebito", query = "SELECT t FROM Tdetallenotadebito t WHERE t.cdndebito = :cdndebito"),
    @NamedQuery(name = "Tdetallenotadebito.findByRazon", query = "SELECT t FROM Tdetallenotadebito t WHERE t.razon = :razon"),
    @NamedQuery(name = "Tdetallenotadebito.findByValor", query = "SELECT t FROM Tdetallenotadebito t WHERE t.valor = :valor")})
public class Tdetallenotadebito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer cdndebito;
    @Basic(optional = false)
    @Column(nullable = false, length = 300)
    private String razon;
    @Basic(optional = false)
    @Column(nullable = false)
    private float valor;
    @JoinColumn(name = "ccnotacd_fk", referencedColumnName = "ccnotacd", nullable = false)
    @ManyToOne(optional = false)
    private Tcabeceranotacreditodebito ccnotacdFk;

    public Tdetallenotadebito() {
    }

    public Tdetallenotadebito(Integer cdndebito) {
        this.cdndebito = cdndebito;
    }

    public Tdetallenotadebito(Integer cdndebito, String razon, float valor) {
        this.cdndebito = cdndebito;
        this.razon = razon;
        this.valor = valor;
    }

    public Integer getCdndebito() {
        return cdndebito;
    }

    public void setCdndebito(Integer cdndebito) {
        this.cdndebito = cdndebito;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Tcabeceranotacreditodebito getCcnotacdFk() {
        return ccnotacdFk;
    }

    public void setCcnotacdFk(Tcabeceranotacreditodebito ccnotacdFk) {
        this.ccnotacdFk = ccnotacdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdndebito != null ? cdndebito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetallenotadebito)) {
            return false;
        }
        Tdetallenotadebito other = (Tdetallenotadebito) object;
        if ((this.cdndebito == null && other.cdndebito != null) || (this.cdndebito != null && !this.cdndebito.equals(other.cdndebito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetallenotadebito[ cdndebito=" + cdndebito + " ]";
    }
    
}
