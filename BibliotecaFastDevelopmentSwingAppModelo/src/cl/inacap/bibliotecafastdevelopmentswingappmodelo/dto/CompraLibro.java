package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class CompraLibro {
	private int id;
	private int compraFK;
	private int libroFK;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompraFK() {
		return compraFK;
	}

	public void setCompraFK(int compraFK) {
		this.compraFK = compraFK;
	}

	public int getLibroFK() {
		return libroFK;
	}
	
	public void setLibroFK(int libroFK) {
		this.libroFK = libroFK;
	}
}
