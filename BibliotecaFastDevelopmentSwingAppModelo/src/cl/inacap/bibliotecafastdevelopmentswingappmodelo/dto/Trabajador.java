package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Trabajador {
	String rut;
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String fechaDeContrato;

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

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getFechaDeContrato() {
		return fechaDeContrato;
	}
	
	public void setFechaDeContrato(String fechaDeContrato) {
		this.fechaDeContrato = fechaDeContrato;
	}

	@Override
	public String toString() {
		return "<" + rut + "> " + nombre + " " + apellidoPaterno + " " + apellidoMaterno + " " + fechaDeContrato;
	}
}
