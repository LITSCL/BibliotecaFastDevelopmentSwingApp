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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EditorialDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Editorial;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarEditorialFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listEditoriales;
	private JButton btnModificar;
	private JComboBox<String> comboBoxAtributos;
	private List<Editorial> editoriales = new ArrayList<Editorial>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarEditorialFrame frame = new ModificarEditorialFrame();
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
	public ModificarEditorialFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				editoriales = new EditorialDAO().getAll();
				if (editoriales.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "No hay editoriales registradas en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (Editorial ed : editoriales) {
						mo.addElement(ed.toString());
					}
					
					listEditoriales.setModel(mo);
					
					comboBoxAtributos.addItem("Nombre");
					
				}
			}
		});
		setTitle("Modificar Editorial");
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
		btnModificar.setIcon(new ImageIcon(ModificarEditorialFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarEditorial(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listEditoriales = new JList();
		listEditoriales.setBounds(10, 67, 664, 337);
		getContentPane().add(listEditoriales);

	}

	private void modificarEditorial(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			String mensaje = "";
			Editorial editorialSeleccionada = editoriales.get(listEditoriales.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText().trim();
			
			if (atributoSeleccionado.equalsIgnoreCase("Nombre")) {
				editorialSeleccionada.setNombre(valorModificado);
				if (editorialSeleccionada.getNombre().isEmpty() != true) {
					validado=true;
				}
				else {
					mensaje = "- Nombre no valido";
				}
			}
			
			if (validado != false) {
				new EditorialDAO().update(editorialSeleccionada);
				JOptionPane.showMessageDialog(null, "Editorial modificada correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null,mensaje,"Error de validación", JOptionPane.WARNING_MESSAGE);
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ninguna editorial", "Error de validación", JOptionPane.WARNING_MESSAGE);
		}	
	}

}
