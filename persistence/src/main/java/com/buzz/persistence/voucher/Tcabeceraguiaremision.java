package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TCABECERAGUIAREMISION database table.
 * 
 */
@Entity
@NamedQuery(name="Tcabeceraguiaremision.findAll", query="SELECT t FROM Tcabeceraguiaremision t")
public class Tcabeceraguiaremision implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ccgremision;

	@Column(name="contribuyente_especial")
	private String contribuyenteEspecial;

	@Column(name="direccion_establecimiento")
	private String direccionEstablecimiento;

	@Column(name="direccion_partida")
	private String direccionPartida;

	@Temporal(TemporalType.DATE)
	@Column(name="ffin_transporte")
	private Date ffinTransporte;

	@Temporal(TemporalType.DATE)
	@Column(name="finicio_transporte")
	private Date finicioTransporte;

	@Column(name="obligado_contabilidad")
	private String obligadoContabilidad;

	private String placa;

	@Column(name="razon_social_transportista")
	private String razonSocialTransportista;

	private String rise;

	@Column(name="ruc_transportista")
	private String rucTransportista;

	@Column(name="tipo_id_transportista")
	private String tipoIdTransportista;

	//bi-directional many-to-one association to Tcomprobante
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ccontribuyente_fk2", referencedColumnName="ccontribuyente_fk"),
		@JoinColumn(name="idpeticion_fk", referencedColumnName="idpeticion")
		})
	private Tcomprobante tcomprobante;

	//bi-directional many-to-one association to Tdestinatarioguiaremision
	@OneToMany(mappedBy="tcabeceraguiaremision")
	private List<Tdestinatarioguiaremision> tdestinatarioguiaremisions;

	public Tcabeceraguiaremision() {
	}

	public int getCcgremision() {
		return this.ccgremision;
	}

	public void setCcgremision(int ccgremision) {
		this.ccgremision = ccgremision;
	}

	public String getContribuyenteEspecial() {
		return this.contribuyenteEspecial;
	}

	public void setContribuyenteEspecial(String contribuyenteEspecial) {
		this.contribuyenteEspecial = contribuyenteEspecial;
	}

	public String getDireccionEstablecimiento() {
		return this.direccionEstablecimiento;
	}

	public void setDireccionEstablecimiento(String direccionEstablecimiento) {
		this.direccionEstablecimiento = direccionEstablecimiento;
	}

	public String getDireccionPartida() {
		return this.direccionPartida;
	}

	public void setDireccionPartida(String direccionPartida) {
		this.direccionPartida = direccionPartida;
	}

	public Date getFfinTransporte() {
		return this.ffinTransporte;
	}

	public void setFfinTransporte(Date ffinTransporte) {
		this.ffinTransporte = ffinTransporte;
	}

	public Date getFinicioTransporte() {
		return this.finicioTransporte;
	}

	public void setFinicioTransporte(Date finicioTransporte) {
		this.finicioTransporte = finicioTransporte;
	}

	public String getObligadoContabilidad() {
		return this.obligadoContabilidad;
	}

	public void setObligadoContabilidad(String obligadoContabilidad) {
		this.obligadoContabilidad = obligadoContabilidad;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRazonSocialTransportista() {
		return this.razonSocialTransportista;
	}

	public void setRazonSocialTransportista(String razonSocialTransportista) {
		this.razonSocialTransportista = razonSocialTransportista;
	}

	public String getRise() {
		return this.rise;
	}

	public void setRise(String rise) {
		this.rise = rise;
	}

	public String getRucTransportista() {
		return this.rucTransportista;
	}

	public void setRucTransportista(String rucTransportista) {
		this.rucTransportista = rucTransportista;
	}

	public String getTipoIdTransportista() {
		return this.tipoIdTransportista;
	}

	public void setTipoIdTransportista(String tipoIdTransportista) {
		this.tipoIdTransportista = tipoIdTransportista;
	}

	public Tcomprobante getTcomprobante() {
		return this.tcomprobante;
	}

	public void setTcomprobante(Tcomprobante tcomprobante) {
		this.tcomprobante = tcomprobante;
	}

	public List<Tdestinatarioguiaremision> getTdestinatarioguiaremisions() {
		return this.tdestinatarioguiaremisions;
	}

	public void setTdestinatarioguiaremisions(List<Tdestinatarioguiaremision> tdestinatarioguiaremisions) {
		this.tdestinatarioguiaremisions = tdestinatarioguiaremisions;
	}

	public Tdestinatarioguiaremision addTdestinatarioguiaremision(Tdestinatarioguiaremision tdestinatarioguiaremision) {
		getTdestinatarioguiaremisions().add(tdestinatarioguiaremision);
		tdestinatarioguiaremision.setTcabeceraguiaremision(this);

		return tdestinatarioguiaremision;
	}

	public Tdestinatarioguiaremision removeTdestinatarioguiaremision(Tdestinatarioguiaremision tdestinatarioguiaremision) {
		getTdestinatarioguiaremisions().remove(tdestinatarioguiaremision);
		tdestinatarioguiaremision.setTcabeceraguiaremision(null);

		return tdestinatarioguiaremision;
	}

}