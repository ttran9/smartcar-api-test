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
    3) make sure to create a configuration file, inside of the /etc directory. In my case I used "/etc/test_config.json".
        a) The file is simply just a JSONObject with three keys ("CLIENT_ID", "CLIENT_SECRET", and "PORT") and their values.
            - Make sure to fill out the CLIENT_ID and CLIENT_SECRET from your Smartcar credentials from the dashboard.
    4) run the builder_helper.sh script by using the below command.
        - "./build_helper.sh"
    5) Go to localhost:8000/
        - Some Notes below:
            - Due to the permissions that I am requiring for testing purposes a user would not be able to view
            all the relevant information for many of the test vehicles.
                - Some working car brands are: Buick, Cadillac, Cheverolet, Dodge, and Ford.
