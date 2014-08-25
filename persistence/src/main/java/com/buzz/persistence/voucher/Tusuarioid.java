/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buzz
 */
@Entity
@Table(name= "TUSUARIOID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tusuarioid.findAll", query = "SELECT t FROM Tusuarioid t"),
    @NamedQuery(name = "Tusuarioid.findByCusuario", query = "SELECT t FROM Tusuarioid t WHERE t.cusuario = :cusuario")})
public class Tusuarioid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String cusuario;
    @JoinColumns({
     @JoinColumn(name = "ccontribuyente_fk", referencedColumnName = "ccontribuyente", nullable = false, insertable = false, updatable = false),
     @JoinColumn(name = "fhasta", referencedColumnName = "fhasta", nullable = false, insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private Tcontribuyente ccontribuyenteFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tusuarioid")
    private List<Tusuario> tusuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cusuarioFk")
    private List<Tusuariosesion> tusuariosesionList;

    public Tusuarioid() {
    }

    public Tusuarioid(String cusuario) {
        this.cusuario = cusuario;
    }

    public String getCusuario() {
        return cusuario;
    }

    public void setCusuario(String cusuario) {
        this.cusuario = cusuario;
    }

    public Tcontribuyente getCcontribuyenteFk() {
        return ccontribuyenteFk;
    }

    public void setCcontribuyenteFk(Tcontribuyente ccontribuyenteFk) {
        this.ccontribuyenteFk = ccontribuyenteFk;
    }

    @XmlTransient
    public List<Tusuario> getTusuarioList() {
        return tusuarioList;
    }

    public void setTusuarioList(List<Tusuario> tusuarioList) {
        this.tusuarioList = tusuarioList;
    }

    @XmlTransient
    public List<Tusuariosesion> getTusuariosesionList() {
        return tusuariosesionList;
    }

    public void setTusuariosesionList(List<Tusuariosesion> tusuariosesionList) {
        this.tusuariosesionList = tusuariosesionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusuario != null ? cusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tusuarioid)) {
            return false;
        }
        Tusuarioid other = (Tusuarioid) object;
        if ((this.cusuario == null && other.cusuario != null) || (this.cusuario != null && !this.cusuario.equals(other.cusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tusuarioid[ cusuario=" + cusuario + " ]";
    }
    
}
