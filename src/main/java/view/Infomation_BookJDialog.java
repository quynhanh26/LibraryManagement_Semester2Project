package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BookDao;
import dao.CategoryDao;
import entities.Books;
import entities.Category;
import helper.Regex;
import helper.Validate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Infomation_BookJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitleBook;
	private JLabel lblAuthor;
	private JLabel lblCategoryId;
	private JLabel lblQuantity;
	private JLabel lblBookID_txt;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtQuantity;
	private JComboBox comboBox_Category;
	private String idBook;
	private Books book;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblImg;
	private JLabel lbl_Error_Title;
	private JLabel lbl_Error_Author;
	private JLabel lbl_Error_IdCategory;
	private JLabel lbl_Error_Quantity;
	private JButton btnimmg;
	private String path;
	private boolean check = false;

	public Infomation_BookJDialog(Frame owner, boolean modal, String book) {
		super(owner, modal);
		idBook = book;
		Layout();
	}

	private void Layout() {
		setBounds(100, 100, 539, 530);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 519, 493);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		JLabel lblBookID = new JLabel("Book ID:");
		lblBookID.setBounds(15, 179, 118, 34);
		lblBookID.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblTitleBook = new JLabel("Title Book:");
		lblTitleBook.setBounds(15, 239, 118, 34);
		lblTitleBook.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(15, 303, 118, 34);
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblCategoryId = new JLabel("Category ID:");
		lblCategoryId.setBounds(15, 368, 118, 34);
		lblCategoryId.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblBookID_txt = new JLabel(" ");
		lblBookID_txt.setBounds(176, 179, 328, 34);
		lblBookID_txt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBookID_txt.setText(idBook);
		BookDao dao = new BookDao();
		book = dao.selectBook_WithID(idBook);

		txtTitle = new JTextField();
		txtTitle.setBounds(176, 240, 328, 33);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTitle.setColumns(10);
		txtTitle.setText(book.getTitle());

		txtAuthor = new JTextField();
		txtAuthor.setBounds(176, 304, 328, 33);
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAuthor.setColumns(10);
		txtAuthor.setText(book.getAuthor());

		comboBox_Category = new JComboBox();
		comboBox_Category.setBounds(176, 368, 328, 34);
		comboBox_Category.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(15, 21, 155, 33);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateActionPerformed(e);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setBounds(15, 64, 155, 33);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblImg = new JLabel("");
		lblImg.setBorder(new LineBorder(Color.BLACK));
		lblImg.setBounds(278, 5, 145, 164);
		lblImg.setIcon(new ImageIcon("images/" + book.getImg()));

		lbl_Error_Title = new JLabel(" ");
		lbl_Error_Title.setForeground(Color.RED);
		lbl_Error_Title.setBounds(176, 245, 328, 15);
		lbl_Error_Title.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.setLayout(null);
		contentPanel.add(btnUpdate);
		contentPanel.add(btnDelete);
		contentPanel.add(lblImg);
		contentPanel.add(lblBookID);
		contentPanel.add(lblBookID_txt);
		contentPanel.add(lblAuthor);
		contentPanel.add(lblCategoryId);
		contentPanel.add(comboBox_Category);
		contentPanel.add(lblTitleBook);
		contentPanel.add(txtTitle);
		contentPanel.add(txtAuthor);
		contentPanel.add(lbl_Error_Title);

		lbl_Error_Author = new JLabel(" ");
		lbl_Error_Author.setForeground(Color.RED);
		lbl_Error_Author.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Error_Author.setBounds(176, 313, 328, 15);
		contentPanel.add(lbl_Error_Author);

		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(15, 432, 118, 34);
		contentPanel.add(lblQuantity);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 17));

		txtQuantity = new JTextField();
		txtQuantity.setBounds(176, 433, 328, 33);
		contentPanel.add(txtQuantity);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtQuantity.setColumns(10);
		txtQuantity.setText(book.getQuantity() + "");
		txtQuantity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
				batnull(e);
			}
		});

		lbl_Error_IdCategory = new JLabel(" ");
		lbl_Error_IdCategory.setForeground(Color.RED);
		lbl_Error_IdCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Error_IdCategory.setBounds(176, 374, 328, 15);
		contentPanel.add(lbl_Error_IdCategory);

		lbl_Error_Quantity = new JLabel(" ");
		lbl_Error_Quantity.setForeground(Color.RED);
		lbl_Error_Quantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Error_Quantity.setBounds(176, 438, 328, 15);
		contentPanel.add(lbl_Error_Quantity);

		btnimmg = new JButton("Browse //");
		btnimmg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnimmgActionPerformed(e);
			}
		});
		btnimmg.setBounds(425, 150, 79, 23);
		contentPanel.add(btnimmg);
		addListToComboboxTwo();
	}

	public void addListToComboboxTwo() {
		CategoryDao dao = new CategoryDao();
		DefaultComboBoxModel<Category> cateModel = new DefaultComboBoxModel<Category>();
		for (Category cate : dao.getNameCategory()) {
			cateModel.addElement(cate);
			comboBox_Category.setModel(cateModel);
			if (cate.getId().contentEquals(book.getCategoryId())) {
				cateModel.setSelectedItem(cate);
			}
		}
	}

	protected void btnUpdateActionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you want to fix it?", "Confirm",
				JOptionPane.ERROR_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			Books book = new Books();
			book.setBookId(lblBookID_txt.getText());
			if (Validate.checkRegex(Regex.TITLE, txtTitle.getText().trim()) && !txtTitle.getText().isEmpty()) {
				book.setTitle(txtTitle.getText());
			} else {
				JOptionPane.showMessageDialog(null, "Title length must be than 5");
			}

			if (!txtAuthor.getText().isEmpty()) {
				book.setAuthor(txtAuthor.getText());
			} else {
				book.setAuthor("Unknow");
			}

			book.setQuantity(Integer.parseInt(txtQuantity.getText()));
			book.setCategoryId(((Category) comboBox_Category.getSelectedItem()).getId());
			if (check) {
				book.setImg(txtTitle.getText() + ".png");
			} else {
				book.setImg(new BookDao().selectBook_WithID(idBook).getImg());
			}
			if(BookDao.updateBooks(book) && check) {
				decoder(encoder(path), book.getImg());
			}
		}

	}

	protected void batnull(KeyEvent e) {
		if (txtTitle.getText().equals("") || txtQuantity.getText().equals("")) {

			btnUpdate.setEnabled(false);
		} else
			btnUpdate.setEnabled(true);

	}

	protected void btnDeleteActionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you want to delete it?", "Confirm",
				JOptionPane.ERROR_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			BookDao dao = new BookDao();
			dao.deleteBook(lblBookID_txt.getText());
			JOptionPane.showMessageDialog(null, "Delete successful");
			setVisible(false);
		}
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
			check = true;
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
