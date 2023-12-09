package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TelefonoTrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.TelefonoTrabajador;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingapp.StartFrame;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class MostrarTrabajadorFrame extends JInternalFrame {
	private JTable tableMostrarTrabajadores;
	private List<Trabajador> trabajadores = new ArrayList<Trabajador>();
	public static String rutSeleccionado = null;
	private TelefonoTrabajadorDAO daoTelefonoTrabajador = new TelefonoTrabajadorDAO();
	private List<TelefonoTrabajador> telefonosTrabajadorSeleccionado = new ArrayList<TelefonoTrabajador>();
	public static JList listTelefono;
	public static DefaultListModel moList = new DefaultListModel<String>();
	private AgregarTelefonoTrabajadorPopUpFrame agregarTelefonoTrabajadorPopUpFrame = new AgregarTelefonoTrabajadorPopUpFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarTrabajadorFrame frame = new MostrarTrabajadorFrame();
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
	public MostrarTrabajadorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				trabajadores = new TrabajadorDAO().getAll();
				
				if (trabajadores.isEmpty()!=true) {
					cargarTabla();
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay trabajadores registrados en el sistema");
					dispose();
				}
				
			}
		});
		setTitle("Mostrar Trabajadores");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 240);
		getContentPane().add(scrollPane);
		
		tableMostrarTrabajadores = new JTable();
		tableMostrarTrabajadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				moList.removeAllElements();
				if (tableMostrarTrabajadores.getSelectedRows().length > 0) {
					rutSeleccionado = tableMostrarTrabajadores.getValueAt(tableMostrarTrabajadores.getSelectedRow(), 0).toString();	
					
					telefonosTrabajadorSeleccionado = daoTelefonoTrabajador.filtrarTelefonoTrabajador(rutSeleccionado);
					
					for (TelefonoTrabajador tt : telefonosTrabajadorSeleccionado) {
						moList.addElement(tt.getTelefono());
					}
					listTelefono.setModel(moList);
				}
			}
		});
		
		//2. Proceso de generar el PopUp.
		tableMostrarTrabajadores.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				moList.removeAllElements();
				int r = tableMostrarTrabajadores.rowAtPoint(e.getPoint());
		           
				if (r >= 0 && r < tableMostrarTrabajadores.getRowCount()) {
					tableMostrarTrabajadores.setRowSelectionInterval(r, r);
				} else {
					tableMostrarTrabajadores.clearSelection();
				}

				int filaSeleccionada = tableMostrarTrabajadores.getSelectedRow();
				if (filaSeleccionada < 0) 
					return;
		    		if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		    			JPopupMenu pm = new JPopupMenu(); //Aca se crea el PopUp (La ventana).
		    			JMenuItem mi = new JMenuItem("Agregar telefono", new ImageIcon(MostrarTrabajadorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentSwingApp/imagenes/add.png"))); //Aca se crea un JMenuItem (Para luego añadirlo al PopUp).
		        	   
		    			mi.addActionListener(a -> agregarTelefono()); //Aca se le añade el listener al JMenuItem.
		        	   
		    			pm.add(mi); //Aca se le añade el JMenuItem al PopUp.
		        	   
		    			pm.show(e.getComponent(), e.getX(), e.getY());
		        	
		    			if (tableMostrarTrabajadores.getSelectedRows().length > 0) {
		    				rutSeleccionado = tableMostrarTrabajadores.getValueAt(tableMostrarTrabajadores.getSelectedRow(), 0).toString();	
							
		    				telefonosTrabajadorSeleccionado = daoTelefonoTrabajador.filtrarTelefonoTrabajador(rutSeleccionado);
		        		
		    				for (TelefonoTrabajador tt : telefonosTrabajadorSeleccionado) {
		    					moList.addElement(tt.getTelefono());
		    				}
		        			listTelefono.setModel(moList);
		    			}
		    		}
			 	}	
		});	
		scrollPane.setViewportView(tableMostrarTrabajadores);
		
		listTelefono = new JList();
		listTelefono.setBounds(206, 261, 135, 135);
		getContentPane().add(listTelefono);
		
		JLabel lblNewLabel = new JLabel("Telefonos asociados");
		lblNewLabel.setBounds(10, 262, 135, 14);
		getContentPane().add(lblNewLabel);

	}
	
	public void agregarTelefono() {
		if(this.agregarTelefonoTrabajadorPopUpFrame != null) { 
			StartFrame.desktopPane.remove(this.agregarTelefonoTrabajadorPopUpFrame); 
		}
		this.agregarTelefonoTrabajadorPopUpFrame = new AgregarTelefonoTrabajadorPopUpFrame(); 
		StartFrame.desktopPane.add(this.agregarTelefonoTrabajadorPopUpFrame); 
		this.agregarTelefonoTrabajadorPopUpFrame.setVisible(true); 	
	}
	
	public void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel() {
			public boolean isCellEditable(int fila, int columna) {
				if (columna == 9) {
					return true;
				} else {
			return false;}}
		};
		
		String nombreColumnas[] = {"Rut", "Nombre", "Apellido paterno", "Apellido materno", "Fecha de contrato"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		for (Trabajador tr : trabajadores) {
			Object filas[] = new Object[5];
			
			filas[0] = tr.getRut();
			filas[1] = tr.getNombre();
			filas[2] = tr.getApellidoPaterno();
			filas[3] = tr.getApellidoMaterno();
			filas[4] = tr.getFechaDeContrato();
			
			mo.addRow(filas);
		}
		this.tableMostrarTrabajadores.setModel(mo);
	}
	
}
