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
@Table(name = "TDETALLEADICIONALDETALLEFACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetalleadicionaldetallefactura.findAll", query = "SELECT t FROM Tdetalleadicionaldetallefactura t"),
    @NamedQuery(name = "Tdetalleadicionaldetallefactura.findByCdafactura", query = "SELECT t FROM Tdetalleadicionaldetallefactura t WHERE t.cdafactura = :cdafactura"),
    @NamedQuery(name = "Tdetalleadicionaldetallefactura.findByNombre", query = "SELECT t FROM Tdetalleadicionaldetallefactura t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tdetalleadicionaldetallefactura.findByValor", query = "SELECT t FROM Tdetalleadicionaldetallefactura t WHERE t.valor = :valor")})
public class Tdetalleadicionaldetallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdafactura", nullable = false)
    private Integer cdafactura;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 300)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false, length = 300)
    private String valor;
    @JoinColumn(name = "cdfactura_fk", referencedColumnName = "cdfactura", nullable = false)
    @ManyToOne(optional = false)
    private Tdetallefactura cdfacturaFk;

    public Tdetalleadicionaldetallefactura() {
    }

    public Tdetalleadicionaldetallefactura(Integer cdafactura) {
        this.cdafactura = cdafactura;
    }

    public Tdetalleadicionaldetallefactura(Integer cdafactura, String nombre, String valor) {
        this.cdafactura = cdafactura;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Integer getCdafactura() {
        return cdafactura;
    }

    public void setCdafactura(Integer cdafactura) {
        this.cdafactura = cdafactura;
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

    public Tdetallefactura getCdfacturaFk() {
        return cdfacturaFk;
    }

    public void setCdfacturaFk(Tdetallefactura cdfacturaFk) {
        this.cdfacturaFk = cdfacturaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdafactura != null ? cdafactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetalleadicionaldetallefactura)) {
            return false;
        }
        Tdetalleadicionaldetallefactura other = (Tdetalleadicionaldetallefactura) object;
        if ((this.cdafactura == null && other.cdafactura != null) || (this.cdafactura != null && !this.cdafactura.equals(other.cdafactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetalleadicionaldetallefactura[ cdafactura=" + cdafactura + " ]";
    }
    
}
