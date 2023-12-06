package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class EstadoLibro {
	String codigo;
	String descripcion;
	
	@Override
	public String toString() {
		return "EstadoLibro [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
