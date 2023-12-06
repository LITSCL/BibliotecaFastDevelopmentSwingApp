package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.BoletaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CompraLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.FacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.LibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.VentaLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Boleta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.CompraLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Factura;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.VentaLibro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import javax.swing.JList;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarFolioBoletaFrame extends JInternalFrame {
	private JTextField textFieldBoleta;
	private JTable tableBoleta;
	private JList listLibrosVendidos;
	private List<Libro> libros = new ArrayList<Libro>();
	private DefaultListModel moLibrosVendidos = new DefaultListModel();
	private JScrollPane scrollPane;
	private DefaultTableModel mo = new DefaultTableModel() {
		public boolean isCellEditable(int fila, int columna) {
			if (columna == 10) {
				return true;
			}
			else {
				return false;
			}
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarFolioFacturaFrame frame = new BuscarFolioFacturaFrame();
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
	public BuscarFolioBoletaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Boleta> boletas = new BoletaDAO().getAll();
				libros = new LibroDAO().getAll();
				
				String nombreColumnas[] = {"Folio", "Precio neto", "Precio con IVA", "Costo IVA", "Fecha de venta", "Hora de venta", "M�todo de pago", "Cliente", "Trabajador", "Venta"};
				
				for (int i = 0; i < nombreColumnas.length; i++) {
					mo.addColumn(nombreColumnas[i]);
					
				}
				
				for (Boleta bo : boletas) {
					Object fila[] = new Object[10];
					
					fila[0] = bo.getFolio();
					fila[1] = bo.getPrecioNeto();
					fila[2] = bo.getPrecioConIVA();
					fila[3] = bo.getCostoIVA();
					fila[4] = bo.getFechaDeVenta();
					fila[5] = bo.getHoraDeVenta();
					fila[6] = bo.getMetodoDePagoBoletaFK();
					fila[7] = bo.getClienteFK();
					fila[8] = bo.getTrabajadorFK();
					fila[9] = bo.getVentaFK();
					
					mo.addRow(fila);
					
				}
				tableBoleta.setModel(mo);		
			}
		});
		setTitle("Buscar Boleta");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Folio");
		lblNewLabel.setBounds(10, 33, 32, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldBoleta = new JTextField();
		textFieldBoleta.setBounds(38, 30, 86, 20);
		getContentPane().add(textFieldBoleta);
		textFieldBoleta.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarFolioBoletaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarBoleta(e));
		btnBuscar.setBounds(134, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 200);
		getContentPane().add(scrollPane);
		
		tableBoleta = new JTable();
		tableBoleta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableBoleta.getSelectedRows().length > 0 ) {
					moLibrosVendidos.removeAllElements(); //Aca se esta limpiando la lista.
					int filaSeleccionada = tableBoleta.getSelectedRow();
					int ventaFKDeLaBoleta = Integer.parseInt(tableBoleta.getValueAt(filaSeleccionada, 9).toString());
					
					VentaLibroDAO daoVentaLibro = new VentaLibroDAO();
					List<VentaLibro> librosVendidos = new ArrayList<VentaLibro>();
					librosVendidos = daoVentaLibro.filtrarVentaLibro(ventaFKDeLaBoleta, "venta_id");
					
					for (VentaLibro vl : librosVendidos) {
						for (Libro li : libros) {
							if (vl.getLibroFK()==li.getNumeroDeSerie()) {
								moLibrosVendidos.addElement(li.getTitulo());
							}
						}
					}
					listLibrosVendidos.setModel(moLibrosVendidos);
				}
			}
		});
		scrollPane.setViewportView(tableBoleta);
		
		JLabel lblNewLabel_1 = new JLabel("Libros vendidos al cliente");
		lblNewLabel_1.setBounds(10, 269, 213, 14);
		getContentPane().add(lblNewLabel_1);
		
		listLibrosVendidos = new JList();
		listLibrosVendidos.setBounds(206, 269, 135, 135);
		getContentPane().add(listLibrosVendidos);
	}

	private void buscarBoleta(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		mo.setRowCount(0); //Esta l�nea borra todas las filas, no es necesario a�adirle nuevamente las columnas ya que simplemente borramos las filas.
		
		String clavePrimariaBoleta = this.textFieldBoleta.getText();
		List<Boleta> boleta = new BoletaDAO().filtrarBoleta(clavePrimariaBoleta);
		
		try {
		
			Object []fila = new Object[9];
			
			fila[0] = boleta.get(0).getFolio();
			fila[1] = boleta.get(0).getPrecioNeto();
			fila[2] = boleta.get(0).getPrecioConIVA();
			fila[3] = boleta.get(0).getCostoIVA();
			fila[4] = boleta.get(0).getFechaDeVenta();
			fila[5] = boleta.get(0).getHoraDeVenta();
			fila[6] = boleta.get(0).getMetodoDePagoBoletaFK();
			fila[7] = boleta.get(0).getClienteFK();
			fila[8] = boleta.get(0).getTrabajadorFK();
			fila[9] = boleta.get(0).getVentaFK();
			
			
			mo.addRow(fila);
			tableBoleta.setModel(mo);
			
			moLibrosVendidos.removeAllElements(); //Aca se esta limpiando la lista.
			int filaSeleccionada = tableBoleta.getSelectedRow();
			int compraFKDeLaBoleta = Integer.parseInt(fila[9].toString());
			
			VentaLibroDAO daoVentaLibro = new VentaLibroDAO();
			
			//La lista de libros comprados se debe traer completa en el Opened y se debe hacer el filtrado con Java (De esta forma el JList se llenar� mas r�pido).
			List<VentaLibro> librosVendidos = new ArrayList<VentaLibro>();
			librosVendidos = daoVentaLibro.filtrarVentaLibro(compraFKDeLaBoleta, "venta_id");
			
			for (VentaLibro cl : librosVendidos) {
				for (Libro li : libros) {
					if (cl.getLibroFK() == li.getNumeroDeSerie()) {
						moLibrosVendidos.addElement(li.getTitulo());
					}
				}
			}
			listLibrosVendidos.setModel(moLibrosVendidos);
				
		} catch (Exception ex) {
			moLibrosVendidos.clear();
			listLibrosVendidos.setModel(moLibrosVendidos);
			JOptionPane.showMessageDialog(null, "La boleta no existe en el sistema");	
		}
	}
	
}

