# ShopEasy - TP Tomcat / Jenkins / SonarQube

Application e-commerce de démonstration (Java 17, Servlets/JSP, Maven, JUnit 5)
servant de support à un TP d'intégration et de déploiement continus.

## Démarrage rapide

```bash
# 1. (optionnel) compiler et tester en local
mvn clean verify

# 2. démarrer Tomcat + Jenkins + SonarQube
./scripts/start-env.sh

# 3. suivre les étapes du PDF du TP pour configurer Jenkins et SonarQube
```

| Service   | URL                          | Identifiants              |
|-----------|------------------------------|---------------------------|
| Application | http://localhost:8080/shop | -                         |
| Tomcat manager | http://localhost:8080/manager/html | deployer / deployer123 |
| Jenkins   | http://localhost:8081        | créé à la 1re connexion   |
| SonarQube | http://localhost:9000        | admin / admin (à changer) |

## Pages de l'application

| URL              | Description                        |
|------------------|------------------------------------|
| `/shop/products` | Catalogue, recherche, filtre       |
| `/shop/cart`     | Panier                             |
| `/shop/checkout` | Validation de la commande          |
| `/shop/health`   | État de l'application (JSON)       |
| `/shop/api/products` | Catalogue au format JSON       |

## Arborescence

```
├── pom.xml                  Build Maven (WAR, JaCoCo, plugin Sonar)
├── Jenkinsfile              Pipeline CI/CD
├── docker-compose.yml       Tomcat + Jenkins + SonarQube + PostgreSQL
├── docker/                  Dockerfiles Jenkins et Tomcat
├── scripts/                 start-env, stop-env, deploy-manual
└── src/                     Code source, tests et pages JSP
```

> Les mots de passe fournis sont réservés au TP. Ne les utilisez jamais en production.
