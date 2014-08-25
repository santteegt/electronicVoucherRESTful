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
@Table(name= "TIMPUESTODETALLEFACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timpuestodetallefactura.findAll", query = "SELECT t FROM Timpuestodetallefactura t"),
    @NamedQuery(name = "Timpuestodetallefactura.findByCidfactura", query = "SELECT t FROM Timpuestodetallefactura t WHERE t.cidfactura = :cidfactura"),
    @NamedQuery(name = "Timpuestodetallefactura.findByCodigo", query = "SELECT t FROM Timpuestodetallefactura t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Timpuestodetallefactura.findByCodigoPorcentaje", query = "SELECT t FROM Timpuestodetallefactura t WHERE t.codigoPorcentaje = :codigoPorcentaje"),
    @NamedQuery(name = "Timpuestodetallefactura.findByTarifa", query = "SELECT t FROM Timpuestodetallefactura t WHERE t.tarifa = :tarifa"),
    @NamedQuery(name = "Timpuestodetallefactura.findByBaseImponible", query = "SELECT t FROM Timpuestodetallefactura t WHERE t.baseImponible = :baseImponible"),
    @NamedQuery(name = "Timpuestodetallefactura.findByValor", query = "SELECT t FROM Timpuestodetallefactura t WHERE t.valor = :valor")})
public class Timpuestodetallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer cidfactura;
    @Basic(optional = false)
    @Column(nullable = false, length = 1)
    private String codigo;
    @Basic(optional = false)
    @Column(name = "codigo_porcentaje", nullable = false, length = 4)
    private String codigoPorcentaje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 4, scale = 2)
    private Float tarifa;
    @Basic(optional = false)
    @Column(name = "base_imponible", nullable = false)
    private float baseImponible;
    @Basic(optional = false)
    @Column(nullable = false)
    private float valor;
    @JoinColumn(name = "cdfactura_fk", referencedColumnName = "cdfactura", nullable = false)
    @ManyToOne(optional = false)
    private Tdetallefactura cdfacturaFk;

    public Timpuestodetallefactura() {
    }

    public Timpuestodetallefactura(Integer cidfactura) {
        this.cidfactura = cidfactura;
    }

    public Timpuestodetallefactura(Integer cidfactura, String codigo, String codigoPorcentaje, float baseImponible, float valor) {
        this.cidfactura = cidfactura;
        this.codigo = codigo;
        this.codigoPorcentaje = codigoPorcentaje;
        this.baseImponible = baseImponible;
        this.valor = valor;
    }

    public Integer getCidfactura() {
        return cidfactura;
    }

    public void setCidfactura(Integer cidfactura) {
        this.cidfactura = cidfactura;
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

    public Tdetallefactura getCdfacturaFk() {
        return cdfacturaFk;
    }

    public void setCdfacturaFk(Tdetallefactura cdfacturaFk) {
        this.cdfacturaFk = cdfacturaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cidfactura != null ? cidfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timpuestodetallefactura)) {
            return false;
        }
        Timpuestodetallefactura other = (Timpuestodetallefactura) object;
        if ((this.cidfactura == null && other.cidfactura != null) || (this.cidfactura != null && !this.cidfactura.equals(other.cidfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Timpuestodetallefactura[ cidfactura=" + cidfactura + " ]";
    }
    
}
