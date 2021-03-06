package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.MyApp;
import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;

public class DodajPredmetProfesoruDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4809669350214907361L;

	public DodajPredmetProfesoruDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		setSize(300, 300);
		setResizable(false);
		//setLocationRelativeTo(owner);
		Dimension dimListe = new Dimension(300, 220);
		Dimension dimScroll = new Dimension(220, 170);
		Dimension dimBtn = new Dimension(300, 50);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		JPanel lblPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));

		lblPnl.setMaximumSize(new Dimension(this.getWidth(), 100));

		JLabel lbllbl = new JLabel( MyApp.resourceBundle.getString("predmetii"));
		lbllbl.setLayout(new FlowLayout(FlowLayout.LEFT));

		lblPnl.add(lbllbl);
		centerPanel.add(lblPnl);

		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPnl.setPreferredSize(dimBtn);
		
		JButton potvrdiBtn = new JButton( MyApp.resourceBundle.getString("apply"));
		JButton odustaniBtn = new JButton( MyApp.resourceBundle.getString("odustani"));
		btnPnl.add(potvrdiBtn);
		btnPnl.add(odustaniBtn);

		ArrayList<Predmet> listaMogucihPredmeta = new ArrayList<Predmet>();

		ArrayList<String> sifraImePredmeta = new ArrayList<String>();

		for (Predmet p : BazaPredmeta.getInstance().getPredmeti()) {
			boolean nalazi = false;

			for (Predmet pp : ProfesorDialog.getSelectedProfesor().getSpisakPredmetaNaKojimaJeProfesor()) {
				if (pp.getSifrPredmeta().equals(p.getSifrPredmeta()))
					nalazi = true;
			}

			if (nalazi == false)
				listaMogucihPredmeta.add(p);
		}

		for (Predmet p : listaMogucihPredmeta) {
			sifraImePredmeta.add(p.getSifrPredmeta() + " - " + p.getNazivPredmeta());
		}

		String[] dodaj = new String[sifraImePredmeta.size()];

		for (int i = 0; i < sifraImePredmeta.size(); i++) {
			dodaj[i] = sifraImePredmeta.get(i);
		}

		JList<String> listBox = new JList<String>(dodaj);
		JPanel listPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JScrollPane sctrollList = new JScrollPane(listBox);
		sctrollList.setPreferredSize(dimScroll);

		listPnl.add(sctrollList);
		listPnl.setPreferredSize(dimListe);
		
		potvrdiBtn.setEnabled(false);
		listBox.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				if (!arg0.getValueIsAdjusting()) {
					potvrdiBtn.setEnabled(true);
				}

			}
		});

		potvrdiBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Predmet p = new Predmet();
				p = listaMogucihPredmeta.get(listBox.getSelectedIndex());

				ProfesorDialog.getSelectedProfesor().getSpisakPredmetaNaKojimaJeProfesor().add(p);
				for(Predmet predmet : BazaPredmeta.getInstance().getPredmeti()) {
					if(predmet.equals(p)) {
						for (Profesor sviProf : BazaProfesora.getInstance().getProfesori()) {
							if (predmet.getPredmetniProfesor() != null && sviProf.getBrojLicneKarte() == predmet.getPredmetniProfesor().getBrojLicneKarte()) {
								sviProf.getSpisakPredmetaNaKojimaJeProfesor().remove(predmet);
							}
						}
						predmet.setPredmetniProfesor(ProfesorDialog.getSelectedProfesor());
					}
				}
				ProfesorDialog.azurirajPrikazPredaje();
				dispose();
			}

		});

		odustaniBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}

		});

		centerPanel.add(listPnl);
		centerPanel.add(btnPnl);
		this.add(centerPanel);
	}

}
