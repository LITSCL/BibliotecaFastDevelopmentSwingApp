package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.AutorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Autor;

import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MostrarAutorFrame extends JInternalFrame {
	private JTable tableMostrarAutores;
	private List<Autor> autores = new ArrayList<Autor>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarAutorFrame frame = new MostrarAutorFrame();
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
	public MostrarAutorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				AutorDAO daoAutor = new AutorDAO();
				autores = daoAutor.getAll();
				
				if (autores.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay autores registrados en el sistema");
					dispose();
				}
				else {
					cargarTabla();
				}
					
				
			}
		});
		setTitle("Mostrar Autores");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPane);
		
		tableMostrarAutores = new JTable();
		tableMostrarAutores.setEnabled(false);
		scrollPane.setViewportView(tableMostrarAutores);
		

	}

	public void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel();
		
		mo.addColumn("C�digo");
		mo.addColumn("Nombre");
		mo.addColumn("Apellido paterno");
		mo.addColumn("Apellido materno");
		mo.addColumn("Fecha de nacimiento");
		
		for (Autor au : autores) {
			Object []fila = new Object[5];
			fila[0] = au.getCodigo();
			fila[1] = au.getNombre();
			fila[2] = au.getApellidoPaterno();
			fila[3] = au.getApellidoMaterno();
			fila[4] = au.getFechaDeNacimiento();
			mo.addRow(fila);
		}
		
		tableMostrarAutores.setModel(mo);
	}
	
}
