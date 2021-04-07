# Smartcar API Testing


# Overview
- I am creating this web application as a way to familiarize myself with the Smartcar API.
- After briefly going through some documentation from the API I will just be using the test mode that the API offers as
I was not sure of a way to use the actual API as I do not believe I have a vehicle that Smartcar Connect supports.


# How to use it
- Since this is not ready for production I will just have instructions for how to run this application in a local
Linux environment.
- For Ubuntu 18.04 LTS:
    1) clone this repository.
    2) make sure to have a tomcat server running.
        a) [This is a guide](https://www.digitalocean.com/community/tutorials/install-tomcat-9-ubuntu-1804) I followed 
        to set up my tomcat server on my local machine.
        b) [This is another guide](https://www.baeldung.com/tomcat-root-application) I followed to deploy it to a local 
        Tomcat 9 server instead of just using an embedded tomcat server that one can run through some IDE (IntelliJ) 
        for example.
    3) Make sure to have a running MySQL database unless you choose to modify the class, DBConfig. 
        - In my case I am using Docker and a MySQL image.
        - You will need to create a configuration file, "sc_db_credentials.json," inside of the "/etc" directory. 
            - This file is a JSONObject with four keys below.
                - "SC_MYSQL_DB_URL":"jdbc:mysql://172.17.0.1:3306/sc_db?useSSL=false",
                - "SC_MYSQL_DB_USER_NAME":"root",
                - "SC_MYSQL_DB_PASSWORD":"password1",
                - "SC_MYSQL_DB_DRIVER_CLASS_NAME":"com.mysql.cj.jdbc.Driver"
        - If you are not familiar with how to set up a MySQL image for docker follow the below steps.
        - Steps
            - 1. Pull down the MySQL image.
                - sudo docker pull mysql
            - 2. create the appropriate MySQL container to run the database on.
                - sudo docker run --name sc_db -e MYSQL_ROOT_PASSWORD=password1 -e MYSQL_DATABASE=sc_db -p 3306:3306 -d mysql
                - the values for MYSQL_ROOT_PASSWORD and MYSQL_DATABASE are based off of the sc_db_credentials.json content
                shown above.
                - note: it is not a best practice to connect to the database as root but as this is just for 
                development/testing/learning purposes this is acceptable.
            - 3. mysql command line connect
                - mysql -h 172.17.0.1 -u root -p
            - 4. install command line tool (if you can't connect with the command above listed in #3.) 
                - sudo apt install mysql-client-core-5.7
    4) make sure to create a configuration file, "sc_api_config.json," inside of the "/etc" directory. 
        a) The file is simply just a JSONObject with three keys ("CLIENT_ID", "CLIENT_SECRET", and "PORT") and their values.
            - Make sure to fill out the CLIENT_ID and CLIENT_SECRET from your Smartcar credentials from the dashboard.
    5) run the builder_helper.sh script by using the below command.
        - "./build_helper.sh"
    6) Go to localhost:8000/
        - Some Notes below:
            - Due to the permissions that I am requiring for testing purposes a user would not be able to view
            all the relevant information for many of the test vehicles.
                - Some working car brands are: Buick, Cadillac, Chevrolet, Dodge, and Ford.
                
                
# TODO:
- Comment code as necessary.