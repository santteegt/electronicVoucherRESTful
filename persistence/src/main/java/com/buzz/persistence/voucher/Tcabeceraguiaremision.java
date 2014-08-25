/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buzz
 */
@Entity
@Table(name="TCABECERAGUIAREMISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcabeceraguiaremision.findAll", query = "SELECT t FROM Tcabeceraguiaremision t"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByCcgremision", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.ccgremision = :ccgremision"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByDireccionEstablecimiento", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.direccionEstablecimiento = :direccionEstablecimiento"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByDireccionPartida", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.direccionPartida = :direccionPartida"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByRazonSocialTransportista", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.razonSocialTransportista = :razonSocialTransportista"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByTipoIdTransportista", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.tipoIdTransportista = :tipoIdTransportista"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByRucTransportista", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.rucTransportista = :rucTransportista"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByRise", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.rise = :rise"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByObligadoContabilidad", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.obligadoContabilidad = :obligadoContabilidad"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByContribuyenteEspecial", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.contribuyenteEspecial = :contribuyenteEspecial"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByFinicioTransporte", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.finicioTransporte = :finicioTransporte"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByFfinTransporte", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.ffinTransporte = :ffinTransporte"),
    @NamedQuery(name = "Tcabeceraguiaremision.findByPlaca", query = "SELECT t FROM Tcabeceraguiaremision t WHERE t.placa = :placa")})
public class Tcabeceraguiaremision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer ccgremision;
    @Column(name = "direccion_establecimiento", length = 300)
    private String direccionEstablecimiento;
    @Basic(optional = false)
    @Column(name = "direccion_partida", nullable = false, length = 300)
    private String direccionPartida;
    @Basic(optional = false)
    @Column(name = "razon_social_transportista", nullable = false, length = 300)
    private String razonSocialTransportista;
    @Basic(optional = false)
    @Column(name = "tipo_id_transportista", nullable = false, length = 2)
    private String tipoIdTransportista;
    @Basic(optional = false)
    @Column(name = "ruc_transportista", nullable = false, length = 13)
    private String rucTransportista;
    @Column(length = 40)
    private String rise;
    @Column(name = "obligado_contabilidad", length = 2)
    private String obligadoContabilidad;
    @Column(name = "contribuyente_especial", length = 5)
    private String contribuyenteEspecial;
    @Basic(optional = false)
    @Column(name = "finicio_transporte", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finicioTransporte;
    @Basic(optional = false)
    @Column(name = "ffin_transporte", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ffinTransporte;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String placa;
    @JoinColumns({
        @JoinColumn(name = "ccontribuyente_fk2", referencedColumnName = "ccontribuyente_fk", nullable = false),
        @JoinColumn(name = "idpeticion_fk", referencedColumnName = "idpeticion", nullable = false)})
    @ManyToOne(optional = false)
    private Tcomprobante tcomprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccgremisionFk")
    private List<Tdestinatarioguiaremision> tdestinatarioguiaremisionList;

    public Tcabeceraguiaremision() {
    }

    public Tcabeceraguiaremision(Integer ccgremision) {
        this.ccgremision = ccgremision;
    }

    public Tcabeceraguiaremision(Integer ccgremision, String direccionPartida, String razonSocialTransportista, String tipoIdTransportista, String rucTransportista, Date finicioTransporte, Date ffinTransporte, String placa) {
        this.ccgremision = ccgremision;
        this.direccionPartida = direccionPartida;
        this.razonSocialTransportista = razonSocialTransportista;
        this.tipoIdTransportista = tipoIdTransportista;
        this.rucTransportista = rucTransportista;
        this.finicioTransporte = finicioTransporte;
        this.ffinTransporte = ffinTransporte;
        this.placa = placa;
    }

    public Integer getCcgremision() {
        return ccgremision;
    }

    public void setCcgremision(Integer ccgremision) {
        this.ccgremision = ccgremision;
    }

    public String getDireccionEstablecimiento() {
        return direccionEstablecimiento;
    }

    public void setDireccionEstablecimiento(String direccionEstablecimiento) {
        this.direccionEstablecimiento = direccionEstablecimiento;
    }

    public String getDireccionPartida() {
        return direccionPartida;
    }

    public void setDireccionPartida(String direccionPartida) {
        this.direccionPartida = direccionPartida;
    }

    public String getRazonSocialTransportista() {
        return razonSocialTransportista;
    }

    public void setRazonSocialTransportista(String razonSocialTransportista) {
        this.razonSocialTransportista = razonSocialTransportista;
    }

    public String getTipoIdTransportista() {
        return tipoIdTransportista;
    }

    public void setTipoIdTransportista(String tipoIdTransportista) {
        this.tipoIdTransportista = tipoIdTransportista;
    }

    public String getRucTransportista() {
        return rucTransportista;
    }

    public void setRucTransportista(String rucTransportista) {
        this.rucTransportista = rucTransportista;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getObligadoContabilidad() {
        return obligadoContabilidad;
    }

    public void setObligadoContabilidad(String obligadoContabilidad) {
        this.obligadoContabilidad = obligadoContabilidad;
    }

    public String getContribuyenteEspecial() {
        return contribuyenteEspecial;
    }

    public void setContribuyenteEspecial(String contribuyenteEspecial) {
        this.contribuyenteEspecial = contribuyenteEspecial;
    }

    public Date getFinicioTransporte() {
        return finicioTransporte;
    }

    public void setFinicioTransporte(Date finicioTransporte) {
        this.finicioTransporte = finicioTransporte;
    }

    public Date getFfinTransporte() {
        return ffinTransporte;
    }

    public void setFfinTransporte(Date ffinTransporte) {
        this.ffinTransporte = ffinTransporte;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Tcomprobante getTcomprobante() {
        return tcomprobante;
    }

    public void setTcomprobante(Tcomprobante tcomprobante) {
        this.tcomprobante = tcomprobante;
    }

    @XmlTransient
    public List<Tdestinatarioguiaremision> getTdestinatarioguiaremisionList() {
        return tdestinatarioguiaremisionList;
    }

    public void setTdestinatarioguiaremisionList(List<Tdestinatarioguiaremision> tdestinatarioguiaremisionList) {
        this.tdestinatarioguiaremisionList = tdestinatarioguiaremisionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccgremision != null ? ccgremision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcabeceraguiaremision)) {
            return false;
        }
        Tcabeceraguiaremision other = (Tcabeceraguiaremision) object;
        if ((this.ccgremision == null && other.ccgremision != null) || (this.ccgremision != null && !this.ccgremision.equals(other.ccgremision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcabeceraguiaremision[ ccgremision=" + ccgremision + " ]";
    }
    
}
