package piikki;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {

	protected double version = 1.41;

	protected GUIController controller; // @jve:decl-index=0:

	private JFrame jFrame = null; // @jve:decl-index=0:visual-constraint="2,1"

	private JMenuBar jJMenuBar = null;

	private JMenu fileMenu = null;

	private JMenuItem exitMenuItem = null;

	private JMenuItem setBackupPathMenuItem = null;

	private JTabbedPane tabPanel = null;

	private JPanel tapJPanel = null;

	private JPanel adminJPanel = null;

	private JPanel buyJPanel = null;

	private JPanel historyJPanel = null;

	private JPanel buy_ordererJPanel = null;

	private JPanel buy_readyDrinksJPanel = null;

	private JPanel buy_actionJPanel = null;

	private JLabel buy_ordererJLabel = null;

	private JComboBox buy_ordererJComboBox = null;

	private JLabel buy_ordererTapJLabel = null;

	private JTextField buy_ordererTapJTextField = null;

	private JLabel buy_readyDrinksDrinkJLabel = null;

	private JLabel buy_readyDrinksPriceJLabel = null;

	private JLabel buy_readyDrinksAmountJLabel = null;

	private JButton buy_readyDrinksDrink1JButton = null;

	private JTextField buy_readyDrinksDrink1CostJTextField = null;

	private JLabel buy_readyDrinksDrink1AmountJLabel = null;

	private JButton buy_readyDrinksDrink2JButton = null;

	private JTextField buy_readyDrinksDrink2CostJTextField = null;

	private JLabel buy_readyDrinksDrink2AmountJLabel = null;

	private JButton buy_readyDrinksDrink3JButton = null;

	private JTextField buy_readyDrinksDrink3CostJTextField = null;

	private JLabel buy_readyDrinksDrink3AmountJLabel = null;

	private JButton buy_readyDrinksDrink4JButton = null;

	private JTextField buy_readyDrinksDrink4CostJTextField = null;

	private JLabel buy_readyDrinksDrink4AmountJLabel = null;

	private JButton buy_actionBuyJButton = null;

	private JLabel buy_actionPriceJLabel = null;

	private JTextField buy_actionPriceJTextField = null;

	private JScrollPane history_tableJScrollPane = null;

	private JTable history_tableJTable = null;

	private JTextField buy_readyDrinksOtherJTextField = null;

	private JLabel admin_infoTextJLabel = null;

	private JPanel admin_ActionJPanel = null;

	private JPanel admin_logsJPanel = null;

	private JPanel admin_modifyUserJPanel = null;

	private JPanel admin_newUserJPanel = null;

	private JPanel admin_removeUserJPanel = null;

	private JLabel admin_modifyUserNameJLabel = null;

	private JComboBox admin_modifyUserNameJComboBox = null;

	private JRadioButton admin_modifyUserPlusJRadioButton = null;

	private JRadioButton admin_modifyUserMinusJRadioButton = null;

	private JTextField admin_modifyUserAmountJTextField = null;

	private JButton admin_modifyUserModifyJButton = null;

	private JLabel admin_modifyUserEJLabel = null;

	private JLabel buy_ordererExtraJLabel = null;

	private JTextField buy_ordererExtraJTextField = null;

	private JLabel admin_newUserNameJLabel = null;

	private JTextField admin_newUserNameJTextField = null;

	private JButton admin_newUserAddJButton = null;

	private JLabel admin_newUserExtraJLabel = null;

	private JTextField admin_newUserExtraJTextField = null;

	private JLabel admin_removeUserNameJLabel = null;

	private JComboBox admin_removeUserNameJComboBox = null;

	private JButton admin_removeUserRemoveJButton = null;

	private JScrollPane admin_logsJScrollPanel = null;

	private JTable admin_logsJTable = null;

	private JPanel statisticsJPanel = null;

	private JPanel stats_up = null;

	private JPanel stats_down = null;

	private JRadioButton admin_modifyUserDiscountYesJRadioButton = null;

	private JRadioButton admin_modifyUserDiscountNoJRadioButton = null;

	private JLabel admin_modifyUserDiscountNameJLabel = null;

	private JButton buy_readyDrinksOtherJButton = null;

	private JTextField admin_modifyUserExtraJTextField = null;

	private JLabel admin_modifyUserExtraJLabel = null;

	private JMenuItem importMenuItem = null;

	private JLabel buy_readyDrinksDiscountJLabel = null;

	private JPanel pricelistJPanel = null;

	private JScrollPane pricelist_productsJScrollPane = null;

	private JTable pricelist_productsJTable = null;

	private JPanel pricelist_tableJPanel = null;

	private JPanel pricelist_controlsJPanel = null;

	private JLabel pricelist_infoJLabel = null;

	private JPanel pricelist_AddJPanel = null;

	private JPanel pricelist_EditJPanel = null;

	private JLabel pricelist_addNameJLabel = null;

	private JTextField pricelist_addNameJTextField = null;

	private JLabel pricelist_addPriceJLabel = null;

	private JButton pricelist_addAddJButton = null;

	private JTextField pricelist_addPriceJTextField = null;

	private JLabel pricelist_editNameJLabel = null;

	private JComboBox pricelist_editNameJComboBox = null;

	private JLabel pricelist_editPriceJLabel = null;

	private JTextField pricelist_editPriceJTextField = null;

	private JLabel pricelist_editNumberJLabel = null;

	private JComboBox pricelist_editNumberJComboBox = null;

	private JButton pricelist_editModifyJButton = null;

	private JButton pricelist_editDeleteJButton = null;

	private JComboBox buy_readyDrinksSelectJComboBox = null;

	private JTextField buy_readyDrinksSelectCostJTextField = null;

	private JLabel buy_readyDrinksSelectAmountJLabel = null;

	private JButton buy_readyDrinksSelectJButton = null;

	private JLabel stats_totalJLabel = null;

	private JTextField stats_totalJTextField = null;

	private JPanel stats_thirdChartJPanel = null;

	private JMenu backupMenu = null;

	private JMenuItem eraseJMenuItem = null;

	private JPanel dbQueryJPanel = null;

	private JPanel balances_totalJPanel = null;

	private JPanel balances_summaryTableJPanel = null;

	private JPanel dbQuery_totalsJPanel = null;

	private JPanel dbQuery_tableJPanel = null;

	private JLabel dbQuery_totalsJLabel = null;

	private JPanel dbQuery_queryJPanel = null;

	private JLabel dbQuery_queryJLabel = null;

	private JTextField dbQuery_queryJTextField = null;

	private JButton dbQuery_queryJButton = null;

	private JScrollPane dbQuery_tableJScrollBar = null;

	private JTable dbQuery_tableJTable = null;

	private JTextField dbQuery_totalsJTextField = null;
	private JMenuItem removeLogsMenuItem;

	/**
	 * This method initializes pricelistJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPricelistJPanel() {
		if (pricelistJPanel == null) {
			GridBagConstraints gridBagConstraints72 = new GridBagConstraints();
			gridBagConstraints72.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints72.gridx = 0;
			gridBagConstraints72.gridy = 0;
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.anchor = GridBagConstraints.NORTH;
			gridBagConstraints71.weightx = 1.0D;
			gridBagConstraints71.fill = GridBagConstraints.BOTH;
			gridBagConstraints71.gridx = 0;
			gridBagConstraints71.gridy = 1;
			gridBagConstraints71.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints71.weighty = 1.0D;
			pricelistJPanel = new JPanel();
			pricelistJPanel.setLayout(new GridBagLayout());
			pricelistJPanel.add(getPricelist_controlsJPanel(),
					gridBagConstraints72);
			pricelistJPanel.add(getPricelist_tableJPanel(),
					gridBagConstraints71);
		}
		return pricelistJPanel;
	}

	/**
	 * This method initializes pricelist_productsJScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getPricelist_productsJScrollPane() {
		if (pricelist_productsJScrollPane == null) {
			pricelist_productsJScrollPane = new JScrollPane();
			pricelist_productsJScrollPane
					.setViewportView(getPricelist_productsJTable());
		}
		return pricelist_productsJScrollPane;
	}

	/**
	 * This method initializes pricelist_productsJTable
	 * 
	 * @return javax.swing.JTable
	 */
	protected JTable getPricelist_productsJTable() {
		if (pricelist_productsJTable == null) {
			pricelist_productsJTable = new JTable();
			pricelist_productsJTable.setRowSelectionAllowed(true);
			pricelist_productsJTable.setEnabled(false);
		}
		return pricelist_productsJTable;
	}

	/**
	 * This method initializes pricelist_tableJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPricelist_tableJPanel() {
		if (pricelist_tableJPanel == null) {
			GridBagConstraints gridBagConstraints70 = new GridBagConstraints();
			gridBagConstraints70.fill = GridBagConstraints.BOTH;
			gridBagConstraints70.gridy = 2;
			gridBagConstraints70.weightx = 1.0;
			gridBagConstraints70.weighty = 1.0;
			gridBagConstraints70.gridx = 0;
			pricelist_tableJPanel = new JPanel();
			pricelist_tableJPanel.setLayout(new GridBagLayout());
			pricelist_tableJPanel.setBorder(BorderFactory.createTitledBorder(
					null, "Hinnasto", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			pricelist_tableJPanel.add(getPricelist_productsJScrollPane(),
					gridBagConstraints70);
		}
		return pricelist_tableJPanel;
	}

	protected JLabel getPricelist_infoJLabel() {
		if (pricelist_infoJLabel == null) {
			pricelist_infoJLabel = new JLabel();
			pricelist_infoJLabel.setText("");
			pricelist_infoJLabel.setBackground(Color.RED);
			pricelist_infoJLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		return pricelist_infoJLabel;
	}

	/**
	 * This method initializes pricelist_controlsJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPricelist_controlsJPanel() {
		if (pricelist_controlsJPanel == null) {
			GridBagConstraints gridBagConstraints58 = new GridBagConstraints();
			gridBagConstraints58.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints58.gridy = 0;
			gridBagConstraints58.gridx = 0;
			GridBagConstraints gridBagConstraints57 = new GridBagConstraints();
			gridBagConstraints57.gridx = 0;
			gridBagConstraints57.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints57.gridy = 2;
			GridBagConstraints gridBagConstraints53 = new GridBagConstraints();
			gridBagConstraints53.gridx = 0;
			gridBagConstraints53.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints53.gridy = 1;
			pricelist_controlsJPanel = new JPanel();
			pricelist_controlsJPanel.setLayout(new GridBagLayout());
			pricelist_controlsJPanel.add(getPricelist_infoJLabel(),
					gridBagConstraints58);
			pricelist_controlsJPanel.add(getPricelist_AddJPanel(),
					gridBagConstraints53);
			pricelist_controlsJPanel.add(getPricelist_EditJPanel(),
					gridBagConstraints57);
		}
		return pricelist_controlsJPanel;
	}

	/**
	 * This method initializes pricelist_AddJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPricelist_AddJPanel() {
		if (pricelist_AddJPanel == null) {
			GridBagConstraints gridBagConstraints75 = new GridBagConstraints();
			gridBagConstraints75.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints75.gridy = 0;
			gridBagConstraints75.gridx = 3;
			gridBagConstraints75.ipadx = 40;
			GridBagConstraints gridBagConstraints78 = new GridBagConstraints();
			gridBagConstraints78.gridx = 0;
			gridBagConstraints78.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints78.gridy = 0;
			GridBagConstraints gridBagConstraints77 = new GridBagConstraints();
			gridBagConstraints77.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints77.gridx = 2;
			gridBagConstraints77.gridy = 0;
			GridBagConstraints gridBagConstraints76 = new GridBagConstraints();
			gridBagConstraints76.gridx = 4;
			gridBagConstraints76.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints76.gridy = 0;
			pricelist_addPriceJLabel = new JLabel();
			pricelist_addPriceJLabel.setText("Hinta:");
			GridBagConstraints gridBagConstraints74 = new GridBagConstraints();
			gridBagConstraints74.gridy = 0;
			gridBagConstraints74.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints74.gridx = 1;
			gridBagConstraints74.ipadx = 200;
			pricelist_addNameJLabel = new JLabel();
			pricelist_addNameJLabel.setText("Nimi:");
			pricelist_AddJPanel = new JPanel();
			pricelist_AddJPanel.setLayout(new GridBagLayout());
			pricelist_AddJPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lisää tuote", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(51, 51, 51)));
			pricelist_AddJPanel.add(pricelist_addNameJLabel,
					gridBagConstraints78);
			pricelist_AddJPanel.add(getPricelist_addNameJTextField(),
					gridBagConstraints74);
			pricelist_AddJPanel.add(pricelist_addPriceJLabel,
					gridBagConstraints77);
			pricelist_AddJPanel.add(getPricelist_addPriceJTextField(),
					gridBagConstraints75);
			pricelist_AddJPanel.add(getPricelist_addAddJButton(),
					gridBagConstraints76);
		}
		return pricelist_AddJPanel;
	}

	/**
	 * This method initializes pricelist_EditJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPricelist_EditJPanel() {
		if (pricelist_EditJPanel == null) {
			GridBagConstraints gridBagConstraints56 = new GridBagConstraints();
			gridBagConstraints56.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints56.gridx = 6;
			gridBagConstraints56.gridy = 1;
			GridBagConstraints gridBagConstraints54 = new GridBagConstraints();
			gridBagConstraints54.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints54.gridx = 6;
			gridBagConstraints54.gridy = 0;
			GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
			gridBagConstraints52.gridy = 0;
			gridBagConstraints52.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints52.gridheight = 2;
			gridBagConstraints52.gridx = 4;
			GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints50.gridx = 2;
			gridBagConstraints50.gridy = 0;
			gridBagConstraints50.gridheight = 2;
			GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
			gridBagConstraints33.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints33.gridy = 0;
			gridBagConstraints33.gridheight = 2;
			gridBagConstraints33.gridx = 0;
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints81.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints81.gridy = 0;
			gridBagConstraints81.gridx = 5;
			gridBagConstraints81.gridheight = 2;
			gridBagConstraints81.ipadx = 5;
			gridBagConstraints81.weightx = 0.5D;
			pricelist_editNumberJLabel = new JLabel();
			pricelist_editNumberJLabel.setText("Numero:");
			GridBagConstraints gridBagConstraints80 = new GridBagConstraints();
			gridBagConstraints80.ipadx = 40;
			gridBagConstraints80.gridheight = 2;
			gridBagConstraints80.gridx = 3;
			gridBagConstraints80.gridy = 0;
			gridBagConstraints80.insets = new Insets(5, 5, 5, 5);
			pricelist_editPriceJLabel = new JLabel();
			pricelist_editPriceJLabel.setText("Hinta:");
			GridBagConstraints gridBagConstraints79 = new GridBagConstraints();
			gridBagConstraints79.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints79.gridx = 1;
			gridBagConstraints79.gridy = 0;
			gridBagConstraints79.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints79.gridheight = 2;
			gridBagConstraints79.weightx = 0.5D;
			pricelist_editNameJLabel = new JLabel();
			pricelist_editNameJLabel.setText("Nimi:");
			pricelist_EditJPanel = new JPanel();
			pricelist_EditJPanel.setLayout(new GridBagLayout());
			pricelist_EditJPanel.setBorder(BorderFactory.createTitledBorder(
					null, "Muokkaa tuotetta",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			pricelist_EditJPanel.add(pricelist_editNameJLabel,
					gridBagConstraints33);
			pricelist_EditJPanel.add(getPricelist_editNameJComboBox(),
					gridBagConstraints79);
			pricelist_EditJPanel.add(pricelist_editPriceJLabel,
					gridBagConstraints50);
			pricelist_EditJPanel.add(getPricelist_editPriceJTextField(),
					gridBagConstraints80);
			pricelist_EditJPanel.add(pricelist_editNumberJLabel,
					gridBagConstraints52);
			pricelist_EditJPanel.add(getPricelist_editNumberJComboBox(),
					gridBagConstraints81);
			pricelist_EditJPanel.add(getPricelist_editModifyJButton(),
					gridBagConstraints54);
			pricelist_EditJPanel.add(getPricelist_editDeleteJButton(),
					gridBagConstraints56);
		}
		return pricelist_EditJPanel;
	}

	/**
	 * This method initializes pricelist_addNameJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getPricelist_addNameJTextField() {
		if (pricelist_addNameJTextField == null) {
			pricelist_addNameJTextField = new JTextField();
			pricelist_addNameJTextField.addCaretListener(controller);
		}
		return pricelist_addNameJTextField;
	}

	/**
	 * This method initializes pricelist_addAddJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getPricelist_addAddJButton() {
		if (pricelist_addAddJButton == null) {
			pricelist_addAddJButton = new JButton();
			pricelist_addAddJButton.setText("Lisää");
			pricelist_addAddJButton.setEnabled(false);
			pricelist_addAddJButton.addActionListener(controller);
		}
		return pricelist_addAddJButton;
	}

	/**
	 * This method initializes pricelist_addPriceJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getPricelist_addPriceJTextField() {
		if (pricelist_addPriceJTextField == null) {
			pricelist_addPriceJTextField = new JTextField();
			pricelist_addPriceJTextField.addCaretListener(controller);
		}
		return pricelist_addPriceJTextField;
	}

	/**
	 * This method initializes pricelist_editNameJComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	protected JComboBox getPricelist_editNameJComboBox() {
		if (pricelist_editNameJComboBox == null) {
			pricelist_editNameJComboBox = new JComboBox();
			pricelist_editNameJComboBox.setMaximumRowCount(15);
			pricelist_editNameJComboBox.addItemListener(controller);
		}
		return pricelist_editNameJComboBox;
	}

	/**
	 * This method initializes pricelist_editPriceJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getPricelist_editPriceJTextField() {
		if (pricelist_editPriceJTextField == null) {
			pricelist_editPriceJTextField = new JTextField();
			pricelist_editPriceJTextField.addCaretListener(controller);
		}
		return pricelist_editPriceJTextField;
	}

	/**
	 * This method initializes pricelist_editNumberJComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	protected JComboBox getPricelist_editNumberJComboBox() {
		if (pricelist_editNumberJComboBox == null) {
			pricelist_editNumberJComboBox = new JComboBox();
		}
		return pricelist_editNumberJComboBox;
	}

	/**
	 * This method initializes pricelist_editModifyJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getPricelist_editModifyJButton() {
		if (pricelist_editModifyJButton == null) {
			pricelist_editModifyJButton = new JButton();
			pricelist_editModifyJButton.setText("Muokkaa");
			pricelist_editModifyJButton.addActionListener(controller);
		}
		return pricelist_editModifyJButton;
	}

	/**
	 * This method initializes pricelist_editDeleteJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getPricelist_editDeleteJButton() {
		if (pricelist_editDeleteJButton == null) {
			pricelist_editDeleteJButton = new JButton();
			pricelist_editDeleteJButton.setText("Poista tuote");
			pricelist_editDeleteJButton.addActionListener(controller);
		}
		return pricelist_editDeleteJButton;
	}

	/**
	 * This method initializes buy_readyDrinksSelectJComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	protected JComboBox getBuy_readyDrinksSelectJComboBox() {
		if (buy_readyDrinksSelectJComboBox == null) {
			buy_readyDrinksSelectJComboBox = new JComboBox();
			buy_readyDrinksSelectJComboBox.setEnabled(false);
			buy_readyDrinksSelectJComboBox.setMaximumRowCount(15);
			buy_readyDrinksSelectJComboBox.addItemListener(controller);
		}
		return buy_readyDrinksSelectJComboBox;
	}

	/**
	 * This method initializes buy_readyDrinksSelectCostJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_readyDrinksSelectCostJTextField() {
		if (buy_readyDrinksSelectCostJTextField == null) {
			buy_readyDrinksSelectCostJTextField = new JTextField();
			buy_readyDrinksSelectCostJTextField.setEditable(false);
		}
		return buy_readyDrinksSelectCostJTextField;
	}

	/**
	 * This method initializes buy_readyDrinksSelectJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getBuy_readyDrinksSelectJButton() {
		if (buy_readyDrinksSelectJButton == null) {
			buy_readyDrinksSelectJButton = new JButton();
			buy_readyDrinksSelectJButton
					.setPreferredSize(new Dimension(200, 26));
			buy_readyDrinksSelectJButton.setVisible(false);
			buy_readyDrinksSelectJButton.addActionListener(controller);
		}
		return buy_readyDrinksSelectJButton;
	}

	/**
	 * This method initializes stats_totalJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getStats_totalJTextField() {
		if (stats_totalJTextField == null) {
			stats_totalJTextField = new JTextField();
			stats_totalJTextField.setEditable(true);
			stats_totalJTextField.setEnabled(false);
		}
		return stats_totalJTextField;
	}

	/**
	 * This method initializes stats_thirdChartJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getStats_thirdChartJPanel() {
		if (stats_thirdChartJPanel == null) {
			stats_thirdChartJPanel = new JPanel();
			stats_thirdChartJPanel.setLayout(new GridBagLayout());
		}
		return stats_thirdChartJPanel;
	}

	/**
	 * This method initializes backupMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	protected JMenu getBackupMenu() {
		if (backupMenu == null) {
			backupMenu = new JMenu();
		}
		return backupMenu;
	}

	/**
	 * This method initializes eraseJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	protected JMenuItem getEraseJMenuItem() {
		if (eraseJMenuItem == null) {
			eraseJMenuItem = new JMenuItem();
			eraseJMenuItem.setText("Poista tietokanta");
			eraseJMenuItem.addActionListener(controller);
		}
		return eraseJMenuItem;
	}
	
	protected JMenuItem getRemoveLogsMenuItem() {
		if (removeLogsMenuItem == null) {
			removeLogsMenuItem = new JMenuItem("Poista logit");
			removeLogsMenuItem.addActionListener(controller);
		}
		return removeLogsMenuItem;
	}

	/**
	 * This method initializes dbQueryJPanel	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JPanel getDbQueryJPanel() {
		if (dbQueryJPanel == null) {
			GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
			gridBagConstraints91.gridy = 0;
			gridBagConstraints91.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints91.gridx = 0;
			GridBagConstraints gridBagConstraints89 = new GridBagConstraints();
			gridBagConstraints89.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints89.gridx = 0;
			gridBagConstraints89.gridy = 1;
			gridBagConstraints89.weighty = 1.0D;
			gridBagConstraints89.fill = GridBagConstraints.BOTH;
			gridBagConstraints89.gridheight = -1;
			GridBagConstraints gridBagConstraints88 = new GridBagConstraints();
			gridBagConstraints88.gridheight = -1;
			gridBagConstraints88.gridx = 0;
			gridBagConstraints88.gridy = 2;
			gridBagConstraints88.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints88.gridwidth = -1;
			dbQueryJPanel = new JPanel();
			dbQueryJPanel.setLayout(new GridBagLayout());
			dbQueryJPanel.add(getDbQuery_queryJPanel(), gridBagConstraints91);
			dbQueryJPanel.add(getDbQuery_tableJPanel(), gridBagConstraints89);
			dbQueryJPanel.add(getDbQuery_totalsJPanel(), gridBagConstraints88);
		}
		return dbQueryJPanel;
	}

	/**
	 * This method initializes balances_totalJPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBalances_totalJPanel() {
		if (balances_totalJPanel == null) {
			balances_totalJPanel = new JPanel();
			balances_totalJPanel.setLayout(new GridBagLayout());
		}
		return balances_totalJPanel;
	}

	/**
	 * This method initializes balances_summaryTableJPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBalances_summaryTableJPanel() {
		if (balances_summaryTableJPanel == null) {
			balances_summaryTableJPanel = new JPanel();
			balances_summaryTableJPanel.setLayout(new GridBagLayout());
		}
		return balances_summaryTableJPanel;
	}

	/**
	 * This method initializes dbQuery_totalsJPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDbQuery_totalsJPanel() {
		if (dbQuery_totalsJPanel == null) {
			GridBagConstraints gridBagConstraints96 = new GridBagConstraints();
			gridBagConstraints96.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints96.gridx = 1;
			gridBagConstraints96.gridy = 0;
			gridBagConstraints96.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints96.weightx = 1.0;
			GridBagConstraints gridBagConstraints90 = new GridBagConstraints();
			gridBagConstraints90.gridx = 0;
			gridBagConstraints90.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints90.gridy = 0;
			dbQuery_totalsJLabel = new JLabel();
			dbQuery_totalsJLabel.setText("Piikkien yhteissumma:");
			dbQuery_totalsJPanel = new JPanel();
			dbQuery_totalsJPanel.setLayout(new GridBagLayout());
			dbQuery_totalsJPanel.add(dbQuery_totalsJLabel, gridBagConstraints90);
			dbQuery_totalsJPanel.add(getDbQuery_totalsJTextField(), gridBagConstraints96);
		}
		return dbQuery_totalsJPanel;
	}

	/**
	 * This method initializes dbQuery_tableJPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDbQuery_tableJPanel() {
		if (dbQuery_tableJPanel == null) {
			GridBagConstraints gridBagConstraints95 = new GridBagConstraints();
			gridBagConstraints95.fill = GridBagConstraints.BOTH;
			gridBagConstraints95.weighty = 1.0;
			gridBagConstraints95.weightx = 1.0;
			dbQuery_tableJPanel = new JPanel();
			dbQuery_tableJPanel.setLayout(new GridBagLayout());
			dbQuery_tableJPanel.add(getDbQuery_tableJScrollBar(), gridBagConstraints95);
		}
		return dbQuery_tableJPanel;
	}

	/**
	 * This method initializes dbQuery_queryJPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDbQuery_queryJPanel() {
		if (dbQuery_queryJPanel == null) {
			GridBagConstraints gridBagConstraints94 = new GridBagConstraints();
			gridBagConstraints94.gridx = 2;
			gridBagConstraints94.gridy = 0;
			GridBagConstraints gridBagConstraints93 = new GridBagConstraints();
			gridBagConstraints93.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints93.gridy = 0;
			gridBagConstraints93.gridx = 0;
			GridBagConstraints gridBagConstraints92 = new GridBagConstraints();
			gridBagConstraints92.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints92.gridx = 1;
			gridBagConstraints92.gridy = 0;
			gridBagConstraints92.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints92.weightx = 1.0;
			dbQuery_queryJLabel = new JLabel();
			dbQuery_queryJLabel.setText("Huom. päivittyy ainoastaan uudelleenkäynnistyksen yhteydessä");
			dbQuery_queryJLabel.setVisible(true);
			dbQuery_queryJPanel = new JPanel();
			dbQuery_queryJPanel.setLayout(new GridBagLayout());
			dbQuery_queryJPanel.add(dbQuery_queryJLabel, gridBagConstraints93);
			dbQuery_queryJPanel.add(getDbQuery_queryJTextField(), gridBagConstraints92);
			dbQuery_queryJPanel.add(getDbQuery_queryJButton(), gridBagConstraints94);
		}
		return dbQuery_queryJPanel;
	}

	/**
	 * This method initializes dbQuery_queryJTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getDbQuery_queryJTextField() {
		if (dbQuery_queryJTextField == null) {
			dbQuery_queryJTextField = new JTextField();
			dbQuery_queryJTextField.setPreferredSize(new Dimension(400, 20));
			dbQuery_queryJTextField.setText("select * from Users order by balance;");
			dbQuery_queryJTextField.setVisible(false);
		}
		return dbQuery_queryJTextField;
	}

	/**
	 * This method initializes dbQuery_queryJButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getDbQuery_queryJButton() {
		if (dbQuery_queryJButton == null) {
			dbQuery_queryJButton = new JButton();
			dbQuery_queryJButton.setText("Hae");
			dbQuery_queryJButton.setVisible(false);
			dbQuery_queryJButton.addActionListener(controller);
		}
		return dbQuery_queryJButton;
	}

	/**
	 * This method initializes dbQuery_tableJScrollBar	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDbQuery_tableJScrollBar() {
		if (dbQuery_tableJScrollBar == null) {
			dbQuery_tableJScrollBar = new JScrollPane();
			dbQuery_tableJScrollBar.setViewportView(getDbQuery_tableJTable());
		}
		return dbQuery_tableJScrollBar;
	}

	/**
	 * This method initializes dbQuery_tableJTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	protected JTable getDbQuery_tableJTable() {
		if (dbQuery_tableJTable == null) {
			dbQuery_tableJTable = new JTable();
			dbQuery_tableJTable.setEnabled(false);
		}
		return dbQuery_tableJTable;
	}

	/**
	 * This method initializes dbQuery_totalsJTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getDbQuery_totalsJTextField() {
		if (dbQuery_totalsJTextField == null) {
			dbQuery_totalsJTextField = new JTextField();
			dbQuery_totalsJTextField.setPreferredSize(new Dimension(100, 20));
			dbQuery_totalsJTextField.setEnabled(true);
			dbQuery_totalsJTextField.setEditable(false);
		}
		return dbQuery_totalsJTextField;
	}

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {


		String arg = "";
		for (int a = 0; a < args.length; a++) {
			arg = arg + " " + args[a];
		}
		arg = arg.trim();
		if (arg.matches("-db=\\S+ -user=\\S+ -pw=\\S+( -dev)?( reset)?"))
			setArgs(arg);
		else {
			System.err
					.println("Oikea syntaksi parametreille: -db=tietokanta -user=käyttäjä -pw=passu");
			System.exit(0);
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI application = new GUI();
				application.controller = new GUIController(application);
				application.getJFrame().setVisible(true);
				application.controller.init();

			}
		});

	}

	private static void setArgs(String args) {
		Pattern pattern = Pattern
				.compile("-db=(\\S+) -user=(\\S+) -pw=(\\S+)( -dev)?( reset)?");
		Matcher matcher = pattern.matcher(args);
		matcher.find();
		DBWrapper.dbName = matcher.group(1);
		DBWrapper.dbUser = matcher.group(2);
		DBWrapper.dbPass = matcher.group(3);
		if (matcher.group(4) != null)
			GUIController.isDev = true;
		if (matcher.group(5) != null)
			GUIWrapper.resetDb();
	}

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	protected JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(599, 784);
			jFrame.setContentPane(getTabPanel());
			jFrame.setTitle("Piikki " + version);
			jFrame.addWindowListener(controller);
		}
		return jFrame;
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getBackupMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("Tiedosto");
			fileMenu.add(getSetBackupPathMenuItem());
			fileMenu.add(getImportMenuItem());
			fileMenu.add(getEraseJMenuItem());
			fileMenu.add(getRemoveLogsMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	protected JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Poistu");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIController.quit();
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getSetBackupPathMenuItem() {
		if (setBackupPathMenuItem == null) {

			setBackupPathMenuItem = new JMenuItem();
			setBackupPathMenuItem.setText("Aseta varmennuksen hakemisto");
			// setBackupPathMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
			// Event.CTRL_MASK, true));
			setBackupPathMenuItem.addActionListener(controller);
		}
		return setBackupPathMenuItem;
	}

	/**
	 * This method initializes tabPanel
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	protected JTabbedPane getTabPanel() {
		if (tabPanel == null) {
			tabPanel = new JTabbedPane();
			tabPanel.addTab("Myynti", null, getTapJPanel(), null);
			tabPanel.addTab("Ylläpito", null, getAdminJPanel(), null);
			tabPanel.addTab("Hinnasto", null, getPricelistJPanel(), null);
			tabPanel.addTab("Piikkitilanne", null, getDbQueryJPanel(), null);
			tabPanel.addTab("Tilastot", null, getStatisticsJPanel(), null);
			tabPanel.addKeyListener(controller);
		}
		return tabPanel;
	}

	/**
	 * This method initializes tapJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getTapJPanel() {
		if (tapJPanel == null) {
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			gridBagConstraints29.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints29.gridy = 1;
			gridBagConstraints29.fill = GridBagConstraints.BOTH;
			gridBagConstraints29.weighty = 1.0D;
			gridBagConstraints29.gridwidth = 2;
			gridBagConstraints29.weightx = 1.0D;
			gridBagConstraints29.gridx = 0;
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.gridx = 0;
			gridBagConstraints28.fill = GridBagConstraints.BOTH;
			gridBagConstraints28.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints28.weightx = 1.0D;
			gridBagConstraints28.gridy = 0;
			tapJPanel = new JPanel();
			tapJPanel.setLayout(new GridBagLayout());
			tapJPanel.setName("Piikki");
			tapJPanel.setPreferredSize(new Dimension(700, 893));
			tapJPanel.add(getBuyJPanel(), gridBagConstraints28);
			tapJPanel.add(getHistoryJPanel(), gridBagConstraints29);
		}
		return tapJPanel;
	}

	/**
	 * This method initializes adminJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getAdminJPanel() {
		if (adminJPanel == null) {
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.gridy = 2;
			gridBagConstraints61.fill = GridBagConstraints.BOTH;
			gridBagConstraints61.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints61.weightx = 1.0D;
			gridBagConstraints61.weighty = 1.0D;
			gridBagConstraints61.gridx = 0;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 0;
			gridBagConstraints51.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints51.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridy = 0;
			adminJPanel = new JPanel();
			adminJPanel.setLayout(new GridBagLayout());
			adminJPanel.add(getAdmin_infoTextJLabel(), gridBagConstraints);
			adminJPanel.add(getAdmin_ActionJPanel(), gridBagConstraints51);
			adminJPanel.add(getAdmin_logsJPanel(), gridBagConstraints61);
		}
		return adminJPanel;
	}

	/**
	 * This method initializes buyJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getBuyJPanel() {
		if (buyJPanel == null) {
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.insets = new Insets(10, 10, 10, 10);
			gridBagConstraints27.gridy = 2;
			gridBagConstraints27.weighty = 0.2D;
			gridBagConstraints27.fill = GridBagConstraints.NONE;
			gridBagConstraints27.anchor = GridBagConstraints.NORTH;
			gridBagConstraints27.gridx = 0;
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.gridx = 0;
			gridBagConstraints26.weighty = 1.0D;
			gridBagConstraints26.fill = GridBagConstraints.BOTH;
			gridBagConstraints26.insets = new Insets(10, 10, 10, 10);
			gridBagConstraints26.gridy = 1;
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.gridx = 0;
			gridBagConstraints25.weighty = 0.2D;
			gridBagConstraints25.fill = GridBagConstraints.NONE;
			gridBagConstraints25.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints25.gridy = 0;
			buyJPanel = new JPanel();
			buyJPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Tilaus", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			buyJPanel.setLayout(new GridBagLayout());
			buyJPanel.setPreferredSize(new Dimension(600, 418));
			buyJPanel.add(getBuy_ordererJPanel(), gridBagConstraints25);
			buyJPanel.add(getBuy_readyDrinksJPanel(), gridBagConstraints26);
			buyJPanel.add(getBuy_actionJPanel(), gridBagConstraints27);
		}
		return buyJPanel;
	}

	/**
	 * This method initializes historyJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getHistoryJPanel() {
		if (historyJPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints5.gridx = 0;
			historyJPanel = new JPanel();
			historyJPanel.setLayout(new GridBagLayout());
			historyJPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Tilaukset", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			historyJPanel.setPreferredSize(new Dimension(600, 455));
			historyJPanel.add(getHistory_tableJScrollPane(),
					gridBagConstraints5);
		}
		return historyJPanel;
	}

	/**
	 * This method initializes buy_ordererJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getBuy_ordererJPanel() {
		if (buy_ordererJPanel == null) {
			GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
			gridBagConstraints43.gridx = 0;
			gridBagConstraints43.gridy = 1;
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.gridx = 1;
			gridBagConstraints42.anchor = GridBagConstraints.WEST;
			gridBagConstraints42.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints42.weightx = 1.0D;
			gridBagConstraints42.ipadx = 300;
			gridBagConstraints42.gridy = 1;
			buy_ordererExtraJLabel = new JLabel();
			buy_ordererExtraJLabel.setText("Lisätietoja:");
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints24.gridx = 1;
			gridBagConstraints24.gridy = 2;
			gridBagConstraints24.weightx = 1.0;
			gridBagConstraints24.anchor = GridBagConstraints.WEST;
			gridBagConstraints24.ipadx = 75;
			gridBagConstraints24.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.gridx = 0;
			gridBagConstraints23.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints23.gridy = 2;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints22.gridx = 1;
			gridBagConstraints22.gridy = 0;
			gridBagConstraints22.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints22.ipadx = 200;
			gridBagConstraints22.weightx = 1.0;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints21.gridy = 0;
			buy_ordererTapJLabel = new JLabel();
			buy_ordererTapJLabel.setText("Saldo:");
			buy_ordererJLabel = new JLabel();
			buy_ordererJLabel.setText("Tilaaja");
			buy_ordererJLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			buy_ordererJLabel
					.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			buy_ordererJPanel = new JPanel();
			buy_ordererJPanel.setLayout(new GridBagLayout());
			buy_ordererJPanel.add(buy_ordererJLabel, gridBagConstraints21);
			buy_ordererJPanel.add(getBuy_ordererJComboBox(),
					gridBagConstraints22);
			buy_ordererJPanel.add(buy_ordererExtraJLabel, gridBagConstraints43);
			buy_ordererJPanel.add(getBuy_ordererExtraJTextField(),
					gridBagConstraints42);
			buy_ordererJPanel.add(buy_ordererTapJLabel, gridBagConstraints23);
			buy_ordererJPanel.add(getBuy_ordererTapJTextField(),
					gridBagConstraints24);
		}
		return buy_ordererJPanel;
	}

	protected JLabel getBuy_readyDrinksDiscountJLabel() {
		if (buy_readyDrinksDiscountJLabel == null) {
			buy_readyDrinksDiscountJLabel = new JLabel();
			buy_readyDrinksDiscountJLabel.setText("Alennus");
			buy_readyDrinksDiscountJLabel.setFont(new Font("Dialog", Font.BOLD,
					12));
			buy_readyDrinksDiscountJLabel.setForeground(new Color(255, 51, 51));
			buy_readyDrinksDiscountJLabel.setVisible(false);
		}
		return buy_readyDrinksDiscountJLabel;
	}

	/**
	 * This method initializes getBuy_readyDrinksSelectAmountJLabel
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JLabel getBuy_readyDrinksSelectAmountJLabel() {
		if (buy_readyDrinksSelectAmountJLabel == null) {
			buy_readyDrinksSelectAmountJLabel = new JLabel();
			buy_readyDrinksSelectAmountJLabel.setText("0");
			buy_readyDrinksSelectAmountJLabel.setEnabled(false);
		}
		return buy_readyDrinksSelectAmountJLabel;
	}

	/**
	 * This method initializes buy_readyDrinksJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getBuy_readyDrinksJPanel() {
		if (buy_readyDrinksJPanel == null) {
			GridBagConstraints gridBagConstraints85 = new GridBagConstraints();
			gridBagConstraints85.gridx = 2;
			gridBagConstraints85.gridy = 5;
			GridBagConstraints gridBagConstraints84 = new GridBagConstraints();
			gridBagConstraints84.gridx = 0;
			gridBagConstraints84.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints84.anchor = GridBagConstraints.EAST;
			gridBagConstraints84.gridy = 5;
			GridBagConstraints gridBagConstraints82 = new GridBagConstraints();
			gridBagConstraints82.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints82.gridy = 5;
			gridBagConstraints82.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints82.gridx = 1;
			GridBagConstraints gridBagConstraints73 = new GridBagConstraints();
			gridBagConstraints73.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints73.gridy = 5;
			gridBagConstraints73.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints73.anchor = GridBagConstraints.EAST;
			gridBagConstraints73.gridx = 0;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 2;
			gridBagConstraints41.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints41.gridy = 6;

			GridBagConstraints gridBagConstraints66 = new GridBagConstraints();
			gridBagConstraints66.gridx = 0;
			gridBagConstraints66.insets = new Insets(20, 5, 5, 5);
			gridBagConstraints66.fill = GridBagConstraints.NONE;
			gridBagConstraints66.anchor = GridBagConstraints.EAST;
			gridBagConstraints66.gridy = 6;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints3.gridy = 6;
			gridBagConstraints3.ipadx = 50;
			gridBagConstraints3.fill = GridBagConstraints.NONE;
			gridBagConstraints3.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 2;
			gridBagConstraints20.gridy = 4;
			gridBagConstraints20.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints19.gridx = 1;
			gridBagConstraints19.gridy = 4;
			gridBagConstraints19.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints18.gridy = 4;
			gridBagConstraints18.anchor = GridBagConstraints.EAST;
			gridBagConstraints18.gridx = 0;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridy = 3;
			gridBagConstraints17.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints17.gridx = 2;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints16.gridy = 3;
			gridBagConstraints16.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints16.gridx = 1;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints15.anchor = GridBagConstraints.EAST;
			gridBagConstraints15.gridy = 3;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridy = 2;
			gridBagConstraints14.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints14.gridx = 2;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridy = 2;
			gridBagConstraints13.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints13.gridx = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridy = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints11.gridx = 2;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints10.gridx = 1;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints9.anchor = GridBagConstraints.EAST;
			gridBagConstraints9.gridy = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 2;
			gridBagConstraints8.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints8.gridy = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 1;
			gridBagConstraints7.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints7.gridy = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints6.gridy = 0;
			buy_readyDrinksAmountJLabel = new JLabel();
			buy_readyDrinksAmountJLabel.setText("Määrä");
			buy_readyDrinksPriceJLabel = new JLabel();
			buy_readyDrinksPriceJLabel.setText("Hinta / kpl");
			buy_readyDrinksDrinkJLabel = new JLabel();
			buy_readyDrinksDrinkJLabel.setText("Juoma");
			buy_readyDrinksJPanel = new JPanel();
			buy_readyDrinksJPanel.setLayout(new GridBagLayout());
			buy_readyDrinksJPanel.add(buy_readyDrinksDrinkJLabel,
					gridBagConstraints6);
			buy_readyDrinksJPanel.add(buy_readyDrinksPriceJLabel,
					gridBagConstraints7);
			buy_readyDrinksJPanel.add(buy_readyDrinksAmountJLabel,
					gridBagConstraints8);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink1JButton(),
					gridBagConstraints9);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink1CostJTextField(),
					gridBagConstraints10);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink1AmountJLabel(),
					gridBagConstraints11);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink2JButton(),
					gridBagConstraints12);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink2CostJTextField(),
					gridBagConstraints13);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink2AmountJLabel(),
					gridBagConstraints14);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink3JButton(),
					gridBagConstraints15);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink3CostJTextField(),
					gridBagConstraints16);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink3AmountJLabel(),
					gridBagConstraints17);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink4JButton(),
					gridBagConstraints18);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink4CostJTextField(),
					gridBagConstraints19);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDrink4AmountJLabel(),
					gridBagConstraints20);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksOtherJTextField(),
					gridBagConstraints3);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksOtherJButton(),
					gridBagConstraints66);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksDiscountJLabel(),
					gridBagConstraints41);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksSelectJComboBox(),
					gridBagConstraints73);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksSelectCostJTextField(),
					gridBagConstraints82);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksSelectJButton(),
					gridBagConstraints84);
			buy_readyDrinksJPanel.add(getBuy_readyDrinksSelectAmountJLabel(),
					gridBagConstraints85);
		}
		return buy_readyDrinksJPanel;
	}

	/**
	 * This method initializes buy_actionJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getBuy_actionJPanel() {
		if (buy_actionJPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.weightx = 0.3D;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints4.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.ipadx = 100;
			gridBagConstraints2.weightx = 0.3D;
			buy_actionPriceJLabel = new JLabel();
			buy_actionPriceJLabel.setText("Kokonaishinta:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.CENTER;
			gridBagConstraints1.gridx = 2;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.fill = GridBagConstraints.NONE;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.weightx = 0.3D;
			gridBagConstraints1.gridheight = 2;
			buy_actionJPanel = new JPanel();
			buy_actionJPanel.setLayout(new GridBagLayout());
			buy_actionJPanel
					.add(getBuy_actionBuyJButton(), gridBagConstraints1);
			buy_actionJPanel.add(buy_actionPriceJLabel, gridBagConstraints4);
			buy_actionJPanel.add(getBuy_actionPriceJTextField(),
					gridBagConstraints2);
		}
		return buy_actionJPanel;
	}

	/**
	 * This method initializes buy_ordererJComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	protected JComboBox getBuy_ordererJComboBox() {
		if (buy_ordererJComboBox == null) {
			buy_ordererJComboBox = new JComboBox();
			buy_ordererJComboBox.setPreferredSize(new Dimension(220, 25));
			buy_ordererJComboBox.setMaximumRowCount(15);

			buy_ordererJComboBox.addItemListener(controller);
			// buy_ordererJComboBox.addActionListener(controller);

		}
		return buy_ordererJComboBox;
	}

	/**
	 * This method initializes buy_ordererTapJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_ordererTapJTextField() {
		if (buy_ordererTapJTextField == null) {
			buy_ordererTapJTextField = new JTextField();
			buy_ordererTapJTextField.setEditable(false);
			buy_ordererTapJTextField.setPreferredSize(new Dimension(75, 20));
		}
		return buy_ordererTapJTextField;
	}

	/**
	 * This method initializes buy_readyDrinksDrink1JButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getBuy_readyDrinksDrink1JButton() {
		if (buy_readyDrinksDrink1JButton == null) {
			buy_readyDrinksDrink1JButton = new JButton();
			buy_readyDrinksDrink1JButton
					.setPreferredSize(new Dimension(200, 26));
			buy_readyDrinksDrink1JButton.addActionListener(controller);
		}
		return buy_readyDrinksDrink1JButton;
	}

	/**
	 * This method initializes buy_readyDrinksDrink1CostJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_readyDrinksDrink1CostJTextField() {
		if (buy_readyDrinksDrink1CostJTextField == null) {
			buy_readyDrinksDrink1CostJTextField = new JTextField();
			buy_readyDrinksDrink1CostJTextField.setEditable(false);
		}
		return buy_readyDrinksDrink1CostJTextField;
	}

	/**
	 * This method initializes buy_readyDrinksDrink1AmountJLabel
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JLabel getBuy_readyDrinksDrink1AmountJLabel() {
		if (buy_readyDrinksDrink1AmountJLabel == null) {
			buy_readyDrinksDrink1AmountJLabel = new JLabel();
			buy_readyDrinksDrink1AmountJLabel.setText("0");
			buy_readyDrinksDrink1AmountJLabel.setEnabled(false);
		}
		return buy_readyDrinksDrink1AmountJLabel;
	}

	/**
	 * This method initializes buy_readyDrinksDrink2JButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getBuy_readyDrinksDrink2JButton() {
		if (buy_readyDrinksDrink2JButton == null) {
			buy_readyDrinksDrink2JButton = new JButton();
			buy_readyDrinksDrink2JButton
					.setPreferredSize(new Dimension(200, 26));
			buy_readyDrinksDrink2JButton.addActionListener(controller);
		}
		return buy_readyDrinksDrink2JButton;
	}

	/**
	 * This method initializes buy_readyDrinksDrink2CostJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_readyDrinksDrink2CostJTextField() {
		if (buy_readyDrinksDrink2CostJTextField == null) {
			buy_readyDrinksDrink2CostJTextField = new JTextField();
			buy_readyDrinksDrink2CostJTextField.setEditable(false);
		}
		return buy_readyDrinksDrink2CostJTextField;
	}

	/**
	 * This method initializes buy_readyDrinksDrink2AmountJLabel
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JLabel getBuy_readyDrinksDrink2AmountJLabel() {
		if (buy_readyDrinksDrink2AmountJLabel == null) {
			buy_readyDrinksDrink2AmountJLabel = new JLabel();
			buy_readyDrinksDrink2AmountJLabel.setText("0");
			buy_readyDrinksDrink2AmountJLabel.setEnabled(false);
		}
		return buy_readyDrinksDrink2AmountJLabel;
	}

	/**
	 * This method initializes buy_readyDrinksDrink3JButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getBuy_readyDrinksDrink3JButton() {
		if (buy_readyDrinksDrink3JButton == null) {
			buy_readyDrinksDrink3JButton = new JButton();
			buy_readyDrinksDrink3JButton
					.setPreferredSize(new Dimension(200, 26));
			buy_readyDrinksDrink3JButton.addActionListener(controller);
		}
		return buy_readyDrinksDrink3JButton;
	}

	/**
	 * This method initializes buy_readyDrinksDrink3CostJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_readyDrinksDrink3CostJTextField() {
		if (buy_readyDrinksDrink3CostJTextField == null) {
			buy_readyDrinksDrink3CostJTextField = new JTextField();
			buy_readyDrinksDrink3CostJTextField.setEditable(false);
		}
		return buy_readyDrinksDrink3CostJTextField;
	}

	/**
	 * This method initializes buy_readyDrinksDrink3AmountJLabel
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JLabel getBuy_readyDrinksDrink3AmountJLabel() {
		if (buy_readyDrinksDrink3AmountJLabel == null) {
			buy_readyDrinksDrink3AmountJLabel = new JLabel();
			buy_readyDrinksDrink3AmountJLabel.setText("0");
			buy_readyDrinksDrink3AmountJLabel.setEnabled(false);
		}
		return buy_readyDrinksDrink3AmountJLabel;
	}

	/**
	 * This method initializes buy_readyDrinksDrink4JButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getBuy_readyDrinksDrink4JButton() {
		if (buy_readyDrinksDrink4JButton == null) {
			buy_readyDrinksDrink4JButton = new JButton();
			buy_readyDrinksDrink4JButton
					.setPreferredSize(new Dimension(200, 26));
			buy_readyDrinksDrink4JButton.addActionListener(controller);
		}
		return buy_readyDrinksDrink4JButton;
	}

	/**
	 * This method initializes buy_readyDrinksDrink4CostJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_readyDrinksDrink4CostJTextField() {
		if (buy_readyDrinksDrink4CostJTextField == null) {
			buy_readyDrinksDrink4CostJTextField = new JTextField();
			buy_readyDrinksDrink4CostJTextField.setEditable(false);
		}
		return buy_readyDrinksDrink4CostJTextField;
	}

	/**
	 * This method initializes buy_readyDrinksDrink4AmountJLabel
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JLabel getBuy_readyDrinksDrink4AmountJLabel() {
		if (buy_readyDrinksDrink4AmountJLabel == null) {
			buy_readyDrinksDrink4AmountJLabel = new JLabel();
			buy_readyDrinksDrink4AmountJLabel.setText("0");
			buy_readyDrinksDrink4AmountJLabel.setEnabled(false);
		}
		return buy_readyDrinksDrink4AmountJLabel;
	}

	/**
	 * This method initializes buy_actionBuyJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getBuy_actionBuyJButton() {
		if (buy_actionBuyJButton == null) {
			buy_actionBuyJButton = new JButton();
			buy_actionBuyJButton.setText("MYY");
			buy_actionBuyJButton.setEnabled(false);
			buy_actionBuyJButton.setPreferredSize(new Dimension(100, 26));
			buy_actionBuyJButton.addActionListener(controller);
		}
		return buy_actionBuyJButton;
	}

	/**
	 * This method initializes buy_actionPriceJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_actionPriceJTextField() {
		if (buy_actionPriceJTextField == null) {
			buy_actionPriceJTextField = new JTextField();
			buy_actionPriceJTextField.setPreferredSize(new Dimension(50, 20));
			buy_actionPriceJTextField.setText("0");
			buy_actionPriceJTextField.setEditable(false);
		}
		return buy_actionPriceJTextField;
	}

	/**
	 * This method initializes history_tableJScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	protected JScrollPane getHistory_tableJScrollPane() {
		if (history_tableJScrollPane == null) {
			history_tableJScrollPane = new JScrollPane();
			history_tableJScrollPane.setViewportView(getHistory_tableJTable());
		}
		return history_tableJScrollPane;
	}

	/**
	 * This method initializes history_tableJTable
	 * 
	 * @return javax.swing.JTable
	 */
	protected JTable getHistory_tableJTable() {
		if (history_tableJTable == null) {
			history_tableJTable = new JTable();
			history_tableJTable.setSize(new Dimension(881, 300));
			history_tableJTable.setRowSelectionAllowed(false);
//			history_tableJTable.addMouseListener(controller); // not ready yet
		}
		return history_tableJTable;
	}

	/**
	 * This method initializes buy_readyDrinksOtherJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_readyDrinksOtherJTextField() {
		if (buy_readyDrinksOtherJTextField == null) {
			buy_readyDrinksOtherJTextField = new JTextField();
			buy_readyDrinksOtherJTextField.addCaretListener(controller);
		}
		return buy_readyDrinksOtherJTextField;
	}

	/**
	 * This method initializes admin_infoTextJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JLabel getAdmin_infoTextJLabel() {
		if (admin_infoTextJLabel == null) {
			admin_infoTextJLabel = new JLabel();
			admin_infoTextJLabel.setEnabled(false);
			admin_infoTextJLabel.setText(" ");
		}
		return admin_infoTextJLabel;
	}

	/**
	 * This method initializes admin_ActionJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAdmin_ActionJPanel() {
		if (admin_ActionJPanel == null) {
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.gridx = 0;
			gridBagConstraints32.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints32.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints32.gridy = 2;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints31.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints31.gridy = 1;
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints30.gridy = 0;
			admin_ActionJPanel = new JPanel();
			admin_ActionJPanel.setBorder(new TitledBorder(null, "Muokkaa käyttäjää ja piikkiä", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			admin_ActionJPanel.setLayout(new GridBagLayout());
			admin_ActionJPanel.add(getAdmin_modifyUserJPanel(),
					gridBagConstraints30);
			admin_ActionJPanel.add(getAdmin_newUserJPanel(),
					gridBagConstraints31);
			admin_ActionJPanel.add(getAdmin_removeUserJPanel(),
					gridBagConstraints32);
		}
		return admin_ActionJPanel;
	}

	/**
	 * This method initializes admin_logsJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getAdmin_logsJPanel() {
		if (admin_logsJPanel == null) {
			GridBagConstraints gridBagConstraints67 = new GridBagConstraints();
			gridBagConstraints67.fill = GridBagConstraints.BOTH;
			gridBagConstraints67.gridy = 0;
			gridBagConstraints67.weightx = 1.0;
			gridBagConstraints67.weighty = 1.0;
			gridBagConstraints67.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints67.gridx = 0;
			admin_logsJPanel = new JPanel();
			admin_logsJPanel.setLayout(new GridBagLayout());
			admin_logsJPanel.setBorder(new TitledBorder(null, "Ylläpitotapahtumat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			admin_logsJPanel.setPreferredSize(new Dimension(600, 455));
			admin_logsJPanel.add(getAdmin_logsJScrollPanel(),
					gridBagConstraints67);
		}
		return admin_logsJPanel;
	}

	/**
	 * This method initializes admin_modifyUserJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAdmin_modifyUserJPanel() {
		if (admin_modifyUserJPanel == null) {
			GridBagConstraints gridBagConstraints69 = new GridBagConstraints();
			gridBagConstraints69.gridx = 0;
			gridBagConstraints69.gridy = 3;
			admin_modifyUserExtraJLabel = new JLabel();
			admin_modifyUserExtraJLabel.setText("Lisätietoja:");
			GridBagConstraints gridBagConstraints68 = new GridBagConstraints();
			gridBagConstraints68.gridy = 3;
			gridBagConstraints68.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints68.ipadx = 300;
			gridBagConstraints68.gridx = 1;
			GridBagConstraints gridBagConstraints65 = new GridBagConstraints();
			gridBagConstraints65.gridx = 0;
			gridBagConstraints65.anchor = GridBagConstraints.WEST;
			gridBagConstraints65.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints65.gridheight = 2;
			gridBagConstraints65.gridy = 1;
			admin_modifyUserDiscountNameJLabel = new JLabel();
			admin_modifyUserDiscountNameJLabel.setText("Alennus:");
			GridBagConstraints gridBagConstraints64 = new GridBagConstraints();
			gridBagConstraints64.gridy = 1;
			gridBagConstraints64.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints64.anchor = GridBagConstraints.WEST;
			gridBagConstraints64.gridheight = 2;
			gridBagConstraints64.gridx = 1;
			GridBagConstraints gridBagConstraints63 = new GridBagConstraints();
			gridBagConstraints63.gridx = 1;
			gridBagConstraints63.insets = new Insets(5, 5, 5, 50);
			gridBagConstraints63.gridheight = 2;
			gridBagConstraints63.gridy = 1;
			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.gridheight = 4;
			gridBagConstraints40.gridy = 0;
			gridBagConstraints40.insets = new Insets(5, 0, 5, 5);
			gridBagConstraints40.anchor = GridBagConstraints.WEST;
			gridBagConstraints40.gridx = 4;
			admin_modifyUserEJLabel = new JLabel();
			admin_modifyUserEJLabel.setText("e");
			GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
			gridBagConstraints39.gridy = 0;
			gridBagConstraints39.gridheight = 4;
			gridBagConstraints39.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints39.gridx = 5;
			GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
			gridBagConstraints38.gridx = 2;
			gridBagConstraints38.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints38.weighty = 5.0D;
			gridBagConstraints38.gridy = 2;
			GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
			gridBagConstraints37.gridx = 2;
			gridBagConstraints37.anchor = GridBagConstraints.NORTH;
			gridBagConstraints37.gridy = 1;
			GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
			gridBagConstraints36.gridx = 0;
			gridBagConstraints36.gridy = 0;
			gridBagConstraints36.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
			gridBagConstraints35.gridx = 3;
			gridBagConstraints35.gridy = 0;
			gridBagConstraints35.gridheight = 4;
			gridBagConstraints35.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints35.ipadx = 40;
			gridBagConstraints35.anchor = GridBagConstraints.EAST;
			GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
			gridBagConstraints34.weightx = 1.0;
			gridBagConstraints34.gridx = 1;
			gridBagConstraints34.gridy = 0;
			gridBagConstraints34.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints34.anchor = GridBagConstraints.WEST;
			gridBagConstraints34.gridheight = 1;
			admin_modifyUserNameJLabel = new JLabel();
			admin_modifyUserNameJLabel.setText("Nimi:");
			admin_modifyUserJPanel = new JPanel();
			admin_modifyUserJPanel.setLayout(new GridBagLayout());
			admin_modifyUserJPanel.add(admin_modifyUserNameJLabel,
					gridBagConstraints36);
			admin_modifyUserJPanel.add(getAdmin_modifyUserNameJComboBox(),
					gridBagConstraints34);
			admin_modifyUserJPanel.add(admin_modifyUserDiscountNameJLabel,
					gridBagConstraints65);
			admin_modifyUserJPanel.add(
					getAdmin_modifyUserDiscountYesJRadioButton(),
					gridBagConstraints64);
			admin_modifyUserJPanel.add(
					getAdmin_modifyUserDiscountNoJRadioButton(),
					gridBagConstraints63);
			admin_modifyUserJPanel.add(admin_modifyUserExtraJLabel,
					gridBagConstraints69);
			admin_modifyUserJPanel.add(getAdmin_modifyUserExtraJTextField(),
					gridBagConstraints68);
			admin_modifyUserJPanel.add(getAdmin_modifyUserPlusJRadioButton(),
					gridBagConstraints37);
			admin_modifyUserJPanel.add(getAdmin_modifyUserMinusJRadioButton(),
					gridBagConstraints38);
			admin_modifyUserJPanel.add(getAdmin_modifyUserAmountJTextField(),
					gridBagConstraints35);
			admin_modifyUserJPanel.add(admin_modifyUserEJLabel,
					gridBagConstraints40);
			admin_modifyUserJPanel.add(getAdmin_modifyUserModifyJButton(),
					gridBagConstraints39);
		}
		return admin_modifyUserJPanel;
	}

	/**
	 * This method initializes admin_newUserJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAdmin_newUserJPanel() {
		if (admin_newUserJPanel == null) {
			GridBagConstraints gridBagConstraints48 = new GridBagConstraints();
			gridBagConstraints48.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints48.gridy = 0;
			gridBagConstraints48.gridheight = 2;
			gridBagConstraints48.gridx = 2;
			GridBagConstraints gridBagConstraints47 = new GridBagConstraints();
			gridBagConstraints47.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints47.gridy = 1;
			gridBagConstraints47.gridx = 0;
			GridBagConstraints gridBagConstraints46 = new GridBagConstraints();
			gridBagConstraints46.gridx = 0;
			gridBagConstraints46.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints46.gridy = 0;
			GridBagConstraints gridBagConstraints45 = new GridBagConstraints();
			gridBagConstraints45.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints45.gridx = 1;
			gridBagConstraints45.gridy = 1;
			gridBagConstraints45.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints45.ipadx = 300;
			gridBagConstraints45.weightx = 1.0;
			admin_newUserExtraJLabel = new JLabel();
			admin_newUserExtraJLabel.setText("Lisätietoja:");
			GridBagConstraints gridBagConstraints44 = new GridBagConstraints();
			gridBagConstraints44.weightx = 1.0;
			gridBagConstraints44.gridy = 0;
			gridBagConstraints44.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints44.ipadx = 300;
			gridBagConstraints44.gridx = 1;
			admin_newUserNameJLabel = new JLabel();
			admin_newUserNameJLabel.setText("Nimi:");
			admin_newUserJPanel = new JPanel();
			admin_newUserJPanel.setLayout(new GridBagLayout());
			admin_newUserJPanel.setBorder(new TitledBorder(null, "Lisää uusi käyttäjä", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			admin_newUserJPanel.add(admin_newUserNameJLabel,
					gridBagConstraints46);
			admin_newUserJPanel.add(getAdmin_newUserNameJTextField(),
					gridBagConstraints44);
			admin_newUserJPanel.add(getAdmin_newUserAddJButton(),
					gridBagConstraints48);
			admin_newUserJPanel.add(admin_newUserExtraJLabel,
					gridBagConstraints47);
			admin_newUserJPanel.add(getAdmin_newUserExtraJTextField(),
					gridBagConstraints45);
		}
		return admin_newUserJPanel;
	}

	/**
	 * This method initializes admin_removeUserJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAdmin_removeUserJPanel() {
		if (admin_removeUserJPanel == null) {
			GridBagConstraints gridBagConstraints59 = new GridBagConstraints();
			gridBagConstraints59.gridx = 2;
			gridBagConstraints59.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints59.gridy = 0;
			GridBagConstraints gridBagConstraints55 = new GridBagConstraints();
			gridBagConstraints55.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints55.gridy = 0;
			gridBagConstraints55.gridx = 0;
			GridBagConstraints gridBagConstraints49 = new GridBagConstraints();
			gridBagConstraints49.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints49.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints49.gridy = 0;
			gridBagConstraints49.gridx = 1;
			gridBagConstraints49.weightx = 1.0;
			admin_removeUserNameJLabel = new JLabel();
			admin_removeUserNameJLabel.setText("Nimi:");
			admin_removeUserJPanel = new JPanel();
			admin_removeUserJPanel.setLayout(new GridBagLayout());
			admin_removeUserJPanel.setBorder(new TitledBorder(null, "Poista käyttäjä", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			admin_removeUserJPanel.add(admin_removeUserNameJLabel,
					gridBagConstraints55);
			admin_removeUserJPanel.add(getAdmin_removeUserNameJComboBox(),
					gridBagConstraints49);
			admin_removeUserJPanel.add(getAdmin_removeUserRemoveJButton(),
					gridBagConstraints59);
		}
		return admin_removeUserJPanel;
	}

	/**
	 * This method initializes admin_modifyUserNameJComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	protected JComboBox getAdmin_modifyUserNameJComboBox() {
		if (admin_modifyUserNameJComboBox == null) {
			admin_modifyUserNameJComboBox = new JComboBox();
			admin_modifyUserNameJComboBox.setPreferredSize(new Dimension(220,
					25));
			admin_modifyUserNameJComboBox.setMaximumRowCount(15);
			admin_modifyUserNameJComboBox.addItemListener(controller);
		}
		return admin_modifyUserNameJComboBox;
	}

	/**
	 * This method initializes admin_modifyUserPlusJRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	protected JRadioButton getAdmin_modifyUserPlusJRadioButton() {
		if (admin_modifyUserPlusJRadioButton == null) {
			admin_modifyUserPlusJRadioButton = new JRadioButton();
			admin_modifyUserPlusJRadioButton.setText("+");
			admin_modifyUserPlusJRadioButton
					.setVerticalAlignment(SwingConstants.TOP);
			admin_modifyUserPlusJRadioButton.setSelected(true);
		}
		return admin_modifyUserPlusJRadioButton;
	}

	/**
	 * This method initializes admin_modifyUserMinusJRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	protected JRadioButton getAdmin_modifyUserMinusJRadioButton() {
		if (admin_modifyUserMinusJRadioButton == null) {
			admin_modifyUserMinusJRadioButton = new JRadioButton();
			admin_modifyUserMinusJRadioButton.setText("-");
			admin_modifyUserMinusJRadioButton
					.setVerticalAlignment(SwingConstants.BOTTOM);
			admin_modifyUserMinusJRadioButton
					.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		}
		return admin_modifyUserMinusJRadioButton;
	}

	/**
	 * This method initializes admin_modifyUserAmountJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getAdmin_modifyUserAmountJTextField() {
		if (admin_modifyUserAmountJTextField == null) {
			admin_modifyUserAmountJTextField = new JTextField();
			admin_modifyUserAmountJTextField.setText("0");
			admin_modifyUserAmountJTextField.setPreferredSize(new Dimension(40,
					20));
			admin_modifyUserAmountJTextField.addCaretListener(controller);
		}
		return admin_modifyUserAmountJTextField;
	}

	/**
	 * This method initializes admin_modifyUserModifyJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getAdmin_modifyUserModifyJButton() {
		if (admin_modifyUserModifyJButton == null) {
			admin_modifyUserModifyJButton = new JButton();
			admin_modifyUserModifyJButton.setText("Muuta");
			admin_modifyUserModifyJButton.setEnabled(false);
			admin_modifyUserModifyJButton.addActionListener(controller);
		}
		return admin_modifyUserModifyJButton;
	}

	/**
	 * This method initializes buy_ordererExtraJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getBuy_ordererExtraJTextField() {
		if (buy_ordererExtraJTextField == null) {
			buy_ordererExtraJTextField = new JTextField();
			buy_ordererExtraJTextField.setPreferredSize(new Dimension(420, 20));
			buy_ordererExtraJTextField.setEditable(false);
		}
		return buy_ordererExtraJTextField;
	}

	/**
	 * This method initializes admin_newUserNameJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getAdmin_newUserNameJTextField() {
		if (admin_newUserNameJTextField == null) {
			admin_newUserNameJTextField = new JTextField();
			admin_newUserNameJTextField
					.setPreferredSize(new Dimension(300, 20));
			admin_newUserNameJTextField.addCaretListener(controller);
		}
		return admin_newUserNameJTextField;
	}

	/**
	 * This method initializes admin_newUserAddJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getAdmin_newUserAddJButton() {
		if (admin_newUserAddJButton == null) {
			admin_newUserAddJButton = new JButton();
			admin_newUserAddJButton.setText("Lisää");
			admin_newUserAddJButton.setEnabled(false);
			admin_newUserAddJButton.addActionListener(controller);
		}
		return admin_newUserAddJButton;
	}

	/**
	 * This method initializes admin_newUserExtraJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getAdmin_newUserExtraJTextField() {
		if (admin_newUserExtraJTextField == null) {
			admin_newUserExtraJTextField = new JTextField();
			admin_newUserExtraJTextField
					.setPreferredSize(new Dimension(300, 20));
			admin_newUserExtraJTextField.addCaretListener(controller);
		}
		return admin_newUserExtraJTextField;
	}

	/**
	 * This method initializes admin_removeUserNameJComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	protected JComboBox getAdmin_removeUserNameJComboBox() {
		if (admin_removeUserNameJComboBox == null) {
			admin_removeUserNameJComboBox = new JComboBox();
			admin_removeUserNameJComboBox.setPreferredSize(new Dimension(220,
					25));
			admin_removeUserNameJComboBox.setMaximumRowCount(15);
			admin_removeUserNameJComboBox
					.addItemListener(new java.awt.event.ItemListener() {
						public void itemStateChanged(java.awt.event.ItemEvent e) {
							if (admin_removeUserNameJComboBox.getSelectedItem() == null)
								return;
							if (admin_removeUserNameJComboBox.getSelectedItem()
									.toString().length() == 0)
								admin_removeUserRemoveJButton.setEnabled(false);
							else
								admin_removeUserRemoveJButton.setEnabled(true);
						}
					});
		}
		return admin_removeUserNameJComboBox;
	}

	/**
	 * This method initializes admin_removeUserRemoveJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getAdmin_removeUserRemoveJButton() {
		if (admin_removeUserRemoveJButton == null) {
			admin_removeUserRemoveJButton = new JButton();
			admin_removeUserRemoveJButton.setText("Poista");
			admin_removeUserRemoveJButton.setEnabled(false);
			admin_removeUserRemoveJButton.addActionListener(controller);
		}
		return admin_removeUserRemoveJButton;
	}

	/**
	 * This method initializes admin_logsJScrollPanel
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getAdmin_logsJScrollPanel() {
		if (admin_logsJScrollPanel == null) {
			admin_logsJScrollPanel = new JScrollPane();
			admin_logsJScrollPanel.setViewportView(getAdmin_logsJTable());
		}
		return admin_logsJScrollPanel;
	}

	/**
	 * This method initializes admin_logsJTable
	 * 
	 * @return javax.swing.JTable
	 */
	protected JTable getAdmin_logsJTable() {
		if (admin_logsJTable == null) {
			admin_logsJTable = new JTable();
		}
		return admin_logsJTable;
	}

	/**
	 * This method initializes statisticsJPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getStatisticsJPanel() {
		if (statisticsJPanel == null) {
			GridBagConstraints gridBagConstraints86 = new GridBagConstraints();
			gridBagConstraints86.gridy = 2;
			gridBagConstraints86.gridx = 0;
			GridBagConstraints gridBagConstraints62 = new GridBagConstraints();
			gridBagConstraints62.gridx = 0;
			gridBagConstraints62.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints62.gridy = 1;
			GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
			gridBagConstraints60.gridx = 0;
			gridBagConstraints60.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints60.fill = GridBagConstraints.BOTH;
			gridBagConstraints60.gridy = 0;
			statisticsJPanel = new JPanel();
			statisticsJPanel.setLayout(new GridBagLayout());
			statisticsJPanel.add(getStats_up(), gridBagConstraints60);
			statisticsJPanel.add(getStats_down(), gridBagConstraints62);
			statisticsJPanel.add(getStats_thirdChartJPanel(),
					gridBagConstraints86);
		}
		return statisticsJPanel;
	}

	/**
	 * This method initializes stats_up
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getStats_up() {
		if (stats_up == null) {
			GridBagConstraints gridBagConstraints87 = new GridBagConstraints();
			gridBagConstraints87.anchor = GridBagConstraints.EAST;
			gridBagConstraints87.gridx = 0;
			gridBagConstraints87.gridy = 0;
			gridBagConstraints87.weightx = 1.0D;
			gridBagConstraints87.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints83 = new GridBagConstraints();
			gridBagConstraints83.ipadx = 5;
			gridBagConstraints83.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints83.gridx = 1;
			gridBagConstraints83.gridy = 0;
			gridBagConstraints83.anchor = GridBagConstraints.WEST;
			gridBagConstraints83.weightx = 1.0;
			stats_totalJLabel = new JLabel();
			stats_totalJLabel.setText("Kaikki myynti yhteensä:");
			stats_up = new JPanel();
			stats_up.setLayout(new GridBagLayout());
			stats_up.add(stats_totalJLabel, gridBagConstraints87);
			stats_up.add(getStats_totalJTextField(), gridBagConstraints83);
		}
		return stats_up;
	}

	/**
	 * This method initializes stats_down
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getStats_down() {
		if (stats_down == null) {
			stats_down = new JPanel();
			stats_down.setLayout(new GridBagLayout());
		}
		return stats_down;
	}

	/**
	 * This method initializes admin_modifyUserDiscountYesJRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	protected JRadioButton getAdmin_modifyUserDiscountYesJRadioButton() {
		if (admin_modifyUserDiscountYesJRadioButton == null) {
			admin_modifyUserDiscountYesJRadioButton = new JRadioButton();
			admin_modifyUserDiscountYesJRadioButton.setText("Kyllä");
		}
		return admin_modifyUserDiscountYesJRadioButton;
	}

	/**
	 * This method initializes admin_modifyUserDiscountNoJRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	protected JRadioButton getAdmin_modifyUserDiscountNoJRadioButton() {
		if (admin_modifyUserDiscountNoJRadioButton == null) {
			admin_modifyUserDiscountNoJRadioButton = new JRadioButton();
			admin_modifyUserDiscountNoJRadioButton.setText("Ei");
			admin_modifyUserDiscountNoJRadioButton.setSelected(true);
		}
		return admin_modifyUserDiscountNoJRadioButton;
	}

	/**
	 * This method initializes buy_readyDrinksOtherJButton
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getBuy_readyDrinksOtherJButton() {
		if (buy_readyDrinksOtherJButton == null) {
			buy_readyDrinksOtherJButton = new JButton();
			buy_readyDrinksOtherJButton.setText("Lisää muuta, hinta:");
			buy_readyDrinksOtherJButton
					.setPreferredSize(new Dimension(200, 26));
			buy_readyDrinksOtherJButton.setEnabled(false);
			buy_readyDrinksOtherJButton.addActionListener(controller);
		}
		return buy_readyDrinksOtherJButton;
	}

	/**
	 * This method initializes admin_modifyUserExtraJTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getAdmin_modifyUserExtraJTextField() {
		if (admin_modifyUserExtraJTextField == null) {
			admin_modifyUserExtraJTextField = new JTextField();
			admin_modifyUserExtraJTextField.setPreferredSize(new Dimension(300,
					20));
		}
		return admin_modifyUserExtraJTextField;
	}

	/**
	 * This method initializes importMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	protected JMenuItem getImportMenuItem() {
		if (importMenuItem == null) {
			importMenuItem = new JMenuItem();
			importMenuItem
					.setText("Tuo varmuuskopio ohjelmaan (ei ehkä toimi linuxis)");
			importMenuItem.addActionListener(controller);
		}
		return importMenuItem;
	}


}
