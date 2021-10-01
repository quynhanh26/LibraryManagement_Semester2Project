package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dao.BookDao;
import dao.BooksDetail_CallCardDao;
import dao.Books_DetailDao;
import dao.CallCardDao;
import dao.PeopleDao;
import entities.Books;
import entities.CallCard;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PayBooksJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JLabel lblIdPeople;
	private final JLabel lblQuantity;
	private JLabel lblNewLabel;
	private JTextField txtbook;
	private JTextField txtQuantity;
	private JTextField txtIdPeople;
	private JButton btnPay;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnSearch;
	private JLabel lblBookTitle;
	private DefaultTableModel model;
	private CallCard callcard = new CallCard();

	public PayBooksJDialog(Frame owner, boolean modal) {
		super(owner, modal);
		setBounds(100, 100, 648, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblIdPeople = new JLabel("ID People: ", SwingConstants.RIGHT);
		lblIdPeople.setBounds(124, 68, 91, 22);
		lblIdPeople.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.add(lblIdPeople);

		lblNewLabel = new JLabel("RETURN BOOKS", SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 15, 529, 39);
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 40));
		contentPanel.add(lblNewLabel);

		txtIdPeople = new JTextField();
		txtIdPeople.setBounds(225, 65, 242, 28);
		txtIdPeople.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtIdPeople.setColumns(10);
		contentPanel.add(txtIdPeople);

		txtbook = new JTextField();
		txtbook.setBounds(225, 296, 334, 28);
		txtbook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtbook.setColumns(10);
		contentPanel.add(txtbook);

		lblQuantity = new JLabel("Quantity: ", SwingConstants.RIGHT);
		lblQuantity.setBounds(134, 348, 81, 22);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(225, 345, 334, 28);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtQuantity.setColumns(10);
		txtQuantity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
		contentPanel.add(txtQuantity);

		btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPayActionPerformed(e);
			}
		});
		btnPay.setForeground(new Color(255, 140, 0));
		btnPay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPay.setBounds(443, 384, 116, 28);
		contentPanel.add(btnPay);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 101, 469, 184);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		scrollPane.setViewportView(table);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchActionPerformed(e);
			}
		});
		btnSearch.setBounds(477, 64, 82, 29);
		contentPanel.add(btnSearch);

		lblBookTitle = new JLabel("Book Title", SwingConstants.RIGHT);
		lblBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBookTitle.setBounds(134, 296, 81, 22);
		contentPanel.add(lblBookTitle);
		loadTbale();
	}

	protected void btnPayActionPerformed(ActionEvent e) {
			if (!Books_DetailDao.payBooks(Integer.parseInt(txtQuantity.getText()), callcard.getCallCardId(),
					callcard.getPeopleId(), txtbook.getText())) {
				JOptionPane.showMessageDialog(null, "Pay Fail");
			}
	}

	protected void btnSearchActionPerformed(ActionEvent e) {
		var idPeople = txtIdPeople.getText();
		if (PeopleDao.checkIDpeople(idPeople)) {
			if (!BooksDetail_CallCardDao.selectInfoCallcardOfPeople(idPeople).isEmpty()) {
				model.setRowCount(0);
				BooksDetail_CallCardDao.selectInfoCallcardOfPeople(idPeople).forEach(item -> model.addRow(
						new Object[] { item.getId(), item.getIdPeople(), item.getId_book(), item.getQuantity() }));
			} else
				JOptionPane.showInternalMessageDialog(null, "No data");
		} else
			JOptionPane.showInternalMessageDialog(null, "Id People not exsist");

	}

	protected void loadTbale() {
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				default:
					return String.class;
				}
			}
		};

		model.addColumn("ID CALLCAD");
		model.addColumn("ID PEOPLE");
		model.addColumn("ID BOOK");
		model.addColumn("QUANTITY");
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
	}

	protected void tableMouseClicked(MouseEvent e) {
		txtbook.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
		txtQuantity.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
		callcard.setCallCardId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
		callcard.setPeopleId(table.getValueAt(table.getSelectedRow(), 1).toString());
	}

}
