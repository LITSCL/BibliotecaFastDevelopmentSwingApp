package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Distribuidor {
	String rut;
	String nombre;
	String pais;
	String comuna;
	String calle;
	String numero;
	String telefono;
	String yearServicio;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getYearServicio() {
		return yearServicio;
	}
	
	public void setYearServicio(String yearServicio) {
		this.yearServicio = yearServicio;
	}

	@Override
	public String toString() {
		return "Distribuidor [rut=" + rut + ", nombre=" + nombre + ", pais=" + pais + ", comuna=" + comuna + ", calle="
			+ calle + ", numero=" + numero + ", telefono=" + telefono + ", yearServicio=" + yearServicio + "]";
	}
}