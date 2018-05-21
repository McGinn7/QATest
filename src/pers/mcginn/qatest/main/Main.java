package pers.mcginn.qatest.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Main {

	private final int width = 800;
	private final int height = 400;

	private JFrame frame = null;

	private List<Problem> list = null;
	private int index = 0;

	Main() {
		getData();
		initFrame();
		updateContent();
	}

	private void initFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((ds.width - width) / 2, (ds.height - height) / 2);
		frame.pack();
		frame.setVisible(true);
	}

	private void updateContent() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		// Solved / Total
		panel.add(new JLabel((index + 1) + " / " + list.size()), gbc);
		// Problem Infomation
		Problem question = list.get(index);
		JTextArea jta = new JTextArea();
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setOpaque(false);
		jta.setText(question.getQuestion());
		jta.setPreferredSize(new Dimension(width, 140));
		jta.setFont(new Font("Serif", Font.PLAIN, 32));
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(jta, gbc);

		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < question.getOptions().size(); ++i) {
			JRadioButton jrb = new JRadioButton(question.getOptions().get(i));
			jrb.setFont(new Font("Serif", Font.PLAIN, 24));
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.NONE;
			group.add(jrb);
			panel.add(jrb, gbc);
			jrb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton btn = (AbstractButton) arg0.getSource();
					String selected = btn.getText();
					if (selected.equals(question.getAnswer())) {
						if (index + 1 < list.size()) {
							index += 1;
							updateContent();
						} else {
							JOptionPane.showMessageDialog(frame, "Good job. :)", "Message",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Answer is \"" + question.getAnswer() + "\"",
								"Wrong Answer", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		// JButton btnNext = new JButton("next");
		// btnNext.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// // TODO Auto-generated method stub
		// if (group.getSelection() != null) {
		// Enumeration<AbstractButton> btns = group.getElements();
		// while (btns.hasMoreElements()) {
		// AbstractButton btn = btns.nextElement();
		// if (btn.isSelected()) {
		// String selected = btn.getText();
		// if (selected.equals(question.getAnswer())) {
		// if (index + 1 < list.size()) {
		// index += 1;
		// updateContent();
		// } else {
		// JOptionPane.showMessageDialog(frame, "Good job. :)", "Message",
		// JOptionPane.INFORMATION_MESSAGE);
		// System.exit(0);
		// }
		// } else {
		// JOptionPane.showMessageDialog(frame, "Answer is \"" + question.getAnswer() +
		// "\"",
		// "Wrong Answer", JOptionPane.ERROR_MESSAGE);
		// }
		// }
		// }
		// }
		// }
		// });
		// gbc.gridx = 0;
		// gbc.gridy++;
		// gbc.gridwidth = 2;
		// panel.add(btnNext, gbc);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		frame.add(panel);

		frame.pack();
		frame.revalidate();
	}

	private void getData() {
		try {
			list = Service.getListProblem("text.txt", "ans.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("size = " + list.size());
		Collections.shuffle(list);
		for (Problem q : list) {
			q.shuffleOptions();
		}
	}

	public static void main(String args[]) {
		new Main();
	}

}
