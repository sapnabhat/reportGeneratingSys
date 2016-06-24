
public class Query1 {

	private Metric maxMetric; // to compute the maximum sales data for
								// "cust-prod"(quantity,date and state)
	private Metric minMetric; // to compute the minimum sales for
								// "cust-prod"(quantity,date and state)
	private Integer sum; // this is used for calculating AVG_Q
	private Integer count; // this is used for calculating AVG_Q

	Query1() { // default constructor
		this.maxMetric = new Metric();
		this.minMetric = new Metric();
		this.sum = 0;
		this.count = 0;
	}

	@Override
	public String toString() { // return computed values
		return String.format("%5s%5s%5s", maxMetric, minMetric, sum / count);
	}

	// Getters and setters for each of the private variables
	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Metric getMaxMetric() {
		return maxMetric;
	}

	public void setMaxMetric(Metric maxMetric) {
		this.maxMetric = maxMetric;
	}

	public Metric getMinMetric() {
		return minMetric;
	}

	public void setMinMetric(Metric minMetric) {
		this.minMetric = minMetric;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}