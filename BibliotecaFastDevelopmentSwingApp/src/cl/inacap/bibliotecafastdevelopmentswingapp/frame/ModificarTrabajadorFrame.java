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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarTrabajadorFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listTrabajadores;
	private JButton btnModificar;
	private JComboBox comboBoxAtributos;
	private List<Trabajador> trabajadores = new ArrayList<Trabajador>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarTrabajadorFrame frame = new ModificarTrabajadorFrame();
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
	public ModificarTrabajadorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				trabajadores = new TrabajadorDAO().getAll();
				if(trabajadores.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay trabajadores registrados en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for(Trabajador tr : trabajadores) {
						mo.addElement(tr.toString());
					}
					
					listTrabajadores.setModel(mo);
					
					comboBoxAtributos.addItem("Nombre");	
					comboBoxAtributos.addItem("Apellido paterno");
					comboBoxAtributos.addItem("Apellido materno");
					comboBoxAtributos.addItem("Fecha de contrato");
				}
			}
		});
		setTitle("Modificar Trabajador");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setBounds(10, 33, 127, 20);
		getContentPane().add(textFieldModificar);
		textFieldModificar.setColumns(10);
		
		comboBoxAtributos = new JComboBox();
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
		btnModificar.setIcon(new ImageIcon(ModificarTrabajadorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarTrabajador(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listTrabajadores = new JList();
		listTrabajadores.setBounds(10, 67, 664, 337);
		getContentPane().add(listTrabajadores);

	}

	private void modificarTrabajador(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			String mensaje= "" ;
			Trabajador trabajadorSeleccionado = trabajadores.get(listTrabajadores.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText().trim();
			
			switch (atributoSeleccionado) {
			case "Nombre":
				trabajadorSeleccionado.setNombre(valorModificado);
				if (trabajadorSeleccionado.getNombre().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Nombre no valido";
				}
				break;
			case "Apellido paterno":
				trabajadorSeleccionado.setApellidoPaterno(valorModificado);
				if (trabajadorSeleccionado.getApellidoPaterno().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Apellido paterno no valido";
				}
				break;
			case "Apellido materno":
				trabajadorSeleccionado.setApellidoMaterno(valorModificado);
				if (trabajadorSeleccionado.getApellidoMaterno().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = " - Apellido materno no valido";
				}
				break;
			case "Fecha de contrato":
				trabajadorSeleccionado.setFechaDeContrato(valorModificado);
				if (new FechaGringaUtil().validarFechaGringa(trabajadorSeleccionado.getFechaDeContrato()) == true) {
					validado=true;
				}else {
					mensaje = "- Fecha no valida";
				}
				break;
			}
			
			if (validado == true) {
				TrabajadorDAO daoTrabajador = new TrabajadorDAO();
				daoTrabajador.update(trabajadorSeleccionado);
				JOptionPane.showMessageDialog(null, "Trabajador modificado correctamente");
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ning�n trabajador", "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
	}	

}

