package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dao.BookDao;
import dao.Books_DetailDao;
import dao.CallCardDao;
import dao.PeopleDao;
import entities.Books;
import entities.Books_Detail;
import entities.CallCard;
import helper.LimitedCharDocument;
import helper.Validate;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.Rectangle;

public class Borrow_BooksJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtidbook;
	private JLabel lblQuantity;
	private JTextField txtQuantity;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnadd;
	private DefaultTableModel model;
	private JLabel lblIDpeople;
	private JTextField txtidpeople;
	private JLabel lblngaytra;
	private JButton btnConfirm;
	private JDateChooser dateDue;
	private JButton btndel;
	private JComboBox cbxbook;
	private JPanel panel;

	public Borrow_BooksJDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		Layout();
	}

	@SuppressWarnings("unchecked")
	private void Layout() {
		setBounds(100, 100, 555, 513);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		txtidbook = new JTextField();
		txtidbook.setBounds(211, 15, 297, 26);
		txtidbook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtidbook.setColumns(10);

		lblQuantity = new JLabel("Quantity:", SwingConstants.RIGHT);
		lblQuantity.setBounds(77, 50, 106, 20);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtQuantity = new JTextField(new LimitedCharDocument(1), "", 10);
		txtQuantity.setBounds(211, 51, 297, 26);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtQuantity.setColumns(10);
		txtQuantity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '1') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 123, 467, 164);

		btnadd = new JButton("Addition");
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnadd.setBounds(415, 87, 93, 25);
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnaddActionPerformed(e);
			}
		});
		btnadd.setToolTipText("");

		panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.setBounds(40, 302, 468, 129);
		panel.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirm.setBounds(415, 441, 93, 25);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirmActionPerformed(e);
			}
		});

		btndel = new JButton("Delete");
		btndel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btndel.setBounds(211, 87, 93, 25);
		btndel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btndelActionPerformed(e);
			}
		});
		btndel.setEnabled(false);
		btndel.setToolTipText("");

		cbxbook = new JComboBox();
		cbxbook.setBounds(77, 15, 104, 25);
		cbxbook.setModel(new DefaultComboBoxModel<String>(new String[] { "ID BOOK", "NAME BOOK" }));

		lblIDpeople = new JLabel("ID People:", SwingConstants.RIGHT);
		lblIDpeople.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtidpeople = new JTextField();
		txtidpeople.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtidpeople.setColumns(10);

		lblngaytra = new JLabel("Date Due:", SwingConstants.RIGHT);
		lblngaytra.setFont(new Font("Tahoma", Font.BOLD, 16));

		dateDue = new JDateChooser();
		dateDue.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateDue.setDateFormatString("yyyy-MM-dd");
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateDue.getDateEditor();
		editor.setEditable(false);
		try {
			dateDue.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(10)
								.addComponent(lblIDpeople, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtidpeople, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addComponent(lblngaytra, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateDue, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)))
						.addGap(28)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIDpeople, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtidpeople, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblngaytra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(dateDue, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
						.addContainerGap(25, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
				tableMouseDoubleClicked(e);
			}
		});
		scrollPane.setViewportView(table);
		contentPanel.setLayout(null);
		contentPanel.add(cbxbook);
		contentPanel.add(txtidbook);
		contentPanel.add(btndel);
		contentPanel.add(btnadd);
		contentPanel.add(lblQuantity);
		contentPanel.add(txtQuantity);
		contentPanel.add(panel);
		contentPanel.add(scrollPane);
		contentPanel.add(btnConfirm);
		loaddata();
	}

	private void loaddata() {
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
					return Integer.class;
				default:
					return String.class;
				}

			}
		};
		model.addColumn("Id Book");
		model.addColumn("Title");
		model.addColumn("Author");
		model.addColumn("Quantity");
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
	}

	protected void btnaddActionPerformed(ActionEvent e) {
		Books book;
		if (cbxbook.getSelectedItem().equals("NAME BOOK")) {
			book = new BookDao().selectBook_WithName(txtidbook.getText());
		} else {
			book = new BookDao().selectBook_WithID(txtidbook.getText());
		}
		if (book.getBookId() != null) {
			int y = 0;
			boolean check = false;
			if (table.getRowCount() > 0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 0).toString().equals(book.getBookId())) {
						check = true;
						y = i;
						break;
					}
				}
				if (check) {
					table.setValueAt(Integer.parseInt(table.getValueAt(y, 3).toString())
							+ Integer.parseInt(txtQuantity.getText()), y, 3);

				} else {
					model.addRow(new Object[] { book.getBookId(), book.getTitle(), book.getAuthor(),
							Integer.parseInt(txtQuantity.getText()) });

				}
			} else {
				model.addRow(new Object[] { book.getBookId(), book.getTitle(), book.getAuthor(),
						Integer.parseInt(txtQuantity.getText()) });
			}
		} else if (txtidbook.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter id book or nam book, please");
		} else if (txtQuantity.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter quantity, please");
		} else
			JOptionPane.showMessageDialog(null, "Book doesn't exist");
	}

	protected void btnConfirmActionPerformed(ActionEvent e) {
		if (table.getRowCount() > 0) {
			if (PeopleDao.checkIDpeople(txtidpeople.getText())) {
				CallCard callcard = new CallCard();
				List<Books_Detail> list = new ArrayList<Books_Detail>();
				callcard.setPeopleId(txtidpeople.getText());
				callcard.setDateDue(dateDue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				callcard.setDateBorrowed(LocalDate.now());
				int quantity = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					quantity += Integer.parseInt(table.getValueAt(i, 3).toString());
					var bookDetail = new Books_Detail();
					bookDetail.setId_book(table.getValueAt(i, 0).toString());
					bookDetail.setQuantity(Integer.parseInt(table.getValueAt(i, 3).toString()));
					list.add(bookDetail);
				}
				callcard.setQuantityBrrowed(quantity);
				callcard.setStatus(true);
				if (BookDao.updateQuantityBook(quantity)) {
					if (CallCardDao.selectTotalBook(callcard.getPeopleId(), quantity)) {
						CallCardDao.insertCallCard(callcard);
						Books_DetailDao.insertBookDetail(list, CallCardDao.idmax());
					} else
						JOptionPane.showMessageDialog(null, "You can only borrow a maximum of 5 books");
				} else {
					JOptionPane.showMessageDialog(null, "Books in insufficient stock");
				}
			} else
				JOptionPane.showMessageDialog(null, "Id people doesn't exist");
		} else
			JOptionPane.showMessageDialog(null, "Enter book");
	}

	protected void tableMouseClicked(MouseEvent e) {
		btndel.setEnabled(true);
	}

	protected void tableMouseDoubleClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && !e.isConsumed()) {
			String quantity = JOptionPane.showInputDialog(null, "ENTER QUANTITY", "Update quantity",
					JOptionPane.INFORMATION_MESSAGE);
			if (quantity != null) {
				if (Validate.chiNhapSo(quantity)) {
					table.setValueAt(quantity, table.getSelectedRow(), 3);
				} else
					JOptionPane.showInternalMessageDialog(null, "enter number");

			}
		}
	}

	protected void btndelActionPerformed(ActionEvent e) {
		model.removeRow(table.getSelectedRow());
		btndel.setEnabled(false);
	}
}
