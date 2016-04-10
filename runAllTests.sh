#/bin/bash

cwd=$('pwd')
allTests=doc/*

for fileTest in $allTests
do
	filename="$cwd/$fileTest"
	java -jar target/0hn0Resolver.jar $1 $2 $filename
done
