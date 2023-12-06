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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.AutorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Autor;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class BuscarAutorFrame extends JInternalFrame {
	private JTextField textFieldAutor;
	private JTable tableAutor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarAutorFrame frame = new BuscarAutorFrame();
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
	public BuscarAutorFrame() {
		setTitle("Buscar Autor");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setBounds(10, 33, 32, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(31, 30, 86, 20);
		getContentPane().add(textFieldAutor);
		textFieldAutor.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarAutorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarAutor(e));
		btnBuscar.setBounds(127, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableAutor = new JTable();
		tableAutor.setEnabled(false);
		scrollPane.setViewportView(tableAutor);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[] = {"C�digo", "Nombre", "Apellido paterno", "Apellido materno", "Fecha de nacimiento"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableAutor.setModel(mo);
	}

	private void buscarAutor(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaAutor = this.textFieldAutor.getText();
		List<Autor> autor = new AutorDAO().filtrarAutor(clavePrimariaAutor);
		
		String nombreColumnas[]= {"C�digo", "Nombre", "Apellido paterno", "Apellido materno", "Fecha de nacimiento"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		try {
			
			Object []fila=new Object[5];
			
			fila[0] = autor.get(0).getCodigo();
			fila[1] = autor.get(0).getNombre();
			fila[2] = autor.get(0).getApellidoPaterno();
			fila[3] = autor.get(0).getApellidoMaterno();
			fila[4] = autor.get(0).getFechaDeNacimiento();
			
			mo.addRow(fila);
			tableAutor.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "El autor no existe en el sistema");
		}
	}
	
}
