
# Important URLs:
1.	GIT URL: https://github.com/SowmyaSuresh23/sogeti.git
2.	SWAGGER URL: http://localhost:8085/swagger-ui/index.html
3.	H2 DB: http://localhost:8085/h2-console/<br />
Database name: carLeaseDB<br />
Username: SogetiAdmin<br />
Password: SogetiAdmin*
4.	Actuator: http://localhost:8085/actuator

# API endpoints:
1.	**Add new car**<br />
					 Endpoint: http://localhost:8085/carDetails/saveCarDetails<br /><br />		
Sample JSON input: 
		
		{
			"carNumber": "8-xxx-90",
    			"make": "toyota",
    			"version": "2",
			"model": "model",
    			"interestRate": 4.5,
    			"noOfDoors": 4,
    			"co2Emission": 125,
    			"grossPrice": 40000,
    			"netPrice": 5000.0,
    			"mileage": 150,
    			"userName": "Sowmya"
		}
		
	
		
2.	**Delete a car** <br />
					 Endpoint: http://localhost:8085/carDetails/deleteCar<br /><br />	
		Sample input: 
		
				Request parameter:  "carNumber"
				Value: "2-xxx-90"
				
	
		
		
3.	**Update car details**<br />
		Endpoint: http://localhost:8085/carDetails/updateCarDetails<br /><br />
		Sample JSON input: 
		
		{
			"carNumber": "8-xxx-90",
    			"make": "toyota new",
    			"version": "5",
			"model": "abc",
    			"interestRate": 4.5,
    			"noOfDoors": 4,
    			"co2Emission": 125,
    			"grossPrice": 40000,
    			"netPrice": 5000.0,
    			"mileage": 150,
    			"userName": "Sowmya"
		}
		
		
	
		
		
4.	**Fetch car details**<br />
		Endpoint: http://localhost:8085/carDetails/getCarDetails<br /><br />
		Sample input:
		
		
				Request parameter:  "carNumber"
				Value: "2-xxx-90"
				
		
5.	**Create new lease**<br />
					 Endpoint: http://localhost:8085/carLease/createNewLease<br /><br />		
		Sample JSON input: 

		
		{
			"carNumber": "2-xxx-90",
    			"customerId": "2",
    			"duration": "1.1",
    			"leaseRate": 400,
    			"interestRate": 20.55,
    			"userId": "Sowmya",
    			"customerEmail": "jacob@fmailcom"
		}
		
	
		
6.	**Calculate lease rate for a car** <br />
					 Endpoint: http://localhost:8085/carLease/calculateLease<br /><br />	
		Sample JSON input: 

		
				{
    					"carNumber": "2-xxx-90",
    					"duration": "1.1",
    					"interestRate": 20.55
				}
				
	
		
		

		
7.	**Fetch lease details**<br />
		Endpoint: http://localhost:8085/carLease/getLeaseDetails<br /><br />
		Sample input:
		
		
				Request parameter:  "leaseId"
				Value: "1"


8.	**Delete lease details**<br />
		Endpoint: http://localhost:8085/carLease/deleteLeaseDetails<br /><br />
		Sample input:
		
		
				Request parameter:  "leaseId"
				Value: "1"
		
