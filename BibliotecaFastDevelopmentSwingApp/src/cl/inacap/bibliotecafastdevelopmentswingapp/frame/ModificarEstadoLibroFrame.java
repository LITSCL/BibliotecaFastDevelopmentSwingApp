package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;



import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarEstadoLibroFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listEstadoLibros;
	private JButton btnModificar;
	private JComboBox<String> comboBoxAtributos;
	private List<EstadoLibro> estadoLibros = new ArrayList<EstadoLibro>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarEstadoLibroFrame frame = new ModificarEstadoLibroFrame();
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
	public ModificarEstadoLibroFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				estadoLibros = new EstadoLibroDAO().getAll();
				if (estadoLibros.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "No hay estados registrados en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (EstadoLibro el : estadoLibros) {
						mo.addElement(el.toString());
					}
					
					listEstadoLibros.setModel(mo);
					
					comboBoxAtributos.addItem("Descripción");
					
				}
			}
		});
		setTitle("Modificar Estado");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setBounds(10, 33, 127, 20);
		getContentPane().add(textFieldModificar);
		textFieldModificar.setColumns(10);
		
		comboBoxAtributos = new JComboBox<String>();
		comboBoxAtributos.setBounds(147, 33, 127, 20);
		getContentPane().add(comboBoxAtributos);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau=new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnModificar.setIcon(new ImageIcon(ModificarEstadoLibroFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarEstadoLibro(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listEstadoLibros = new JList();
		listEstadoLibros.setBounds(10, 67, 664, 337);
		getContentPane().add(listEstadoLibros);

	}

	private void modificarEstadoLibro(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			EstadoLibro estadoLibroSeleccionado = estadoLibros.get(listEstadoLibros.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText().trim();
			
			if (atributoSeleccionado.equalsIgnoreCase("Descripción")) {
				estadoLibroSeleccionado.setDescripcion(valorModificado);

			}
			if (estadoLibroSeleccionado.getDescripcion().isEmpty() == true) {
				
			}
			else {
				validado = true;
			}
			
			if (validado == true) {
				new EstadoLibroDAO().update(estadoLibroSeleccionado);
				JOptionPane.showMessageDialog(null, "Estado modificado correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "- Descripción no valida", "Error de validación", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ningún estado", "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
