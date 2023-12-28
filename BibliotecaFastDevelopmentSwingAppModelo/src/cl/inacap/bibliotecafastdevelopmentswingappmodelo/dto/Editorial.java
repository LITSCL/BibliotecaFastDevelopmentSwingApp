package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Editorial {
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
		return "Editorial [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
