package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Factura {
	String folio;
	double precioNeto;
	double precioConIVA;
	double costoIVA;
	String fechaDeCompra;
	String horaDeCompra;
	String metodoDePagoFK;
	String distribuidorFK;
	int compraFK;
	
	@Override
	public String toString() {
		return "Factura [folio=" + folio + ", precioNeto=" + precioNeto + ", precioConIVA=" + precioConIVA
				+ ", costoIVA=" + costoIVA + ", fechaDeCompra=" + fechaDeCompra + ", horaDeCompra=" + horaDeCompra
				+ ", metodoDePagoFK=" + metodoDePagoFK + ", distribuidorFK=" + distribuidorFK + "]";
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public double getPrecioNeto() {
		return precioNeto;
	}
	public void setPrecioNeto(double precioNeto) {
		this.precioNeto = precioNeto;
	}
	public double getPrecioConIVA() {
		return precioConIVA;
	}
	public void setPrecioConIVA(double precioConIVA) {
		this.precioConIVA = precioConIVA;
	}
	public double getCostoIVA() {
		return costoIVA;
	}
	public void setCostoIVA(double costoIVA) {
		this.costoIVA = costoIVA;
	}
	public String getFechaDeCompra() {
		return fechaDeCompra;
	}
	public void setFechaDeCompra(String fechaDeCompra) {
		this.fechaDeCompra = fechaDeCompra;
	}
	public String getHoraDeCompra() {
		return horaDeCompra;
	}
	public void setHoraDeCompra(String horaDeCompra) {
		this.horaDeCompra = horaDeCompra;
	}
	public String getMetodoDePagoFK() {
		return metodoDePagoFK;
	}
	public void setMetodoDePagoFK(String metodoDePagoFK) {
		this.metodoDePagoFK = metodoDePagoFK;
	}
	public String getDistribuidorFK() {
		return distribuidorFK;
	}
	public void setDistribuidorFK(String distribuidorFK) {
		this.distribuidorFK = distribuidorFK;
	}
	public int getCompraFK() {
		return compraFK;
	}
	public void setCompraFK(int compraFK) {
		this.compraFK = compraFK;
	}
}
