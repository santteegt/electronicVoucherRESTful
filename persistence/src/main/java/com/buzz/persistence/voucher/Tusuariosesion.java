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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karina
 */
@Entity
@Table(name = "TUSUARIOSESION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tusuariosesion.findAll", query = "SELECT t FROM Tusuariosesion t"),
    @NamedQuery(name = "Tusuariosesion.findByCsesion", query = "SELECT t FROM Tusuariosesion t WHERE t.csesion = :csesion"),
    @NamedQuery(name = "Tusuariosesion.findByFechaAcceso", query = "SELECT t FROM Tusuariosesion t WHERE t.fechaAcceso = :fechaAcceso"),
    @NamedQuery(name = "Tusuariosesion.findByFechaVigencia", query = "SELECT t FROM Tusuariosesion t WHERE t.fechaVigencia = :fechaVigencia")})
public class Tusuariosesion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "csesion", nullable = false, length = 30)
    private String csesion;
    @Basic(optional = false)
    @Column(name = "fecha_acceso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcceso;
    @Basic(optional = false)
    @Column(name = "fecha_vigencia", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVigencia;
    @JoinColumn(name = "cusuario_fk", referencedColumnName = "cusuario", nullable = false)
    @ManyToOne(optional = false)
    private Tusuarioid cusuarioFk;

    public Tusuariosesion() {
    }

    public Tusuariosesion(String csesion) {
        this.csesion = csesion;
    }

    public Tusuariosesion(String csesion, Date fechaAcceso, Date fechaVigencia) {
        this.csesion = csesion;
        this.fechaAcceso = fechaAcceso;
        this.fechaVigencia = fechaVigencia;
    }

    public String getCsesion() {
        return csesion;
    }

    public void setCsesion(String csesion) {
        this.csesion = csesion;
    }

    public Date getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(Date fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Tusuarioid getCusuarioFk() {
        return cusuarioFk;
    }

    public void setCusuarioFk(Tusuarioid cusuarioFk) {
        this.cusuarioFk = cusuarioFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (csesion != null ? csesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tusuariosesion)) {
            return false;
        }
        Tusuariosesion other = (Tusuariosesion) object;
        if ((this.csesion == null && other.csesion != null) || (this.csesion != null && !this.csesion.equals(other.csesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tusuariosesion[ csesion=" + csesion + " ]";
    }
    
}
