
# Important URLs:
1.	GIT URL: https://github.com/SowmyaSuresh23/Sogeti-assessment.git
2.	SWAGGER URL: http://localhost:8086/swagger-ui/index.html
3.	H2 DB: http://localhost:8086/h2-console/<br />
Database name: carLeaseDB<br />
Username: SogetiAdmin<br />
Password: SogetiAdmin*
4.	Actuator: http://localhost:8085/actuator

# API endpoints:
1.	**Add new customer**<br />
					 Endpoint: http://localhost:8086/customerDetails/saveCustomer<br /><br />		
Sample JSON input: 
		
		{
			"customerName": "Jacob",
    			"street": "wierdensestraat",
    			"houseNumber": "11",
    			"zipCode": "1234HJ",
    			"place": "Utrecth",
    			"email": "jacob@gmail.com",
    			"phoneNumber": "+31698756965",
    			"userName": "Sowmya"
		}
		
	
		
2.	**Delete a customer** <br />
					 Endpoint: http://localhost:8086/customerDetails/deleteCustomer<br /><br />	
		Sample input: 
		
				Request parameter:  "email"
				Value: "jacob@gmail.com"
				
	
		
		
3.	**Update customer details**<br />
		Endpoint: http://localhost:8086/customerDetails/updateCustomerDetails<br /><br />
		Sample JSON input: 
		
		{
			"customerName": "Jacob",
    			"street": "wierdensestraat",
    			"houseNumber": "11",
    			"zipCode": "1234HJ",
    			"place": "Almelo",
    			"email": "jacob@gmail.com",
    			"phoneNumber": "+31698756965",
    			"userName": "Sowmya"
		}
		
		
	
		
		
4.	**Fetch customer details**<br />
		Endpoint: http://localhost:8086/customerDetails/findCustomerDetails<br /><br />
		Sample input:
		
		
				Request parameter:  "email"
				Value: "jacob@gmail.com"
				
		