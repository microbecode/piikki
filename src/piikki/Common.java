package piikki;

import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class Common implements Comparator {
	
	public static double discount = 0.2;
	
	public static Hashtable usersTable = null;

	public static List usersVector = new Vector<Object>();

	public static List<Product> products = new Vector<Product>();

	@SuppressWarnings("unchecked")
	public static void sortUsers() {
		Collections.sort(usersVector,new Common());
		
		// Refresh the hashtable;
		usersTable = new Hashtable();
		for (int a = 0; a < usersVector.size(); a++) {
			usersTable.put(((User)usersVector.get(a)).getName(), ((User)usersVector.get(a)));
		}
	}
	
	public static void setProducts(Vector<Product> prod) {
		products = prod;
	}
	public int compare(Object arg0, Object arg1) {
		 User user1 = (User)arg0;
		 User user2 = (User)arg1;
		 return user1.getName().toLowerCase().compareTo(user2.getName().toLowerCase());

	}
	
	// Fixes problems with double's mantis, a + b = x.00000001 
	public static double getNum(double num) {
		Double d;
		if (num < 0) d = new Double(num * 20 - 0.5);
		else d = new Double(num * 20 + 0.5);
		int a = d.intValue();
		double b = (double)a/20;
		return b;
	}

}
