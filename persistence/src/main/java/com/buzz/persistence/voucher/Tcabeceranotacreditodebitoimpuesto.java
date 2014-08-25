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
@Table(name="TCABECERANOTACREDITODEBITOIMPUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcabeceranotacreditodebitoimpuesto.findAll", query = "SELECT t FROM Tcabeceranotacreditodebitoimpuesto t"),
    @NamedQuery(name = "Tcabeceranotacreditodebitoimpuesto.findByCcncdimpuesto", query = "SELECT t FROM Tcabeceranotacreditodebitoimpuesto t WHERE t.ccncdimpuesto = :ccncdimpuesto"),
    @NamedQuery(name = "Tcabeceranotacreditodebitoimpuesto.findByCodigo", query = "SELECT t FROM Tcabeceranotacreditodebitoimpuesto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tcabeceranotacreditodebitoimpuesto.findByCodigoPorcentaje", query = "SELECT t FROM Tcabeceranotacreditodebitoimpuesto t WHERE t.codigoPorcentaje = :codigoPorcentaje"),
    @NamedQuery(name = "Tcabeceranotacreditodebitoimpuesto.findByTarifa", query = "SELECT t FROM Tcabeceranotacreditodebitoimpuesto t WHERE t.tarifa = :tarifa"),
    @NamedQuery(name = "Tcabeceranotacreditodebitoimpuesto.findByBaseImponible", query = "SELECT t FROM Tcabeceranotacreditodebitoimpuesto t WHERE t.baseImponible = :baseImponible"),
    @NamedQuery(name = "Tcabeceranotacreditodebitoimpuesto.findByValor", query = "SELECT t FROM Tcabeceranotacreditodebitoimpuesto t WHERE t.valor = :valor")})
public class Tcabeceranotacreditodebitoimpuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer ccncdimpuesto;
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
    @JoinColumn(name = "ccnotacd_fk", referencedColumnName = "ccnotacd", nullable = false)
    @ManyToOne(optional = false)
    private Tcabeceranotacreditodebito ccnotacdFk;

    public Tcabeceranotacreditodebitoimpuesto() {
    }

    public Tcabeceranotacreditodebitoimpuesto(Integer ccncdimpuesto) {
        this.ccncdimpuesto = ccncdimpuesto;
    }

    public Tcabeceranotacreditodebitoimpuesto(Integer ccncdimpuesto, String codigo, String codigoPorcentaje, float baseImponible, float valor) {
        this.ccncdimpuesto = ccncdimpuesto;
        this.codigo = codigo;
        this.codigoPorcentaje = codigoPorcentaje;
        this.baseImponible = baseImponible;
        this.valor = valor;
    }

    public Integer getCcncdimpuesto() {
        return ccncdimpuesto;
    }

    public void setCcncdimpuesto(Integer ccncdimpuesto) {
        this.ccncdimpuesto = ccncdimpuesto;
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

    public Tcabeceranotacreditodebito getCcnotacdFk() {
        return ccnotacdFk;
    }

    public void setCcnotacdFk(Tcabeceranotacreditodebito ccnotacdFk) {
        this.ccnotacdFk = ccnotacdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccncdimpuesto != null ? ccncdimpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcabeceranotacreditodebitoimpuesto)) {
            return false;
        }
        Tcabeceranotacreditodebitoimpuesto other = (Tcabeceranotacreditodebitoimpuesto) object;
        if ((this.ccncdimpuesto == null && other.ccncdimpuesto != null) || (this.ccncdimpuesto != null && !this.ccncdimpuesto.equals(other.ccncdimpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcabeceranotacreditodebitoimpuesto[ ccncdimpuesto=" + ccncdimpuesto + " ]";
    }
    
}
