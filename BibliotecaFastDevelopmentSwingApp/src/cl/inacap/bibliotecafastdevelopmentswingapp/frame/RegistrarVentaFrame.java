package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.ClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.LibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.VentaLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.VentaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Venta;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarVentaFrame extends JInternalFrame {
	private JComboBox<Trabajador> comboBoxTrabajador;
	private JComboBox<Cliente> comboBoxCliente;
	private JList listLibrosDisponibles;
	private JList <Libro> listCarrito;
	private List<Libro> libros;
	private DefaultListModel moLibros = new DefaultListModel();
	private DefaultListModel moLibrosCarrito = new DefaultListModel();
	EstadoLibro estadoConDescripcionDisponible = null;
	EstadoLibro estadoConDescripcionVendido = null;
	private JLabel lblPrecio;
	private JLabel lblTotal;
	private double precio = 0;
	List<Libro> librosCarrito = new ArrayList<Libro>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarVentaFrame frame = new RegistrarVentaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrarVentaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Trabajador> trabajador = new TrabajadorDAO().getAll();
				List<Cliente> clientes = new ClienteDAO().getAll();
				LibroDAO daoLibro = new LibroDAO();
				libros = daoLibro.getAll();
				List<EstadoLibro> estadoLibros = new EstadoLibroDAO().getAll();
				
				for (Trabajador tr : trabajador) {
					comboBoxTrabajador.addItem(tr);
				}
				
				for (Cliente cl : clientes) {
					comboBoxCliente.addItem(cl);
				}
				boolean existeEstadoVendido = false;
				for (EstadoLibro el : estadoLibros) {
					if (el.getDescripcion().equals("Vendido")) {
						estadoConDescripcionVendido = el;
						existeEstadoVendido = true;
					}
				}
				if (existeEstadoVendido == false) {
					JOptionPane.showMessageDialog(null, "El estado con descripci�n 'Vendido' no existe, por lo cual no se puede efectuar una venta");
					dispose();
				}
				for (EstadoLibro el : estadoLibros) {
					if (el.getDescripcion().equalsIgnoreCase("Disponible")) {
						estadoConDescripcionDisponible = el;
						break;
					}
				}
				
				for (int i = 0; i < libros.size(); i++) {
					if (libros.get(i).getEstadoLibroFK().equals(estadoConDescripcionDisponible.getCodigo())) {
						moLibros.addElement(libros.get(i));
					}
					
				}
				listLibrosDisponibles.setModel(moLibros);		
			}
		});
		setTitle("Vender Libro");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblTrabajador = new JLabel("Trabajador");
		lblTrabajador.setBounds(10, 33, 107, 14);
		getContentPane().add(lblTrabajador);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 58, 56, 14);
		getContentPane().add(lblCliente);
		
		JButton btnVender = new JButton("Vender");
		btnVender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnVender.setIcon(new ImageIcon(RegistrarVentaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnVender.addActionListener(e -> venderLibro(e));
		btnVender.setBounds(559, 381, 115, 23);
		getContentPane().add(btnVender);
		
		comboBoxCliente = new JComboBox<Cliente>();
		comboBoxCliente.setBounds(76, 55, 280, 20);
		getContentPane().add(comboBoxCliente);
		
		comboBoxTrabajador = new JComboBox<Trabajador>();
		comboBoxTrabajador.setBounds(76, 30, 280, 20);
		getContentPane().add(comboBoxTrabajador);
		
		JLabel lblNewLabel = new JLabel("Libros disponibles");
		lblNewLabel.setBounds(10, 83, 129, 14);
		getContentPane().add(lblNewLabel);
		
		listLibrosDisponibles = new JList();
		listLibrosDisponibles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (listLibrosDisponibles.getSelectedValue() != null) {
					JList listLibrosDisponibles = (JList)arg0.getSource(); //Es necesario para detectar el doble click.
					if (arg0.getClickCount() == 2) { //Si se hace doble click en el modelo se ejecuta el c�digo de adentro.
						Libro libroSeleccionado = (Libro)listLibrosDisponibles.getSelectedValue();
						moLibrosCarrito.addElement(libroSeleccionado);
						moLibros.removeElement(libroSeleccionado);
						listLibrosDisponibles.setModel(moLibros);
						listCarrito.setModel(moLibrosCarrito);
						
						librosCarrito.clear();
						for (int i = 0; i < moLibrosCarrito.size(); i++) {
							librosCarrito.add((Libro)(moLibrosCarrito.getElementAt(i)));
						}
						precio = 0;
						for (Libro li : librosCarrito) {
							precio = precio+li.getPrecioDeReferencia();
						}
						lblPrecio.setText("Precio " + Double.toString(precio));
						lblTotal.setText("Total " + Double.toString(precio * 1.19));
					}
				}
			}
		});
		listLibrosDisponibles.setBounds(10, 108, 175, 175);
		getContentPane().add(listLibrosDisponibles);
		
		JLabel lblNewLabel_1 = new JLabel("Carrito");
		lblNewLabel_1.setBounds(212, 83, 66, 14);
		getContentPane().add(lblNewLabel_1);
		
		listCarrito = new JList();
		listCarrito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (listCarrito.getSelectedValue() != null) {
					JList listCarrito = (JList)e.getSource(); //Es necesario para detectar el doble click.
					if (e.getClickCount() == 2) { //Si se hace doble click en el modelo se ejecuta el c�digo de adentro.
						Libro libroSeleccionado=(Libro)listCarrito.getSelectedValue();
						moLibros.addElement(libroSeleccionado);
						moLibrosCarrito.removeElement(libroSeleccionado);
						listLibrosDisponibles.setModel(moLibros);
						listCarrito.setModel(moLibrosCarrito);

						librosCarrito.clear();
						for (int i = 0; i < moLibrosCarrito.size(); i++) {
							librosCarrito.add((Libro)(moLibrosCarrito.getElementAt(i)));
						}
						precio = 0;
						for (Libro li : librosCarrito) {
							precio=precio+li.getPrecioDeReferencia();
						}
						lblPrecio.setText("Precio " + Double.toString(precio));
						lblTotal.setText("Total " + Double.toString(precio * 1.19));
					}	
				}

			}		
		});
		
		listCarrito.setBounds(212, 108, 175, 175);
		getContentPane().add(listCarrito);
		
		JLabel lblNewLabel_2 = new JLabel("IVA: 19%");
		lblNewLabel_2.setBounds(212, 294, 66, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblPrecio = new JLabel("Precio: " + precio);
		lblPrecio.setBounds(212, 319, 462, 14);
		getContentPane().add(lblPrecio);
		
		lblTotal = new JLabel("Total: "+precio*1.19);
		lblTotal.setBounds(212, 344, 462, 14);
		getContentPane().add(lblTotal);

	}

	private void venderLibro(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		if (precio == 0) {
			errores.add("El carrito esta vac�o");
		}
		Trabajador trabajadorSeleccionado = (Trabajador)this.comboBoxTrabajador.getSelectedItem();
		Cliente ClienteSeleccionado = (Cliente)this.comboBoxCliente.getSelectedItem();
		
		String rutTrabajador = trabajadorSeleccionado.getRut();
		String rutcliente = ClienteSeleccionado.getRut();
		
		if (errores.isEmpty()) {
			Venta ve = new Venta();
			ve.setTrabajadorFK(rutTrabajador);
			ve.setClienteFK(rutcliente);
			ve.setPrecio(precio);
			
			VentaDAO daoVenta = new VentaDAO();
			daoVenta.save(ve);
			
			List<Venta> ventas = new ArrayList<Venta>();
			ventas = daoVenta.getAll();
			
			int contador = 0;
			for (int i = 0; i < ventas.size(); i++) {
				contador++;
			}
			
			VentaLibroDAO daoVentaLibro = new VentaLibroDAO();
			daoVentaLibro.save(librosCarrito, contador, estadoConDescripcionVendido.getCodigo());
			JOptionPane.showMessageDialog(null, "Venta realizada con exito");
			dispose();
		}
		else {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
}
