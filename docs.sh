#!/usr/bin/env bash

./gradlew api:build

rm -rf docs/
(
./gradlew api:javadoc
cp -r api/build/docs/javadoc/ docs/
)
