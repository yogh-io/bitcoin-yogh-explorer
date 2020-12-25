#!/usr/bin/env bash

SOURCE_DIR='..'

# Exit on error
set -e

# Change current directory to directory of script so it can be called from everywhere
SCRIPT_PATH=$(readlink -f "${0}")
SCRIPT_DIR=$(dirname "${SCRIPT_PATH}")
cd "${SCRIPT_DIR}"

# bitcoin-explorer-server
cp -auv "${SOURCE_DIR}"/explorer-score-server/target/bitcoin-explorer-server-*.war \
        explorer-server/uitkoop.war
