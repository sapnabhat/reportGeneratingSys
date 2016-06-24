
public class Metric {

	private Integer quantity; // Sales Quantity
	private String state; // State string
	private String dateStr; // Date string

	Metric() { // default constructor
		this.quantity = 0;
		this.state = "";
		this.dateStr = "";
	}

	public void setDate(String mm, String dd, String year) { // concatenate
																// day,month and
																// year in the
																// format
																// mm/dd//year
		dateStr = (mm + "/" + dd + "/" + year);
	}

	public String enrichDateStr(String dat) { // enrich the Date string into
												// mm/dd/yyyy (i.e., 01/02/2002
												// instead of 1/2/2002)
		String[] datParam = new String[3];
		datParam = dat.split("/");
		if (datParam[0].length() == 1) // if single digit convert it into double
										// digit
			datParam[0] = "0" + datParam[0];
		if (datParam[1].length() == 1) // if single digit convert it into double
										// digit
			datParam[1] = "0" + datParam[1];
		return (datParam[0] + "/" + datParam[1] + "/" + datParam[2]);
	}

	@Override
	public String toString() {
		// return "[ QTY=" + quantity + ", ST=" + state + ", DATE=" + dateStr +
		// "]";
		if (quantity > 0)
			return String.format("%5s  %-10s  %-5s", quantity, enrichDateStr(dateStr), state);
		else
			return String.format("  %-10s %-10s ", "NULL", "NULL");
	}

	// Getters and setters for each of the private variables
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}