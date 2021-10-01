package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CallCardDao;
import dao.PeopleDao;
import dao.People_CallCardDao;
import model_view.CallCard_People;
import model_view.Detail_OverDue;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class OverDueJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JButton btnSendMail;

	public OverDueJDialog() {
		Layout();
	}

	private void Layout() {
		setBounds(100, 100, 741, 402);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();

		lblNewLabel = new JLabel("Overdue list", SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));

		btnSendMail = new JButton("Send Mail");
		btnSendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSendMailActionPerformed(e);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(34)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														655, Short.MAX_VALUE)))
								.addGroup(
										gl_contentPanel.createSequentialGroup().addGap(274).addComponent(btnSendMail)))
						.addContainerGap(26, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE).addComponent(btnSendMail)));

		table = new JTable();
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		LoadData(new People_CallCardDao());
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem menuItemViewOverDue = new JMenuItem("View");
		menuItemViewOverDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewOverDueList();
			}
		});

		popupMenu.add(menuItemViewOverDue);
		table.setComponentPopupMenu(popupMenu);
	}

	protected void ViewOverDueList() {
		Detail_OverDue item = new Detail_OverDue();
		item.setIdCallCard(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString()));
		DetailOverDueJDialog detailOverDueJDialog = new DetailOverDueJDialog(item.getIdCallCard());
		detailOverDueJDialog.setVisible(true);
	}

	public void LoadData(People_CallCardDao dao) {
		DefaultTableModel model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};

		model.addColumn("NO.");
		model.addColumn("Id Call Card");
		model.addColumn("Id People");
		model.addColumn("Name People");
		model.addColumn("Class");
		model.addColumn("Quantity Borrowed");

		dao.getListCheckDateBorrowed().forEach(item -> model.addRow(new Object[] { item.getNo(), item.getCallCardId(),
				item.getPeopleId(), item.getPeopleName(), item.getClassName(), item.getQuantityBrrowed(), }));

		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
	}

	protected void btnSendMailActionPerformed(ActionEvent e) {
		if (table.getRowCount() > 0) {
			for (int i = 0; i < table.getRowCount(); i++) {
				sendMail(PeopleDao.infoPeopleWithID(table.getValueAt(i, 2).toString()).getEmail());
			}
			JOptionPane.showMessageDialog(null, "Sent message successfully....");
		}
	}

	private void sendMail(String email) {
		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		String from = "dinhnam0629@gmail.com";

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, "truclinh2000");

			}

		});
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
