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
import javax.persistence.JoinColumns;
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
@Table(name = "TINFORMACIONADICIONALCOMPROBANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tinformacionadicionalcomprobante.findAll", query = "SELECT t FROM Tinformacionadicionalcomprobante t"),
    @NamedQuery(name = "Tinformacionadicionalcomprobante.findByCiacomprobante", query = "SELECT t FROM Tinformacionadicionalcomprobante t WHERE t.ciacomprobante = :ciacomprobante"),
    @NamedQuery(name = "Tinformacionadicionalcomprobante.findByNombre", query = "SELECT t FROM Tinformacionadicionalcomprobante t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tinformacionadicionalcomprobante.findByValor", query = "SELECT t FROM Tinformacionadicionalcomprobante t WHERE t.valor = :valor")})
public class Tinformacionadicionalcomprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ciacomprobante", nullable = false)
    private Integer ciacomprobante;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 300)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false, length = 300)
    private String valor;
    @JoinColumns({
        @JoinColumn(name = "ccontribuyente_fk2", referencedColumnName = "ccontribuyente_fk", nullable = false),
        @JoinColumn(name = "idpeticion_fk", referencedColumnName = "idpeticion", nullable = false)})
    @ManyToOne(optional = false)
    private Tcomprobante tcomprobante;

    public Tinformacionadicionalcomprobante() {
    }

    public Tinformacionadicionalcomprobante(Integer ciacomprobante) {
        this.ciacomprobante = ciacomprobante;
    }

    public Tinformacionadicionalcomprobante(Integer ciacomprobante, String nombre, String valor) {
        this.ciacomprobante = ciacomprobante;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Integer getCiacomprobante() {
        return ciacomprobante;
    }

    public void setCiacomprobante(Integer ciacomprobante) {
        this.ciacomprobante = ciacomprobante;
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

    public Tcomprobante getTcomprobante() {
        return tcomprobante;
    }

    public void setTcomprobante(Tcomprobante tcomprobante) {
        this.tcomprobante = tcomprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciacomprobante != null ? ciacomprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tinformacionadicionalcomprobante)) {
            return false;
        }
        Tinformacionadicionalcomprobante other = (Tinformacionadicionalcomprobante) object;
        if ((this.ciacomprobante == null && other.ciacomprobante != null) || (this.ciacomprobante != null && !this.ciacomprobante.equals(other.ciacomprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tinformacionadicionalcomprobante[ ciacomprobante=" + ciacomprobante + " ]";
    }
    
}
