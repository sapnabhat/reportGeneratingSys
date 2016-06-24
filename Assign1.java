
/* Simple Database Application Program #1" -Generating 2 separate reports based on the following queries (one report for query #1 and another for query #2):
 * 1. For each combination of customer and product, compute the maximum and minimum sales quantities along with the corresponding dates (i.e., dates of those maximum and minimum sales quantities) 
 * and the state in which the sale transaction took place. If there are >1 occurrences of the max or min, choose only one. 
 * For the same combination of product and customer, compute the average sales quantity. 
 * 2. For each combination of customer and product, output the maximum sales quantities for NY and NJ and minimum sales quantities for CT in 3 separate columns. 
 * Like the first report, display the corresponding dates (i.e., dates of those maximum and minimum sales quantities). 
 * Furthermore, for NY and NJ, include only the sales that occurred between 2000 and 2005; for CT, include all sales.
 * (The output is generated with a single scan of the ‘sales’ table).
 */

import java.sql.*;
import java.util.*;

public class Assign1 {

	public static void main(String[] args) {
		// set up database's user name, password and URL
		String usr = "postgres";
		String pwd = "Sapna123";
		String url = "jdbc:postgresql://localhost:5432/postgres";

		// for server
		// String usr = "XXXXXX";
		// String pwd = "**********";
		// String url = "jdbc:postgresql://xxx.xxx.xxx.xxx:5432/postgres";
		// load driver

		// To implement Query1 and Query2 using two Hashmaps.
		// The results computed for Query 1 and Query 2 will be added to the
		// corresponding hashmaps
		HashMap<String, Query1> hmap1 = new HashMap<String, Query1>();
		HashMap<String, Query2> hmap2 = new HashMap<String, Query2>();

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Successfully loaded the driver!");
		} catch (Exception e) {
			System.out.println("Failed to load the driver!");
			e.printStackTrace();
		}
		// connecting server
		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Successfully connected to the server!");
			// get query result to ResultSet rs
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			String combination; // concatenation of strings "cust" and "prod" in
								// the format "cust-prod"

			// Begin iterating from first row and compute the results of Query 1
			// and Query 2 one row one at a time(single scan)
			while (rs.next()) {

				combination = rs.getString("cust") + "-" + rs.getString("prod");

				// Begin computing Query 1
				Query1 obj1 = null;

				if (!hmap1.containsKey(combination)) { // If "cust-prod" unique
														// combination does not
														// exist in hashmap 1
														// then add it with corresponding entries quant,date,state

					obj1 = new Query1();
					obj1.getMaxMetric().setQuantity(rs.getInt("quant"));
					obj1.getMinMetric().setQuantity(rs.getInt("quant"));
					obj1.getMaxMetric().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
					obj1.getMaxMetric().setState(rs.getString("state"));
					obj1.getMinMetric().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
					obj1.getMinMetric().setState(rs.getString("state"));

					obj1.setSum(rs.getInt("quant"));
					obj1.setCount(1);
					hmap1.put(combination, obj1); // add the entry in hashmap 1
				}

				else { // if "cust-prod" combination already exists in hashmap 1
						// then update the corresponding entries (quant,date,state) in hashmap1
					obj1 = hmap1.get(combination);
					obj1.setCount(obj1.getCount() + 1); //update count for each "cust-prod" unique combination

					if (obj1.getMaxMetric().getQuantity() < rs.getInt("quant")) {
						obj1.getMaxMetric().setQuantity(rs.getInt("quant"));
						obj1.getMaxMetric().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						obj1.getMaxMetric().setState(rs.getString("state"));
					}

					if (obj1.getMinMetric().getQuantity() > rs.getInt("quant")) {
						obj1.getMinMetric().setQuantity(rs.getInt("quant"));
						obj1.getMinMetric().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						obj1.getMinMetric().setState(rs.getString("state"));
					}
					obj1.setSum(obj1.getSum() + rs.getInt("quant")); // compute
																		// sum
																		// to
																		// calculate
																		// AVG_Q

				}

				// Begin computing Query2
				Query2 obj2 = null;

				if (!hmap2.containsKey(combination)) { // If "cust-prod" unique
														// combination does not
														// exist in hashmap 2
														// then add it after
														// checking conditions
														// state belongs to
														// NY(max quant,date),NJ(max quant,date) and date is
														// between (2000 and
														// 2005) and CT(min quant,date)

					obj2 = new Query2();

					if ((rs.getString("state").contains("NY")) && (rs.getInt("year") >= 2000)
							&& (rs.getInt("year") <= 2005)) {
						obj2.getMaxNY().setQuantity(rs.getInt("quant"));
						obj2.getMaxNY().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						hmap2.put(combination, obj2); // add the entry in
														// hashmap2

					} else if ((rs.getString("state").contains("NJ")) && (rs.getInt("year") >= 2000)
							&& (rs.getInt("year") <= 2005)) {
						obj2.getMaxNJ().setQuantity(rs.getInt("quant"));
						obj2.getMaxNJ().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						hmap2.put(combination, obj2); // add the entry in
														// hashmap2
					}

					else if (rs.getString("state").contains("CT")) {
						obj2.getMinCT().setQuantity(rs.getInt("quant"));
						obj2.getMinCT().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						hmap2.put(combination, obj2); // add the entry in
														// hashmap2
					}

				}

				else { // if "cust-prod" unique combination already exists in hashmap2
						// then update the entries(quant,date) in hashmap2
					obj2 = hmap2.get(combination);

					if ((rs.getString("state").contains("NY")) && (rs.getInt("year") >= 2000)
							&& (rs.getInt("year") <= 2005)) {
						if (obj2.getMaxNY().getQuantity() < rs.getInt("quant")) {
							obj2.getMaxNY().setQuantity(rs.getInt("quant"));
							obj2.getMaxNY().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						}
					} else if ((rs.getString("state").contains("NJ")) && (rs.getInt("year") >= 2000)
							&& (rs.getInt("year") <= 2005)) {
						if (obj2.getMaxNJ().getQuantity() < rs.getInt("quant")) {
							obj2.getMaxNJ().setQuantity(rs.getInt("quant"));
							obj2.getMaxNJ().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						}
					} else if (rs.getString("state").contains("CT")) {
						if (obj2.getMinCT().getQuantity() > 0 && obj2.getMinCT().getQuantity() > rs.getInt("quant")) {
							obj2.getMinCT().setQuantity(rs.getInt("quant"));
							obj2.getMinCT().setDate(rs.getString("month"), rs.getString("day"), rs.getString("year"));
						}
					}
				}

			}

		} catch (SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

		// Printing Query 1 results in the desired tabular format
		Iterator it1 = hmap1.entrySet().iterator();
		System.out.println("===========================================================================");
		System.out.format("%-10s %-10s %-5s %-11s %-2s %-5s %-11s %-2s %-5s", "CUSTOMER", "PRODUCT", "MAX_Q", "DATE",
				"ST", "  MIN_Q", " DATE", " ST", "  AVG_Q");
		System.out.println("\n");
		System.out.println("===========================================================================");

		while (it1.hasNext()) { // Iterating through hashmap 1
			Map.Entry entry = (Map.Entry) it1.next();
			String[] custProd = splitCombination((String) entry.getKey()); // Split
																			// "cust-prod"
																			// combination
			System.out.format("%-10s %-10s", custProd[0], custProd[1]);
			System.out.format("%5s \n", (String) entry.getValue().toString());
		}

		System.out.println("\n");

		// Printing Query 2 results in the desired tabular format
		Iterator it2 = hmap2.entrySet().iterator();
		System.out.println("=======================================================================================");
		System.out.format("%-10s %-10s %-6s %-16s %-8s %-10s %-12s %-10s", "CUSTOMER", "PRODUCT", "NY_MAX", "DATE",
				"NJ_MAX", "DATE", "  CT_MIN", "DATE");
		System.out.println("\n");
		System.out.println("=======================================================================================");

		while (it2.hasNext()) { // Iterating through hashmap 2
			Map.Entry entry = (Map.Entry) it2.next();
			String[] custProd = splitCombination((String) entry.getKey()); // Split
																			// "cust-prod"
																			// combination
			System.out.format("%-10s %-10s", custProd[0], custProd[1]);
			System.out.format("%-5s \n", (String) entry.getValue().toString());

		}
	}

	// Method to split the "cust-prod" combination which is separated by '-'
	public static String[] splitCombination(String uniqueCombination) {
		String[] custProd = new String[2];
		custProd = uniqueCombination.split("-");
		return custProd;
	}
}
