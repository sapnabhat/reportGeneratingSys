
* Simple Database Application 
Program #1" -Generating 2 separate reports based on the following queries (one report for query #1 and another for query #2):

1. For each combination of customer and product, compute the maximum and minimum sales quantities along with the corresponding dates (i.e., dates of those maximum and  minimum sales quantities) and the state in which the sale transaction took place. 
If there are >1 occurrences of the max or min, choose only one. 
For the same combination of product and customer, compute the average sales quantity. 
 
2. For each combination of customer and product, output the maximum sales quantities for NY and NJ and minimum sales quantities for CT in 3 separate columns. Like the first report, display the corresponding dates (i.e., dates of those maximum and minimum sales quantities). 
Furthermore, for NY and NJ, include only the sales that occurred between 2000 and 2005; for CT, include all sales.
(The output is generated with a single scan of the ‘sales’ table).


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

To compile the program - 


1. The assignment zip file consists of 4 .java files - Metric.java , Query1.java, Query2.java, SimpleQuery.java

2. SimpleQuery.java contains main() method hence use this to execute the program. This runs on Eclipse.

3. I have used the USR(postgres) PWD(Sapna123) of my database server in main(). Please use appropriate USR PWD to run on your machine.
 
4. Metric.java contains Metric Class that defines private variables Quantity,State and Date String. It contains getter methods and setter methods for the same.
   It also contains a method to convert Date string to the required format MM/DD/YYYY (i.e., 01/02/2002 instead of 1/2/2002).

3. Query1.java consists of private variables to compute values for each customer-product combination - maximum sales quantity,minimum sales quantity,sum and count(compute average quantity)

4. Query2.java consists of private variables to compute values for each customer-product combination - maximum sales quantity for state NY(during year 2000 to 2005),maximum sales quantity for state NJ(during year 2000 to 2005), minimum sales quantity for state CT.
 
5. SimpleQuery.java has the main() method, data structure Hashmap to compute the above values and store the results.



--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Choice of Data Structure - Hashmaps


1. Hashmap is the data structure used. The reason is hashmap works with (key,value) pairs. 
   For this assignment, we need maximum sales quantity,minimum sales quantity and average sales quantity for each unique customer-product combination hence its easier to retrieve and search through hashmaps.

2. Hashmap <Key,Value> for Query 1
   They key is (customer-product) and value is (maxMetric(quantity,date,state) , minMetric(quantity,date,state), avg(sum/count))


3. Hashmap <Key,Value> for Query 2
   They key is (customer-product) and value is (maxNY(quantity,date) , maxNJ(quantity,date), minCT(quantity,date))


4. All this is done using single scan. Every row is computed for Query 1 and Query 2 and results are stored in appropriate hashmaps(hmap1 and hmap2)

5. For printing the results of Query 1 and Query 2 I first split the customer-product unique combination. Then I format the results in tabular format during String.format

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Pseudo code-

Connect to database server{

	declare hashmap 1, hashmap2

	while(till you the last row of sales table){

		create "cust-prod" unique combination;

		For Query 1 check if "cust-prod" exists in hashmap1
		if 'no' then add it to hashmap 1 with entries (quant,date,state)
			compute sum and update count
		if 'yes' then update the already existing entry in hashmap 1
			compare if its max, if yes then make it the new max, get its date and state entries
			compare if its	min, if yes then make new min, get its date and state entries


		For Query 2 check if "cust-prod" exists in hashmap2
		if 'no' check if cust-prod combination belongs to NY and date is between 2000 and 2005 
			If 'yes' then add it to hashmap 2 with entries (max quant,date)
		if 'no' check if cust-prod combination belongs to NJ and date is between 2000 and 2005 
			If 'yes' then add it to hashmap 2 with entries (max quant,date)
		if 'no' check if cust-prod combination belongs to CT 
			If 'yes' then add it to hashmap 2 with entries (min quant,date)

		
		if 'yes' then update the already existing entry in hashmap 2
			compare if its max, if yes then make it the new max, get its date
			compare if its	min, if yes then make new min, get its date
	}
}

Print the results of Query 1 and Query 2 in tabular format

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		
	
		





	





 
 





 
 




