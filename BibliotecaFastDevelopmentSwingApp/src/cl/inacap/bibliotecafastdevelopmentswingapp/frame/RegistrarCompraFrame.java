package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CompraLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CompraDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.LibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Compra;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarCompraFrame extends JInternalFrame {
	private List<Libro> libros = new ArrayList<Libro>();
	private List<EstadoLibro> estadoLibros = new ArrayList<EstadoLibro>();
	private JList listLibrosDistribuidor;
	private JList listLibrosCarrito;
	private DefaultListModel moLibrosDistribuidor = new DefaultListModel();
	private DefaultListModel moLibrosCarrito = new DefaultListModel();
	private double precio;
	ListModel lmoLibros;
	private JLabel lblTotal;
	private List<Libro> librosComprados = new ArrayList<Libro>();
	private JLabel lblPrecio;
	EstadoLibro estadoConDescripcionNoDisponible = null;
	EstadoLibro estadoConDescripcionDisponible = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarCompraFrame frame = new RegistrarCompraFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getPrecio() {


	}
	/**
	 * Create the frame.
	 */
	public RegistrarCompraFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				estadoLibros = new EstadoLibroDAO().getAll();
				libros = new LibroDAO().getAll();
				
				boolean disponible = false;
				
				for (EstadoLibro el : estadoLibros) {
					if (el.getDescripcion().equalsIgnoreCase("No disponible")) {
						estadoConDescripcionNoDisponible = el;
						disponible = true;
						break;
					}
				}
				if (disponible == false) {
					JOptionPane.showMessageDialog(null, "El estado con descripci�n 'No disponible' no existe, por lo cual no se puede efectuar una compra");
					dispose();
				}
				
				disponible = false;
				for (EstadoLibro el : estadoLibros) {
					if (el.getDescripcion().equalsIgnoreCase("Disponible")) {
						estadoConDescripcionDisponible = el;
						disponible = true;
						break;
					}
				}
				
				if (disponible == false) {
					JOptionPane.showMessageDialog(null, "El estado con descripci�n 'Disponible' no existe, por lo cual no se puede efectuar una compra");
					dispose();
				}
				
				for (Libro li : libros) {
					if (li.getEstadoLibroFK().equals(estadoConDescripcionNoDisponible.getCodigo()) && estadoConDescripcionNoDisponible.getDescripcion().equalsIgnoreCase("No disponible")) {
						moLibrosDistribuidor.addElement(li);
					}
				}
				listLibrosDistribuidor.setModel(moLibrosDistribuidor);
			}
		});
		setTitle("Comprar Libro");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Libros disponibles de los distribuidores");
		lblNewLabel_1.setBounds(10, 33, 239, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnComprar.setIcon(new ImageIcon(RegistrarCompraFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnComprar.addActionListener(e -> comprar(e));
		btnComprar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnComprar);
		
		listLibrosDistribuidor = new JList();
		listLibrosDistribuidor.setBounds(10, 58, 239, 220);
		getContentPane().add(listLibrosDistribuidor);
		
		JLabel lblNewLabel_2 = new JLabel("Carrito");
		lblNewLabel_2.setBounds(435, 33, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		listLibrosCarrito = new JList();
		listLibrosCarrito.setBounds(435, 58, 239, 220);
		getContentPane().add(listLibrosCarrito);
		
		JButton btnPasarAlCarrito = new JButton(">");
		btnPasarAlCarrito.addActionListener(e -> pasarAlCarrito(e));
		btnPasarAlCarrito.setBounds(300, 137, 89, 23);
		getContentPane().add(btnPasarAlCarrito);
		
		JButton btnRemoverDelCarrito = new JButton("<");
		btnRemoverDelCarrito.addActionListener(e -> removerDelCarrito(e));
		btnRemoverDelCarrito.setBounds(300, 171, 89, 23);
		getContentPane().add(btnRemoverDelCarrito);
		
		JLabel lblNewLabel = new JLabel("IVA: 19%");
		lblNewLabel.setBounds(435, 289, 57, 14);
		getContentPane().add(lblNewLabel);
		
		lblPrecio = new JLabel("Precio: " + precio);
		lblPrecio.setBounds(435, 314, 239, 14);
		getContentPane().add(lblPrecio);
		
		lblTotal = new JLabel("Total: " + precio * 1.19);
		lblTotal.setBounds(435, 339, 239, 14);
		getContentPane().add(lblTotal);
		

	}
	
	private void removerDelCarrito(ActionEvent e) {
		if (listLibrosCarrito.getSelectedValue() != null) {
			try {
				Libro libroSeleccionado = (Libro)listLibrosCarrito.getSelectedValue();
				moLibrosDistribuidor.addElement(libroSeleccionado);
				listLibrosDistribuidor.setModel(moLibrosDistribuidor);
				moLibrosCarrito.removeElement(libroSeleccionado);
				listLibrosCarrito.setModel(moLibrosCarrito);
				
				lmoLibros=listLibrosCarrito.getModel();
				
				List<Libro> librosSeleccionados = new ArrayList<Libro>();
				for (int i = 0; i < lmoLibros.getSize(); i++) {
					librosSeleccionados.add((Libro)lmoLibros.getElementAt(i));
				}
				librosComprados.clear();
				precio = 0;
				for (Libro li : librosSeleccionados) {
					precio = precio-li.getPrecioDeReferencia();
					librosComprados.add(li);
				}
						
				//Arreglar esto por que no deber�a ser necesario este condicional.
				if (precio < 0) {
					precio = precio * -1;
				}
				lblPrecio.setText("Precio: " + Double.toString(precio));
				lblTotal.setText("Total: " + Double.toString(precio * 1.19));
			} catch (Exception ex) {

			}
		}

		
	}

	private void pasarAlCarrito(ActionEvent e) {
		if (listLibrosDistribuidor.getSelectedValue() != null) {
			try {
				Libro libroSeleccionado = (Libro)listLibrosDistribuidor.getSelectedValue();
				moLibrosCarrito.addElement(libroSeleccionado);
				listLibrosCarrito.setModel(moLibrosCarrito);
				moLibrosDistribuidor.removeElement(libroSeleccionado);
				listLibrosDistribuidor.setModel(moLibrosDistribuidor);
				
				lmoLibros = listLibrosCarrito.getModel();
				List<Libro> librosSeleccionados = new ArrayList<Libro>();
				for (int i = 0;i < lmoLibros.getSize(); i++) {
					librosSeleccionados.add((Libro)lmoLibros.getElementAt(i));
				}
				precio = 0;
				librosComprados.clear();
				for (Libro li : librosSeleccionados) {
					precio += li.getPrecioDeReferencia();
					librosComprados.add(li);
				}	
				lblPrecio.setText("Precio: " + Double.toString(precio));
				lblTotal.setText("Total: " + Double.toString(precio * 1.19));
			} catch (Exception ex) {
				
			}
		}	
	}

	private void comprar(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		if (precio == 0) {
			errores.add("El carrito esta vac�o");
		}
			
		if (errores.isEmpty()) {
			CompraDAO daoCompra = new CompraDAO();
			daoCompra.save(precio);
			
			List<Compra> compras = daoCompra.getAll();
			
			int comprasRealizadas = compras.size();
			
			CompraLibroDAO daoCompraLibro = new CompraLibroDAO();
			daoCompraLibro.save(librosComprados, comprasRealizadas, estadoConDescripcionDisponible.getCodigo());
			JOptionPane.showMessageDialog(null, "Compra realizada con exito");
			dispose();
		}
		else {
			String mensaje = "";
			for(int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
}
