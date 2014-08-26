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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TDETALLEDESTINATARIOGUIAREMISION", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetalledestinatarioguiaremision.findAll", query = "SELECT t FROM Tdetalledestinatarioguiaremision t"),
    @NamedQuery(name = "Tdetalledestinatarioguiaremision.findByCddgremision", query = "SELECT t FROM Tdetalledestinatarioguiaremision t WHERE t.cddgremision = :cddgremision"),
    @NamedQuery(name = "Tdetalledestinatarioguiaremision.findByCodigoInterno", query = "SELECT t FROM Tdetalledestinatarioguiaremision t WHERE t.codigoInterno = :codigoInterno"),
    @NamedQuery(name = "Tdetalledestinatarioguiaremision.findByCodigoAdicional", query = "SELECT t FROM Tdetalledestinatarioguiaremision t WHERE t.codigoAdicional = :codigoAdicional"),
    @NamedQuery(name = "Tdetalledestinatarioguiaremision.findByDescripcion", query = "SELECT t FROM Tdetalledestinatarioguiaremision t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tdetalledestinatarioguiaremision.findByCantidad", query = "SELECT t FROM Tdetalledestinatarioguiaremision t WHERE t.cantidad = :cantidad")})
public class Tdetalledestinatarioguiaremision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cddgremision", nullable = false)
    private Integer cddgremision;
    @Basic(optional = false)
    @Column(name = "codigo_interno", nullable = false, length = 25)
    private String codigoInterno;
    @Column(name = "codigo_adicional", length = 25)
    private String codigoAdicional;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false)
    private float cantidad;
    @JoinColumn(name = "cdgremision_fk", referencedColumnName = "cdgremision", nullable = false)
    @ManyToOne(optional = false)
    private Tdestinatarioguiaremision cdgremisionFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cddgremisionFk")
    private List<Tdetalleadicionaldestinatarioguiaremision> tdetalleadicionaldestinatarioguiaremisionList;

    public Tdetalledestinatarioguiaremision() {
    }

    public Tdetalledestinatarioguiaremision(Integer cddgremision) {
        this.cddgremision = cddgremision;
    }

    public Tdetalledestinatarioguiaremision(Integer cddgremision, String codigoInterno, String descripcion, float cantidad) {
        this.cddgremision = cddgremision;
        this.codigoInterno = codigoInterno;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Integer getCddgremision() {
        return cddgremision;
    }

    public void setCddgremision(Integer cddgremision) {
        this.cddgremision = cddgremision;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getCodigoAdicional() {
        return codigoAdicional;
    }

    public void setCodigoAdicional(String codigoAdicional) {
        this.codigoAdicional = codigoAdicional;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Tdestinatarioguiaremision getCdgremisionFk() {
        return cdgremisionFk;
    }

    public void setCdgremisionFk(Tdestinatarioguiaremision cdgremisionFk) {
        this.cdgremisionFk = cdgremisionFk;
    }

    @XmlTransient
    public List<Tdetalleadicionaldestinatarioguiaremision> getTdetalleadicionaldestinatarioguiaremisionList() {
        return tdetalleadicionaldestinatarioguiaremisionList;
    }

    public void setTdetalleadicionaldestinatarioguiaremisionList(List<Tdetalleadicionaldestinatarioguiaremision> tdetalleadicionaldestinatarioguiaremisionList) {
        this.tdetalleadicionaldestinatarioguiaremisionList = tdetalleadicionaldestinatarioguiaremisionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cddgremision != null ? cddgremision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetalledestinatarioguiaremision)) {
            return false;
        }
        Tdetalledestinatarioguiaremision other = (Tdetalledestinatarioguiaremision) object;
        if ((this.cddgremision == null && other.cddgremision != null) || (this.cddgremision != null && !this.cddgremision.equals(other.cddgremision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetalledestinatarioguiaremision[ cddgremision=" + cddgremision + " ]";
    }
    
}
