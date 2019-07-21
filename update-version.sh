#!/usr/bin/env bash

if [[ $# -ne 1 ]]
then
  echo "$0 <version>"
  exit 1
fi

mvn release:update-versions -DdevelopmentVersion=$1 -P Impl -DautoVersionSubmodules=true