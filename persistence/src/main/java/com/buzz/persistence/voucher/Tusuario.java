/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karina
 */
@Entity
@Table(name = "TUSUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tusuario.findAll", query = "SELECT t FROM Tusuario t"),
    @NamedQuery(name = "Tusuario.findByCusuarioFk", query = "SELECT t FROM Tusuario t WHERE t.cusuarioFk = :cusuarioFk"),
    @NamedQuery(name = "Tusuario.findByFhasta", query = "SELECT t FROM Tusuario t WHERE t.fhasta = :fhasta"),
    @NamedQuery(name = "Tusuario.findByFdesde", query = "SELECT t FROM Tusuario t WHERE t.fdesde = :fdesde"),
    @NamedQuery(name = "Tusuario.findByActivo", query = "SELECT t FROM Tusuario t WHERE t.activo = :activo"),
    @NamedQuery(name = "Tusuario.findByRazonSocial", query = "SELECT t FROM Tusuario t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "Tusuario.findByIdentificacion", query = "SELECT t FROM Tusuario t WHERE t.identificacion = :identificacion")})
public class Tusuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cusuario_fk", nullable = false, length = 10)
    private String cusuarioFk;
    @Basic(optional = false)
    @Column(name = "fhasta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhasta;
    @Basic(optional = false)
    @Column(name = "fdesde", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdesde;
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @Basic(optional = false)
    @Lob
    @Column(name = "contrasena", nullable = false, length = 65535)
    private String contrasena;
    @Column(name = "razonSocial", length = 100)
    private String razonSocial;
    @Column(name = "identificacion", length = 13)
    private String identificacion;
    @JoinColumn(name = "cusuario_fk", referencedColumnName = "cusuario", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Tusuarioid tusuarioid;

    public Tusuario() {
    }

    public Tusuario(String cusuarioFk) {
        this.cusuarioFk = cusuarioFk;
    }

    public Tusuario(String cusuarioFk, Date fhasta, Date fdesde, boolean activo, String contrasena) {
        this.cusuarioFk = cusuarioFk;
        this.fhasta = fhasta;
        this.fdesde = fdesde;
        this.activo = activo;
        this.contrasena = contrasena;
    }

    public String getCusuarioFk() {
        return cusuarioFk;
    }

    public void setCusuarioFk(String cusuarioFk) {
        this.cusuarioFk = cusuarioFk;
    }

    public Date getFhasta() {
        return fhasta;
    }

    public void setFhasta(Date fhasta) {
        this.fhasta = fhasta;
    }

    public Date getFdesde() {
        return fdesde;
    }

    public void setFdesde(Date fdesde) {
        this.fdesde = fdesde;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Tusuarioid getTusuarioid() {
        return tusuarioid;
    }

    public void setTusuarioid(Tusuarioid tusuarioid) {
        this.tusuarioid = tusuarioid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusuarioFk != null ? cusuarioFk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tusuario)) {
            return false;
        }
        Tusuario other = (Tusuario) object;
        if ((this.cusuarioFk == null && other.cusuarioFk != null) || (this.cusuarioFk != null && !this.cusuarioFk.equals(other.cusuarioFk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tusuario[ cusuarioFk=" + cusuarioFk + " ]";
    }
    
}
