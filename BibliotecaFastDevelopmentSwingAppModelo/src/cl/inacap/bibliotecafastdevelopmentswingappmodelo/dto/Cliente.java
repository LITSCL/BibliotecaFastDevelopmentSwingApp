package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Cliente {
	String rut;
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String FechaDeNacimiento;

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

	public String getFechaDeNacimiento() {
		return FechaDeNacimiento;
	}
	
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		FechaDeNacimiento = fechaDeNacimiento;
	}

	@Override
	public String toString() {
		return "<" + rut + "> " + nombre + " " + apellidoPaterno + " " + apellidoMaterno;
	}
}
