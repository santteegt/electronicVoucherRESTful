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
@Table(name = "TDETALLEADICIONALDESTINATARIOGUIAREMISION", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetalleadicionaldestinatarioguiaremision.findAll", query = "SELECT t FROM Tdetalleadicionaldestinatarioguiaremision t"),
    @NamedQuery(name = "Tdetalleadicionaldestinatarioguiaremision.findByCdaddgremision", query = "SELECT t FROM Tdetalleadicionaldestinatarioguiaremision t WHERE t.cdaddgremision = :cdaddgremision"),
    @NamedQuery(name = "Tdetalleadicionaldestinatarioguiaremision.findByNombre", query = "SELECT t FROM Tdetalleadicionaldestinatarioguiaremision t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tdetalleadicionaldestinatarioguiaremision.findByValor", query = "SELECT t FROM Tdetalleadicionaldestinatarioguiaremision t WHERE t.valor = :valor")})
public class Tdetalleadicionaldestinatarioguiaremision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdaddgremision", nullable = false)
    private Integer cdaddgremision;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 300)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false, length = 300)
    private String valor;
    @JoinColumn(name = "cddgremision_fk", referencedColumnName = "cddgremision", nullable = false)
    @ManyToOne(optional = false)
    private Tdetalledestinatarioguiaremision cddgremisionFk;

    public Tdetalleadicionaldestinatarioguiaremision() {
    }

    public Tdetalleadicionaldestinatarioguiaremision(Integer cdaddgremision) {
        this.cdaddgremision = cdaddgremision;
    }

    public Tdetalleadicionaldestinatarioguiaremision(Integer cdaddgremision, String nombre, String valor) {
        this.cdaddgremision = cdaddgremision;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Integer getCdaddgremision() {
        return cdaddgremision;
    }

    public void setCdaddgremision(Integer cdaddgremision) {
        this.cdaddgremision = cdaddgremision;
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

    public Tdetalledestinatarioguiaremision getCddgremisionFk() {
        return cddgremisionFk;
    }

    public void setCddgremisionFk(Tdetalledestinatarioguiaremision cddgremisionFk) {
        this.cddgremisionFk = cddgremisionFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdaddgremision != null ? cdaddgremision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetalleadicionaldestinatarioguiaremision)) {
            return false;
        }
        Tdetalleadicionaldestinatarioguiaremision other = (Tdetalleadicionaldestinatarioguiaremision) object;
        if ((this.cdaddgremision == null && other.cdaddgremision != null) || (this.cdaddgremision != null && !this.cdaddgremision.equals(other.cdaddgremision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetalleadicionaldestinatarioguiaremision[ cdaddgremision=" + cdaddgremision + " ]";
    }
    
}
