package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class MetodoPagoBoleta {
	private String codigo;
	private String descripcion;
	
	@Override
	public String toString() {
		return "MetodoPagoBoleta [codigo=" + codigo + ", descripcion=" + descripcion + "]";
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
