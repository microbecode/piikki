package piikki;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class GUIWrapper {
	private static DBWrapper db = new DBWrapper();

	public static void buttonAdminAddUser(String name, String details) {
		String msg = "K�ytt�j� " + name + " lis�tty onnistuneesti";
		String query = "insert into Users values ('" + name + "', '" + details
				+ "', " + "false, " + 0.0 + ");";
		boolean ok = db.execute(query);
		if (!ok) {
			msg = "Tietokantavirhe (k�ytt�j� on jo olemassa?)";
		} else {
			refreshUsers();
		}
		GUIController.notifyMsg = msg;
	}

	public static boolean buttonAdminRemoveUser(User user) {
		// user = (User)Common.usersTable.get(user.toString());
		String msg = "K�ytt�j� " + user + " poistettu onnistuneesti";

		if (user.getBalance() != 0.0) {
			msg = "K�ytt�j�� ei voi poistaa, h�nen saldonsa on: "
					+ user.getBalance() + "e";
			GUIController.notifyMsg = msg;
			return false;
		} else {
			String query = "delete from Users where name = '" + user + "';";
			boolean ok = db.execute(query);
			if (!ok) {
				msg = "Tietokantavirhe";
			} else {
				refreshUsers();
			}
		}
		GUIController.notifyMsg = msg;
		return true;
	}

	public static void addProduct(int number, String name, double price) {
		String query = "insert into Products values (" + number + ", '" + name
				+ "', " + price + ");";
		db.execute(query);
	}

	public static void buttonPricelistRemoveProd(String name) {
		String query = "delete from Products where name = '" + name + "';";
		db.execute(query);
	}

	public static void buttonPricelistChangeProd(int num, String name,
			double price) {
		// String msg = "Tuotenumero " + num + " muutettu onnistuneesti";
		String query = "update Products set number = " + num + ", name = '"
				+ name + "', price = " + price + " WHERE name = '" + name
				+ "';";
		boolean ok = db.execute(query);
		if (!ok) {
			// msg = "Tietokantavirhe";
		} else
			refreshProducts();
		// GUIController.notifyMsg = msg;
	}

	public static void modifyUserAndTap(User user, String extra, boolean plus,
			boolean discount, double delta) {
		// user = (User)Common.usersTable.get(user.toString());
		if (!plus)
			delta = delta - delta * 2;
		// System.out.println(user.getBalance() + delta);
		double newBalance = Common.getNum(user.getBalance() + delta);
		// newBalance = (int) (newBalance * 100 + 0.5) / 100.0;
		String msg = "K�ytt�j�� " + user
				+ " muokattu onnistuneesti, uusi saldo: " + newBalance;
		String query = "update Users set discount = " + discount
				+ ", balance = " + newBalance + ", details = '" + extra
				+ "' WHERE name = '" + user + "';";
		// System.out.println(newBalance);
		boolean ok = db.execute(query);
		if (!ok) {
			msg = "Tietokantavirhe";
		} else {
			refreshUsers();
		}
		GUIController.notifyMsg = msg;
	}

	public static String getBackupPath() {
		return db.getBackUpPathAndDiscount();
	}

	public static void setBackupPath(String path) {
		String query = "update Stuff SET backup = '" + path + "';";
		db.execute(query);
	}

	public static void order(Vector sells, User user) {

		double newBalance = Common.getNum(user.getBalance()
				- Double.parseDouble(sells.lastElement().toString()));

		String query = "update Users set balance = " + newBalance
				+ " WHERE name = '" + user + "';";
		boolean ok = db.execute(query);
		if (ok) {
			user.setBalance(newBalance);
			db.addOrderLog(sells, user);
		}
	}

	public static void addAdminLog(String action, String actionValue,
			String target) {
		db.addAdminLog(action, actionValue, target);
	}

	// from dbwrapper to GUI
	@SuppressWarnings("unchecked")
	public static void updateAdminLog(Timestamp stamp, String action,
			String actionValue, String target) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy   HH:mm:ss");
		Vector<String> row = new Vector();
		row.add(format.format(stamp));
		row.add(action);
		row.add(actionValue);
		row.add(target);
		GUIController.adminLog.insertElementAt(row, 0);
	}

	public static void resetDb() {
		db.init();
	}

	// From GUI to dbwrapper
	public static void getAdminLog() {
		db.getAdminLog();
	}

	// from dbwrapper to GUI
	@SuppressWarnings("unchecked")
	public static void updateOrderLog(Timestamp stamp, String name,
			String product, double price) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy   HH:mm:ss");
		Vector<Object> row = new Vector<Object>();
		row.add(format.format(stamp));
		row.add(name);
		row.add(product);
		row.add(new Double(price));
		GUIController.buyLog.insertElementAt(row, 0);
		// System.out.println("jo");
	}

	// From GUI to dbwrapper
	public static void getOrderLog() {
		db.getOrderLog();
	}

	public static void refreshUsers() {
		Common.usersVector = db.getUsers();
		Common.sortUsers();
	}

	public static void refreshProducts() {
		Common.products = db.getProducts();
	}

	public static List<Analysis.StatsData> getStats(String product) {
		return db.getStats(product);
	}

	public static void eraseInfo() {
		db.dropTables();
	}
	
	/**
	 * Removes logs
	 */
	public static void removeLogs() {
		db.removeLogs();
	}

	public static CustomQueryResult customQuery(String query) {
		//return db.getCustomQueryResult(query);
		return null;
		
	}

	public class CustomQueryResult {
		private List<String> headers;
		private String[][] data;
		
		public List<String> getHeaders() {
			return headers;
		}
		public void setHeaders(List<String> headers) {
			this.headers = headers;
		}
		public String[][] getData() {
			return data;
		}
		public void setData(String[][] data) {
			this.data = data;
		}



	}

	
	
	public static String[][] getBalanceStats() {
		return db.getBalanceStats();
	}

}
