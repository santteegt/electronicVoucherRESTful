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
@Table(name = "TCABECERANOTACREDITODEBITO", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcabeceranotacreditodebito.findAll", query = "SELECT t FROM Tcabeceranotacreditodebito t"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByCcnotacd", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.ccnotacd = :ccnotacd"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByFechaEmision", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByDireccionEstablecimiento", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.direccionEstablecimiento = :direccionEstablecimiento"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByTipoIdComprador", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.tipoIdComprador = :tipoIdComprador"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByRazonSocialComprador", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.razonSocialComprador = :razonSocialComprador"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByIdentificacionComprador", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.identificacionComprador = :identificacionComprador"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByContribuyenteEspecial", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.contribuyenteEspecial = :contribuyenteEspecial"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByObligadoContabilidad", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.obligadoContabilidad = :obligadoContabilidad"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByRise", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.rise = :rise"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByCodigoDocModificado", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.codigoDocModificado = :codigoDocModificado"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByNumeroDocModificado", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.numeroDocModificado = :numeroDocModificado"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByFemisionDocSustento", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.femisionDocSustento = :femisionDocSustento"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByTotalSinImpuestos", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.totalSinImpuestos = :totalSinImpuestos"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByValorModificacion", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.valorModificacion = :valorModificacion"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByMoneda", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.moneda = :moneda"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByMotivo", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.motivo = :motivo"),
    @NamedQuery(name = "Tcabeceranotacreditodebito.findByValorTotal", query = "SELECT t FROM Tcabeceranotacreditodebito t WHERE t.valorTotal = :valorTotal")})
public class Tcabeceranotacreditodebito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ccnotacd", nullable = false)
    private Integer ccnotacd;
    @Basic(optional = false)
    @Column(name = "fecha_emision", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Column(name = "direccion_establecimiento", length = 300)
    private String direccionEstablecimiento;
    @Basic(optional = false)
    @Column(name = "tipo_id_comprador", nullable = false, length = 2)
    private String tipoIdComprador;
    @Basic(optional = false)
    @Column(name = "razon_social_comprador", nullable = false, length = 300)
    private String razonSocialComprador;
    @Basic(optional = false)
    @Column(name = "identificacion_comprador", nullable = false, length = 13)
    private String identificacionComprador;
    @Column(name = "contribuyente_especial", length = 5)
    private String contribuyenteEspecial;
    @Column(name = "obligado_contabilidad", length = 2)
    private String obligadoContabilidad;
    @Column(name = "rise", length = 40)
    private String rise;
    @Basic(optional = false)
    @Column(name = "codigo_doc_modificado", nullable = false, length = 2)
    private String codigoDocModificado;
    @Column(name = "numero_doc_modificado", length = 17)
    private String numeroDocModificado;
    @Basic(optional = false)
    @Column(name = "femision_doc_sustento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date femisionDocSustento;
    @Basic(optional = false)
    @Column(name = "total_sin_impuestos", nullable = false)
    private float totalSinImpuestos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_modificacion", precision = 14, scale = 2)
    private Float valorModificacion;
    @Column(name = "moneda", length = 15)
    private String moneda;
    @Column(name = "motivo", length = 300)
    private String motivo;
    @Column(name = "valor_total", precision = 14, scale = 2)
    private Float valorTotal;
    @JoinColumns({
        @JoinColumn(name = "ccontribuyente_fk2", referencedColumnName = "ccontribuyente_fk", nullable = false),
        @JoinColumn(name = "idpeticion_fk", referencedColumnName = "idpeticion", nullable = false)})
    @ManyToOne(optional = false)
    private Tcomprobante tcomprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccnotacdFk")
    private List<Tdetallenotacredito> tdetallenotacreditoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccnotacdFk")
    private List<Tcabeceranotacreditodebitoimpuesto> tcabeceranotacreditodebitoimpuestoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccnotacdFk")
    private List<Tdetallenotadebito> tdetallenotadebitoList;

    public Tcabeceranotacreditodebito() {
    }

    public Tcabeceranotacreditodebito(Integer ccnotacd) {
        this.ccnotacd = ccnotacd;
    }

    public Tcabeceranotacreditodebito(Integer ccnotacd, Date fechaEmision, String tipoIdComprador, String razonSocialComprador, String identificacionComprador, String codigoDocModificado, Date femisionDocSustento, float totalSinImpuestos) {
        this.ccnotacd = ccnotacd;
        this.fechaEmision = fechaEmision;
        this.tipoIdComprador = tipoIdComprador;
        this.razonSocialComprador = razonSocialComprador;
        this.identificacionComprador = identificacionComprador;
        this.codigoDocModificado = codigoDocModificado;
        this.femisionDocSustento = femisionDocSustento;
        this.totalSinImpuestos = totalSinImpuestos;
    }

    public Integer getCcnotacd() {
        return ccnotacd;
    }

    public void setCcnotacd(Integer ccnotacd) {
        this.ccnotacd = ccnotacd;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDireccionEstablecimiento() {
        return direccionEstablecimiento;
    }

    public void setDireccionEstablecimiento(String direccionEstablecimiento) {
        this.direccionEstablecimiento = direccionEstablecimiento;
    }

    public String getTipoIdComprador() {
        return tipoIdComprador;
    }

    public void setTipoIdComprador(String tipoIdComprador) {
        this.tipoIdComprador = tipoIdComprador;
    }

    public String getRazonSocialComprador() {
        return razonSocialComprador;
    }

    public void setRazonSocialComprador(String razonSocialComprador) {
        this.razonSocialComprador = razonSocialComprador;
    }

    public String getIdentificacionComprador() {
        return identificacionComprador;
    }

    public void setIdentificacionComprador(String identificacionComprador) {
        this.identificacionComprador = identificacionComprador;
    }

    public String getContribuyenteEspecial() {
        return contribuyenteEspecial;
    }

    public void setContribuyenteEspecial(String contribuyenteEspecial) {
        this.contribuyenteEspecial = contribuyenteEspecial;
    }

    public String getObligadoContabilidad() {
        return obligadoContabilidad;
    }

    public void setObligadoContabilidad(String obligadoContabilidad) {
        this.obligadoContabilidad = obligadoContabilidad;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getCodigoDocModificado() {
        return codigoDocModificado;
    }

    public void setCodigoDocModificado(String codigoDocModificado) {
        this.codigoDocModificado = codigoDocModificado;
    }

    public String getNumeroDocModificado() {
        return numeroDocModificado;
    }

    public void setNumeroDocModificado(String numeroDocModificado) {
        this.numeroDocModificado = numeroDocModificado;
    }

    public Date getFemisionDocSustento() {
        return femisionDocSustento;
    }

    public void setFemisionDocSustento(Date femisionDocSustento) {
        this.femisionDocSustento = femisionDocSustento;
    }

    public float getTotalSinImpuestos() {
        return totalSinImpuestos;
    }

    public void setTotalSinImpuestos(float totalSinImpuestos) {
        this.totalSinImpuestos = totalSinImpuestos;
    }

    public Float getValorModificacion() {
        return valorModificacion;
    }

    public void setValorModificacion(Float valorModificacion) {
        this.valorModificacion = valorModificacion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Tcomprobante getTcomprobante() {
        return tcomprobante;
    }

    public void setTcomprobante(Tcomprobante tcomprobante) {
        this.tcomprobante = tcomprobante;
    }

    @XmlTransient
    public List<Tdetallenotacredito> getTdetallenotacreditoList() {
        return tdetallenotacreditoList;
    }

    public void setTdetallenotacreditoList(List<Tdetallenotacredito> tdetallenotacreditoList) {
        this.tdetallenotacreditoList = tdetallenotacreditoList;
    }

    @XmlTransient
    public List<Tcabeceranotacreditodebitoimpuesto> getTcabeceranotacreditodebitoimpuestoList() {
        return tcabeceranotacreditodebitoimpuestoList;
    }

    public void setTcabeceranotacreditodebitoimpuestoList(List<Tcabeceranotacreditodebitoimpuesto> tcabeceranotacreditodebitoimpuestoList) {
        this.tcabeceranotacreditodebitoimpuestoList = tcabeceranotacreditodebitoimpuestoList;
    }

    @XmlTransient
    public List<Tdetallenotadebito> getTdetallenotadebitoList() {
        return tdetallenotadebitoList;
    }

    public void setTdetallenotadebitoList(List<Tdetallenotadebito> tdetallenotadebitoList) {
        this.tdetallenotadebitoList = tdetallenotadebitoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccnotacd != null ? ccnotacd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcabeceranotacreditodebito)) {
            return false;
        }
        Tcabeceranotacreditodebito other = (Tcabeceranotacreditodebito) object;
        if ((this.ccnotacd == null && other.ccnotacd != null) || (this.ccnotacd != null && !this.ccnotacd.equals(other.ccnotacd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tcabeceranotacreditodebito[ ccnotacd=" + ccnotacd + " ]";
    }
    
}
