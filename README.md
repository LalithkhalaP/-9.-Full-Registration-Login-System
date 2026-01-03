# -9.-Full-Registration-Login-System
Objective:
To develop a complete user registration and login system using Servlet lifecycle methods and JDBC.
Description:
This project contains register.html and login.html pages.
The servlet uses init() to establish the database connection, service() to handle both registration and login logic, and destroy() to close the connection.
Registration stores username/password into DB, while the login page validates the user.
Requirements:
Frontend:
register.html
login.html
Input fields for username & password
Servlet:
init() → load driver + connect DB
service() →
Insert user in registration
Validate user in login
destroy() → close connection
Use PreparedStatement & ResultSet
connect jar and server then run it it show register page full detail and then login with same detail.
