
# NOVI University backend assignment -Car garage

Welcome to my school project where i created a backend system for a car garage. The main goal of the system is to support the APK-inspection of a car. 
This is my very first developing backend-project in the Java programming language using Springboot. The scope is kept small to small so my learning curve was manageable. 




## Acknowledgements

 - [University NOVI](https://novi.nl/)

## Installation

To get this project working i used the following software programs. Be my guest installing the same or try something different: 
- IntelliJ IDEA 2021.2 
- Postman v9.15.13
- pgAdmin 4 v6
- Postgresql database via Docker versie 4.4.4

The following conditions need to be set to get started: 

1. Create a Postgresql database. I used it in a docker container. Use the following command to create and start it: 
```bash
docker run -p 5432:5432 --name autogarage -e POSTGRES_PASSWORD=novi -e 
POSTGRES_USER=novi -e POSTGRES_DB=autogarage -d postgres
```
2. Start the pgAdmin software program. 
```bash
- Set your master POSTGRES_PASSWORD 
- Add the Postgresql database made in step 1 as an server. Use the credentials as inputted in step 1. 
```
3. Download the project ZIP (use the green download button on this page) and unzip it. 
4. Start the IntelliJ IDEA and open the downloaded, unzipped project as a new excisting project. In the project you need to change a few settings based on your own preferences or its something you need to be aware of: 
```bash
- application.properties: Change the upload path to your own situation! The path /Users/Novi is probably not your username! 
- The data.sql has some pre-configured data whom you can use right from the beginning. You can add, or delete as prefered. 
- The encoded set passwords all have a plain text value of 'password'. You can also create a new user, but only the admin (florus) is authorised doing so!
- Project SDK: openjdk-17,  project language-level: SDK default
- Using Postman needs a valid JWT token, generate one by logging in. 

```


## Postman collections
Each role (admin, assistant and mechanic) has its own permission on all end-points. Check out the webapplication.config to see who is allowed to do what. 
As stated before, you need to login, get a valid JWT and use this in Postman under 'Authority' and choose 'Bearer Token'. 
I will put a few Postman examples below: 

#### Login
Endpoint: http://localhost:8080/login
Raw Json data: 
```bash
{
    "username": "florus",
    "password": "password"
}
```

#### GetAllCustomers
Endpoint: http://localhost:8080/customers

#### AddNewCustomer
Endpoint: http://localhost:8080/customers/add
Raw Json data:
```bash
{
"bsnnumber": "999990999",  
"firstname": "Jihn", 
"lastname": "Doe", 
"phonenumber": "0656548781" 
}
```

#### getOneCarByLicenseplateNumber
Endpoint: http://localhost:8080/cars/licenseplate?number=31-lz-xl

#### getOneCarByLicenseplateNumber
```bash
{
    "username": "florus",
    "password": "password"
}
```

#### partialEditCar
Endpoint: http://localhost:8080/cars/77-XZ-ML
Method: PATCH
Raw Json data:
```bash
{
"brand": "Opel" 
}
```

### getAllApkApointments
Endpoint: http://localhost:8080/appointments/apk
Method: GET

### addNewAppointment
Endpoint: http://localhost:8080/appointments/new
Method: POST
Raw Json data: 
```bash
{
"apk": "true",
"repair": "false", 
"dateTimeAppointment": "2022-04-01T15:00:00"
}
```

### changeApkStatusAppointment
Endpoint: http://localhost:8080/appointments/3/setapkstatus
Method: PATCH
Raw Json data: 
```bash
{
    "apkStatus": "FAIL"
}
```

### addCustomerToAppointment
Endpoint: http://localhost:8080/appointments/2/customer
Method: PATCH
Raw Json data: 
```bash
222222222
```

### deletePart
Endpoint: http://localhost:8080/parts/1
Method: Delete

### uploadCarRegistrationDocument
Endpoint: http://localhost:8080/upload/car_registration_document
Method: POST
Data: form-data;
```bash
    key     = file 
    value   = {YOUR_CHOSEN.PDF_FILE}
```
