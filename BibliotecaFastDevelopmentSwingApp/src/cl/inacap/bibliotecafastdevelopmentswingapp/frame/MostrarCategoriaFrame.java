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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CategoriaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;

public class MostrarCategoriaFrame extends JInternalFrame {
	private JTable tableMostrarCategorias;
	private List<Categoria> categorias = new ArrayList<Categoria>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarCategoriaFrame frame = new MostrarCategoriaFrame();
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
	public MostrarCategoriaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				CategoriaDAO daoCategoria = new CategoriaDAO();
				categorias = daoCategoria.getAll();
				
				if (categorias.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay categorias registradas en el sistema");
					dispose();
				}
				else {
					cargarTabla();
				}
			}

		});
		setTitle("Mostrar Categorías");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPane);
		
		tableMostrarCategorias = new JTable();
		tableMostrarCategorias.setEnabled(false);
		scrollPane.setViewportView(tableMostrarCategorias);

	}
	
	private void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel();
		
		mo.addColumn("Código");
		mo.addColumn("Nombre");
		
		for(Categoria ca:categorias) {
			Object []fila = new Object [2];
			
			fila[0] = ca.getCodigo();
			fila[1] = ca.getNombre();
			
			mo.addRow(fila);
		}
		
		tableMostrarCategorias.setModel(mo);
	}

}
