package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Boleta {
	private String folio;
	private double precioNeto;
	private double precioConIVA;
	private double costoIVA;
	private String fechaDeVenta;
	private String horaDeVenta;
	private String trabajadorFK;
	private String metodoDePagoBoletaFK;
	private String clienteFK;
	private int ventaFK;

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

	public String getFechaDeVenta() {
		return fechaDeVenta;
	}

	public void setFechaDeVenta(String fechaDeVenta) {
		this.fechaDeVenta = fechaDeVenta;
	}

	public String getHoraDeVenta() {
		return horaDeVenta;
	}

	public void setHoraDeVenta(String horaDeVenta) {
		this.horaDeVenta = horaDeVenta;
	}

	public String getTrabajadorFK() {
		return trabajadorFK;
	}

	public void setTrabajadorFK(String trabajadorFK) {
		this.trabajadorFK = trabajadorFK;
	}

	public String getMetodoDePagoBoletaFK() {
		return metodoDePagoBoletaFK;
	}

	public void setMetodoDePagoBoletaFK(String metodoDePagoBoletaFK) {
		this.metodoDePagoBoletaFK = metodoDePagoBoletaFK;
	}

	public String getClienteFK() {
		return clienteFK;
	}

	public void setClienteFK(String clienteFK) {
		this.clienteFK = clienteFK;
	}

	public int getVentaFK() {
		return ventaFK;
	}
	
	public void setVentaFK(int ventaFK) {
		this.ventaFK = ventaFK;
	}

	@Override
	public String toString() {
		return "Boleta [folio=" + folio + ", precioNeto=" + precioNeto + ", precioConIVA=" + precioConIVA
			+ ", costoIVA=" + costoIVA + ", fechaDeVenta=" + fechaDeVenta + ", horaDeVenta=" + horaDeVenta
			+ ", trabajadorFK=" + trabajadorFK + ", metodoDePagoBoletaFK=" + metodoDePagoBoletaFK + ", clienteFK="
			+ clienteFK + "]";
	}
}
