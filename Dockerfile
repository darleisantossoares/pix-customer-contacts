FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/pix-customer-contacts-0.0.1-SNAPSHOT-standalone.jar /pix-customer-contacts/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/pix-customer-contacts/app.jar"]
