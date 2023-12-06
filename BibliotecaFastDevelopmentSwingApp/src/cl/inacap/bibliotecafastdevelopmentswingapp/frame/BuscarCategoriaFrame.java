package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CategoriaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarCategoriaFrame extends JInternalFrame {
	private JTextField textFieldCategoria;
	private JTable tableCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarCategoriaFrame frame = new BuscarCategoriaFrame();
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
	public BuscarCategoriaFrame() {
		setTitle("Buscar Categor�a");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 33, 61, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(51, 30, 86, 20);
		getContentPane().add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarCategoriaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarCategoria(e));
		btnBuscar.setBounds(147, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableCategoria = new JTable();
		tableCategoria.setEnabled(false);
		scrollPane.setViewportView(tableCategoria);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[] = {"C�digo", "Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableCategoria.setModel(mo);
	}

	private void buscarCategoria(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaCategoria = this.textFieldCategoria.getText();
		List<Categoria> categoria = new CategoriaDAO().filtrarCategoria(clavePrimariaCategoria);
		
		String nombreColumnas[]= {"C�digo", "Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		try {
			
			Object []fila = new Object[2];
			
			fila[0] = categoria.get(0).getCodigo();
			fila[1] = categoria.get(0).getNombre();
			
			mo.addRow(fila);
			tableCategoria.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "La categor�a no existe en el sistema");
		}
	}
	
}
