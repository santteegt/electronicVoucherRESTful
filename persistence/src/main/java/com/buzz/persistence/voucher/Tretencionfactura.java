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
@Table(name = "TRETENCIONFACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tretencionfactura.findAll", query = "SELECT t FROM Tretencionfactura t"),
    @NamedQuery(name = "Tretencionfactura.findByCrfactura", query = "SELECT t FROM Tretencionfactura t WHERE t.crfactura = :crfactura"),
    @NamedQuery(name = "Tretencionfactura.findByCodigo", query = "SELECT t FROM Tretencionfactura t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tretencionfactura.findByCodigoPorcentaje", query = "SELECT t FROM Tretencionfactura t WHERE t.codigoPorcentaje = :codigoPorcentaje"),
    @NamedQuery(name = "Tretencionfactura.findByTarifa", query = "SELECT t FROM Tretencionfactura t WHERE t.tarifa = :tarifa"),
    @NamedQuery(name = "Tretencionfactura.findByValor", query = "SELECT t FROM Tretencionfactura t WHERE t.valor = :valor")})
public class Tretencionfactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "crfactura", nullable = false)
    private Integer crfactura;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 1)
    private String codigo;
    @Basic(optional = false)
    @Column(name = "codigo_porcentaje", nullable = false, length = 3)
    private String codigoPorcentaje;
    @Basic(optional = false)
    @Column(name = "tarifa", nullable = false)
    private float tarifa;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private float valor;
    @JoinColumn(name = "ccfactura_fk", referencedColumnName = "ccfactura", nullable = false)
    @ManyToOne(optional = false)
    private Tcabecerafactura ccfacturaFk;

    public Tretencionfactura() {
    }

    public Tretencionfactura(Integer crfactura) {
        this.crfactura = crfactura;
    }

    public Tretencionfactura(Integer crfactura, String codigo, String codigoPorcentaje, float tarifa, float valor) {
        this.crfactura = crfactura;
        this.codigo = codigo;
        this.codigoPorcentaje = codigoPorcentaje;
        this.tarifa = tarifa;
        this.valor = valor;
    }

    public Integer getCrfactura() {
        return crfactura;
    }

    public void setCrfactura(Integer crfactura) {
        this.crfactura = crfactura;
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

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
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
        hash += (crfactura != null ? crfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tretencionfactura)) {
            return false;
        }
        Tretencionfactura other = (Tretencionfactura) object;
        if ((this.crfactura == null && other.crfactura != null) || (this.crfactura != null && !this.crfactura.equals(other.crfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tretencionfactura[ crfactura=" + crfactura + " ]";
    }
    
}
