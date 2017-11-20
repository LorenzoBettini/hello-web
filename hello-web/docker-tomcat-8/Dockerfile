FROM tomcat:8.5

# deploy our war in the Tomcat's webapps directory
COPY /target/*.war /usr/local/tomcat/webapps/

# we inherit the default CMD from tomcat image, i.e., just run Tomcat