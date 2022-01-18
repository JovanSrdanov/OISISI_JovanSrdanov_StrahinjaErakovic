package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 4070509246110827584L;

	public static MyFrame instance = null;
	
	private static boolean pretraga;

	private Toolbar toolbar;
	private MenuBar menu;
	private StatusBar statusBar;
	private static MyTab tabbedPane;
	private static JTable tabelaStduenti;
	private static Tabela tabelaPredmeta;
	private static Tabela tabelaProfesora;

	private JPanel panelGlavni;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JPanel panelEast;

	private MyFrame() {
		pretraga = false;
		this.createToolbar();
		this.initialise();
		this.createStatusBar();
		this.createMenuBar();
		this.createTabbedPane();

	}

	public static MyFrame getInstance() {
		if (instance == null)
			instance = new MyFrame();

		return instance;
	}

	private void createToolbar() {

		this.toolbar = new Toolbar();
		this.add(this.toolbar, BorderLayout.NORTH);
	}

	private void createMenuBar() {

		this.menu = new MenuBar();
		this.setJMenuBar(this.menu);
	}

	private void createStatusBar() {

		statusBar = new StatusBar();
		this.add(statusBar, BorderLayout.SOUTH);
	}

	private void initialise() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		int screenHeight = screenSize.height;
		int screenWidith = screenSize.width;

		setSize((screenWidith / 4) * 3, (screenHeight / 4) * 3);
		setTitle("Studentska služba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Color color = new Color(0, 95, 105);

		this.panelGlavni = new JPanel();
		this.panelGlavni.setBackground(color);
		this.panelGlavni.setLayout(new BorderLayout());

		this.panelNorth = new JPanel();
		this.panelNorth.setBackground(color);
		this.panelNorth.setPreferredSize(new Dimension(50, 50));

		this.panelSouth = new JPanel();
		this.panelSouth.setBackground(color);
		this.panelSouth.setPreferredSize(new Dimension(50, 50));

		this.panelWest = new JPanel();
		this.panelWest.setBackground(color);
		this.panelWest.setPreferredSize(new Dimension(75, 75));

		this.panelEast = new JPanel();
		this.panelEast.setBackground(color);
		this.panelEast.setPreferredSize(new Dimension(75, 75));

		this.panelGlavni.add(panelNorth, BorderLayout.NORTH);
		this.panelGlavni.add(panelSouth, BorderLayout.SOUTH);
		this.panelGlavni.add(panelWest, BorderLayout.WEST);
		this.panelGlavni.add(panelEast, BorderLayout.EAST);

		this.add(this.panelGlavni, BorderLayout.CENTER);

	}

	private void createTabbedPane() {

		tabbedPane = new MyTab();
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				statusBar.setAktivniTab(tabbedPane.getSelectedIndex());
			}

		});

		ImageIcon iconStudenti = createImageIcon("icons/studenti.png", true, 32, 32);
		tabelaStduenti = new Tabela(new AbstractTableModelStudenti());
		JScrollPane scrollPaneStudenti = new JScrollPane(tabelaStduenti);
		tabbedPane.addTab("Studenti", iconStudenti, scrollPaneStudenti, "Prikaz studenata");

		ImageIcon iconProfesori = createImageIcon("icons/profesori.png", true, 32, 32);
		tabelaProfesora = new Tabela(new AbstractTableModelProfesor());
		tabelaProfesora.setAutoCreateRowSorter(true);
		JScrollPane scrollPaneProfesori = new JScrollPane(tabelaProfesora);
		tabbedPane.addTab("Profesori", iconProfesori, scrollPaneProfesori, "Prikaz profseora");

		ImageIcon iconPredmeti = createImageIcon("icons/predmeti.png", true, 32, 32);
		tabelaPredmeta = new Tabela(new AbstractTableModelPredmeti());
		JScrollPane scrollPanePredmeti = new JScrollPane(tabelaPredmeta);
		tabbedPane.addTab("Predmeti", iconPredmeti, scrollPanePredmeti, "Prikaz predmeta");

		this.panelGlavni.add(tabbedPane, BorderLayout.CENTER);

	}

	public void azurirajPrikazStudenata() {
		AbstractTableModelStudenti model = (AbstractTableModelStudenti) tabelaStduenti.getModel();
		model.fireTableDataChanged();
		validate();
	}

	public void azurirajPrikazPredmeta() {
		AbstractTableModelPredmeti model = (AbstractTableModelPredmeti) tabelaPredmeta.getModel();
		model.fireTableDataChanged();
		validate();
	}

	public void azurirajPrikazProfesora() {
		AbstractTableModelProfesor model = (AbstractTableModelProfesor) tabelaProfesora.getModel();
		model.fireTableDataChanged();
		validate();
	}

	protected static ImageIcon createImageIcon(String path, boolean scaleImage, int width, int height) {
		if (scaleImage) {
			// kopirano iz materijala sa vezbi
			ImageIcon imageIcon = new ImageIcon(path);
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			return imageIcon;

		} else {
			return new ImageIcon(path);
		}
	}

	public static JTable getTabelaStduenti() {
		return tabelaStduenti;
	}

	public static Tabela getTabelaPredmeta() {
		return tabelaPredmeta;
	}

	public static Tabela getTabelaProfesora() {
		return tabelaProfesora;
	}

	public MyTab getTab() {
		return tabbedPane;
	}
	
	public boolean isPretraga()
	{
		return pretraga;
	}
	
	public void setPretraga(boolean isPretraga)
	{
		pretraga = isPretraga;
	}

}