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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EditorialDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Editorial;

public class MostrarEditorialFrame extends JInternalFrame {
	private JTable tableMostrarEditoriales;
	private List<Editorial> editoriales = new ArrayList<Editorial>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarEditorialFrame frame = new MostrarEditorialFrame();
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
	public MostrarEditorialFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				EditorialDAO daoEditorial = new EditorialDAO();
				
				editoriales = daoEditorial.getAll();
				
				if (editoriales.isEmpty() == false) {
					cargarTabla();
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay editoriales registradas en el sistema");
					dispose();
				}
			
			}

		});
		setTitle("Mostrar Editoriales");
		setBounds(100, 100, 700, 445);
		setClosable(true);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPane);
		
		tableMostrarEditoriales = new JTable();
		tableMostrarEditoriales.setEnabled(false);
		scrollPane.setViewportView(tableMostrarEditoriales);

	}
	
	public void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel();
		
		mo.addColumn("C�digo");
		mo.addColumn("Nombre");
		
		for (Editorial ed : editoriales) {
			Object []fila = new Object[2];
			fila[0] = ed.getCodigo();
			fila[1] = ed.getNombre();
			
			mo.addRow(fila);
		}
		
		tableMostrarEditoriales.setModel(mo);
	}
	
}
