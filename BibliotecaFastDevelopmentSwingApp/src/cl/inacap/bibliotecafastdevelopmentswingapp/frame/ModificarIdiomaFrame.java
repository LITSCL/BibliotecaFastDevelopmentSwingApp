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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.IdiomaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarIdiomaFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listIdiomas;
	private JButton btnModificar;
	private JComboBox<String> comboBoxAtributos;
	private List<Idioma> idiomas = new ArrayList<Idioma>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarIdiomaFrame frame = new ModificarIdiomaFrame();
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
	public ModificarIdiomaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				idiomas = new IdiomaDAO().getAll();
				if (idiomas.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "No hay idiomas registrados en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (Idioma id : idiomas) {
						mo.addElement(id.toStringV2());
					}
					
					listIdiomas.setModel(mo);
					
					comboBoxAtributos.addItem("Nombre");
					
				}
			}
		});
		setTitle("Modificar Idioma");
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
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnModificar.setIcon(new ImageIcon(ModificarIdiomaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarIdioma(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listIdiomas = new JList();
		listIdiomas.setBounds(10, 67, 664, 337);
		getContentPane().add(listIdiomas);

	}

	private void modificarIdioma(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado=false;
			String mensaje = "";
			Idioma idiomaSeleccionado = idiomas.get(listIdiomas.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText().trim();
			
			if (atributoSeleccionado.equalsIgnoreCase("Nombre")) {
				idiomaSeleccionado.setNombre(valorModificado);
				if (idiomaSeleccionado.getNombre().isEmpty()) {
					mensaje = "- Nombre no valido";
				}
				else {
					validado = true;
				}
			}
			if (validado == true) {
				new IdiomaDAO().update(idiomaSeleccionado);
				JOptionPane.showMessageDialog(null, "Idioma modificado correctamente");
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ning�n idioma", "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
}

