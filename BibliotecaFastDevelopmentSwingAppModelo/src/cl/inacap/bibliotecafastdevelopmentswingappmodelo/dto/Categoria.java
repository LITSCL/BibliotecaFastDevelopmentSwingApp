package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Categoria {
	String codigo;
	String nombre;

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

	@Override
	public String toString() {
		return nombre;
	}

	public String toStringV2() {
		return "Categoria [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
