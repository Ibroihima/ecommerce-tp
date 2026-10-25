pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
        timeout(time: 20, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    // Vérifie le dépôt Git toutes les ~2 minutes (pratique pour un TP sans webhook)
    triggers {
        pollSCM('H/2 * * * *')
    }

    environment {
        TOMCAT_URL  = 'http://tomcat:8080'   // nom du service Docker, pas "localhost" !
        APP_CONTEXT = '/shop'
        WAR_FILE    = 'target/shop.war'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                sh 'git log -1 --oneline'
            }
        }

        stage('Build & Tests') {
            steps {
                sh 'mvn -B clean verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    jacoco execPattern: 'target/jacoco.exec',
                           classPattern: 'target/classes',
                           sourcePattern: 'src/main/java'
                }
            }
        }

        stage('Analyse SonarQube') {
            steps {
                // "SonarQube" = nom du serveur configuré dans Manage Jenkins > System
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn -B sonar:sonar \
                            -Dsonar.host.url="$SONAR_HOST_URL" \
                            -Dsonar.token="$SONAR_AUTH_TOKEN"
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Nécessite le webhook SonarQube -> Jenkins (voir le PDF du TP)
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Archivage') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Déploiement Tomcat') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'tomcat-credentials',
                                                  usernameVariable: 'TOMCAT_USER',
                                                  passwordVariable: 'TOMCAT_PASS')]) {
                    sh '''
                        RESPONSE=$(curl -s -u "$TOMCAT_USER:$TOMCAT_PASS" \
                            -T "$WAR_FILE" \
                            "$TOMCAT_URL/manager/text/deploy?path=$APP_CONTEXT&update=true")
                        echo "$RESPONSE"
                        echo "$RESPONSE" | grep -q "^OK"
                    '''
                }
            }
        }

        stage('Smoke test') {
            steps {
                sh '''
                    for i in 1 2 3 4 5 6 7 8 9 10; do
                        if curl -sf "$TOMCAT_URL$APP_CONTEXT/health" | grep -q UP; then
                            echo "Application disponible."
                            exit 0
                        fi
                        echo "Tentative $i : application pas encore prête..."
                        sleep 3
                    done
                    echo "Application indisponible après déploiement."
                    exit 1
                '''
            }
        }
    }

    post {
        success { echo 'Pipeline réussi : http://localhost:8080/shop' }
        failure { echo 'Pipeline en échec : consultez les logs de l\'étape en erreur.' }
    }
}
