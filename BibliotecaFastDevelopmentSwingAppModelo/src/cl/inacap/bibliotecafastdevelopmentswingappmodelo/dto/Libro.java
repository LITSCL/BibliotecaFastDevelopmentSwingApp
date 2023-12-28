package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Libro {
	int numeroDeSerie;
	String isbn;
	String titulo;
	int numeroDePaginas;
	double precioDeReferencia;
	String fechaDePublicacion;
	String estadoLibroFK;
	String editorialFK;

	public int getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(int numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public double getPrecioDeReferencia() {
		return precioDeReferencia;
	}

	public void setPrecioDeReferencia(double precioDeReferencia) {
		this.precioDeReferencia = precioDeReferencia;
	}

	public String getFechaDePublicacion() {
		return fechaDePublicacion;
	}

	public void setFechaDePublicacion(String fechaDePublicacion) {
		this.fechaDePublicacion = fechaDePublicacion;
	}

	public String getEstadoLibroFK() {
		return estadoLibroFK;
	}

	public void setEstadoLibroFK(String estadoLibroFK) {
		this.estadoLibroFK = estadoLibroFK;
	}

	public String getEditorialFK() {
		return editorialFK;
	}
	
	public void setEditorialFK(String editorialFK) {
		this.editorialFK = editorialFK;
	}

	@Override
	public String toString() {
		return titulo;
	}
}
