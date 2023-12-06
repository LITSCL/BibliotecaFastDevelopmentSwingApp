package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class VentaLibro {
	private int id;
	private int ventaFK;
	private int libroFK;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVentaFK() {
		return ventaFK;
	}
	public void setVentaFK(int ventaFK) {
		this.ventaFK = ventaFK;
	}
	public int getLibroFK() {
		return libroFK;
	}
	public void setLibroFK(int libroFK) {
		this.libroFK = libroFK;
	}
	
}
