#!/usr/bin/env bash
# Arrête l'environnement. Ajouter --purge pour supprimer aussi les données (volumes).
set -euo pipefail
cd "$(dirname "$0")/.."
if [ "${1:-}" = "--purge" ]; then
  docker compose down -v
else
  docker compose down
fi
