package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class Venta {
	private int id;
	private double precio;
	private String trabajadorFK;
	private String clienteFK;

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

	public String getTrabajadorFK() {
		return trabajadorFK;
	}

	public void setTrabajadorFK(String trabajadorFK) {
		this.trabajadorFK = trabajadorFK;
	}

	public String getClienteFK() {
		return clienteFK;
	}
	
	public void setClienteFK(String clienteFK) {
		this.clienteFK = clienteFK;
	}

	@Override
	public String toString() {
		String idString = Integer.toString(this.id);
		return idString;
	}
}
