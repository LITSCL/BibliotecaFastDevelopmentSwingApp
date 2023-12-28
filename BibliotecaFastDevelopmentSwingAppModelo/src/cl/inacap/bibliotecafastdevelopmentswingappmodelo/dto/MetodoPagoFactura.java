package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class MetodoPagoFactura {
	String codigo;
	String descripcion;

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

	@Override
	public String toString() {
		return "MetodoPagoFactura [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
}
