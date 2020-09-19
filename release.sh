#!/usr/bin/env bash

color_from_hex(){
  curl -s -L -X GET "http://thecolorapi.com/id?hex=$1" | jq -r '.name.value'
}

get_hex_color_from_commit(){
  git rev-parse --short=6 HEAD
}

if [[ $# -ne 1 ]]
then
  echo "$0 <version>"
  exit 1
fi

git checkout master
git fetch origin
git reset origin/master --hard

old_tag=$(git describe --abbrev=0 --tags "$(git rev-list --tags --skip=1 --max-count=1)")

changes=$(git log --pretty="- %s" "${old_tag}"..master | grep --invert-match 'Update README.md')

escaped_changes="$(printf "%q" "$changes")"

release_name=$(color_from_hex "$(get_hex_color_from_commit)")
sed -i.bak -E "/^## \[${old_tag}\].*/i## [$1] $release_name\\n\\n${escaped_changes}\n" CHANGELOG.md
sed -i.bak -E "s/\\$'//" CHANGELOG.md
rm CHANGELOG.md.bak

mvn versions:set -DnewVersion="$1"
mvn versions:commit
sed -i.bak -E "s/(<version>).*(<\/version>)/\1$1\2/" README.md
rm README.md.bak
git commit -am "Release $1"
git tag "$1"
git push origin master --tags
git reset origin/master --hard
