#!/bin/bash


for dir in `find . -type d`
do
    if [ -f ${dir}/gradlew ]; then
        pushd .
        cd ${dir}
        ./gradlew build
        popd
    fi
done

