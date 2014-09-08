/*
 * To change this template, choose Tools | Templates
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
 * @author karina
 */
@Entity
@Table(name = "TDETALLEADICIONALDETALLENOTACREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetalleadicionaldetallenotacredito.findAll", query = "SELECT t FROM Tdetalleadicionaldetallenotacredito t"),
    @NamedQuery(name = "Tdetalleadicionaldetallenotacredito.findByCdancredito", query = "SELECT t FROM Tdetalleadicionaldetallenotacredito t WHERE t.cdancredito = :cdancredito"),
    @NamedQuery(name = "Tdetalleadicionaldetallenotacredito.findByNombre", query = "SELECT t FROM Tdetalleadicionaldetallenotacredito t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tdetalleadicionaldetallenotacredito.findByValor", query = "SELECT t FROM Tdetalleadicionaldetallenotacredito t WHERE t.valor = :valor")})
public class Tdetalleadicionaldetallenotacredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdancredito", nullable = false)
    private Integer cdancredito;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 300)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false, length = 300)
    private String valor;
    @JoinColumn(name = "cdncredito_fk", referencedColumnName = "cdncredito", nullable = false)
    @ManyToOne(optional = false)
    private Tdetallenotacredito cdncreditoFk;

    public Tdetalleadicionaldetallenotacredito() {
    }

    public Tdetalleadicionaldetallenotacredito(Integer cdancredito) {
        this.cdancredito = cdancredito;
    }

    public Tdetalleadicionaldetallenotacredito(Integer cdancredito, String nombre, String valor) {
        this.cdancredito = cdancredito;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Integer getCdancredito() {
        return cdancredito;
    }

    public void setCdancredito(Integer cdancredito) {
        this.cdancredito = cdancredito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Tdetallenotacredito getCdncreditoFk() {
        return cdncreditoFk;
    }

    public void setCdncreditoFk(Tdetallenotacredito cdncreditoFk) {
        this.cdncreditoFk = cdncreditoFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdancredito != null ? cdancredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetalleadicionaldetallenotacredito)) {
            return false;
        }
        Tdetalleadicionaldetallenotacredito other = (Tdetalleadicionaldetallenotacredito) object;
        if ((this.cdancredito == null && other.cdancredito != null) || (this.cdancredito != null && !this.cdancredito.equals(other.cdancredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetalleadicionaldetallenotacredito[ cdancredito=" + cdancredito + " ]";
    }
    
}
