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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CompraLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.FacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.LibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.CompraLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Factura;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import javax.swing.JList;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarFolioFacturaFrame extends JInternalFrame {
	private JTextField textFieldFactura;
	private JTable tableFactura;
	private JList listLibrosComprados;
	private List<Libro> libros=new ArrayList<Libro>();
	private DefaultListModel moLibrosComprados = new DefaultListModel();
	private JScrollPane scrollPane;
	private DefaultTableModel mo = new DefaultTableModel() {
		public boolean isCellEditable(int fila, int columna) {
			if (columna == 9) {
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
	public BuscarFolioFacturaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Factura> facturas = new FacturaDAO().getAll();
				libros = new LibroDAO().getAll();
				
				String nombreColumnas[] = {"Folio", "Precio neto", "Precio con IVA", "Costo IVA", "Fecha de venta", "Hora de venta", "Método de pago", "Distribuidor", "Compra"};
				
				for (int i = 0; i < nombreColumnas.length; i++) {
					mo.addColumn(nombreColumnas[i]);
					
				}
				
				for (Factura fa : facturas) {
					Object fila[] = new Object[9];
					
					fila[0] = fa.getFolio();
					fila[1] = fa.getPrecioNeto();
					fila[2] = fa.getPrecioConIVA();
					fila[3] = fa.getCostoIVA();
					fila[4] = fa.getFechaDeCompra();
					fila[5] = fa.getHoraDeCompra();
					fila[6] = fa.getMetodoDePagoFK();
					fila[7] = fa.getDistribuidorFK();
					fila[8] = fa.getCompraFK();
					
					mo.addRow(fila);
					
				}
				tableFactura.setModel(mo);		
			}
		});
		setTitle("Buscar Factura");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Folio");
		lblNewLabel.setBounds(10, 33, 32, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldFactura = new JTextField();
		textFieldFactura.setBounds(38, 30, 86, 20);
		getContentPane().add(textFieldFactura);
		textFieldFactura.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarFolioFacturaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarFactura(e));
		btnBuscar.setBounds(134, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 200);
		getContentPane().add(scrollPane);
		
		tableFactura = new JTable();
		tableFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableFactura.getSelectedRows().length > 0 ) {
					moLibrosComprados.removeAllElements(); //Aca se esta limpiando la lista.
					int filaSeleccionada = tableFactura.getSelectedRow();
					int compraFKDeLaFactura = Integer.parseInt(tableFactura.getValueAt(filaSeleccionada, 8).toString());
					
					CompraLibroDAO daoCompraLibro = new CompraLibroDAO();
					List<CompraLibro> librosComprados = new ArrayList<CompraLibro>();
					librosComprados = daoCompraLibro.filtrarCompraLibro(compraFKDeLaFactura, "compra_id");
					
					for (CompraLibro cl : librosComprados) {
						for (Libro li : libros) {
							if (cl.getLibroFK() == li.getNumeroDeSerie()) {
								moLibrosComprados.addElement(li.getTitulo());
							}
						}
					}
					listLibrosComprados.setModel(moLibrosComprados);
				}
			}
		});
		scrollPane.setViewportView(tableFactura);
		
		JLabel lblNewLabel_1 = new JLabel("Libros comprados al distribuidor");
		lblNewLabel_1.setBounds(10, 269, 213, 14);
		getContentPane().add(lblNewLabel_1);
		
		listLibrosComprados = new JList();
		listLibrosComprados.setBounds(206, 269, 135, 135);
		getContentPane().add(listLibrosComprados);
	}

	private void buscarFactura(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		mo.setRowCount(0); //Esta línea borra todas las filas, no es necesario añadirle nuevamente las columnas ya que simplemente borramos las filas.
		
		String clavePrimariaFactura = this.textFieldFactura.getText();
		List<Factura> factura = new FacturaDAO().filtrarFactura(clavePrimariaFactura);
		
		try {
		
			Object []fila = new Object[10];
			
			fila[0] = factura.get(0).getFolio();
			fila[1] = factura.get(0).getPrecioNeto();
			fila[2] = factura.get(0).getPrecioConIVA();
			fila[3] = factura.get(0).getCostoIVA();
			fila[4] = factura.get(0).getFechaDeCompra();
			fila[5] = factura.get(0).getHoraDeCompra();
			fila[6] = factura.get(0).getMetodoDePagoFK();
			fila[7] = factura.get(0).getDistribuidorFK();
			fila[8] = factura.get(0).getCompraFK();
			
			
			mo.addRow(fila);
			tableFactura.setModel(mo);
			
			moLibrosComprados.removeAllElements(); //Aca se esta limpiando la lista.
			int filaSeleccionada = tableFactura.getSelectedRow();
			int compraFKDeLaFactura = Integer.parseInt(fila[8].toString());
			
			CompraLibroDAO daoCompraLibro = new CompraLibroDAO();
			
			//La lista de libros comprados se debe traer completa en el Opened y se debe hacer el filtrado con Java (De esta forma el JList se llenará mas rápido).
			List<CompraLibro> librosComprados = new ArrayList<CompraLibro>();
			librosComprados = daoCompraLibro.filtrarCompraLibro(compraFKDeLaFactura, "compra_id");
			
			for (CompraLibro cl : librosComprados) {
				for (Libro li : libros) {
					if (cl.getLibroFK() == li.getNumeroDeSerie()) {
						moLibrosComprados.addElement(li.getTitulo());
					}
				}
			}
			listLibrosComprados.setModel(moLibrosComprados);
				
		} catch (Exception ex) {
			moLibrosComprados.clear();
			listLibrosComprados.setModel(moLibrosComprados);
			JOptionPane.showMessageDialog(null, "La factura no existe en el sistema");	
		}
	}
	
}
