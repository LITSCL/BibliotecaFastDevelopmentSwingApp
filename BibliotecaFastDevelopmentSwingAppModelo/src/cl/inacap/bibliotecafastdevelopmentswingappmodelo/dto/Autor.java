package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Autor {
	String codigo;
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String fechaDeNacimiento;
	
	
	@Override
	public String toString() {
		return codigo;
	}
	public String toStringV2() {
		return "Autor [codigo=" + codigo + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", nomapellidoMaternobre=" + apellidoMaterno + ", fechaDeNacimiento=" + fechaDeNacimiento + "]";
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	

}
