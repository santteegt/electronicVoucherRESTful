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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author buzz
 */
@Embeddable
public class TusuarioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "cusuario_fk", nullable = false, length = 10)
    private String cusuarioFk;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhasta;

    public TusuarioPK() {
    }

    public TusuarioPK(String cusuarioFk, Date fhasta) {
        this.cusuarioFk = cusuarioFk;
        this.fhasta = fhasta;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusuarioFk != null ? cusuarioFk.hashCode() : 0);
        hash += (fhasta != null ? fhasta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TusuarioPK)) {
            return false;
        }
        TusuarioPK other = (TusuarioPK) object;
        if ((this.cusuarioFk == null && other.cusuarioFk != null) || (this.cusuarioFk != null && !this.cusuarioFk.equals(other.cusuarioFk))) {
            return false;
        }
        if ((this.fhasta == null && other.fhasta != null) || (this.fhasta != null && !this.fhasta.equals(other.fhasta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.TusuarioPK[ cusuarioFk=" + cusuarioFk + ", fhasta=" + fhasta + " ]";
    }
    
}
