/*
 * To change this template, choose Tools | Templates
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
 * @author karina
 */
@Entity
@Table(name = "TDETALLENOTACREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdetallenotacredito.findAll", query = "SELECT t FROM Tdetallenotacredito t"),
    @NamedQuery(name = "Tdetallenotacredito.findByCdncredito", query = "SELECT t FROM Tdetallenotacredito t WHERE t.cdncredito = :cdncredito"),
    @NamedQuery(name = "Tdetallenotacredito.findByCodigoPrincipal", query = "SELECT t FROM Tdetallenotacredito t WHERE t.codigoPrincipal = :codigoPrincipal"),
    @NamedQuery(name = "Tdetallenotacredito.findByCodigoAuxiliar", query = "SELECT t FROM Tdetallenotacredito t WHERE t.codigoAuxiliar = :codigoAuxiliar"),
    @NamedQuery(name = "Tdetallenotacredito.findByDescripcion", query = "SELECT t FROM Tdetallenotacredito t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tdetallenotacredito.findByCantidad", query = "SELECT t FROM Tdetallenotacredito t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Tdetallenotacredito.findByPrecioUnitario", query = "SELECT t FROM Tdetallenotacredito t WHERE t.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "Tdetallenotacredito.findByDescuento", query = "SELECT t FROM Tdetallenotacredito t WHERE t.descuento = :descuento"),
    @NamedQuery(name = "Tdetallenotacredito.findByPrecioTotalSinImpuesto", query = "SELECT t FROM Tdetallenotacredito t WHERE t.precioTotalSinImpuesto = :precioTotalSinImpuesto")})
public class Tdetallenotacredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdncredito", nullable = false)
    private Integer cdncredito;
    @Basic(optional = false)
    @Column(name = "codigo_principal", nullable = false, length = 25)
    private String codigoPrincipal;
    @Column(name = "codigo_auxiliar", length = 25)
    private String codigoAuxiliar;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false)
    private float cantidad;
    @Basic(optional = false)
    @Column(name = "precio_unitario", nullable = false)
    private float precioUnitario;
    @Basic(optional = false)
    @Column(name = "descuento", nullable = false)
    private float descuento;
    @Basic(optional = false)
    @Column(name = "precio_total_sin_impuesto", nullable = false)
    private float precioTotalSinImpuesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdncreditoFk")
    private List<Tdetalleadicionaldetallenotacredito> tdetalleadicionaldetallenotacreditoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdncreditoFk")
    private List<Timpuestodetallenotacredito> timpuestodetallenotacreditoList;
    @JoinColumn(name = "ccnotacd_fk", referencedColumnName = "ccnotacd", nullable = false)
    @ManyToOne(optional = false)
    private Tcabeceranotacreditodebito ccnotacdFk;

    public Tdetallenotacredito() {
    }

    public Tdetallenotacredito(Integer cdncredito) {
        this.cdncredito = cdncredito;
    }

    public Tdetallenotacredito(Integer cdncredito, String codigoPrincipal, String descripcion, float cantidad, float precioUnitario, float descuento, float precioTotalSinImpuesto) {
        this.cdncredito = cdncredito;
        this.codigoPrincipal = codigoPrincipal;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.precioTotalSinImpuesto = precioTotalSinImpuesto;
    }

    public Integer getCdncredito() {
        return cdncredito;
    }

    public void setCdncredito(Integer cdncredito) {
        this.cdncredito = cdncredito;
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
    public List<Tdetalleadicionaldetallenotacredito> getTdetalleadicionaldetallenotacreditoList() {
        return tdetalleadicionaldetallenotacreditoList;
    }

    public void setTdetalleadicionaldetallenotacreditoList(List<Tdetalleadicionaldetallenotacredito> tdetalleadicionaldetallenotacreditoList) {
        this.tdetalleadicionaldetallenotacreditoList = tdetalleadicionaldetallenotacreditoList;
    }

    @XmlTransient
    public List<Timpuestodetallenotacredito> getTimpuestodetallenotacreditoList() {
        return timpuestodetallenotacreditoList;
    }

    public void setTimpuestodetallenotacreditoList(List<Timpuestodetallenotacredito> timpuestodetallenotacreditoList) {
        this.timpuestodetallenotacreditoList = timpuestodetallenotacreditoList;
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
        hash += (cdncredito != null ? cdncredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdetallenotacredito)) {
            return false;
        }
        Tdetallenotacredito other = (Tdetallenotacredito) object;
        if ((this.cdncredito == null && other.cdncredito != null) || (this.cdncredito != null && !this.cdncredito.equals(other.cdncredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdetallenotacredito[ cdncredito=" + cdncredito + " ]";
    }
    
}
