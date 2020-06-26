#!/usr/bin/env bash

if [[ $# -ne 1 ]]
then
  echo "$0 <version>"
  exit 1
fi

git checkout master
git fetch origin
git reset origin/master --hard
mvn versions:set -DnewVersion="$1"
mvn versions:commit
git commit -am "Release $1"
git tag "$1"
git push origin master --tags
git reset origin/master --hard