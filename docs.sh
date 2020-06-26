#!/usr/bin/env bash

export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

mvn clean install -P Impl -T 1C -DdependencyLocationsEnabled=false

rm -rf docs/
(
cd API/ || exit
mvn javadoc:javadoc
cp -r target/site/apidocs ../docs
)