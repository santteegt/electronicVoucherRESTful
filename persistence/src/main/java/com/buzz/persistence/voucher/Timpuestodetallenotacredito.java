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
@Table(name = "TIMPUESTODETALLENOTACREDITO", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timpuestodetallenotacredito.findAll", query = "SELECT t FROM Timpuestodetallenotacredito t"),
    @NamedQuery(name = "Timpuestodetallenotacredito.findByCidncredito", query = "SELECT t FROM Timpuestodetallenotacredito t WHERE t.cidncredito = :cidncredito"),
    @NamedQuery(name = "Timpuestodetallenotacredito.findByCodigo", query = "SELECT t FROM Timpuestodetallenotacredito t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Timpuestodetallenotacredito.findByCodigoPorcentaje", query = "SELECT t FROM Timpuestodetallenotacredito t WHERE t.codigoPorcentaje = :codigoPorcentaje"),
    @NamedQuery(name = "Timpuestodetallenotacredito.findByTarifa", query = "SELECT t FROM Timpuestodetallenotacredito t WHERE t.tarifa = :tarifa"),
    @NamedQuery(name = "Timpuestodetallenotacredito.findByBaseImponible", query = "SELECT t FROM Timpuestodetallenotacredito t WHERE t.baseImponible = :baseImponible"),
    @NamedQuery(name = "Timpuestodetallenotacredito.findByValor", query = "SELECT t FROM Timpuestodetallenotacredito t WHERE t.valor = :valor")})
public class Timpuestodetallenotacredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cidncredito", nullable = false)
    private Integer cidncredito;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 1)
    private String codigo;
    @Basic(optional = false)
    @Column(name = "codigo_porcentaje", nullable = false, length = 4)
    private String codigoPorcentaje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tarifa", precision = 4, scale = 2)
    private Float tarifa;
    @Basic(optional = false)
    @Column(name = "base_imponible", nullable = false)
    private float baseImponible;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private float valor;
    @JoinColumn(name = "cdncredito_fk", referencedColumnName = "cdncredito", nullable = false)
    @ManyToOne(optional = false)
    private Tdetallenotacredito cdncreditoFk;

    public Timpuestodetallenotacredito() {
    }

    public Timpuestodetallenotacredito(Integer cidncredito) {
        this.cidncredito = cidncredito;
    }

    public Timpuestodetallenotacredito(Integer cidncredito, String codigo, String codigoPorcentaje, float baseImponible, float valor) {
        this.cidncredito = cidncredito;
        this.codigo = codigo;
        this.codigoPorcentaje = codigoPorcentaje;
        this.baseImponible = baseImponible;
        this.valor = valor;
    }

    public Integer getCidncredito() {
        return cidncredito;
    }

    public void setCidncredito(Integer cidncredito) {
        this.cidncredito = cidncredito;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoPorcentaje() {
        return codigoPorcentaje;
    }

    public void setCodigoPorcentaje(String codigoPorcentaje) {
        this.codigoPorcentaje = codigoPorcentaje;
    }

    public Float getTarifa() {
        return tarifa;
    }

    public void setTarifa(Float tarifa) {
        this.tarifa = tarifa;
    }

    public float getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(float baseImponible) {
        this.baseImponible = baseImponible;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
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
        hash += (cidncredito != null ? cidncredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timpuestodetallenotacredito)) {
            return false;
        }
        Timpuestodetallenotacredito other = (Timpuestodetallenotacredito) object;
        if ((this.cidncredito == null && other.cidncredito != null) || (this.cidncredito != null && !this.cidncredito.equals(other.cidncredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Timpuestodetallenotacredito[ cidncredito=" + cidncredito + " ]";
    }
    
}
