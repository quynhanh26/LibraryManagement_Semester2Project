package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import dao.Book_BookDetail_CallCardDao;
import dao.CallCardDao;
import dao.People_CallCardDao;
import entities.Account;
import entities.Books;
import entities.CallCard;
import model_view.CallCard_People;
import model_view.Detail_OverDue;

import javax.swing.DefaultRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class Library_Management extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JTabbedPane tabbedPane;
	private JLabel lblNewLabel;
	private JPanel panel_Info_Books;
	private JLabel lbl_Img_BOOKS;
	private JLabel lblBOOKS;
	private JScrollPane scrollPane_BOOKS;
	private JPanel panel_Call_card;
	private JLabel lblNewLabel_1;
	private JTable table_BOOKS;
	private JScrollPane scrollPane_BOOKS_1;
	private JTable table_CALL_CARD;
	private JPanel panel_Statistics;
	private JButton btnDueList;
	private JButton btnMostBorrowed;
	private JTextField txtSearchBook;
	private JLabel lblNewLabel_2;
	private JButton btnAddCallCard;
	private JButton btnAddCategory;
	private JTextField txtSearchCallCard;
	private JLabel lblNewLabel_3;
	private JButton btnRemovedList;
	private JLabel lbl_txt_title;
	private JButton btnCallCardList;
	private JButton btnPayBook;
	private JButton btnPayList;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	private JLabel lblBooksIsBorrowed;
	private JLabel lbl_txtBooksIsBorrowed;
	private JLabel lblTotalOfGuests;
	private JLabel lblTotalCallCard;
	private JLabel lblTotalGuestsBorrowing;
	private JLabel lblTotalCardOverdue;
	private JLabel lbl_txtTotalOfGuests;
	private JLabel lbl_txtTotalCallCard;
	private JLabel lbl_txtTotalGuestsBorrowing;
	private JLabel lbl_txtTotalCardOverdue;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_16;
	private JButton btnNewButton;
	private JTable table_OverDue;
	private JButton btnchangepass;
	private String password;
	private String user;

	public Library_Management(String result, String username) {
		password = result;
		user = username;
		Layout();
	}
	
	public Library_Management() {
		Layout();
	}

	private void Layout() {
		setTitle("Library Management ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		panel_Info_Books = new JPanel();
		panel_Info_Books.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				panel_Info_BooksAncestorAdded(event);
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPane.addTab("Information Books", null, panel_Info_Books, null);

		lbl_Img_BOOKS = new JLabel("");
		lbl_Img_BOOKS.setIcon(new ImageIcon("images/Books.png"));

		lblBOOKS = new JLabel("BOOKS");
		lblBOOKS.setForeground(Color.BLUE);
		lblBOOKS.setFont(new Font("VNI-Algerian", Font.PLAIN, 20));

		scrollPane_BOOKS = new JScrollPane();

		btnDueList = new JButton("Due List");
		btnDueList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDueListActionPerformed(e);
			}
		});
		btnDueList.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnMostBorrowed = new JButton("Most borrowed");
		btnMostBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMostBorrowedActionPerformed(e);
			}
		});
		btnMostBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtSearchBook = new JTextField();
		txtSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchActionPerformed(e);
			}
		});
		txtSearchBook.setColumns(10);

		lblNewLabel_2 = new JLabel("<html>&#128270;</html>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnAddCategory = new JButton("Add Category");
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCategoryActionPerformed(e);
			}
		});
		btnAddCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));

		table_BOOKS = new JTable();
		table_BOOKS.setAutoCreateRowSorter(true);
		scrollPane_BOOKS.setViewportView(table_BOOKS);

		panel_Call_card = new JPanel();
		panel_Call_card.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				panel_Call_cardAncestorAdded(event);
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPane.addTab("Information Card", null, panel_Call_card, null);

		lblNewLabel_1 = new JLabel("Call Card");
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setFont(new Font("VNI-Algerian", Font.PLAIN, 20));

		scrollPane_BOOKS_1 = new JScrollPane();

		btnAddCallCard = new JButton("Add Call Card");
		btnAddCallCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCallCardActionPerformed(e);
			}
		});
		btnAddCallCard.setIcon(new ImageIcon("images/icon_addCard.png"));
		btnAddCallCard.setFont(new Font("VNI-Arial Rounded", Font.PLAIN, 16));

		txtSearchCallCard = new JTextField();
		txtSearchCallCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldActionPerformed(e);
			}
		});
		txtSearchCallCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearchCallCard.setColumns(10);

		lblNewLabel_3 = new JLabel("<html>&#128270;</html>");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnRemovedList = new JButton("Removed List");
		btnRemovedList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemovedListActionPerformed(e);
			}
		});
		btnRemovedList.setFont(new Font("VNI-Arial Rounded", Font.PLAIN, 16));

		lbl_txt_title = new JLabel(" ", SwingConstants.CENTER);
		lbl_txt_title.setForeground(new Color(255, 0, 0));
		lbl_txt_title.setFont(new Font("VNI-Hobo", Font.PLAIN, 18));

		btnCallCardList = new JButton("Call Card List");
		btnCallCardList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCallCardListActionPerformed(e);
			}
		});
		btnCallCardList.setFont(new Font("VNI-Arial Rounded", Font.PLAIN, 16));

		btnPayBook = new JButton("Pay books");
		btnPayBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPayBookActionPerformed(e);
			}
		});
		btnPayBook.setFont(new Font("VNI-Arial Rounded", Font.PLAIN, 16));

		btnPayList = new JButton("Pay List");
		btnPayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPayListActionPerformed(e);
			}
		});
		btnPayList.setFont(new Font("VNI-Arial Rounded", Font.PLAIN, 16));
		GroupLayout gl_panel_Call_card = new GroupLayout(panel_Call_card);
		gl_panel_Call_card
				.setHorizontalGroup(
						gl_panel_Call_card.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_Call_card
								.createSequentialGroup().addGroup(gl_panel_Call_card
										.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_Call_card
												.createSequentialGroup().addGap(24).addGroup(gl_panel_Call_card
														.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnRemovedList, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
														.addComponent(btnCallCardList, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
														.addComponent(btnPayBook, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
														.addComponent(btnPayList, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
														.addComponent(
																btnAddCallCard, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
												.addGroup(gl_panel_Call_card.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_Call_card
																.createSequentialGroup().addGap(160)
																.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE,
																		175, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_panel_Call_card.createSequentialGroup().addGap(18)
																.addGroup(gl_panel_Call_card
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lbl_txt_title,
																				GroupLayout.PREFERRED_SIZE, 397,
																				GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_panel_Call_card
																				.createSequentialGroup()
																				.addComponent(txtSearchCallCard,
																						GroupLayout.PREFERRED_SIZE, 432,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(4).addComponent(lblNewLabel_3,
																						GroupLayout.PREFERRED_SIZE, 46,
																						GroupLayout.PREFERRED_SIZE))))))
										.addGroup(gl_panel_Call_card.createSequentialGroup().addContainerGap()
												.addComponent(scrollPane_BOOKS_1, GroupLayout.PREFERRED_SIZE, 759,
														GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(30, Short.MAX_VALUE)));
		gl_panel_Call_card.setVerticalGroup(gl_panel_Call_card.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Call_card.createSequentialGroup().addContainerGap().addGroup(gl_panel_Call_card
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Call_card.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_panel_Call_card.createParallelGroup(Alignment.LEADING)
										.addComponent(txtSearchCallCard, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_Call_card.createSequentialGroup().addGap(2).addComponent(
												lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(22).addComponent(lbl_txt_title, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Call_card.createSequentialGroup()
								.addComponent(btnAddCallCard, GroupLayout.PREFERRED_SIZE, 34,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnRemovedList, GroupLayout.PREFERRED_SIZE, 34,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnCallCardList, GroupLayout.PREFERRED_SIZE, 34,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnPayBook, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnPayList, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_BOOKS_1, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
						.addGap(18)));

		table_CALL_CARD = new JTable();
		table_CALL_CARD.setAutoCreateRowSorter(true);
		scrollPane_BOOKS_1.setViewportView(table_CALL_CARD);
		panel_Call_card.setLayout(gl_panel_Call_card);

		panel_Statistics = new JPanel();
		tabbedPane.addTab("Statistics", null, panel_Statistics, null);

		lblNewLabel_4 = new JLabel("From");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblNewLabel_5 = new JLabel("To");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));

		dateStart = new JDateChooser();
		dateStart.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateStart.setDateFormatString("yyyy-MM-dd");
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateStart.getDateEditor();
		editor.setEditable(false);
		try {
			dateStart.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		dateEnd = new JDateChooser();
		dateEnd.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateEnd.setDateFormatString("yyyy-MM-dd");
		JTextFieldDateEditor editor1 = (JTextFieldDateEditor) dateEnd.getDateEditor();
		editor1.setEditable(false);
		try {
			dateEnd.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		lblBooksIsBorrowed = new JLabel("Number of books is borrowed:");
		lblBooksIsBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbl_txtBooksIsBorrowed = new JLabel(" ");
		lbl_txtBooksIsBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTotalOfGuests = new JLabel("Total Of Guests:");
		lblTotalOfGuests.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTotalCallCard = new JLabel("Total Call Card:");
		lblTotalCallCard.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTotalGuestsBorrowing = new JLabel("Total guests borrowing books:");
		lblTotalGuestsBorrowing.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTotalCardOverdue = new JLabel("Total card overdue:");
		lblTotalCardOverdue.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbl_txtTotalOfGuests = new JLabel(" ");
		lbl_txtTotalOfGuests.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbl_txtTotalCallCard = new JLabel(" ");
		lbl_txtTotalCallCard.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbl_txtTotalGuestsBorrowing = new JLabel(" ");
		lbl_txtTotalGuestsBorrowing.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbl_txtTotalCardOverdue = new JLabel(" ");
		lbl_txtTotalCardOverdue.setFont(new Font("Tahoma", Font.PLAIN, 18));

		scrollPane = new JScrollPane();

		lblNewLabel_16 = new JLabel("Overdue Call Card List");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnNewButton = new JButton("Get Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButtonActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_panel_Statistics = new GroupLayout(panel_Statistics);
		gl_panel_Statistics.setHorizontalGroup(gl_panel_Statistics.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Statistics.createSequentialGroup().addGap(55).addGroup(gl_panel_Statistics
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Statistics.createSequentialGroup()
								.addComponent(lblTotalCardOverdue, GroupLayout.PREFERRED_SIZE, 251,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lbl_txtTotalCardOverdue, GroupLayout.PREFERRED_SIZE, 94,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Statistics.createSequentialGroup()
								.addComponent(lblTotalGuestsBorrowing, GroupLayout.PREFERRED_SIZE, 251,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lbl_txtTotalGuestsBorrowing, GroupLayout.PREFERRED_SIZE, 94,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Statistics.createSequentialGroup()
								.addComponent(lblTotalCallCard, GroupLayout.PREFERRED_SIZE, 251,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lbl_txtTotalCallCard, GroupLayout.PREFERRED_SIZE, 94,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Statistics.createSequentialGroup()
								.addComponent(lblTotalOfGuests, GroupLayout.PREFERRED_SIZE, 251,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lbl_txtTotalOfGuests, GroupLayout.PREFERRED_SIZE, 94,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Statistics.createSequentialGroup()
								.addGroup(gl_panel_Statistics.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_Statistics.createSequentialGroup()
												.addComponent(lblBooksIsBorrowed, GroupLayout.PREFERRED_SIZE, 251,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(lbl_txtBooksIsBorrowed,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addGroup(gl_panel_Statistics.createSequentialGroup()
												.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 56,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(dateStart, GroupLayout.PREFERRED_SIZE, 243,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 42,
														GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(dateEnd, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(btnNewButton))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 676, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(gl_panel_Statistics.createSequentialGroup().addContainerGap(283, Short.MAX_VALUE)
						.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addGap(313)));
		gl_panel_Statistics.setVerticalGroup(gl_panel_Statistics.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Statistics.createSequentialGroup().addGap(28).addGroup(gl_panel_Statistics
						.createParallelGroup(Alignment.LEADING)
						.addComponent(dateEnd, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addGroup(gl_panel_Statistics.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(dateStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31,
										Short.MAX_VALUE))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)).addGap(42)
						.addGroup(gl_panel_Statistics.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lbl_txtBooksIsBorrowed, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblBooksIsBorrowed, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_Statistics.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalOfGuests, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_txtTotalOfGuests, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_Statistics.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalCallCard, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_txtTotalCallCard, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_Statistics.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalGuestsBorrowing, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_txtTotalGuestsBorrowing, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_Statistics.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_txtTotalCardOverdue, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotalCardOverdue, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addGap(41)));

		table_OverDue = new JTable();
		scrollPane.setViewportView(table_OverDue);
		panel_Statistics.setLayout(gl_panel_Statistics);

		lblNewLabel = new JLabel("Library Manager", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 25));

		btnchangepass = new JButton("Change PassWord");
		btnchangepass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnchangepass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnchangepassActionPerformed(e);
			}
		});
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addGap(192)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_desktopPane.createSequentialGroup()
					.addContainerGap(607, Short.MAX_VALUE)
					.addComponent(btnchangepass, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(64))
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnchangepass, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		desktopPane.setLayout(gl_desktopPane);
		BookDao dao = new BookDao();
		Load_Data_Books(dao);
		CallCardDao dao1 = new CallCardDao();
		Load_Data_CallCard(dao1);

		this.setResizable(false);
		// ==================================================
		JPopupMenu popupMenuBooks = new JPopupMenu();
		JMenuItem menuItemViewBook = new JMenuItem("View");
		menuItemViewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookMenuItemView();
			}
		});
		JMenuItem menuItemAddBook = new JMenuItem("Addition");
		menuItemAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookMenuItemAdd();
			}
		});

		popupMenuBooks.add(menuItemViewBook);
		popupMenuBooks.add(menuItemAddBook);

		table_BOOKS.setComponentPopupMenu(popupMenuBooks);
		GroupLayout gl_panel_Info_Books = new GroupLayout(panel_Info_Books);
		gl_panel_Info_Books.setHorizontalGroup(gl_panel_Info_Books.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info_Books.createSequentialGroup().addGroup(gl_panel_Info_Books
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info_Books.createSequentialGroup().addGap(10).addComponent(scrollPane_BOOKS,
								GroupLayout.PREFERRED_SIZE, 759, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info_Books.createSequentialGroup().addGap(28).addGroup(gl_panel_Info_Books
								.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDueList, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_Info_Books.createSequentialGroup()
										.addComponent(btnAddCategory, GroupLayout.PREFERRED_SIZE, 153,
												GroupLayout.PREFERRED_SIZE)
										.addGap(64)
										.addComponent(txtSearchBook, GroupLayout.PREFERRED_SIZE, 432,
												GroupLayout.PREFERRED_SIZE)
										.addGap(4).addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 46,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_Info_Books.createSequentialGroup().addComponent(btnMostBorrowed)
										.addGap(173)
										.addComponent(lblBOOKS, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6).addComponent(lbl_Img_BOOKS, GroupLayout.PREFERRED_SIZE, 82,
												GroupLayout.PREFERRED_SIZE)))))
						.addGap(30)));
		gl_panel_Info_Books.setVerticalGroup(gl_panel_Info_Books.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Info_Books.createSequentialGroup().addGap(10).addGroup(gl_panel_Info_Books
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info_Books.createSequentialGroup().addGap(13)
								.addComponent(lblBOOKS, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(lbl_Img_BOOKS)
						.addGroup(gl_panel_Info_Books.createSequentialGroup()
								.addComponent(btnDueList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnMostBorrowed,
										GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_Info_Books.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddCategory, GroupLayout.PREFERRED_SIZE, 32,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSearchBook, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addComponent(scrollPane_BOOKS, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(12, Short.MAX_VALUE)));
		panel_Info_Books.setLayout(gl_panel_Info_Books);

		// ==================================================
		JPopupMenu popupMenuCallCard = new JPopupMenu();

		JMenuItem menuItemViewCallCard = new JMenuItem("View");
		menuItemViewCallCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallCardMenuItemView();
			}
		});
		JMenuItem menuItemRemoveCallCard = new JMenuItem("Remove");
		menuItemRemoveCallCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallCardMenuItemRemove();
			}
		});

		popupMenuCallCard.add(menuItemViewCallCard);
		popupMenuCallCard.add(menuItemRemoveCallCard);

		table_CALL_CARD.setComponentPopupMenu(popupMenuCallCard);

		JPopupMenu popupMenuOverDue = new JPopupMenu();

		JMenuItem menuItemViewOverDue = new JMenuItem("View");
		menuItemViewOverDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewOverDueList();
			}
		});

		popupMenuOverDue.add(menuItemViewOverDue);
		table_OverDue.setComponentPopupMenu(popupMenuOverDue);

//		JPopupMenu popupMenuPayList = new JPopupMenu();
//		JMenuItem menuItemViewPayList = new JMenuItem("View2");
//		menuItemViewPayList.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				PayListMenuItemView();
//			}
//		});
//
//		popupMenuPayList.add(menuItemViewPayList);
//		table_CALL_CARD.setComponentPopupMenu(popupMenuPayList);

	}

	protected void PayListMenuItemView() {
		if (checkSelectRow()) {
			CallCard_People call = new CallCard_People();
			call.setCallCardId(
					Integer.parseInt(table_CALL_CARD.getValueAt(table_CALL_CARD.getSelectedRow(), 1).toString()));
			Infomation_CallCardJDialog infomation_CallCardJDialog = new Infomation_CallCardJDialog(
					call.getCallCardId());
			infomation_CallCardJDialog.setVisible(true);
		}

	}

	public void Load_Data_Books(BookDao dao) {
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
					return Integer.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				case 7:
					return ImageIcon.class;
				default:
					return String.class;
				}
			}
		};

		model.addColumn("NO.");
		model.addColumn("Id Book");
		model.addColumn("Title");
		model.addColumn("Author");
		model.addColumn("Quantity");
		model.addColumn("Id Category");
		model.addColumn("Name Category");
		model.addColumn("images");

		dao.getListBooks()
				.forEach(item -> model.addRow(new Object[] { item.getNo(), item.getBookId(), item.getTitle(),
						item.getAuthor(), item.getQuantity(), item.getCategoryId(), item.getNameCategory(),
						new ImageIcon(new ImageIcon("images/" + item.getImg()).getImage().getScaledInstance(100, 100,
								Image.SCALE_SMOOTH)) }));
		table_BOOKS.setModel(model);
		table_BOOKS.getTableHeader().setReorderingAllowed(false);
		table_BOOKS.setRowHeight(120);
	}

	public void Load_Data_CallCard(CallCardDao dao) {
		lbl_txt_title.setText("CALL CARD LIST");
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
		model.addColumn("Total");

		dao.getListCallCard().forEach(item -> model.addRow(new Object[] { item.getNo(), item.getCallCardId(),
				item.getPeopleId(), item.getPeopleName(), item.getClassName(), item.getQuantityBorrowed() }));

		table_CALL_CARD.setModel(model);
		table_CALL_CARD.getTableHeader().setReorderingAllowed(false);
	}

	protected void panel_Info_BooksAncestorAdded(AncestorEvent event) {
		BookDao dao = new BookDao();
		Load_Data_Books(dao);
	}

	protected void panel_Call_cardAncestorAdded(AncestorEvent event) {
		CallCardDao dao = new CallCardDao();
		Load_Data_CallCard(dao);
	}
//============================================================================================================

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

//=============================================================================================================
	protected void BookMenuItemView() {
		if (checkSelect()) {
			Infomation_BookJDialog infomation_BookJDialog = new Infomation_BookJDialog(this, true,
					table_BOOKS.getValueAt(table_BOOKS.getSelectedRow(), 1).toString());
			Dimension desktopSize = desktopPane.getSize();
			Dimension jInternalFrameSize = infomation_BookJDialog.getSize();
			infomation_BookJDialog.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
					(desktopSize.height - jInternalFrameSize.height) / 2);
			infomation_BookJDialog.setVisible(true);
		}
	}

	protected void BookMenuItemAdd() {
		Add_BookJDialog add_BookJDialog = new Add_BookJDialog(this, true);
		Dimension desktopSize = desktopPane.getSize();
		Dimension jInternalFrameSize = add_BookJDialog.getSize();
		add_BookJDialog.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
		add_BookJDialog.setVisible(true);
	}

//=============================================================================================================
	protected void CallCardMenuItemView() {
			CallCard_People call = new CallCard_People();
			call.setCallCardId(
					Integer.parseInt(table_CALL_CARD.getValueAt(table_CALL_CARD.getSelectedRow(), 1).toString()));
			Infomation_CallCardJDialog infomation_CallCardJDialog = new Infomation_CallCardJDialog(
					call.getCallCardId());
			infomation_CallCardJDialog.setVisible(true);
		
	}

	protected void CallCardMenuItemRemove() {
		CallCardDao dao = new CallCardDao();
		if (checkSelect()) {
			int result = JOptionPane.showConfirmDialog(null, "Are you want to remove it?", "Confirm",
					JOptionPane.ERROR_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				dao.RemoveCallCard(
						Integer.parseInt(table_CALL_CARD.getValueAt(table_CALL_CARD.getSelectedRow(), 1).toString()));
				JOptionPane.showMessageDialog(null, "Removed successful");
			} else {
				JOptionPane.showMessageDialog(null, "Removed fail");
			}
		}

	}

//=============================================================================================================
	protected void txtSearchActionPerformed(ActionEvent e) {
		String find = txtSearchBook.getText();
		DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) table_BOOKS.getRowSorter();
		if (sorter != null) {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + find));
		} else {
			sorter.setSortKeys(null);
		}

	}

	protected void btnDueListActionPerformed(ActionEvent e) {
		DueListJDialog notice = new DueListJDialog();
		Dimension desktopSize = desktopPane.getSize();
		Dimension jInternalFrameSize = notice.getSize();
		notice.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
		notice.setVisible(true);
	}

	protected void btnMostBorrowedActionPerformed(ActionEvent e) {
		ShowBookMostBorrowed most = new ShowBookMostBorrowed(this, true);
		Dimension desktopSize = desktopPane.getSize();
		Dimension jInternalFrameSize = most.getSize();
		most.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
		most.setVisible(true);
	}

	protected void btnAddCategoryActionPerformed(ActionEvent e) {
		Add_CategoryJDialog add_CategoryJDialog = new Add_CategoryJDialog(this, true);
		Dimension desktopSize = desktopPane.getSize();
		Dimension jInternalFrameSize = add_CategoryJDialog.getSize();
		add_CategoryJDialog.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
		add_CategoryJDialog.setVisible(true);
	}

	protected void btnAddCallCardActionPerformed(ActionEvent e) {
		Borrow_BooksJDialog callCardJDialog = new Borrow_BooksJDialog(this, "Borrowed Books", true);
		Dimension desktopSize = desktopPane.getSize();
		Dimension jInternalFrameSize = callCardJDialog.getSize();
		callCardJDialog.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
		callCardJDialog.setVisible(true);
	}

	protected void textFieldActionPerformed(ActionEvent e) {
		String find = txtSearchCallCard.getText();
		DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) table_CALL_CARD.getRowSorter();
		sorter.setRowFilter(RowFilter.regexFilter("(?i)" + find));
		sorter.setSortKeys(null);
	}

	private boolean checkSelect() {
		boolean check = false;
		if (!(table_BOOKS.getSelectedRow() == -1)) {
			check = true;
		}
		return check;
	}

	private boolean checkSelectRow() {
		boolean check = false;
		if (!(table_CALL_CARD.getSelectedRow() == -1)) {
			check = true;
		}
		return check;
	}

	protected void btnRemovedListActionPerformed(ActionEvent e) {
		lbl_txt_title.setText("CALL CARD REMOVED LIST");
		People_CallCardDao dao = new People_CallCardDao();
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
		model.addColumn("Total");

		dao.getListCallCardFalse().forEach(item -> model.addRow(new Object[] { item.getNo(), item.getCallCardId(),
				item.getPeopleId(), item.getPeopleName(), item.getClassName(), item.getQuantityBorrowed() }));

		table_CALL_CARD.setModel(model);
		table_CALL_CARD.getTableHeader().setReorderingAllowed(false);
	}

	protected void btnCallCardListActionPerformed(ActionEvent e) {
		Load_Data_CallCard(new CallCardDao());
	}

	protected void btnPayBookActionPerformed(ActionEvent e) {
		PayBooksJDialog booksJDialog = new PayBooksJDialog(this, true);
		booksJDialog.setVisible(true);
	}

	protected void btnNewButtonActionPerformed(ActionEvent e) {
		CallCardDao dao = new CallCardDao();
		Book_BookDetail_CallCardDao bookdao = new Book_BookDetail_CallCardDao();
		int sumQuantity = bookdao.BookIsBorrowed(
				dateStart.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				dateEnd.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		lbl_txtBooksIsBorrowed.setText(sumQuantity + "");
		int totalOfGuests = dao.TotalOfGuests(
				dateStart.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				dateEnd.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		lbl_txtTotalOfGuests.setText(totalOfGuests + "");
		int totalCallCard = dao.TotalCallCard(
				dateStart.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				dateEnd.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		lbl_txtTotalCallCard.setText(totalCallCard + "");
		int totalGuestsBorrowing = dao.TotalGuestsBorrowing(
				dateStart.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				dateEnd.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		lbl_txtTotalGuestsBorrowing.setText(totalGuestsBorrowing + "");
		int totalCardOverdue = bookdao.TotalCardOverdue();
		lbl_txtTotalCardOverdue.setText(totalCardOverdue + "");

		People_CallCardDao dao1 = new People_CallCardDao();
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

		dao1.getListCheckDateBorrowed().forEach(item -> model.addRow(new Object[] { item.getNo(), item.getCallCardId(),
				item.getPeopleId(), item.getPeopleName(), item.getClassName(), item.getQuantityBrrowed(), }));

		table_OverDue.setModel(model);
		table_OverDue.getTableHeader().setReorderingAllowed(false);

	}

	protected void ViewOverDueList() {
		Detail_OverDue item = new Detail_OverDue();
		item.setIdCallCard(Integer.parseInt(table_OverDue.getValueAt(table_OverDue.getSelectedRow(), 1).toString()));
		DetailOverDueJDialog detailOverDueJDialog = new DetailOverDueJDialog(item.getIdCallCard());
		detailOverDueJDialog.setVisible(true);
	}

	protected void btnPayListActionPerformed(ActionEvent e) {
		lbl_txt_title.setText("PAY LIST");
		People_CallCardDao dao = new People_CallCardDao();
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
		model.addColumn("OverDue");

		dao.getPayList()
				.forEach(item -> model.addRow(
						new Object[] { item.getNo(), item.getCallCardId(), item.getPeopleId(), item.getPeopleName(),
								item.getClassName(), item.getQuantityBrrowed(), item.getOverdueFines() }));

		table_CALL_CARD.setModel(model);
		table_CALL_CARD.getTableHeader().setReorderingAllowed(false);
	}

	protected void btnchangepassActionPerformed(ActionEvent e) {
		ChangesPass pass = new ChangesPass(password, user, this, true);
		this.setVisible(true);
		pass.setVisible(true);

	}
}
