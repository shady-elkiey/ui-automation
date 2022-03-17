# Automation Testing

The Automation Testing repository contains the test cases related to the following:

- Authentication using standard user credentials for logging in
- Comprehensive test cases related to the test cases which cover the CATEGORIES endpoint.

### Tools and Technologies Used

* [Selenium 3.141.59](https://www.selenium.dev/)
* [Gson 2.9.0](https://github.com/google/gson)
* [REST Assured](https://rest-assured.io/)
* [TestNG 6.10](https://testng.org/doc/)
* [Maven 3.8.1](https://maven.apache.org)

### Requirements

For building and running the application you need:

- [JDK 16](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.8.1](https://maven.apache.org)

### Running the application locally

There are several ways to run the application on your local machine. One way is to execute the `test cases` method
in the `com.dell.ApiTest` class from your IDE.

Alternatively you can use the maven goal like so:

#### MacOS/Linux:

```shell
./mvnw test
```

#### Windows:

```shell
mvn test
```