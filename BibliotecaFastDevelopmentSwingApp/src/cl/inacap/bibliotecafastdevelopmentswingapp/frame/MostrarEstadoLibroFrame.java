package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;

public class MostrarEstadoLibroFrame extends JInternalFrame {
	private JTable tableMostrarEstadoLibros;
	private List<EstadoLibro> estadoLibros = new ArrayList<EstadoLibro>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarEstadoLibroFrame frame = new MostrarEstadoLibroFrame();
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
	public MostrarEstadoLibroFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				estadoLibros = new EstadoLibroDAO().getAll();
				
				if (estadoLibros.isEmpty() != true) {
					cargarTabla();
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay estados registrados en el sistema");
					dispose();
				}
				
			}
		});
		setTitle("Mostrar Estados");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPane);
		
		tableMostrarEstadoLibros = new JTable();
		scrollPane.setViewportView(tableMostrarEstadoLibros);

	}
	
	public void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel();
		
		mo.addColumn("Código");
		mo.addColumn("Descripción");
		
		for(EstadoLibro el : estadoLibros) {
			Object []fila = new Object[2];
			
			fila[0] = el.getCodigo();
			fila[1] = el.getDescripcion();
			
			mo.addRow(fila);
		}
		
		tableMostrarEstadoLibros.setModel(mo);
	}
	
}
