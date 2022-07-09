#!/usr/bin/env bash

./gradlew build

rm -rf docs/
(
./gradlew clean javadoc
cp -r build/docs/javadoc/ docs/
)
