
public class Query2 {

	private Metric maxNY; // to compute the maximum sales data for "cust-prod"
							// and for "NY" state
	private Metric maxNJ; // to compute the maximum sales data for "cust-prod"
							// and for "NJ" state
	private Metric minCT; // to compute the minimum sales data for "cust-prod"
							// and for "CT" state

	Query2() { // default constructor
		this.maxNY = new Metric();
		this.maxNJ = new Metric();
		this.minCT = new Metric();
	}

	@Override
	public String toString() { // return computed values
		return String.format("%6s%6s%6s", maxNY, maxNJ, minCT);
	}

	// Getters and setters for each of the private variables
	public Metric getMaxNY() {
		return maxNY;
	}

	public void setMaxNY(Metric maxNY) {
		this.maxNY = maxNY;
	}

	public Metric getMaxNJ() {
		return maxNJ;
	}

	public void setMaxNJ(Metric maxNJ) {
		this.maxNJ = maxNJ;
	}

	public Metric getMinCT() {
		return minCT;
	}

	public void setMinCT(Metric minCT) {
		this.minCT = minCT;
	}

}
