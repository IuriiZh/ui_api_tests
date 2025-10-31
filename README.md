# Selenide, JUnit5, Allure Example


- [**Libraries & Tools**](#libraries--tools)
- [**Project Structure**](#project-structure)
- [**How To Use**](#how-to-use)
  - [**Clone Repository**](#clone-repository)
  - [**Building**](#building)
  - [**Generate Allure report**](#Generate-Allure-Report)
  - [**Perform On Browsers**](#perform-on-browsers)

## **Libraries & Tools**
> **Maven**<br/>
> **Selenide**<br/>
> **Allure**<br/>
> **JUnit5 - Platform**<br/>
> **JUnit5 - Jupiter**<br/>
> **JUnit5 - Vintage**<br/>

## **Project Structure**
```
UI_API_Tests/
├── src/
│   ├── main/
│   │   ├── java/
│   │       ├── api/
│   │       │   ├── reqres_in/
│   │       │   │   ├── CreateUserRequest.java
│   │       │   │   ├── CreatedUserResponse.java
│   │       │   │   ├── ListResourceResponse.java
│   │       │   │   ├── SuccessfulLoginRequest.java
│   │       │   │   ├── SuccessfulLoginResponse.java
│   │       │   │   ├── SuccessfulRegisterRequest.java
│   │       │   │   ├── SuccessfulRegisterResponse.java
│   │       │   │   └── UserdataResponse.java
│   │       │   ├── setup/
│   │       │   │   ├── RequestApiService.java
│   │       │   │   └── RequestSpecs.java
│   │       │   ├── util/
│   │       │       ├── JsonToStringConverter.java
│   │       │       └── ReadProperties.java
│   │       ├── ui/
│   │       │   ├── pageObject/
│   │       │       ├── CartPage.java
│   │       │       ├── CheckoutInformationPage.java
│   │       │       ├── CheckoutOverviewPage.java
│   │       │       ├── LandingPage.java
│   │       │       ├── LoginPage.java
│   │       │       ├── Page.java
│   │       │       └── ProductsPage.java
│   │       ├── utils/
│   │           ├── PropertyReader.java
│   │           ├── UIBaseTest.java
│   │           └── WebEventListener.java
│   ├── test/
│       ├── java/
│       │   ├── tests/
│       │       ├── api/
│       │       │   ├── DeleteUserTest.java
│       │       │   ├── LoginTests.java
│       │       │   ├── RegisterTests.java
│       │       │   ├── ResourceTests.java
│       │       │   ├── UserTests.java
│       │       │   └── UserdataTests.java
│       │       ├── ui/
│       │           └── UITests.java
│       ├── resources/
│           ├── reqres/
│           │   ├── json_scheme/
│           │   │   ├── list_resource.json
│           │   │   ├── single_user.json
│           │   │   └── userdata_list.json
│           │   ├── test_data/
│           │       ├── td1.json
│           │       └── td2.json
│           ├── allure.properties
│           ├── log4j.properties
│           ├── login-data.csv
│           └── test.properties
├── README.md
├── pom.xml
└── testCases.txt
```

## **How To Use**
### **Clone Repository**
> `$ git clone https://github.com/IuriiZh/ui_api_tests.git
>
### **Building**

> `mvn clean verify`

### **Generate Allure report**

> `allure serve target/allure-results`


### **Perform On Browsers**
- chrome
- edge
- firefox
- ie
