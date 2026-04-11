FROM tomcat:9.0-jdk8-temurin

# Deploy prebuilt NetBeans artifact into Tomcat as ROOT app for simpler URL smoke tests.
COPY dist/GarageCarASM2.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
