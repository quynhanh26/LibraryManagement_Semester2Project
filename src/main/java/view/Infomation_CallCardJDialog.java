package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Frame;

import dao.BookDao;
import dao.Book_BookDetail_CallCardDao;
import dao.CallCardDao;
import dao.CategoryDao;
import dao.People_CallCardDao;
import entities.Books;
import entities.CallCard;
import entities.Category;
import model_view.BooksDetail_CallCard;
import model_view.CallCard_People;

import javax.swing.JCheckBox;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Infomation_CallCardJDialog extends JDialog {
	private int callCardId;
	private JLabel lblID;
	private JLabel lblIdPeople;
	private JLabel lblNamePeople;
	private JLabel lblPhoneNumber;
	private JLabel lblEmail;
	private JLabel lblClassName;
	private JLabel lblStartDate;
	private JLabel lblExpected;
	private JLabel lblQuantityPay;
	private JLabel lblId_text;
	private JLabel lblIdPeople_text;
	private JLabel lblNamePeople_text;
	private JLabel lblPhone_text;
	private JLabel lblEmail_text;
	private JLabel lblClass_text;
	private JLabel lblDateBorrowed_text;
	private JLabel lblDateDue_text;
	private JLabel lblQuantityPay_text;
	private JLabel lblNewLabel;
	private JCheckBox chckboxStatus;
	private JScrollPane scrollPane;
	private JTable table_BooksDetail;

	public Infomation_CallCardJDialog(int id) {

		callCardId = id;
		Layout();
	}

	private void Layout() {
		setBounds(100, 100, 780, 589);
		lblID = new JLabel("ID:", SwingConstants.RIGHT);
		lblID.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblId_text = new JLabel(" ");
		lblId_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblIdPeople = new JLabel("ID People:", SwingConstants.RIGHT);
		lblIdPeople.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblIdPeople_text = new JLabel(" ");
		lblIdPeople_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblNamePeople = new JLabel("Name People:", SwingConstants.RIGHT);
		lblNamePeople.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblNamePeople_text = new JLabel(" ");
		lblNamePeople_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblPhoneNumber = new JLabel("Phone Number:", SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblPhone_text = new JLabel(" ");
		lblPhone_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblEmail_text = new JLabel(" ");
		lblEmail_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblClassName = new JLabel("Class Name:", SwingConstants.RIGHT);
		lblClassName.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblClass_text = new JLabel(" ");
		lblClass_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblStartDate = new JLabel("Date Borrowed:", SwingConstants.RIGHT);
		lblStartDate.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblDateBorrowed_text = new JLabel(" ");
		lblDateBorrowed_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblExpected = new JLabel("Date Due:", SwingConstants.RIGHT);
		lblExpected.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblDateDue_text = new JLabel(" ");
		lblDateDue_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblQuantityPay = new JLabel("Quantity Pay:", SwingConstants.RIGHT);
		lblQuantityPay.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		lblQuantityPay_text = new JLabel(" ");
		lblQuantityPay_text.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		lblNewLabel = new JLabel("Status:", SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));

		chckboxStatus = new JCheckBox("Removed");
		chckboxStatus.setEnabled(false);
		chckboxStatus.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		scrollPane = new JScrollPane();

		table_BooksDetail = new JTable();
		scrollPane.setViewportView(table_BooksDetail);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(lblID, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(lblId_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(lblIdPeople, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(lblIdPeople_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(lblNamePeople, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(lblNamePeople_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(lblPhone_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(lblEmail_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(lblClassName, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(lblClass_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(lblDateBorrowed_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblQuantityPay, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(66)
							.addComponent(lblQuantityPay_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(66)
							.addComponent(chckboxStatus, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblExpected, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(66)
							.addComponent(lblDateDue_text, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE)
					.addGap(92))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblID)
						.addComponent(lblId_text))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIdPeople)
						.addComponent(lblIdPeople_text))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNamePeople)
						.addComponent(lblNamePeople_text))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPhoneNumber)
						.addComponent(lblPhone_text))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addComponent(lblEmail_text))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClassName)
						.addComponent(lblClass_text))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStartDate)
						.addComponent(lblDateBorrowed_text))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExpected)
						.addComponent(lblDateDue_text))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantityPay)
						.addComponent(lblQuantityPay_text))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(chckboxStatus, 0, 0, Short.MAX_VALUE)))
					.addGap(23)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(110))
		);
		getContentPane().setLayout(groupLayout);
		LoadData();
	}

	public void LoadData() {
		People_CallCardDao dao = new People_CallCardDao();
		CallCard_People call = dao.getInforCallCard(callCardId);
		lblId_text.setText(callCardId + "");
		lblIdPeople_text.setText(call.getPeopleId());
		lblNamePeople_text.setText(call.getPeopleName());
		lblPhone_text.setText(call.getPhone());
		lblEmail_text.setText(call.getEmail());
		lblClass_text.setText(call.getClassName());
		lblQuantityPay_text.setText(call.getQuantityBrrowed() + "");
		lblDateBorrowed_text.setText(call.getDateBorrowed().toString());
		lblDateDue_text.setText(call.getDateDue().toString());
		chckboxStatus.setSelected(call.isStatus() ? true : false);
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
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return Integer.class;
				case 6:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};

		model.addColumn("NO.");
		model.addColumn("Id Book");
		model.addColumn("Title");
		model.addColumn("Author");
		model.addColumn("Id Category");
		model.addColumn("Quantity pay");
		model.addColumn("Quantity due");
		 
		Book_BookDetail_CallCardDao bookdao = new Book_BookDetail_CallCardDao();
		bookdao.selectBook_Detail_CallCard_Book(callCardId).forEach(item -> model.addRow(new Object[] { item.getNo(), item.getId_book(),
				item.getTitle(), item.getAuthor(), item.getId_category(), item.getQuantity(), item.getQuantity_due() }));
 
		table_BooksDetail.setModel(model); 
		table_BooksDetail.getTableHeader().setReorderingAllowed(false);
	}
}
