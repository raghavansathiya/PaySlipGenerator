# Project Title

Springboot application that generates payslip for list of employees for the provided taxcategory

### Prerequisites

This project requires Java 1.8 and Maven to be installed 

## Getting Started
Clone or download the project and run the following maven command

```mvn clean package```

Copy src/main/resources/taxcategory.json to your home dir

### Starting the application

```java -jar target/EmployeePayrollSystem-spring-boot.jar```

### Testing the application

Sample input data

```[
  {
    "firstName": "David",
    "lastName": "Jones",
    "salary": 100000,
    "super_rate": 9.5,
    "payment_start_date": "01 March - 31 March"
  },
  {
    "firstName": "Ryan",
    "lastName": "Chen",
    "salary": 120000,
    "super_rate": 10,
    "payment_start_date": "01 January - 31 January"
  },
  {
    "firstName": "Sathiya Raghavan",
    "lastName": "Navaneethakrishnan",
    "salary": 108000,
    "super_rate": 9,
    "payment_start_date": "01 December - 15 December"
  }
]
```

## Running the tests

Inside the project dir run 

```mvn test```
