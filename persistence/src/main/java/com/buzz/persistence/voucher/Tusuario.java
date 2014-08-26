/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buzz
 */
@Entity
@Table(name = "TUSUARIO", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tusuario.findAll", query = "SELECT t FROM Tusuario t"),
    @NamedQuery(name = "Tusuario.findByCusuarioFk", query = "SELECT t FROM Tusuario t WHERE t.tusuarioPK.cusuarioFk = :cusuarioFk"),
    @NamedQuery(name = "Tusuario.findByFhasta", query = "SELECT t FROM Tusuario t WHERE t.tusuarioPK.fhasta = :fhasta"),
    @NamedQuery(name = "Tusuario.findByFdesde", query = "SELECT t FROM Tusuario t WHERE t.fdesde = :fdesde"),
    @NamedQuery(name = "Tusuario.findByActivo", query = "SELECT t FROM Tusuario t WHERE t.activo = :activo"),
    @NamedQuery(name = "Tusuario.findByRazonSocial", query = "SELECT t FROM Tusuario t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "Tusuario.findByIdentificacion", query = "SELECT t FROM Tusuario t WHERE t.identificacion = :identificacion")})
public class Tusuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TusuarioPK tusuarioPK;
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
    @ManyToOne(optional = false)
    private Tusuarioid tusuarioid;

    public Tusuario() {
    }

    public Tusuario(TusuarioPK tusuarioPK) {
        this.tusuarioPK = tusuarioPK;
    }

    public Tusuario(TusuarioPK tusuarioPK, Date fdesde, boolean activo, String contrasena) {
        this.tusuarioPK = tusuarioPK;
        this.fdesde = fdesde;
        this.activo = activo;
        this.contrasena = contrasena;
    }

    public Tusuario(String cusuarioFk, Date fhasta) {
        this.tusuarioPK = new TusuarioPK(cusuarioFk, fhasta);
    }

    public TusuarioPK getTusuarioPK() {
        return tusuarioPK;
    }

    public void setTusuarioPK(TusuarioPK tusuarioPK) {
        this.tusuarioPK = tusuarioPK;
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
        hash += (tusuarioPK != null ? tusuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tusuario)) {
            return false;
        }
        Tusuario other = (Tusuario) object;
        if ((this.tusuarioPK == null && other.tusuarioPK != null) || (this.tusuarioPK != null && !this.tusuarioPK.equals(other.tusuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tusuario[ tusuarioPK=" + tusuarioPK + " ]";
    }
    
}
