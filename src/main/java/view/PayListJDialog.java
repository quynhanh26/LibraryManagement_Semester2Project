package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CallCardDao;
import dao.People_CallCardDao;
import model_view.CallCard_People;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class PayListJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable table_PayList;
	private JLabel lblHeader;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			PayListJDialog dialog = new PayListJDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public PayListJDialog() {
		setBounds(100, 100, 696, 405);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		
		lblHeader = new JLabel("Pay List");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 26));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(259)
							.addComponent(lblHeader, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(30)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 603, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHeader, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
					.addGap(142))
		);

		table_PayList = new JTable();
		scrollPane.setViewportView(table_PayList);
		contentPanel.setLayout(gl_contentPanel);

		JPopupMenu popupMenuPayList = new JPopupMenu();
		JMenuItem menuItemViewPayList = new JMenuItem("View");
		menuItemViewPayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PayListMenuItemView();
			}
		});

		popupMenuPayList.add(menuItemViewPayList);
		table_PayList.setComponentPopupMenu(popupMenuPayList);
		
		LoadData(new People_CallCardDao());
	}

	protected void PayListMenuItemView() {
		if (checkSelect()) {
			CallCard_People call = new CallCard_People();
			call.setCallCardId(
					Integer.parseInt(table_PayList.getValueAt(table_PayList.getSelectedRow(), 1).toString()));
			Infomation_CallCardJDialog infomation_CallCardJDialog = new Infomation_CallCardJDialog(
					call.getCallCardId());
			infomation_CallCardJDialog.setVisible(true);
		}
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
				case 6 :
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

		dao.getPayList().forEach(item -> model.addRow(new Object[] { item.getNo(), item.getCallCardId(),
				item.getPeopleId(), item.getPeopleName(), item.getClassName(), item.getQuantityBrrowed(), item.getOverdueFines() }));

		table_PayList.setModel(model);
		table_PayList.getTableHeader().setReorderingAllowed(false);
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
		
	private boolean checkSelect() {
		boolean check = false;
		if (!(table_PayList.getSelectedRow() == -1)) {
			check = true;
		}
		return check;
	}
}
