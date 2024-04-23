package piikki;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

public class GUIController implements ActionListener, ItemListener,
		CaretListener, WindowListener, KeyListener, MouseListener {

	private GUI gui;

	private static int backupDelay = 1000 * 60 * 1; // Backup every 1 minute
	// private static int backupDelay = 1000 * 10; // Backup every 10 secs

	private static String startPath = "c:\\";

	public static boolean isDev = false;

	private static int dumpTimestamp;

	private static boolean listeners = true;

	private static String backupPath;

	private static int numOfReadyProds = 4;

	public static String notifyMsg = null;

	public static Vector buyLog = new Vector();

	public static Vector adminLog = new Vector();

	// public static Vector pricelist = new Vector();

	private DefaultTableModel buyModel = new DefaultTableModel();

	private DefaultTableModel adminModel = new DefaultTableModel();

	private DefaultTableModel pricelistModel = new DefaultTableModel();
	
	private DefaultTableModel balancestatsModel = new DefaultTableModel();

	private JFrame jFrame = null;

	public GUIController(GUI gui) {
		/*
		 * File lock = new File("Piikkimutex.lock"); if (lock.exists()) {
		 * System.out.println("Piikki on jo k�ynniss�!"); quit();
		 * 
		 * } try { lock.createNewFile(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		this.gui = gui;
	}

	protected static void quit() {
		/*
		 * File lock = new File("Piikkimutex.lock"); if (lock.exists())
		 * lock.delete();
		 */
		System.exit(0);

	}

	public void init() {
		if (!checkBackupPath()) {
			JOptionPane
					.showMessageDialog(
							jFrame,
							"Ongelmia varmistusaseman tarkistamisessa. Annettu asema joko "
									+ "1) ei ole olemassa 2) ei ole USB (siellä on yli 19GB tilaa) tai 3) sinne ei voida kirjoittaa!");
			buttonSetBackup();
			init();
		} else {

			dumpTimestamp = (int) (System.currentTimeMillis() / 1000);

			startBackups();

			setProducts(false);
			groupRadios();

			GUIWrapper.refreshUsers();

			refreshNames();

			// changeProd((Object) new Integer(1));

			setProdNumbers();
			setProdNamesAndPrice();

			createBuyTable();
			createAdminTable();
			createPricelistTable();
			createBalanceStatsTable();
			
			addStats();
			addBalanceStats();

		}
	}

	private void addBalanceStats() {
		String[][] stats = GUIWrapper.getBalanceStats();
		Vector<Object> row;
		double total = 0.0;
		
		for (int a = 0; a < stats.length; a++) {
			row = new Vector<Object>();

			row.add(stats[a][0]);
			row.add(stats[a][1]);
			balancestatsModel.addRow(row);
			
			total += Double.parseDouble(stats[a][1]);

		}
		total = Common.getNum(total);
		gui.getDbQuery_totalsJTextField().setText(""+total);
	}

	private void addStats() {
		statsTab = gui.getTabPanel().getComponentAt(
				gui.getTabPanel().getTabCount() - 1);
		gui.getTabPanel().removeTabAt(gui.getTabPanel().getTabCount() - 1); // hide
																			// last
																			// tab
		Analysis an = new Analysis();
		gui.getStats_down().add(an.getChart(new String[] { "" }));
		String[] products = { "%siideri%", "%olut%", "%lonk%" };
		gui.getStats_thirdChartJPanel().add(an.getChart(products));
		gui.getStats_totalJTextField().setText("" + an.getTotalSells());
	}

	private void backup() {
		try {

			String fileName = "piikki-" + dumpTimestamp + ".backup";
			File file = new File(backupPath + File.separatorChar + fileName);

			String cmd = "mysqldump -u" + DBWrapper.dbUser + " -p"
					+ DBWrapper.dbPass + " -r " + file.getAbsoluteFile() + " "
					+ DBWrapper.dbName;
			file.createNewFile();
			if (!file.canWrite())
				throw new Exception("Varmistushakemisto ei kirjoitettavissa");
			Runtime.getRuntime().exec(cmd);
			// System.out.println(cmd);

			/*
			 * File file = new File(backupPath + "\\" + fileName);
			 * FileOutputStream writer = new FileOutputStream(file);
			 * 
			 * String[] cmds = { "mysqldump", "-u", DBWrapper.dbUser, "-p" +
			 * DBWrapper.dbPass, DBWrapper.dbName };
			 * 
			 * Process proc = Runtime.getRuntime().exec(cmds);
			 * 
			 * String line; BufferedReader read = new BufferedReader( new
			 * InputStreamReader(proc.getInputStream()));
			 * 
			 * while ((line = read.readLine()) != null) { line = line + "\n";
			 * writer.write(line.getBytes());
			 * 
			 * } writer.flush(); writer.close();
			 */

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(jFrame,
					"Varmistusten kanssa ongelmia! Käynnistä uusiks.");
			quit();
		}

	}

	private void startBackups() {

		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				backup();
			}
		}, backupDelay, backupDelay);

		// gui.getBackupMenu().setText("  Varmuuskopiot (PAKKO OLLA USB): "+backupPath);

	}

	private boolean checkBackupPath() {
		String path = GUIWrapper.getBackupPath();
		if ((path.length() < 1) || (path == null))
			return false;
		File file = new File(path);
		if (!file.canWrite()) {
			new File(path).mkdir(); // try to create the dir
			if (!file.canWrite()) {
				return false;
			}
		}
		if (!isDev && file.getTotalSpace() > 19000000000L)
			return false;
		backupPath = path;
		return true;
	}

	private void importBackup(String path) {

		String cmd = "mysql -u" + DBWrapper.dbUser + " -p" + DBWrapper.dbPass
				+ " -e \"source " + path + "\" " + DBWrapper.dbName;

		try {
			backup();
			Runtime.getRuntime().exec(cmd);
			JOptionPane.showMessageDialog(jFrame,
					"Varmistus ladattu. Käynnistä ohjelma uudestaan.");
			quit();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(jFrame,
					"Varmistusten kanssa ongelmia! Käynnistä uusiks.");
			quit();
		}

	}

	private void buttonImportBackup() {
		JFileChooser chooser = new JFileChooser();
		File file = new File(startPath);
		chooser.setCurrentDirectory(file);

		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser
				.setDialogTitle("Valitse varmuuskopiotiedosto, joka tuodaan ohjelmaan");
		chooser.setApproveButtonText("Valitse");

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			importBackup("\"" + chooser.getSelectedFile().getAbsolutePath()
					+ "\"");
		}
	}

	private void buttonErase() {
		int result = JOptionPane
				.showConfirmDialog(
						null,
						"Oletko varma, että haluat poistaa tietokannan koneelta? \nTämä "
								+ "poistaa KAIKEN tiedon koneelta - tulisi käyttää ainoastaan jos on OIKEA hätä ja tiedot täytyy OIKEASTI "
								+ "saada poistettua nopeasti. \nTietojen palautus muistitikun varmuuskopiolta vaatii jo työtä vähän.");
		if (result == JOptionPane.OK_OPTION) {
			GUIWrapper.eraseInfo();
			quit();
		}

	}
	
	private void buttonRemoveLogs() {
		int result = JOptionPane
				.showConfirmDialog(
						null,
						"Oletko varma, että haluat poistaa logit?\n"
						+ "Tämä poistaa sekä ylläpitologit että myyntilogit. Mihinkään tärkeään tietoon ei kosketa.\n"	
						+ "Logit on hyvä poistaa noin vuoden välein, jotta varmistusten koko pysyy järkevänä.");
		if (result == JOptionPane.OK_OPTION) {
			GUIWrapper.removeLogs();
		}
	}

	private void buttonSetBackup() {
		JFileChooser chooser = new JFileChooser();
		File file = new File(startPath);
		chooser.setCurrentDirectory(file);

		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser
				.setDialogTitle("Valitse MUISTITIKKU, jolle varmuuskopiot talletetaan");
		chooser.setApproveButtonText("Valitse");

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			GUIWrapper.setBackupPath(chooser.getSelectedFile()
					.getAbsolutePath());
			backupPath = chooser.getSelectedFile().getAbsolutePath();
		}
		if (returnVal == JFileChooser.CANCEL_OPTION) {
			if (!checkBackupPath())
				quit();
		}
	}

	private void buttonModify() {

		User user = (User) Common.usersTable.get(gui
				.getAdmin_modifyUserNameJComboBox().getSelectedItem()
				.toString());

		GUIWrapper.modifyUserAndTap(user, gui
				.getAdmin_modifyUserExtraJTextField().getText(), gui
				.getAdmin_modifyUserPlusJRadioButton().isSelected(), gui
				.getAdmin_modifyUserDiscountYesJRadioButton().isSelected(),
				Double.parseDouble(gui.getAdmin_modifyUserAmountJTextField()
						.getText()));

		String action = "Muokkaa käyttäjää";
		String actionValue;

		if (((gui.getAdmin_modifyUserDiscountYesJRadioButton().isSelected()) && (!user
				.getDiscount()))
				|| ((!gui.getAdmin_modifyUserDiscountYesJRadioButton()
						.isSelected()) && (user.getDiscount()))) {
			// Discount changed
			actionValue = "Alennus = "
					+ gui.getAdmin_modifyUserDiscountYesJRadioButton()
							.isSelected();
			GUIWrapper.addAdminLog(action, actionValue, user.getName());
		}
		if (!gui.getAdmin_modifyUserExtraJTextField().getText().equals(
				user.getDetails())) {
			// Extra info changed
			actionValue = "Lisätiedot = "
					+ gui.getAdmin_modifyUserExtraJTextField().getText();
			GUIWrapper.addAdminLog(action, actionValue, user.getName());
		}
		if ((!gui.getAdmin_modifyUserAmountJTextField().getText().equals("0"))
				&& (!gui.getAdmin_modifyUserAmountJTextField().getText()
						.equals("0.0"))) {
			// Balance changed
			char plus;
			if (gui.getAdmin_modifyUserPlusJRadioButton().isSelected())
				plus = '+';
			else
				plus = '-';
			actionValue = "Piikki " + plus
					+ gui.getAdmin_modifyUserAmountJTextField().getText();
			GUIWrapper.addAdminLog(action, actionValue, user.getName());
		}

		recreateAdminTable();
		gui.getAdmin_infoTextJLabel().setText(notifyMsg);
		ordererNameChanged(true);
		gui.getBuy_ordererJComboBox().setSelectedIndex(0);
		clearModify();
	}

	private String[] getBuyTableHeaders() {
		String[] a = { "Aika", "Tilaaja", "Tuote", "Hinta" };
		return a;
	}

	private String[] getAdminTableHeaders() {
		String[] a = { "Aika", "Tapahtuman nimi", "Tapahtuma", "Kohde" };
		return a;
	}

	private String[] getPricelistTableHeaders() {
		String[] a = { "Numero", "Tuote", "Hinta" };
		return a;
	}

	private void createBuyTable() {
		GUIWrapper.getOrderLog();
		recreateBuyTable(null);
	}

	private void createPricelistTable() {
		recreatePricelistTable();
	}

	private void recreatePricelistTable() {
		newPricelistModel();
		Vector<Object> row;
		Product prod;

		for (int a = 0; a < Common.products.size(); a++) {
			row = new Vector<Object>();
			prod = (Product) Common.products.get(a);
			// if (prod.getNumber() == 0) row.add(new Integer(0));
			row.add(Integer.toString(prod.getNumber()));
			row.add(prod.getName());
			row.add(Double.valueOf(prod.getPrice()));
			pricelistModel.addRow(row);
			// System.out.println(row.get(0)
			// +" ! "+row.get(1)+" ! "+row.get(2));
		}
	}

	private void recreateBuyTable(String filter) {
		newBuyModel();
		Vector row;

		for (int a = 0; a < buyLog.size(); a++) {
			row = (Vector) buyLog.get(a);
			if ((filter == null)
					|| ((filter != null) && (row.get(1).equals(filter)))) {
				buyModel.addRow(row);
				// System.out.println(row.get(0)
				// +" ! "+row.get(1)+" ! "+row.get(2));

			}
		}
		// System.out.println(buyModel.getRowCount());
	}

	private void updateBuyLog() {
		recreateBuyTable(null);

	}

	private void createAdminTable() {
		GUIWrapper.getAdminLog();
		recreateAdminTable();
	}

	private void recreateAdminTable() {
		newAdminModel();
		Vector row;
		for (int a = 0; a < adminLog.size(); a++) {
			row = (Vector) adminLog.get(a);
			adminModel.addRow(row);
		}
	}
	
	private void createBalanceStatsTable() {
		newBalanceStatsModel();
	}

	private void clearModify() {
		gui.getAdmin_modifyUserNameJComboBox().setSelectedIndex(0);
		gui.getAdmin_modifyUserDiscountNoJRadioButton().setSelected(true);
		gui.getAdmin_modifyUserPlusJRadioButton().setSelected(true);
		gui.getAdmin_modifyUserAmountJTextField().setText("0");
		gui.getAdmin_modifyUserExtraJTextField().setText("");
		gui.getAdmin_modifyUserModifyJButton().setEnabled(false);
	}

	private void clearOrder() {
		// this.buy_ordererJComboBox.setSelectedIndex(0);

		gui.getBuy_ordererExtraJTextField().setText("");
		gui.getBuy_ordererTapJTextField().setText("");
		gui.getBuy_ordererTapJTextField().setBackground(
				new Color(238, 238, 238));

		gui.getBuy_readyDrinksDrink1AmountJLabel().setText("0");
		gui.getBuy_readyDrinksDrink2AmountJLabel().setText("0");
		gui.getBuy_readyDrinksDrink3AmountJLabel().setText("0");
		gui.getBuy_readyDrinksDrink4AmountJLabel().setText("0");
		gui.getBuy_readyDrinksSelectAmountJLabel().setText("0");
		gui.getBuy_readyDrinksSelectCostJTextField().setText("");
		gui.getBuy_readyDrinksOtherJTextField().setText("");

		gui.getBuy_readyDrinksSelectJComboBox().setSelectedIndex(0);
		gui.getBuy_actionPriceJTextField().setText("0");

		gui.getBuy_actionBuyJButton().setEnabled(false);
	}

	private void groupRadios() {
		ButtonGroup group = new ButtonGroup();
		group.add(gui.getAdmin_modifyUserDiscountYesJRadioButton());
		group.add(gui.getAdmin_modifyUserDiscountNoJRadioButton());

		group = new ButtonGroup();
		group.add(gui.getAdmin_modifyUserPlusJRadioButton());
		group.add(gui.getAdmin_modifyUserMinusJRadioButton());

	}

	private void setProdNumbers() {
		for (int a = 0; a <= numOfReadyProds; a++) {
			gui.getPricelist_editNumberJComboBox().addItem(new Integer(a));
		}
	}

	private void setProdNamesAndPrice() {
		gui.getPricelist_editNameJComboBox().removeAllItems();
		for (int a = 0; a < Common.products.size(); a++) {
			gui.getPricelist_editNameJComboBox()
					.addItem(Common.products.get(a));
		}
		gui.getPricelist_editPriceJTextField().setText(
				"" + ((Product) Common.products.get(0)).getPrice());
	}

	private void refreshNames() {

		List users = Common.usersVector;
		User user;

		gui.getBuy_ordererJComboBox().removeAllItems();
		gui.getAdmin_modifyUserNameJComboBox().removeAllItems();
		gui.getAdmin_removeUserNameJComboBox().removeAllItems();

		gui.getBuy_ordererJComboBox().addItem("");

		gui.getAdmin_modifyUserNameJComboBox().addItem("");
		gui.getAdmin_removeUserNameJComboBox().addItem("");

		for (int a = 0; a < users.size(); a++) {
			user = (User) users.get(a);
			gui.getBuy_ordererJComboBox().addItem(user);
			gui.getAdmin_modifyUserNameJComboBox().addItem(user);
			gui.getAdmin_removeUserNameJComboBox().addItem(user);
		}
	}

	private void setProducts(boolean discount) {
		GUIWrapper.refreshProducts();
		List products = Common.products;
		Product prod;

		// Set all to empty
		gui.getBuy_readyDrinksDrink1CostJTextField().setText("");
		gui.getBuy_readyDrinksDrink2CostJTextField().setText("");
		gui.getBuy_readyDrinksDrink3CostJTextField().setText("");
		gui.getBuy_readyDrinksDrink4CostJTextField().setText("");
		gui.getBuy_readyDrinksDrink1JButton().setText("");
		gui.getBuy_readyDrinksDrink2JButton().setText("");
		gui.getBuy_readyDrinksDrink3JButton().setText("");
		gui.getBuy_readyDrinksDrink4JButton().setText("");

		gui.getBuy_readyDrinksSelectJComboBox().removeAllItems();
		gui.getBuy_readyDrinksSelectJComboBox().addItem("");

		gui.getBuy_readyDrinksSelectJComboBox().setVisible(true);
		gui.getBuy_readyDrinksSelectJButton().setVisible(false);
		gui.getBuy_readyDrinksOtherJButton().setEnabled(false);

		for (int a = 0; a < products.size(); a++) {
			prod = (Product) products.get(a);
			double price = prod.getPrice();
			if (discount)
				price -= Common.discount;
			price = Common.getNum(price);

			if (prod.getNumber() == 1) {
				gui.getBuy_readyDrinksDrink1JButton().setText(prod.getName());
				gui.getBuy_readyDrinksDrink1CostJTextField()
						.setText("" + price);
				gui.getBuy_readyDrinksDrink1AmountJLabel().setText("0");
			} else if (prod.getNumber() == 2) {
				gui.getBuy_readyDrinksDrink2JButton().setText(prod.getName());
				gui.getBuy_readyDrinksDrink2CostJTextField()
						.setText("" + price);
				gui.getBuy_readyDrinksDrink2AmountJLabel().setText("0");
			} else if (prod.getNumber() == 3) {
				gui.getBuy_readyDrinksDrink3JButton().setText(prod.getName());
				gui.getBuy_readyDrinksDrink3CostJTextField()
						.setText("" + price);
				gui.getBuy_readyDrinksDrink3AmountJLabel().setText("0");
			} else if (prod.getNumber() == 4) {
				gui.getBuy_readyDrinksDrink4JButton().setText(prod.getName());
				gui.getBuy_readyDrinksDrink4CostJTextField()
						.setText("" + price);
				gui.getBuy_readyDrinksDrink4AmountJLabel().setText("0");
			} else {
				gui.getBuy_readyDrinksSelectJComboBox().addItem(prod.getName());
				// gui.getBuy_readyDrinksSelectCostJTextField().setText("" +
				// price);
				// gui.getBuy_readyDrinksSelectAmountJLabel().setText("0");
			}
		}

		if (!discount)
			gui.getBuy_readyDrinksDiscountJLabel().setVisible(false);

	}

	private void prodChanged(String name) {
		if (name.equals(""))
			return;

		List prods = Common.products;
		for (int a = 0; a < prods.size(); a++) {
			Product prod = (Product) prods.get(a);
			if (prod.getName().equals(name)) {
				gui.getPricelist_editPriceJTextField().setText(
						"" + prod.getPrice());
				gui.getPricelist_editNumberJComboBox().setSelectedIndex(
						prod.getNumber());
				break;
			}
		}
	}

	private void checkChangeUserStatus(boolean combo) {
		if ((gui.getAdmin_modifyUserNameJComboBox().getSelectedItem() == null)
				|| (gui.getAdmin_modifyUserNameJComboBox().getSelectedItem()
						.toString().length() == 0))
			return;
		boolean ok = true;
		// if
		// (this.admin_modifyUserNameJComboBox.getSelectedItem().toString().length()
		// == 0)
		// ok = false;
		if (gui.getAdmin_modifyUserNameJComboBox().getSelectedItem().toString()
				.length() > 49)
			ok = false;
		try {
			if (Double.parseDouble(gui.getAdmin_modifyUserAmountJTextField()
					.getText().toString()) < 0)
				ok = false;
		} catch (NumberFormatException e) {
			ok = false;
		}
		if (gui.getAdmin_modifyUserExtraJTextField().getText().length() > 99)
			ok = false;

		gui.getAdmin_modifyUserModifyJButton().setEnabled(ok);
		User user = (User) Common.usersTable.get(gui
				.getAdmin_modifyUserNameJComboBox().getSelectedItem()
				.toString());
		if (combo) {
			if (user.getDiscount())
				gui.getAdmin_modifyUserDiscountYesJRadioButton().setSelected(
						true);
			else
				gui.getAdmin_modifyUserDiscountNoJRadioButton().setSelected(
						true);

			gui.getAdmin_modifyUserExtraJTextField().setText(user.getDetails());
		}
	}

	private void checkAddUserFields() {
		boolean isOk = true;
		if (gui.getAdmin_newUserNameJTextField().getText().length() < 1)
			isOk = false;
		if (gui.getAdmin_newUserNameJTextField().getText().length() > 49)
			isOk = false;
		if (gui.getAdmin_newUserExtraJTextField().getText().length() > 99)
			isOk = false;

		gui.getAdmin_newUserAddJButton().setEnabled(isOk);
	}

	private void checkAddProductFields() {
		boolean isOk = true;
		if (gui.getPricelist_addNameJTextField().getText().equals(""))
			isOk = false;
		if (gui.getPricelist_addNameJTextField().getText() == null)
			isOk = false;
		if (gui.getPricelist_addNameJTextField().getText().length() == 0)
			isOk = false;
		if (gui.getPricelist_addNameJTextField().getText().length() > 39)
			isOk = false;
		try {
			if (Double.parseDouble(gui.getPricelist_addPriceJTextField()
					.getText().toString()) < 0)
				isOk = false;
		} catch (NumberFormatException e) {
			isOk = false;
		}
		gui.getPricelist_addAddJButton().setEnabled(isOk);
	}

	private void checkEditProductFields() {
		boolean isOk = true;
		try {
			if (Double.parseDouble(gui.getPricelist_editPriceJTextField()
					.getText().toString()) < 0)
				isOk = false;
		} catch (NumberFormatException e) {
			isOk = false;
		}
		gui.getPricelist_editDeleteJButton().setEnabled(isOk);
		gui.getPricelist_editModifyJButton().setEnabled(isOk);
	}

	private void ordererNameChanged(boolean isnull) {
		// gui.getBuy_ordererJComboBox().setSelectedIndex(0);
		clearOrder();

		// int selected = buy_ordererJComboBox.getSelectedIndex();

		// buy_ordererJComboBox.setSelectedIndex(selected);

		gui.getBuy_readyDrinksDrink1JButton().setEnabled(!isnull);
		gui.getBuy_readyDrinksDrink2JButton().setEnabled(!isnull);
		gui.getBuy_readyDrinksDrink3JButton().setEnabled(!isnull);
		gui.getBuy_readyDrinksDrink4JButton().setEnabled(!isnull);
		// gui.getBuy_actionBuyJButton().setEnabled(!isnull);
		gui.getBuy_readyDrinksOtherJTextField().setEnabled(!isnull);
		gui.getBuy_readyDrinksSelectJComboBox().setEnabled(!isnull);

		if (isnull) {
			setProducts(false);
			recreateBuyTable(null);
			return;
		}

		User user = (User) Common.usersTable.get(gui.getBuy_ordererJComboBox()
				.getSelectedItem().toString());
		// if (user == null) return;

		gui.getBuy_ordererExtraJTextField().setText(user.getDetails());
		gui.getBuy_ordererTapJTextField().setText("" + user.getBalance());

		if (user.getBalance() < 0)
			gui.getBuy_ordererTapJTextField().setBackground(Color.PINK);
		else
			gui.getBuy_ordererTapJTextField().setBackground(
					new Color(238, 238, 238));

		if (user.getDiscount()) {
			gui.getBuy_readyDrinksDiscountJLabel().setVisible(true);
			setProducts(true);
		} else {
			gui.getBuy_readyDrinksDiscountJLabel().setVisible(false);
			setProducts(false);
		}
		recreateBuyTable(user.getName());

	}

	private void ordererNameChanged() {
		if ((gui.getBuy_ordererJComboBox().getSelectedItem() == null)
				|| (gui.getBuy_ordererJComboBox().getSelectedItem().toString()
						.length() == 0)) {

			ordererNameChanged(true);
			return;
		}
		ordererNameChanged(false);
	}

	private void increaseAmount(JLabel amount, JTextField price) {
		if (price.getText().length() == 0)
			return;
		int a = Integer.parseInt(amount.getText());
		a++;
		amount.setText("" + a);
		double sum = Double.parseDouble(gui.getBuy_actionPriceJTextField()
				.getText());

		// System.out.println("price: "+price.getText()
		// +" len: "+price.getText().length());
		sum += Double.parseDouble(price.getText());
		sum = Common.getNum(sum);

		gui.getBuy_actionPriceJTextField().setText("" + sum);

		gui.getBuy_actionBuyJButton().setEnabled(true);
	}

	private void otherDrinkUpdate() {
		try {
			Double.parseDouble(gui.getBuy_readyDrinksOtherJTextField()
					.getText());
		} catch (NumberFormatException e) {
			gui.getBuy_readyDrinksOtherJButton().setEnabled(false);
			return;
		}
		gui.getBuy_readyDrinksOtherJButton().setEnabled(true);
	}

	private void otherDrinkAdd() {
		double price = Double.parseDouble(gui
				.getBuy_readyDrinksOtherJTextField().getText());
		double sum = Double.parseDouble(gui.getBuy_actionPriceJTextField()
				.getText());
		sum += price;
		sum = Common.getNum(sum);
		gui.getBuy_actionPriceJTextField().setText("" + sum);
	}

	private void changeProd() {
		String name;
		if (gui.getBuy_readyDrinksSelectJComboBox().getSelectedItem() == null)
			name = "";
		else
			name = gui.getBuy_readyDrinksSelectJComboBox().getSelectedItem()
					.toString();

		if (name.equals("")) {
			gui.getBuy_readyDrinksSelectCostJTextField().setText("");
			gui.getBuy_readyDrinksSelectAmountJLabel().setText("0");
			// gui.getBuy_readyDrinksSelectAddJButton().setEnabled(false);
		} else {

			List prods = Common.products;
			for (int a = 0; a < prods.size(); a++) {
				Product prod = (Product) prods.get(a);
				if (prod.getName().equals(name)) {
					double price = prod.getPrice();
					User user = (User) Common.usersTable.get(gui
							.getBuy_ordererJComboBox().getSelectedItem()
							.toString());
					if (user.getDiscount())
						price -= Common.discount;
					gui.getBuy_readyDrinksSelectCostJTextField().setText(
							"" + Common.getNum(price));
					break;
				}
			}
			// gui.getBuy_readyDrinksSelectAddJButton().setEnabled(true);
			gui.getBuy_readyDrinksSelectJComboBox().setVisible(false);
			gui.getBuy_readyDrinksSelectJButton().setVisible(true);
			gui.getBuy_readyDrinksSelectJButton().setText(name);
		}
		gui.getBuy_readyDrinksSelectAmountJLabel().setText("0");

	}

	private void sell() {
		// Vector sell, sells = new Vector();
		// for (int a = 1; a < 4; a++) {
		// sell = new Vector();
		// sell.addElement(gui.getBuy_readyDrinksDrink1JButton().getText());
		// sell.addElement(gui.getBuy_readyDrinksDrink1CostJTextField().getText());
		// sell.addElement(gui.getBuy_readyDrinksDrink1AmountJLabel().getText());
		// sells.addElement(sell);
		// }
		// double price =

		Vector<Object> sell;
		Vector<Object> sells = new Vector<Object>();

		if (!gui.getBuy_readyDrinksDrink1AmountJLabel().getText().equals("0")) {
			sell = new Vector<Object>();
			sell.addElement(gui.getBuy_readyDrinksDrink1JButton().getText());
			sell.addElement(gui.getBuy_readyDrinksDrink1CostJTextField()
					.getText());
			sell.addElement(gui.getBuy_readyDrinksDrink1AmountJLabel()
					.getText());
			sells.addElement(sell);
		}
		if (!gui.getBuy_readyDrinksDrink2AmountJLabel().getText().equals("0")) {
			sell = new Vector<Object>();
			sell.addElement(gui.getBuy_readyDrinksDrink2JButton().getText());
			sell.addElement(gui.getBuy_readyDrinksDrink2CostJTextField()
					.getText());
			sell.addElement(gui.getBuy_readyDrinksDrink2AmountJLabel()
					.getText());
			sells.addElement(sell);
		}
		if (!gui.getBuy_readyDrinksDrink3AmountJLabel().getText().equals("0")) {
			sell = new Vector<Object>();
			sell.addElement(gui.getBuy_readyDrinksDrink3JButton().getText());
			sell.addElement(gui.getBuy_readyDrinksDrink3CostJTextField()
					.getText());
			sell.addElement(gui.getBuy_readyDrinksDrink3AmountJLabel()
					.getText());
			sells.addElement(sell);
		}
		if (!gui.getBuy_readyDrinksDrink4AmountJLabel().getText().equals("0")) {
			sell = new Vector<Object>();
			sell.addElement(gui.getBuy_readyDrinksDrink4JButton().getText());
			sell.addElement(gui.getBuy_readyDrinksDrink4CostJTextField()
					.getText());
			sell.addElement(gui.getBuy_readyDrinksDrink4AmountJLabel()
					.getText());
			sells.addElement(sell);
		}
		if (!gui.getBuy_readyDrinksSelectAmountJLabel().getText().equals("0")) {
			sell = new Vector<Object>();
			sell.addElement(gui.getBuy_readyDrinksSelectJButton().getText());
			sell.addElement(gui.getBuy_readyDrinksSelectCostJTextField()
					.getText());
			sell.addElement(gui.getBuy_readyDrinksSelectAmountJLabel()
					.getText());
			sells.addElement(sell);
		}
		sells.addElement(gui.getBuy_actionPriceJTextField().getText());

		// GUIWrapper.sell(gui.getBuy_actionPriceJTextField().getText(), (User)
		// Common.usersTable.get(gui.getBuy_ordererJComboBox().getSelectedItem().toString()),
		// sells);
		GUIWrapper.order(sells, (User) Common.usersTable.get(gui
				.getBuy_ordererJComboBox().getSelectedItem().toString()));
		updateBuyLog();
		ordererNameChanged(true);
		gui.getBuy_ordererJComboBox().setSelectedIndex(0);
		setProducts(false);
	}

	private void newBuyModel() {
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(this.getBuyTableHeaders());
		buyModel = mod;
		gui.getHistory_tableJTable().setModel(buyModel);
		gui.getHistory_tableJTable().getColumnModel().getColumn(0)
				.setPreferredWidth(45);
		gui.getHistory_tableJTable().getColumnModel().getColumn(2)
				.setPreferredWidth(40);
		gui.getHistory_tableJTable().getColumnModel().getColumn(3)
				.setPreferredWidth(20);

	}

	private void newAdminModel() {
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(this.getAdminTableHeaders());

		adminModel = mod;
		gui.getAdmin_logsJTable().setModel(adminModel);
		gui.getAdmin_logsJTable().getColumnModel().getColumn(0)
				.setPreferredWidth(45);
		gui.getAdmin_logsJTable().getColumnModel().getColumn(1)
				.setPreferredWidth(40);
		gui.getAdmin_logsJTable().getColumnModel().getColumn(3)
				.setPreferredWidth(40);
	}

	private void newPricelistModel() {
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(this.getPricelistTableHeaders());
		pricelistModel = mod;
		gui.getPricelist_productsJTable().setModel(pricelistModel);

		// Hide the column
		gui.getPricelist_productsJTable().getColumnModel().getColumn(0)
				.setPreferredWidth(0);
		gui.getPricelist_productsJTable().getColumnModel().getColumn(0)
				.setMinWidth(0);
		gui.getPricelist_productsJTable().getColumnModel().getColumn(0)
				.setMaxWidth(0);

		// gui.getPricelist_productsJTable().getColumnModel().getColumn(0)
		// .setPreferredWidth(20);
		gui.getPricelist_productsJTable().getColumnModel().getColumn(2)
				.setMaxWidth(100);

	}

	private void newBalanceStatsModel() {
		DefaultTableModel mod = new DefaultTableModel();
		String[] ids = {"Nimi", "Saldo"};
		mod.setColumnIdentifiers(ids);
		
		

		balancestatsModel = mod;
		gui.getDbQuery_tableJTable().setModel(balancestatsModel);
		//gui.getDbQuery_tableJTable().getColumnModel().getColumn(0).set
		gui.getDbQuery_tableJTable().getColumnModel().getColumn(1).setMaxWidth(100);
	}
	
	private void modifyProd() {
		int num = Integer.parseInt(gui.getPricelist_editNumberJComboBox()
				.getSelectedItem().toString());

		String name = gui.getPricelist_editNameJComboBox().getSelectedItem()
				.toString();
		if (num != 0)
			for (int a = 0; a < this.pricelistModel.getRowCount(); a++) {
				// System.out.println(Integer.parseInt(pricelistModel.getValueAt(a,
				// 0).toString()) + " ! "+pricelistModel.getValueAt(a,
				// 1).toString());
				if ((Integer.parseInt(pricelistModel.getValueAt(a, 0)
						.toString()) == num)
						&& (!name.equals(pricelistModel.getValueAt(a, 1)
								.toString()))) {
					gui
							.getPricelist_infoJLabel()
							.setText(
									"Tuotetta "
											+ name
											+ " ei voi muokata, numero on jo käytössä myyntisivulla.");
					return;
				}
			}

		double price = Double.parseDouble(gui
				.getPricelist_editPriceJTextField().getText());

		GUIWrapper.buttonPricelistChangeProd(num, name, price);

		GUIWrapper.refreshProducts();

		String target = name;
		String action = "Muuta hinnastoa";
		String actionValue = "Numero=" + num + ", Hinta=" + price;
		GUIWrapper.addAdminLog(action, actionValue, target);
		recreateAdminTable();
		// gui.getAdmin_infoTextJLabel().setText(notifyMsg);
		setProducts(false);
		ordererNameChanged(true);
		gui.getBuy_ordererJComboBox().setSelectedIndex(0);
		recreatePricelistTable();
		gui.getPricelist_infoJLabel().setText(
				"Tuotetta " + name + " muokattu onnistuneesti.");

		/*
		 * int num =
		 * Integer.parseInt(gui.getAdmin_changeProdNumberJComboBox().getSelectedItem
		 * ().toString()); String name =
		 * gui.getAdmin_changeProdNameJComboBox().getSelectedItem().toString();
		 * double price =
		 * ((Product)Common.products.get(gui.getAdmin_changeProdNameJComboBox
		 * ().getSelectedIndex())).getPrice(); //System.out.println(price);
		 * //double price =
		 * ((Product)Common.products.get(Common.products.indexOf
		 * (name))).getPrice(); GUIWrapper.buttonAdminChangeProds(num, name,
		 * price);
		 * 
		 * String target = gui.getAdmin_changeProdNumberJComboBox()
		 * .getSelectedItem().toString(); String action = "Muuta tuotteita";
		 * String actionValue = name; GUIWrapper.addAdminLog(action,
		 * actionValue, target); recreateAdminTable();
		 * gui.getAdmin_infoTextJLabel().setText(notifyMsg); setProducts(false);
		 * clearOrder();
		 */
	}

	private void addProd() {
		String name = gui.getPricelist_addNameJTextField().getText();
		double price = Double.parseDouble(gui.getPricelist_addPriceJTextField()
				.getText());
		int number = 0;

		for (int a = 0; a < pricelistModel.getRowCount(); a++) {
			if (pricelistModel.getValueAt(a, 1).equals(name)) {
				gui
						.getPricelist_infoJLabel()
						.setText(
								"Lisääminen epäonnistui! Tämän niminen tuote on jo olemassa.");
				return;
			}
		}

		GUIWrapper.addProduct(number, name, price);
		Product prod = new Product();
		prod.setName(name);
		prod.setNumber(number);
		prod.setPrice(price);
		Common.products.add(prod);
		Vector<Object> pro = new Vector<Object>();
		pro.addElement("" + number);
		pro.addElement(name);
		pro.addElement("" + price);
		gui.getPricelist_editNameJComboBox().addItem(name);
		this.pricelistModel.addRow(pro);
		gui.getPricelist_infoJLabel().setText(
				"Tuote " + name + " lisätty onnistuneesti.");
		gui.getPricelist_addPriceJTextField().setText("");
		gui.getPricelist_addNameJTextField().setText("");

		String target = name;
		String action = "Lisää tuote";
		String actionValue = "Hinta=" + price;
		GUIWrapper.addAdminLog(action, actionValue, target);

		recreateAdminTable();
		recreatePricelistTable();
		ordererNameChanged(true);
		gui.getBuy_ordererJComboBox().setSelectedIndex(0);
		setProducts(false);
	}

	private void deleteProd() {
		int num = ((Product) Common.products.get(gui
				.getPricelist_editNameJComboBox().getSelectedIndex()))
				.getNumber();
		// System.out.println(num);
		if (num != 0) {
			gui
					.getPricelist_infoJLabel()
					.setText(
							"Poistaminen epäonnistui! Tuote on käytössä myyntisivulla.");
			return;
		}
		String name = gui.getPricelist_editNameJComboBox().getSelectedItem()
				.toString();
		GUIWrapper.buttonPricelistRemoveProd(name);

		GUIWrapper.refreshProducts();
		createPricelistTable();

		String target = name;
		String action = "Poista tuote";
		String actionValue = "";
		GUIWrapper.addAdminLog(action, actionValue, target);

		recreateAdminTable();
		recreatePricelistTable();

		setProdNamesAndPrice();
		gui.getPricelist_infoJLabel().setText(
				"Tuote " + name + " poistettu onnistuneesti.");

		ordererNameChanged(true);
		gui.getBuy_ordererJComboBox().setSelectedIndex(0);
		setProducts(false);
	}

	private void newUser() {
		GUIWrapper.buttonAdminAddUser(gui.getAdmin_newUserNameJTextField()
				.getText(), gui.getAdmin_newUserExtraJTextField().getText());

		String action = "Uusi käyttäjä";
		String target = gui.getAdmin_newUserNameJTextField().getText();

		GUIWrapper.addAdminLog(action, "", target);
		recreateAdminTable();

		gui.getAdmin_infoTextJLabel().setText(notifyMsg);
		gui.getAdmin_newUserNameJTextField().setText("");
		gui.getAdmin_newUserExtraJTextField().setText("");
		gui.getAdmin_newUserAddJButton().setEnabled(false);
		GUIWrapper.refreshUsers();
		ordererNameChanged(true);
		refreshNames();
	}

	private void removeUser() {
		if (GUIWrapper.buttonAdminRemoveUser((User) Common.usersTable.get(gui
				.getAdmin_removeUserNameJComboBox().getSelectedItem()
				.toString()))) {
			GUIWrapper.addAdminLog("Poista käyttäjä", "", gui
					.getAdmin_removeUserNameJComboBox().getSelectedItem()
					.toString());
			ordererNameChanged(true);
			gui.getBuy_ordererJComboBox().setSelectedIndex(0);
			GUIWrapper.refreshUsers();
			refreshNames();
			recreateAdminTable();
		}
		gui.getAdmin_infoTextJLabel().setText(notifyMsg);
	}

	private void customQuery() {
		String txt = gui.getDbQuery_queryJTextField().getText();
		
		GUIWrapper.CustomQueryResult res = GUIWrapper.customQuery(txt);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (listeners) {
			listeners = false;
			Object src = event.getSource();

			if (src.equals(gui.getSetBackupPathMenuItem()))
				buttonSetBackup();
			if (src.equals(gui.getBuy_readyDrinksDrink1JButton()))
				increaseAmount(gui.getBuy_readyDrinksDrink1AmountJLabel(), gui
						.getBuy_readyDrinksDrink1CostJTextField());
			if (src.equals(gui.getBuy_readyDrinksDrink2JButton()))
				increaseAmount(gui.getBuy_readyDrinksDrink2AmountJLabel(), gui
						.getBuy_readyDrinksDrink2CostJTextField());
			if (src.equals(gui.getBuy_readyDrinksDrink3JButton()))
				increaseAmount(gui.getBuy_readyDrinksDrink3AmountJLabel(), gui
						.getBuy_readyDrinksDrink3CostJTextField());
			if (src.equals(gui.getBuy_readyDrinksDrink4JButton()))
				increaseAmount(gui.getBuy_readyDrinksDrink4AmountJLabel(), gui
						.getBuy_readyDrinksDrink4CostJTextField());

			if (src.equals(gui.getBuy_readyDrinksSelectJButton()))
				increaseAmount(gui.getBuy_readyDrinksSelectAmountJLabel(), gui
						.getBuy_readyDrinksSelectCostJTextField());

			if (src.equals(gui.getBuy_actionBuyJButton())) {
				sell();
			}
			if (src.equals(gui.getBuy_readyDrinksOtherJTextField()))
				otherDrinkUpdate();
			if (src.equals(gui.getAdmin_modifyUserModifyJButton()))
				buttonModify();
			if (src.equals(gui.getAdmin_newUserAddJButton()))
				newUser();
			if (src.equals(gui.getAdmin_removeUserRemoveJButton()))
				removeUser();
			if (src.equals(gui.getPricelist_addAddJButton()))
				addProd();
			if (src.equals(gui.getPricelist_editModifyJButton()))
				modifyProd();
			if (src.equals(gui.getPricelist_editDeleteJButton()))
				deleteProd();

			if (src.equals(gui.getBuy_readyDrinksOtherJButton())) {
				otherDrinkAdd();
				gui.getBuy_readyDrinksOtherJTextField().setText("");
				gui.getBuy_actionBuyJButton().setEnabled(true);
				gui.getBuy_readyDrinksOtherJButton().setEnabled(false);
			}
			
			if (src.equals(gui.getDbQuery_queryJButton())) {
				customQuery();
			}

			if (src.equals(gui.getImportMenuItem()))
				buttonImportBackup();
			if (src.equals(gui.getEraseJMenuItem()))
				buttonErase();
			if (src.equals(gui.getRemoveLogsMenuItem()))
				buttonRemoveLogs();
			if (src.equals(gui.getExitMenuItem())) {
				backup();
				quit();
			}

			listeners = true;
		}
	}


	public void itemStateChanged(ItemEvent event) {
		if (listeners) {
			listeners = false;
			Object src = event.getSource();
			if (src.equals(gui.getBuy_ordererJComboBox()))
				ordererNameChanged();
			if (src.equals(gui.getAdmin_modifyUserNameJComboBox()))
				checkChangeUserStatus(true);

			if (src.equals(gui.getPricelist_editNameJComboBox())
					&& gui.getPricelist_editNameJComboBox().getSelectedItem() != null)
				prodChanged(gui.getPricelist_editNameJComboBox()
						.getSelectedItem().toString());
			if (src.equals(gui.getBuy_readyDrinksSelectJComboBox()))
				changeProd();
			listeners = true;
		}
	}

	public void caretUpdate(CaretEvent event) {
		if (listeners) {
			listeners = false;
			Object src = event.getSource();

			if (src.equals(gui.getBuy_readyDrinksOtherJTextField()))
				otherDrinkUpdate();
			if (src.equals(gui.getAdmin_modifyUserAmountJTextField()))
				checkChangeUserStatus(false);
			if (src.equals(gui.getAdmin_newUserNameJTextField()))
				checkAddUserFields();
			if (src.equals(gui.getAdmin_newUserExtraJTextField()))
				checkAddUserFields();
			if (src.equals(gui.getPricelist_addNameJTextField()))
				this.checkAddProductFields();
			if (src.equals(gui.getPricelist_addPriceJTextField()))
				this.checkAddProductFields();
			if (src.equals(gui.getPricelist_editPriceJTextField()))
				this.checkEditProductFields();
			listeners = true;
		}
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent src) {
		backup();
		quit();
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	private Component statsTab;
	private boolean statsAdded = false;

	public void keyPressed(KeyEvent arg) {
		if (!statsAdded && arg.isControlDown() && arg.isShiftDown()
				&& arg.getKeyChar() == ' ') {
			statsAdded = true;
			statsTab.setName("Tilastot");

			gui.getTabPanel().add(statsTab);
			gui.getTabPanel().setSelectedComponent(statsTab);
		}
		// arg.consume();
	}

	public void keyReleased(KeyEvent arg) {
		if (statsAdded) {
			gui.getTabPanel().removeTabAt(gui.getTabPanel().getTabCount() - 1);
			statsAdded = false;
		}
		// arg.consume();

	}

	public void keyTyped(KeyEvent arg0) {

	}

	// Not ready yet - choose the selected person in the dropdown list
	public void mouseClicked(MouseEvent event) {
		if (listeners) {
			listeners = false;
			Object src = event.getSource();

			if (src.equals(gui.getHistory_tableJTable()))
//				Object obj = ((DefaultTableModel)gui.getHistory_tableJTable().getModel()).getDataVector().get(gui.getHistory_tableJTable().getSelectedRow());
//				System.out.println("hello " + obj);
			

			listeners = true;
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
