#!/usr/bin/env bash
# Déploiement manuel du WAR sur Tomcat via l'API texte du manager (exercice du TP)
set -euo pipefail
cd "$(dirname "$0")/.."

TOMCAT_URL="${TOMCAT_URL:-http://localhost:8080}"
TOMCAT_USER="${TOMCAT_USER:-deployer}"
TOMCAT_PASS="${TOMCAT_PASS:-deployer123}"
WAR="target/shop.war"

[ -f "$WAR" ] || { echo "WAR introuvable. Lancez d'abord : mvn clean package"; exit 1; }

curl -s -u "$TOMCAT_USER:$TOMCAT_PASS" -T "$WAR" \
  "$TOMCAT_URL/manager/text/deploy?path=/shop&update=true"
echo ""
echo "Application : $TOMCAT_URL/shop"
