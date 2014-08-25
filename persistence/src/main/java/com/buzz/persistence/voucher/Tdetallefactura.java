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
@Table(name= "TDETALLEFACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetallefactura.findAll", query = "SELECT t FROM Tdetallefactura t"),
    @NamedQuery(name = "Tdetallefactura.findByCdfactura", query = "SELECT t FROM Tdetallefactura t WHERE t.cdfactura = :cdfactura"),
    @NamedQuery(name = "Tdetallefactura.findByCodigoPrincipal", query = "SELECT t FROM Tdetallefactura t WHERE t.codigoPrincipal = :codigoPrincipal"),
    @NamedQuery(name = "Tdetallefactura.findByCodigoAuxiliar", query = "SELECT t FROM Tdetallefactura t WHERE t.codigoAuxiliar = :codigoAuxiliar"),
    @NamedQuery(name = "Tdetallefactura.findByDescripcion", query = "SELECT t FROM Tdetallefactura t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tdetallefactura.findByCantidad", query = "SELECT t FROM Tdetallefactura t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Tdetallefactura.findByPrecioUnitario", query = "SELECT t FROM Tdetallefactura t WHERE t.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "Tdetallefactura.findByDescuento", query = "SELECT t FROM Tdetallefactura t WHERE t.descuento = :descuento"),
    @NamedQuery(name = "Tdetallefactura.findByPrecioTotalSinImpuesto", query = "SELECT t FROM Tdetallefactura t WHERE t.precioTotalSinImpuesto = :precioTotalSinImpuesto")})
public class Tdetallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer cdfactura;
    @Basic(optional = false)
    @Column(name = "codigo_principal", nullable = false, length = 25)
    private String codigoPrincipal;
    @Column(name = "codigo_auxiliar", length = 25)
    private String codigoAuxiliar;
    @Basic(optional = false)
    @Column(nullable = false, length = 300)
    private String descripcion;
    @Basic(optional = false)
    @Column(nullable = false)
    private float cantidad;
    @Basic(optional = false)
    @Column(name = "precio_unitario", nullable = false)
    private float precioUnitario;
    @Basic(optional = false)
    @Column(nullable = false)
    private float descuento;
    @Basic(optional = false)
    @Column(name = "precio_total_sin_impuesto", nullable = false)
    private float precioTotalSinImpuesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdfacturaFk")
    private List<Tdetalleadicionaldetallefactura> tdetalleadicionaldetallefacturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdfacturaFk")
    private List<Timpuestodetallefactura> timpuestodetallefacturaList;
    @JoinColumn(name = "ccfactura_fk", referencedColumnName = "ccfactura", nullable = false)
    @ManyToOne(optional = false)
    private Tcabecerafactura ccfacturaFk;

    public Tdetallefactura() {
    }

    public Tdetallefactura(Integer cdfactura) {
        this.cdfactura = cdfactura;
    }

    public Tdetallefactura(Integer cdfactura, String codigoPrincipal, String descripcion, float cantidad, float precioUnitario, float descuento, float precioTotalSinImpuesto) {
        this.cdfactura = cdfactura;
        this.codigoPrincipal = codigoPrincipal;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.precioTotalSinImpuesto = precioTotalSinImpuesto;
    }

    public Integer getCdfactura() {
        return cdfactura;
    }

    public void setCdfactura(Integer cdfactura) {
        this.cdfactura = cdfactura;
    }

    public String getCodigoPrincipal() {
        return codigoPrincipal;
    }

    public void setCodigoPrincipal(String codigoPrincipal) {
        this.codigoPrincipal = codigoPrincipal;
    }

    public String getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    public void setCodigoAuxiliar(String codigoAuxiliar) {
        this.codigoAuxiliar = codigoAuxiliar;
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

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getPrecioTotalSinImpuesto() {
        return precioTotalSinImpuesto;
    }

    public void setPrecioTotalSinImpuesto(float precioTotalSinImpuesto) {
        this.precioTotalSinImpuesto = precioTotalSinImpuesto;
    }

    @XmlTransient
    public List<Tdetalleadicionaldetallefactura> getTdetalleadicionaldetallefacturaList() {
        return tdetalleadicionaldetallefacturaList;
    }

    public void setTdetalleadicionaldetallefacturaList(List<Tdetalleadicionaldetallefactura> tdetalleadicionaldetallefacturaList) {
        this.tdetalleadicionaldetallefacturaList = tdetalleadicionaldetallefacturaList;
    }

    @XmlTransient
    public List<Timpuestodetallefactura> getTimpuestodetallefacturaList() {
        return timpuestodetallefacturaList;
    }

    public void setTimpuestodetallefacturaList(List<Timpuestodetallefactura> timpuestodetallefacturaList) {
        this.timpuestodetallefacturaList = timpuestodetallefacturaList;
    }

    public Tcabecerafactura getCcfacturaFk() {
        return ccfacturaFk;
    }

    public void setCcfacturaFk(Tcabecerafactura ccfacturaFk) {
        this.ccfacturaFk = ccfacturaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdfactura != null ? cdfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetallefactura)) {
            return false;
        }
        Tdetallefactura other = (Tdetallefactura) object;
        if ((this.cdfactura == null && other.cdfactura != null) || (this.cdfactura != null && !this.cdfactura.equals(other.cdfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetallefactura[ cdfactura=" + cdfactura + " ]";
    }
    
}
