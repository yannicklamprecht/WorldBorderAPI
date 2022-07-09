#!/usr/bin/env bash

color_from_hex() {
  curl -s -L -X GET "http://thecolorapi.com/id?hex=$1" | jq -r '.name.value'
}

get_hex_color_from_commit() {
  git rev-parse --short=6 HEAD
}

get_changes() {
  printf "%q" "$(git log --pretty="- %s" "$1".."$target_branch" | grep --invert-match 'Update README.md')"
}

add_latest_changes_to_changelog() {
  sed -i.bak -E "/^## \[$1\].*/i## [$2] $3\\n\\n$4\n" CHANGELOG.md
  sed -i.bak -E "s/\\$'//" CHANGELOG.md
  rm CHANGELOG.md.bak
}

if [[ $# -ne 1 ]]; then
  echo "$0 <version>"
  exit 1
fi

target_branch="main"

git checkout "$target_branch"
git fetch origin
git reset origin/"$target_branch" --hard

old_tag=$(git describe --abbrev=0 --tags "$(git rev-list --tags --skip=1 --max-count=1)")

changes="$(get_changes "$old_tag")"
release_name=$(color_from_hex "$(get_hex_color_from_commit)")

add_latest_changes_to_changelog "$old_tag" "$1" "$release_name" "$changes"

sed -i.bak -E "s/(<version>).*(<\/version>)/\1$1\2/" README.md
rm README.md.bak

echo "$1" > version.txt
git commit -am "Release $1"
git tag "$1"
git push origin "$target_branch" --tags
git reset origin/"$target_branch" --hard
