sudo systemctl stop tomcat
sudo rm -rf /opt/tomcat/webapps/smartcartest /opt/tomcat/webapps/smartcartest.war /opt/tomcat/webapps/ROOT
mvn clean install package
sudo mv target/smartcartest-0.0.1-SNAPSHOT.war target/smartcartest.war
sudo cp target/smartcartest.war /opt/tomcat/webapps/
sudo systemctl start tomcat
