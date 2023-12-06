package cl.inacap.bibliotecafastdevelopmentswingapp.graphic;

import java.awt.Color;
import java.awt.Graphics; //Esta clase permite trabajar con Gráficos 2D.

import javax.swing.JPanel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CompraLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.VentaLibroDAO;


public class PanelRelacionLibros extends JPanel {
	private Color colorBarraLibrosComprados = Color.green; //Este atributo indica el color que se utilizará para su respectiva barra.
	private Color colorBarraLibrosVendidos = Color.yellow;
	private CompraLibroDAO daoCompraLibro = new CompraLibroDAO();
	private VentaLibroDAO daoVentaLibro = new VentaLibroDAO();
	private int librosComprados = 0;
	private int librosVendidos = 0;
	private int alturaGraficoComprados = 0;
	private int alturaGraficoVendidos = 0;
	
	@Override
	public void paint(Graphics pintor) { //El objeto pintor permite dibujar en pantalla.
		super.paint(pintor);

		getDatosLibros();
		
		System.out.println("Pintando...");
		pintor.setColor(colorBarraLibrosComprados);
		pintor.fillRect(0, 332, 200, -(alturaGraficoComprados));
		
		pintor.setColor(colorBarraLibrosVendidos);
		pintor.fillRect(300, 332, 200, -(alturaGraficoVendidos));
		pintor.dispose();
	}
	
	public int[] getDatosLibros() {
		int[] datos = new int[4];
		
		this.librosComprados = daoCompraLibro.contarLibros();
		this.librosVendidos = daoVentaLibro.contarLibros();
			
		if (librosComprados >= 1 && librosVendidos >= 1) {
			this.alturaGraficoComprados = (librosComprados * 310) / (librosComprados + librosVendidos);
			this.alturaGraficoVendidos = (librosVendidos * 310) / (librosComprados + librosVendidos);
				
			datos[0] = this.alturaGraficoComprados;
			datos[1] = this.librosComprados;
			datos[2] = this.alturaGraficoVendidos;
			datos[3] = this.librosVendidos;
			return datos;
		}
		else if (librosComprados >= 1 && librosVendidos == 0) {
			this.alturaGraficoComprados = 310;
			this.alturaGraficoVendidos = 0;
			
			datos[0] = 310;
			datos[1] = this.librosComprados;
			datos[2] = 0;
			datos[3] = 0;
			return datos;
		}
		else {
			datos[0] = 0;
			datos[1] = 0;
			datos[2] = 0;
			datos[3] = 0;
			return datos;
		}						
	}	
}