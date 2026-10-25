FROM tomcat:9.0-jdk17-temurin

# L'image officielle ne contient pas les applications "manager" par défaut :
# on les copie depuis webapps.dist
RUN cp -r /usr/local/tomcat/webapps.dist/manager /usr/local/tomcat/webapps/manager

# Utilisateur de déploiement + accès distant au manager (pour Jenkins)
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY manager-context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml

EXPOSE 8080
