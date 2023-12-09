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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoFacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoFactura;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarMetodoPagoFacturaFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listMetodoPagoFactura;
	private JButton btnModificar;
	private JComboBox<String> comboBoxAtributos;
	private List<MetodoPagoFactura> metodoPagoFacturas = new ArrayList<MetodoPagoFactura>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarMetodoPagoFacturaFrame frame = new ModificarMetodoPagoFacturaFrame();
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
	public ModificarMetodoPagoFacturaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				metodoPagoFacturas = new MetodoPagoFacturaDAO().getAll();
				if (metodoPagoFacturas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay metodos de pago registrados en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (MetodoPagoFactura mpf : metodoPagoFacturas) {
						mo.addElement(mpf.toString());
					}
					
					listMetodoPagoFactura.setModel(mo);
						
					comboBoxAtributos.addItem("Descripción");
				}
			}
		});
		setTitle("Modificar M\u00E9todo de Pago");
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
		btnModificar.setIcon(new ImageIcon(ModificarMetodoPagoFacturaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarMetodo(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listMetodoPagoFactura = new JList();
		listMetodoPagoFactura.setBounds(10, 67, 664, 337);
		getContentPane().add(listMetodoPagoFactura);

	}

	private void modificarMetodo(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			String mensaje = "";
			MetodoPagoFactura metodoPagoFacturaSeleccionado = metodoPagoFacturas.get(this.listMetodoPagoFactura.getSelectedIndex());
			String atributoSeleccionado=(String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText().trim();
			
			switch (atributoSeleccionado) {
			
			case "Descripción":
				metodoPagoFacturaSeleccionado.setDescripcion(valorModificado);
				if (metodoPagoFacturaSeleccionado.getDescripcion().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Descripción no valida";
				}
				break;
			}
			
			if (validado == true) {
				MetodoPagoFacturaDAO daoMetodoPagoFactura = new MetodoPagoFacturaDAO();
				daoMetodoPagoFactura.update(metodoPagoFacturaSeleccionado);
				JOptionPane.showMessageDialog(null, "Método de pago modificado correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ningún método de pago", "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
