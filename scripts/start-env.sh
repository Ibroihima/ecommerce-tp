#!/usr/bin/env bash
# Démarre l'environnement Tomcat + Jenkins + SonarQube
set -euo pipefail
cd "$(dirname "$0")/.."

command -v docker >/dev/null 2>&1 || { echo "Docker n'est pas installé."; exit 1; }

if [ "$(uname -s)" = "Linux" ]; then
  current="$(sysctl -n vm.max_map_count 2>/dev/null || echo 0)"
  if [ "$current" -lt 524288 ]; then
    echo "[WARN] vm.max_map_count=$current (< 524288). SonarQube peut échouer."
    echo "       Corrigez avec : sudo sysctl -w vm.max_map_count=524288"
  fi
fi

docker compose up -d --build

echo ""
echo "Services en cours de démarrage (SonarQube peut prendre 1 à 2 minutes) :"
echo "  Tomcat    : http://localhost:8080  (manager : http://localhost:8080/manager/html)"
echo "  Jenkins   : http://localhost:8081"
echo "  SonarQube : http://localhost:9000  (admin / admin à la première connexion)"
echo ""
echo "Mot de passe initial Jenkins :"
echo "  docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword"
