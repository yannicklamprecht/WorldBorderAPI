#!/usr/bin/env bash

./gradlew build

rm -rf docs/
(
./gradlew javadoc
cp -r build/docs/javadoc/ docs/
)
