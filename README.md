 **Technical Assessment Case Study -	myRetail RESTful service**
 ----------------------------------------------------------------
**Technology Used :**

 - Spring boot 2.0.2
 - Java 1.8.0_171 
 - Junit,Mokito 
 - Mongo DB 
 - Maven 
 
**Application set up:**

 - Java 1.8.0_171 (Need to have java verison >= 1.8.0151, faced issue while mocking few classes)
 - Install Mongo DB  
 - Maven 3.3
 - Postman (or any rest cleint)
 - Download/clone the project from GitHub repository.
 
  
**To execute Test cases and Integration Test:**

 ```sh
  mvn clean test (From the project folder execute the command from command promot)
```
**To start Mongo DB**
 
 ```sh
C:\data\db  (Need to create directory)
then 
C:\Program Files\MongoDB\Server\3.2\bin>mongod (by default, mongodb server will start at port 27017)
```

**To start application:**
 
 ```sh
  mvn spring-boot:run -Dspring.profiles.active=dev  (Tomcat will starts on default port 8080)
```

**Application endpoints:**
 
 ```sh
 Get Method  : http://localhost:8080/products/v1/product/{productid}   (Not secured)
 
 Post Method : http://localhost:8080/products/v1/product  (secured- Admin/Admin)
  ```
 
 
**Swagger Documentation link:**

http://localhost:8080/swagger-ui.html#/product-controller

**Postman API document:**

https://documenter.getpostman.com/view/534845/RW87p9pT


**Implemented Features:**
 - **Basic Authentication:**
    - Get method to fecth the product info, will be access to all.
    - Post method to update price info, will be secured. (Admin/Admin).
- **Logging:**
    - Used logback for logging, Followed logging best pratices helps troubleshooting and also helps monitoring, creating metrices and dashboards.
    - Maintaing key values and Transaction id for each request.  
 - **RestService calls:**
    - RestService template acts as a wrapper class for any rest service calls.    
- **Documentation:**
    - Code has been completely documented through unit test case and integration test.
 
 





