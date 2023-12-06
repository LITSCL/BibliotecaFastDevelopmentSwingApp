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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EditorialDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Editorial;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarEditorialFrame extends JInternalFrame {
	private JTextField textFieldEditorial;
	private JTable tableEditorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarEditorialFrame frame = new BuscarEditorialFrame();
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
	public BuscarEditorialFrame() {
		setTitle("Buscar Editorial");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 33, 61, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldEditorial = new JTextField();
		textFieldEditorial.setBounds(51, 30, 86, 20);
		getContentPane().add(textFieldEditorial);
		textFieldEditorial.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarEditorialFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarEditorial(e));
		btnBuscar.setBounds(147, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableEditorial = new JTable();
		tableEditorial.setEnabled(false);
		scrollPane.setViewportView(tableEditorial);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[] = {"C�digo","Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableEditorial.setModel(mo);
	}

	private void buscarEditorial(ActionEvent e) {
		StartAudioUtil sau=new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaEditorial = this.textFieldEditorial.getText();
		List<Editorial> editorial = new EditorialDAO().filtrarEditorial(clavePrimariaEditorial);
		
		String nombreColumnas[] = {"C�digo","Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		try {
			
			Object []fila = new Object[2];
			
			fila[0] = editorial.get(0).getCodigo();
			fila[1] = editorial.get(0).getNombre();
			
			mo.addRow(fila);
			tableEditorial.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "La editorial no existe en el sistema");
		}
	}
	
}
