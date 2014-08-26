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
@Table(name = "TCABECERAFACTURAIMPUESTO", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcabecerafacturaimpuesto.findAll", query = "SELECT t FROM Tcabecerafacturaimpuesto t"),
    @NamedQuery(name = "Tcabecerafacturaimpuesto.findByCcfimpuesto", query = "SELECT t FROM Tcabecerafacturaimpuesto t WHERE t.ccfimpuesto = :ccfimpuesto"),
    @NamedQuery(name = "Tcabecerafacturaimpuesto.findByCodigo", query = "SELECT t FROM Tcabecerafacturaimpuesto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tcabecerafacturaimpuesto.findByCodigoPorcentaje", query = "SELECT t FROM Tcabecerafacturaimpuesto t WHERE t.codigoPorcentaje = :codigoPorcentaje"),
    @NamedQuery(name = "Tcabecerafacturaimpuesto.findByBaseImponible", query = "SELECT t FROM Tcabecerafacturaimpuesto t WHERE t.baseImponible = :baseImponible"),
    @NamedQuery(name = "Tcabecerafacturaimpuesto.findByTarifa", query = "SELECT t FROM Tcabecerafacturaimpuesto t WHERE t.tarifa = :tarifa"),
    @NamedQuery(name = "Tcabecerafacturaimpuesto.findByValor", query = "SELECT t FROM Tcabecerafacturaimpuesto t WHERE t.valor = :valor")})
public class Tcabecerafacturaimpuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ccfimpuesto", nullable = false)
    private Integer ccfimpuesto;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 1)
    private String codigo;
    @Basic(optional = false)
    @Column(name = "codigo_porcentaje", nullable = false, length = 4)
    private String codigoPorcentaje;
    @Basic(optional = false)
    @Column(name = "base_imponible", nullable = false)
    private float baseImponible;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tarifa", precision = 14, scale = 2)
    private Float tarifa;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private float valor;
    @JoinColumn(name = "ccfactura_fk", referencedColumnName = "ccfactura", nullable = false)
    @ManyToOne(optional = false)
    private Tcabecerafactura ccfacturaFk;

    public Tcabecerafacturaimpuesto() {
    }

    public Tcabecerafacturaimpuesto(Integer ccfimpuesto) {
        this.ccfimpuesto = ccfimpuesto;
    }

    public Tcabecerafacturaimpuesto(Integer ccfimpuesto, String codigo, String codigoPorcentaje, float baseImponible, float valor) {
        this.ccfimpuesto = ccfimpuesto;
        this.codigo = codigo;
        this.codigoPorcentaje = codigoPorcentaje;
        this.baseImponible = baseImponible;
        this.valor = valor;
    }

    public Integer getCcfimpuesto() {
        return ccfimpuesto;
    }

    public void setCcfimpuesto(Integer ccfimpuesto) {
        this.ccfimpuesto = ccfimpuesto;
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

    public float getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(float baseImponible) {
        this.baseImponible = baseImponible;
    }

    public Float getTarifa() {
        return tarifa;
    }

    public void setTarifa(Float tarifa) {
        this.tarifa = tarifa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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
        hash += (ccfimpuesto != null ? ccfimpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcabecerafacturaimpuesto)) {
            return false;
        }
        Tcabecerafacturaimpuesto other = (Tcabecerafacturaimpuesto) object;
        if ((this.ccfimpuesto == null && other.ccfimpuesto != null) || (this.ccfimpuesto != null && !this.ccfimpuesto.equals(other.ccfimpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcabecerafacturaimpuesto[ ccfimpuesto=" + ccfimpuesto + " ]";
    }
    
}
