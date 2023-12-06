package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.DistribuidorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Distribuidor;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MostrarDistribuidorFrame extends JInternalFrame {
	private JTable tableMostrarDistribuidores;
	private List<Distribuidor> distribuidores = new ArrayList<Distribuidor>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarDistribuidorFrame frame = new MostrarDistribuidorFrame();
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
	public MostrarDistribuidorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				DistribuidorDAO daoDistribuidor = new DistribuidorDAO();
				distribuidores = daoDistribuidor.getAll();
				
				if (distribuidores.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "No hay distribuidores registrados en el sistema");
					dispose();
				}
				else {
					cargarTabla();
				}
			}
		});
		setTitle("Mostrar Distribuidores");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPane);
		
		tableMostrarDistribuidores = new JTable();
		tableMostrarDistribuidores.setEnabled(false);
		scrollPane.setViewportView(tableMostrarDistribuidores);


	}

	private void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel();
		
		mo.addColumn("Rut");
		mo.addColumn("Nombre");
		mo.addColumn("Pais");
		mo.addColumn("Comuna");
		mo.addColumn("Calle");
		mo.addColumn("N�mero");
		mo.addColumn("Telefono");
		mo.addColumn("A�os de servicio");
		
		for (Distribuidor di : distribuidores){
			Object []fila = new Object[8];
			
			fila[0] = di.getRut();
			fila[1] = di.getNombre();
			fila[2] = di.getPais();
			fila[3] = di.getComuna();
			fila[4] = di.getCalle();
			fila[5] = di.getNumero();
			fila[6] = di.getTelefono();
			fila[7] = di.getYearServicio();
			
			mo.addRow(fila);
			
		}
		
		tableMostrarDistribuidores.setModel(mo);
	}
}
