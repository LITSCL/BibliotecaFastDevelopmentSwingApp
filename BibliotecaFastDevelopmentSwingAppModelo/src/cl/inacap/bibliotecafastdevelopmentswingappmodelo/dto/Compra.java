package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

/**
 * Esta clase permite crear instancias de tipo Compra.
 * @author MelchioT
 *
 */
public class Compra {
	private int id;
	private double precio;
	private int libroFK;
	
	@Override
	public String toString() {
		return Integer.toString(this.id);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getLibroFK() {
		return libroFK;
	}
	public void setLibroFK(int libroFK) {
		this.libroFK = libroFK;
	}
	
}
