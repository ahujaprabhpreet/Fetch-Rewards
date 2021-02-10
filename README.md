# Fetch-Rewards
Fetch Rewards Coding Exercise

<h3>Description</h3>
Background
Our users have points in their accounts. Users only see a single balance in their accounts. But for reporting purposes we actually track their points per payer/partner. In our system, each transaction record contains: payer name(string), points (integer), transactionDate (Date).
For earning points it is easy to assign a payer, we know which actions earned the points. And thus which partner should be paying for the points.
When a user spends points, they don't know or care which payer the points come from. But, our accounting team does care how the points are spent. There are two rules for determining what points to "spend" first:
We want the oldest points to be spent first We want no payer's points to go negative.

<h3>Expectations</h3>
Provide routes that:
1. Add points to user account for specific payer and date
2. Deduct points from the user account using above constraints and return a list of [payer, points deducted] for each call to spend points 
3. Return point balance per user that would list all positive points per payer.

<h3>Example</h3>
Suppose you call your add points route with the following sequence of calls:<br/>
add [DANNON, 300 points, 10/31 10AM] to user<br/>
add [UNILEVER, 200 points, 10/31 11AM] to user<br/>
add [DANNON, -200 points, 10/31 3PM] to user<br/>
add [MILLER COORS, 10,000 points , 11/1 2PM] to user<br/>
add [DANNON, 1000 points 11/2 2PM] to user<br/>

Then you call your deduct points route with the following request: <br/>
deduct 5000 points from user<br/>

The expected response from the deduct call would be:<br/>
[DANNON, -100 points, now], <br/>
[UNILEVER, -200 points, now], <br/>
[MILLER COORS, -4,700 points, now]<br/>

A subsequent query to the points balance route, after the deduction, should returns the following results: <br/>
DANNON, 1000 points<br/>
UNILEVER, 0 points<br/>
MILLER COORS, 5,300 points<br/>

<h3>Implementation</h3>
This project is built using Spring boot with Maven.

<h3>Usage and Testing</h3>
<b>Clone the repository:</b><br/>
<p>git clone https://github.com/ahujaprabhpreet/Fetch-Rewards.git</p>

<b>Install the dependencies:</b>

mvn clean install

Maven should be installed

<b>Start the App:</b>

mvn spring-boot:run

Note: port 8080 must be available

Postman can be used to test this API.

<b>To Add points to User:</b><br/>
Make a POST request to http://localhost:8080/points with the Payer name and points in the body:<br/>
{
	"payerName": "Dannon",
	"points": 200
}

<b>To Deduct points:</b><br/>
Make a POST request to http://localhost:8080/deduct with the points in the body:<br/>
{
	"points": 5000
}

<b>To check Balance points:</b><br/>
Make a GET request to http://localhost:8080/points 



