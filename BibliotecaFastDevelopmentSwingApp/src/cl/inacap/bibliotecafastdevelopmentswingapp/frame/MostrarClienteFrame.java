package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.ClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TelefonoClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.TelefonoCliente;
import cl.inacap.bibliotecafastdevelopmentswingapp.StartFrame;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MostrarClienteFrame extends JInternalFrame {
	private JTable tableMostrarClientes;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	public static JList listTelefono;
	public static DefaultListModel moList = new DefaultListModel<String>();
	public static String rutSeleccionado = null;
	private TelefonoClienteDAO daoTelefonoCliente = new TelefonoClienteDAO();
	private List<TelefonoCliente> telefonosClienteSeleccionado = new ArrayList<TelefonoCliente>();
	private AgregarTelefonoClientePopUpFrame agregarTelefonoClientePopUpFrame = new AgregarTelefonoClientePopUpFrame();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarClienteFrame frame = new MostrarClienteFrame();
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
	public MostrarClienteFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				ClienteDAO daoClientes = new ClienteDAO();
				clientes = daoClientes.getAll();
				
				if (clientes.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema");
					dispose();
				}
				else {
					cargarTabla();
				}
			}
			
		});
		setTitle("Mostrar Clientes");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 240);
		getContentPane().add(scrollPane);
		
		tableMostrarClientes = new JTable();
		tableMostrarClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tableMostrarClientes.getSelectedRows().length > 0) {
					rutSeleccionado = tableMostrarClientes.getValueAt(tableMostrarClientes.getSelectedRow(), 0).toString();	
					
					telefonosClienteSeleccionado = daoTelefonoCliente.filtrarTelefonoCliente(rutSeleccionado);
					DefaultListModel moList = new DefaultListModel<String>();
					
					for (TelefonoCliente tc : telefonosClienteSeleccionado) {
						moList.addElement(tc.getTelefono());
					}
					listTelefono.setModel(moList);
				}
			}
		});
		
		//2. Proceso de generar el PopUp.
		tableMostrarClientes.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				moList.removeAllElements();
				int r = tableMostrarClientes.rowAtPoint(e.getPoint());
				           
				if (r >= 0 && r < tableMostrarClientes.getRowCount()) {
					tableMostrarClientes.setRowSelectionInterval(r, r);
				} else {
					tableMostrarClientes.clearSelection();
				}

				int filaSeleccionada = tableMostrarClientes.getSelectedRow();
				if (filaSeleccionada < 0) 
					return;
				    if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
				    	JPopupMenu pm = new JPopupMenu(); //Aca se crea el PopUp (La ventana).
				    	JMenuItem mi = new JMenuItem("Agregar telefono", new ImageIcon(MostrarClienteFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentSwingApp/imagenes/add.png"))); //Aca se crea un JMenuItem (Para luego a�adirlo al PopUp).
				        	   
				    	mi.addActionListener(a -> agregarTelefono()); //Aca se le a�ade el listener al JMenuItem.
				        	   
				    	pm.add(mi); //Aca se le a�ade el JMenuItem al PopUp.
				        	   
				    	pm.show(e.getComponent(), e.getX(), e.getY());
				        	
				    	if (tableMostrarClientes.getSelectedRows().length > 0) {
				    		rutSeleccionado = tableMostrarClientes.getValueAt(tableMostrarClientes.getSelectedRow(), 0).toString();	
									
				    		telefonosClienteSeleccionado = daoTelefonoCliente.filtrarTelefonoCliente(rutSeleccionado);
				        		
				    		for (TelefonoCliente tc : telefonosClienteSeleccionado) {
				    			moList.addElement(tc.getTelefono());
				    		}
				        	listTelefono.setModel(moList);
				    	}
				    }
				}	
			});			
		scrollPane.setViewportView(tableMostrarClientes);
		
		JLabel lblNewLabel = new JLabel("Telefonos asociados");
		lblNewLabel.setBounds(10, 262, 144, 14);
		getContentPane().add(lblNewLabel);
		
		listTelefono = new JList();
		listTelefono.setBounds(206, 261, 135, 135);
		getContentPane().add(listTelefono);

	}
	
	public void agregarTelefono() {
		if(this.agregarTelefonoClientePopUpFrame != null) { 
			StartFrame.desktopPane.remove(this.agregarTelefonoClientePopUpFrame); 
		}
		this.agregarTelefonoClientePopUpFrame = new AgregarTelefonoClientePopUpFrame(); 
		StartFrame.desktopPane.add(this.agregarTelefonoClientePopUpFrame); 
		this.agregarTelefonoClientePopUpFrame.setVisible(true); 	
	}
	
	private void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel() {
			public boolean isCellEditable(int fila, int columna) {
				if (columna == 9) {
					return true;
				}
				else {
					return false;
				}
			}
		};
		
		mo.addColumn("Rut");
		mo.addColumn("Nombre");
		mo.addColumn("Apellido paterno");
		mo.addColumn("Apellido materno");
		mo.addColumn("fecha de nacimiento");
		
		
		for (Cliente cl : clientes) {
			Object []fila = new Object [5];
			
			fila[0] = cl.getRut();
			fila[1] = cl.getNombre();
			fila[2] = cl.getApellidoPaterno();
			fila[3] = cl.getApellidoMaterno();
			fila[4] = cl.getFechaDeNacimiento();
			
			mo.addRow(fila);
		}
		
		tableMostrarClientes.setModel(mo);
	}
	
}
