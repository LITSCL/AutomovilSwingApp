package cl.inacap.automovilswingapp.frame;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.automovilswingappmodelo.dao.*;
import cl.inacap.automovilswingappmodelo.dto.*;
import javax.swing.JComboBox;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MostrarAutomovilFrame extends JInternalFrame { //Esta clase hereda de JInternalFrame (Son los Frames que se muestran cuando se ejecuta el codigo de los JMenuItem).
	private JTable tableMostrarAutomovil; 
	private JComboBox comboBoxFiltrarAutomoviles;
	private JButton btnFiltrar;
	

	//Proceso de creación del frame.
	public MostrarAutomovilFrame() {
		setTitle("Mostrar Automoviles"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		//Todo este código se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JComboBox).
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				AutomovilDAO daoAutomovil = new AutomovilDAO();
				List<Automovil> automovilesIngresados = daoAutomovil.getAll(); //Aca se trae la lista de automoviles.
				
				if (automovilesIngresados.isEmpty() == true) { //Aca se revisa si hay automoviles en la lista, de no ser así se ejecuta el código.
					JOptionPane.showMessageDialog(null, "No hay automoviles registrados en el sistema"); //El primer parámetro corresponde a la ubicación del mensaje y el segundo parámetro corresponde al texto del mensaje.
					dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Eliminar Automovil)).
				}
				
				//Proceso de agregación de items al JComboBox.
				comboBoxFiltrarAutomoviles.addItem("Petrolero");
				comboBoxFiltrarAutomoviles.addItem("Bencinero");
				comboBoxFiltrarAutomoviles.addItem("Eléctrico");
			}
		});
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 664, 363);
		getContentPane().add(scrollPane);
		
		tableMostrarAutomovil = new JTable();
		tableMostrarAutomovil.setEnabled(false);
		scrollPane.setViewportView(tableMostrarAutomovil);
		cargarTabla();
		
		comboBoxFiltrarAutomoviles = new JComboBox();
		comboBoxFiltrarAutomoviles.setBounds(484, 11, 91, 20);
		getContentPane().add(comboBoxFiltrarAutomoviles);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this::filtrarAutomoviles); //Aca se añade el Listener del JButton llamado (Filtrar) el cual llama al método llamado filtrarAutomoviles().
		btnFiltrar.setBounds(585, 10, 89, 23);
		getContentPane().add(btnFiltrar);
		

	}
	
	//Proceso de muestreo con filtros (El filtro se hace en la campa dao, aca solo se muestran).
	private void filtrarAutomoviles(ActionEvent e) {
		String seleccion = (String)this.comboBoxFiltrarAutomoviles.getSelectedItem(); //En esta variable se almacena el valor del JComboBox.
		AutomovilDAO daoAutomovil = new AutomovilDAO();
		
			//1. Proceso de traer los estudiantes ingresados.
			List<Automovil> automovilesFiltrados = daoAutomovil.filtrarAutomovil(seleccion); //En esta lista se almacenan todos los automoviles filtrados según el valor del JcomboBox.
			
			//2. Proceso de creación del TableModel.
			DefaultTableModel mo = new DefaultTableModel(); //Este es el modelo donde van a ir las filas y columnas.
			
			//3. Proceso de llenado de columnas.
			mo.addColumn("Patente"); //Se añade una columna llamada (Nombre).
			mo.addColumn("Nombre de contacto"); //Se añade una columna llamada (Asignatura).
			mo.addColumn("Tipo de atención"); //Se añade una columna llamada (Promedio).
			
			//4. Proceso de agregación de filas.
			for (Automovil au : automovilesFiltrados) {
				Object[] fila = new Object[3];
				fila[0] = au.getPatente();
				fila[1] = au.getNombreDeContacto();
				fila[2] = au.getTipoDeAtencion();
				mo.addRow(fila);
			}	
			
			//5. Proceso de definir en la tabla el TableModel.
			tableMostrarAutomovil.setModel(mo);
		}
		
	private void cargarTabla() {
		//1. Proceso de traer los estudiantes ingresados.
		List<Automovil> automovilesIngresados = new AutomovilDAO().getAll(); //En esta lista se almacenan todos los automoviles ingresados.
		
		//2. Proceso de creación del TableModel.
		DefaultTableModel mo = new DefaultTableModel(); //Este es el modelo donde van a ir las filas y columnas.
		
		//3. Proceso de llenado de columnas.
		mo.addColumn("Patente"); //Se añade una columna llamada (Nombre).
		mo.addColumn("Nombre de contacto"); //Se añade una columna llamada (Asignatura).
		mo.addColumn("Tipo de atención"); //Se añade una columna llamada (Promedio).
		
		//4. Proceso de agregación de filas.
		for (Automovil au : automovilesIngresados) {
			Object[] fila = new Object[3];
			fila[0] = au.getPatente();
			fila[1] = au.getNombreDeContacto();
			fila[2] = au.getTipoDeAtencion();
			mo.addRow(fila);
		}	
		
		//5. Proceso de definir en la tabla el TableModel.
		tableMostrarAutomovil.setModel(mo);
	}
}
