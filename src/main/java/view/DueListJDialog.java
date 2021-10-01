package view;

import java.awt.BorderLayout;
import java.awt.Component;
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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DueListJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtSearch;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnSendMail;

	public DueListJDialog() {
		Layout();
	}

	private void Layout() {
		setTitle("The list is close to the deadline");
		setBounds(100, 100, 575, 379);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchActionPerformed(e);
			}
		});

		txtSearch.setColumns(10);
		
		lblNewLabel = new JLabel("<html>&#128270;</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblNewLabel_1 = new JLabel("List due before 2 days ", SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		btnSendMail = new JButton("Send Mail");
		btnSendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSendMailActionPerformed(e);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 533, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 533, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(231, Short.MAX_VALUE)
					.addComponent(btnSendMail)
					.addGap(229))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSendMail)
					.addGap(147))
		);
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		CallCardDao dao = new CallCardDao();
		LoadData(new People_CallCardDao());
		
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem menuItemInfor = new JMenuItem("Information CallCard");
		menuItemInfor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItemInfor(e);
			}
		}); 
		
		popupMenu.add(menuItemInfor);
		
		table.setComponentPopupMenu(popupMenu);
	}

	protected void menuItemInfor(ActionEvent e) {
		CallCard_People call = new CallCard_People();
		call.setCallCardId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString()));
		Infomation_CallCardJDialog infomation_CallCardJDialog = new Infomation_CallCardJDialog(call.getCallCardId());
		infomation_CallCardJDialog.setVisible(true);
		
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
				case 6:
					return String.class;
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
		model.addColumn("Total");
		model.addColumn("Date Due");

		dao.getListCheckDate().forEach(item -> model.addRow(new Object[] { item.getNo(), item.getCallCardId(),
				item.getPeopleId(), item.getPeopleName(), item.getClassName(), item.getQuantityBrrowed(), item.getDateDue() }));
		
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
	}

	protected void txtSearchActionPerformed(ActionEvent e) {
		String find = txtSearch.getText();
		DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) table.getRowSorter();
		sorter.setRowFilter(RowFilter.regexFilter("(?i)" + find));
		sorter.setSortKeys(null);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	protected void btnSendMailActionPerformed(ActionEvent e) {
		if (table.getRowCount() > 0) {
			for (int i = 0; i < table.getRowCount(); i++) {
				System.out.println(table.getValueAt(i, 2).toString());
				System.out.println(PeopleDao.infoPeopleWithID(table.getValueAt(i, 2).toString()).getEmail());
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
