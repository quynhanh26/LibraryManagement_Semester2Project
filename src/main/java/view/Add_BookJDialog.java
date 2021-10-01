package view;

import java.awt.BorderLayout;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.BookDao;
import dao.CategoryDao;
import entities.Books;
import entities.Category;
import helper.LimitedCharDocument;
import helper.Regex;
import helper.Validate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Add_BookJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitleBook;
	private JLabel lblAuthor;
	private JLabel lblCategoryId;
	private JLabel lblQuantity;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtQuantity;
	private JComboBox comboBox_Category;

	private JButton btnAdd;
	private JTextField txtBookID;
	private JLabel lbl_Title_AddBook;
	private JLabel lbl_Error_BookID;
	private JLabel lbl_Error_TitleBook;
	private JLabel lbl_Error_Author;
	private JLabel lbl_Error_CategoryId;
	private JLabel lbl_Error_Quantity;
	private JLabel lblImg;
	private JButton btnimmg;
	private String path;
	private String nameImg;

	public Add_BookJDialog(Frame owner, boolean modal) {
		super(owner, modal);
		setTitle("ADD BOOK");

		Layout();
	}

	private void Layout() {
		setBounds(100, 100, 869, 516);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblBookID = new JLabel("Book ID:");
		lblBookID.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblTitleBook = new JLabel("Title Book:");
		lblTitleBook.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblCategoryId = new JLabel("Category ID:");
		lblCategoryId.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 17));

		txtTitle = new JTextField(new LimitedCharDocument(50), "", 10);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtAuthor = new JTextField("", 10);
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtQuantity = new JTextField(new LimitedCharDocument(10), "", 10);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtQuantity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});

		comboBox_Category = new JComboBox();
		comboBox_Category.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});

		txtBookID = new JTextField(new LimitedCharDocument(20), "", 10);
		txtBookID.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbl_Title_AddBook = new JLabel("Create new Book");
		lbl_Title_AddBook.setForeground(new Color(50, 205, 50));
		lbl_Title_AddBook.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title_AddBook.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));

		lbl_Error_BookID = new JLabel(" ");
		lbl_Error_BookID.setForeground(new Color(255, 0, 0));

		lbl_Error_TitleBook = new JLabel(" ");
		lbl_Error_TitleBook.setForeground(new Color(255, 0, 0));

		lbl_Error_Author = new JLabel(" ");
		lbl_Error_Author.setForeground(new Color(255, 0, 0));

		lbl_Error_CategoryId = new JLabel(" ");
		lbl_Error_CategoryId.setForeground(new Color(255, 0, 0));

		lbl_Error_Quantity = new JLabel(" ");
		lbl_Error_Quantity.setForeground(new Color(255, 0, 0));

		lblImg = new JLabel("");

		btnimmg = new JButton("Browse //");
		btnimmg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnimmgActionPerformed(e);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(174).addComponent(btnAdd,
										GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
												gl_contentPanel.createSequentialGroup().addGap(
														10).addComponent(lbl_Title_AddBook, GroupLayout.PREFERRED_SIZE,
																450, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
														.addComponent(lblBookID, GroupLayout.PREFERRED_SIZE, 118,
																GroupLayout.PREFERRED_SIZE)
														.addGap(4)
														.addGroup(gl_contentPanel
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(lbl_Error_BookID,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(txtBookID, GroupLayout.DEFAULT_SIZE, 328,
																		Short.MAX_VALUE)))
												.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
														.addComponent(lblTitleBook, GroupLayout.PREFERRED_SIZE, 118,
																GroupLayout.PREFERRED_SIZE)
														.addGap(4)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(
																		lbl_Error_TitleBook, GroupLayout.PREFERRED_SIZE,
																		328, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 328,
																		GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
														.addComponent(lblAuthor, GroupLayout.PREFERRED_SIZE, 118,
																GroupLayout.PREFERRED_SIZE)
														.addGap(4)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(
																		lbl_Error_Author, GroupLayout.PREFERRED_SIZE,
																		328, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtAuthor, GroupLayout.PREFERRED_SIZE,
																		328, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
														.addComponent(lblCategoryId, GroupLayout.PREFERRED_SIZE, 118,
																GroupLayout.PREFERRED_SIZE)
														.addGap(4)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(lbl_Error_CategoryId,
																		GroupLayout.PREFERRED_SIZE, 328,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(comboBox_Category,
																		GroupLayout.PREFERRED_SIZE, 328,
																		GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPanel
												.createSequentialGroup().addContainerGap()
												.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 118,
														GroupLayout.PREFERRED_SIZE)
												.addGap(4)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lbl_Error_Quantity, GroupLayout.PREFERRED_SIZE,
																328, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 328,
																GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup().addGap(42)
														.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 297,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup().addGap(83)
														.addComponent(btnimmg)))))
						.addContainerGap(44, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup()
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
						.createSequentialGroup().addGap(10)
						.addComponent(lbl_Title_AddBook, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addGap(37)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBookID, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtBookID, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_Error_BookID)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitleBook, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(txtTitle,
										GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_Error_TitleBook)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAuthor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAuthor, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_Error_Author).addGap(8)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCategoryId, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(
										comboBox_Category, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_Error_CategoryId)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(txtQuantity,
										GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_Error_Quantity).addGap(18)
						.addComponent(btnAdd))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(27)
								.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
								.addGap(33).addComponent(btnimmg)))
				.addContainerGap(23, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		addListToComboboxTwo();
	}

	public void addListToComboboxTwo() {
		CategoryDao dao = new CategoryDao();
		DefaultComboBoxModel<Category> cateModel = new DefaultComboBoxModel<Category>();
		for (Category cate : dao.getNameCategory()) {
			cateModel.addElement(cate);
		}
		comboBox_Category.setModel(cateModel);

	}

	protected void btnAddActionPerformed(ActionEvent e) {
		BookDao dao = new BookDao();
		Books book = new Books();

		if (Validate.checkRegex(Regex.ID, txtBookID.getText().trim()) && !txtBookID.getText().isEmpty()) {
			if (dao.check_IDBook(txtBookID.getText().trim()) == false) {
				book.setBookId(txtBookID.getText().trim());
				lbl_Error_BookID.setText(" ");
			} else {
				lbl_Error_BookID.setText("ID already exists");
			}
		} else {
			lbl_Error_BookID.setText("ID is invalid! ID length must be greater than 3 and less than 20");
		}

		if (Validate.checkRegex(Regex.TITLE, txtTitle.getText().trim()) && !txtTitle.getText().isEmpty()) {
			book.setTitle(txtTitle.getText().trim());
			lbl_Error_TitleBook.setText(" ");
		} else {
			lbl_Error_TitleBook.setText("Title length must be than 5");
		}

		if (Validate.checkRegex(Regex.CHARS, txtAuthor.getText().trim()) && !txtAuthor.getText().isEmpty()) {
			book.setAuthor(txtAuthor.getText().trim());
			lbl_Error_Author.setText(" ");
		} else if (txtAuthor.getText().isEmpty()) {
			book.setAuthor("Unknown");
		}

		if (Validate.checkRegex(Regex.NUM, txtQuantity.getText()) && !txtQuantity.getText().isEmpty()) {
			book.setQuantity(Integer.parseInt(txtQuantity.getText()));
			lbl_Error_Quantity.setText(" ");
		} else {
			lbl_Error_Quantity.setText("Quantity must be number");
		}
		book.setCategoryId(((Category) comboBox_Category.getSelectedItem()).getId());
		nameImg = book.getTitle() + ".png";
		book.setImg(nameImg);
		if (BookDao.addBook(book)) {
			decoder(encoder(path), nameImg);
		} else
			JOptionPane.showMessageDialog(null, "false");
		Library_Management library_Management = new Library_Management();
		library_Management.Load_Data_Books(dao);
	}

	protected void btnimmgActionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("please enter images: ");
		chooser.setFileFilter(new FileNameExtensionFilter("image(png, jpg, gif)", "png", "jpg", "gif"));
		int result = chooser.showOpenDialog(null);
		if (result == chooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			path = f.getAbsolutePath();
			lblImg.setIcon(new ImageIcon(path));
		}
	}

	public static String encoder(String imagePath) {
		String base64Image = "";
		File file = new File(imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte[] imageData = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return base64Image;
	}

	public static void decoder(String base64Image, String nameImg) {
		try (FileOutputStream imageOutFile = new FileOutputStream("images\\" + nameImg)) {
			// Converting a Base64 String into Image byte array
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
	}
}
