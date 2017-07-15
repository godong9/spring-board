#!/usr/bin/env bash

cd client

npm run build

tar -zcvf dist.tar dist/

scp dist.tar godong@godong9.com:/home/godong


cd ../board

./gradlew build -x test

scp build/libs/board-0.0.1-SNAPSHOT.jar godong@godong9.com:/home/godong/board