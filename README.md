# OnlineRetailSystem
## This is a retail online system which developed by using microservices.
## The application have 6 service DiscoveryService, ConfigService, APIGateway, ProductService, CustomerService and OrderService
### To run the application, we must follow step by step below
#### 1. start Eureka Server (DiscoveryService)
#### 2. start Configuration Service
#### 3. create 4 database ```online_retail_system_apigateway, online_retail_system_customer, online_retail_system_product and online_retail_system_orders```
#### 4. start APIGateway
#### 5. start ProductService, CustomerService and OrderService.

### To view list apis, you can go to 
#### Customer Service: ```http://localhost:9000/swagger-ui/index.html```
#### Product Service: ```http://localhost:9002/swagger-ui/index.html```
#### Orders Service: ```http://localhost:9004/swagger-ui/index.html```
### To verify all API, you need to send request via APIGateway (http://localhost:8081)
### We are using JWT for authorization, so you need to do two steps belows to get access to api gateway
#### 1. send POST request to api gateway to register  user and role with url ```http://localhost:8081/api/v1/user/register```
##### payload is 
                {
                    "username": "john",
                    "password": "123",
                    "role": "ADMIN"
                } 
#### 2. sent POST request to api gateway to login user and get token with url ```http://localhost:8081/api/v1/user/login```
##### payload is 
                {
                    "username": "john",
                    "password": "123"
                } 
##### Result is 
                {
                "message": "Login Successful!",
                "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNjg0ODkzNTI4fQ.uN9GGAXDFCK9SXsKqUh3FlfsbHp291rOiJ8B4nXK94g"
                }

#### 3. add a header to your requests with key is ```Authorization``` and value is ```Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNjg0ODEwNjA1fQ.hj1VZL5EB50X1-qTH5zkzeEOPdzggTY7LVOZaEgpYmI```

## To run domain services (ProductService, CustomerService and OrdersService) you need to create 4 database ```online_retail_system_apigateway, online_retail_system_customer, online_retail_system_product and online_retail_system_orders```
#### Now you can ready to call ONLINE RETAIL SYSTEM APIs

#### Payload sample:
##### 1. Create Order
                        {
                        "customerId": 1,
                        "orderItems": [
                            {
                            "quantity": 5,
                            "discountedPrice": 10,
                            "productId":1
                            }
                        ],
                        "totalPrice": 50
                        }
##### 2. Add Items to Existing Orders
                        {
                        "quantity": 5,
                        "discountedPrice": 10,
                        "productId": 1
                        }
##### 3. Create a new Review:
                        {
                        "title": "JavaSceience",
                        "description": "nive book",
                        "numberOfStars": 6,
                        "date": "12/6/2024",
                        "productId": 1
                        }