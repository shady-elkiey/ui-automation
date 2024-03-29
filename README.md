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

## Login Test cases:

Test case id: TC_Login_001

Test scenario: Valid Login

**Test Steps**:

1. Navigate to "https://www.saucedemo.com/"
2. Enter a valid username
3. Enter a valid password
4. Click on the login button

**Expected output**: Successful login with the text "PRODUCTS" is displayed

Test case id: TC_Login_002

Test scenario: Login with empty values

**Test Steps**:

1. Navigate to "https://www.saucedemo.com/"
2. Click on the login button
   
**Expected output**: login failed with a message displayed "Epic sadface: Username is required"

Test case id: TC_Login_003

Test scenario: login with an invalid username, and a valid password

**Test Steps**:

1. Navigate to "https://www.saucedemo.com/"
2. Enter an  invalid username
3. Enter a valid password
4. Click on the login button
   
**Expected output**: login failed with a message displayed "Epic sadface: Username and password do not match any user in this service"

Test case id: TC_Login_004

Test scenario: login with a valid username and, an invalid password

**Test Steps**:

1. Navigate to "https://www.saucedemo.com/"
2. Enter a valid username
3. Enter an invalid password
4. Click on the login button

**Expected output**: login failed with a message displayed "Epic sadface: Username and password do not match any user in this service"

Test case id: TC_Login_005

Test scenario: login with invalid username and invalid password

**Test Steps**:

1. Navigate to "https://www.saucedemo.com/"
2. Enter an invalid username
3. Enter an invalid password
4. Click on the login button

**Expected output**: login failed with a message displayed "Epic sadface: Username and password do not match any user in this service"

Test case id: TC_Login_006

Test scenario: login without adding the username

**Test Steps**:

1. Navigate to "https://www.saucedemo.com/"
2. Enter a valid/invalid password
3. Click on the login button
   
**Expected output**: login failed with a message displayed "Epic sadface: Username is required"

Test case id: TC_Login_007

Test scenario: login without adding the password

**Test Steps**:

1. Navigate to "https://www.saucedemo.com/"
2. Enter a valid/invalid username
3. Click on the login button

**Expected output**: login failed with a message displayed "Epic sadface: Password is required"

###Category Test Cases

Test case id: TC_Category_008

Test scenario: Validate the json schema response

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Click on the "Try it out!" button.
3. Check that json response is matching the json schema.

**Expected output**: Response with 200 http status code is displayed.

Test case id: TC_Category_009

Test scenario: Test the get request with the default limit and skip query parameters

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Click on the "Try it out!" button.
3. Check that limit in the response body is equal to 10
4. Check that skip in the response body is equal to 0
5. Check that data array length in the response body is equal to 10
6. Check that data[0].id in the response body is not null value
7. Check that data[1].name in the response body is not null value
8. Check that data[0].subCategories.size() in the response body is equal to 8

**Expected output**: Response with 200 http status code and response body are displayed as expected.

Test case id: TC_Category_010

Test scenario: Test the get request with a custom limit query parameter

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Assign the limit query parameter to 1
3. Click on the "Try it out!" button.
4. Check that limit in the response body is equal to 1
5. Check that skip in the response body is equal to 0
6. Check that data array in the response body length is equal to 1
7. Check that data[0].id in the response body is not null value
8. Check that data[1].name in the response body is not null value
9. Check that data[0].subCategories.size() in the response body is equal to 8

**Expected output**: Response with 200 http status code and response body are displayed as expected.

Test case id: TC_Category_011

Test scenario: Test the get request with a custom skip query parameter

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Assign the skip query parameter to 1
3. Click on the "Try it out!" button.
4. Check that limit in the response body is equal to 10
5. Check that skip in the response body is equal to 1
6. Check that data array length in the response body is equal to 10
7. Check that data[0].id in the response body is not null value
8. Check that data[1].name in the response body is not null value
9. Check that data[0].subCategories.size() in the response body is equal to 0

**Expected output**: Response with 200 http status code and response body are displayed as expected.

Test case id: TC_Category_012

Test scenario: Test the get request with a valid limit and skip query parameter

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Assign the skip query parameter to 1
3. Assign the limit query parameter to 1
4. Click on the "Try it out!" button.
5. Check that limit in the response body is equal to 1
6. Check that skip in the response body is equal to 1
7. Check that data array length in the response body is equal to 1
8. Check that data[0].id in the response body is not null value
9. Check that data[1].name in the response body is not null value
10. Check that data[0].subCategories.size() in the response body is equal to 0

**Expected output**: Response with 200 http status code and response body are displayed as expected.

Test case id: TC_Category_013

Test scenario: Test the get request with an invalid limit and valid skip query parameter

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Assign the skip query parameter to 1
3. Assign the limit query parameter to xyz
4. Click on the "Try it out!" button.
5. Check that message in the response body is not null value

**Expected output**: Response with 500 http status code and response body are displayed as expected.

Test case id: TC_Category_014

Test scenario: Test the get request with an invalid limit query parameter

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Assign the limit query parameter to xyz
3. Click on the "Try it out!" button.
4. Check that message in the response body is not null value

**Expected output**: Response with 500 http status code and response body are displayed as expected.

Test case id: TC_Category_015

Test scenario: Test the get request with an invalid skip query parameter

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategories"
2. Assign the skip query parameter to xyz
3. Click on the "Try it out!" button.
4. Check that message in the response body is not null value

**Expected output**: Response with 500 http status code and response body are displayed as expected.

Test case id: TC_Category_016

Test scenario: Test the post request with a valid request body

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/addCategory"
2. Add a valid request body example:
            {
            "name": "any name",
            "id": "any number"
            }
3. Click on the "Try it out!" button.

**Expected output**: Response with 201 http status code is displayed as expected.

Test case id: TC_Category_017

Test scenario: Test the post request in case of a duplicate

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/addCategory"
2. Add a valid request body example:
            {
            "name": "any name",
            "id": "same number in test case 16"
            }
3. Click on the "Try it out!" button.

**Expected output**: Response with 400 http status code is displayed as expected.

Test case id: TC_Category_018

Test scenario: Test the post request without ID

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/addCategory"
2. Add request body without id example:
            {
            "name": "any name"
            }
3. Click on the "Try it out!" button.

**Expected output**: Response with 400 http status code is displayed as expected.

Test case id: TC_Category_019

Test scenario: Test the post request without name

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/addCategory"
2. Add request body without name example:
            {
            "id": "any number"
            }
3. Click on the "Try it out!" button.

**Expected output**: Response with 400 http status code is displayed as expected.

Test case id: TC_Category_020

Test scenario: Test the post request with an empty request body

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/addCategory"
2. Add an empty request body example:
            {
            }
3. Click on the "Try it out!" button.

**Expected output**: Response with 400 http status code is displayed as expected.

Test case id: TC_Category_021

Test scenario: Test the post request with an invalid request body

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/addCategory"
2. Add an invalid request body example:
            {"name":"any name",
            "id":"any number,
            "age":"any number"
            }
3. Click on the "Try it out!" button.

**Expected output**: Response with 400 http status code is displayed as expected.

Test case id: TC_Category_022

Test scenario: Test the patch request with a valid request body and a valid id

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/updateCategoryById"
2. Add a valid request body example:
            {
            "name":"any name"
            }
3. Add an existing ID in the path parameter
4. Click on the "Try it out!" button.

**Expected output**: Response with 200 http status code is displayed as expected.

Test case id: TC_Category_023

Test scenario: Test the patch request with an invalid id and a valid request body

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/updateCategoryById"
2. Add a valid request body example:
            {
            "name":"any name"
            }
3. Add a non-existing ID in the path parameter
4. Click on the "Try it out!" button.

**Expected output**: Response with 404 http status code is displayed as expected.

Test case id: TC_Category_024

Test scenario: Test the patch request with an invalid request body and a valid id

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/updateCategoryById"
2. Add an invalid request body example:
            {
            "name":
            }
3. Add an existing ID in the path parameter
4. Click on the "Try it out!" button.

**Expected output**: Response with 500 http status code is displayed as expected.

Test case id: TC_Category_025

Test scenario: Test the patch request with an empty request body and a valid id

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/updateCategoryById"
2. Add an invalid request body example:
            {
            }
3. Add an existing ID in the path parameter
4. Click on the "Try it out!" button.

**Expected output**: Response with 200 http status code is displayed as expected.

Test case id: TC_Category_026

Test scenario: Test the delete request with a valid id

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/deletecategory"
2. Add an existing ID in the path parameter
3. Click on the "Try it out!" button.

**Expected output**: Response with 200 http status code is displayed as expected.

Test case id: TC_Category_027

Test scenario: Test the delete request with an invalid id

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/deletecategory"
2. Add a non-existing ID in the path parameter
3. Click on the "Try it out!" button.

**Expected output**: Response with 404 http status code is displayed as expected.

Test case id: TC_Category_028

Test scenario: Test the get request with a valid id

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategoryById"
2. Add an existing ID in the path parameter
3. Click on the "Try it out!" button.

**Expected output**: Response with 200 http status code and the name you have added in a patch/post requests are displayed as expected.

Test case id: TC_Category_029

Test scenario: Test the get request with a valid id

**Test Steps**:

1. Navigate to Swagger url "http://localhost:3030/docs/#!/categories/findCategoryById"
2. Add an existing ID in the path parameter
3. Click on the "Try it out!" button.

**Expected output**: Response with 404 http status code is displayed as expected.


