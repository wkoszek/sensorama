#!/bin/sh

TARGET=connectedCheck			# Travis is using this for emulator
if [ "x$1" != "x" ]; then
	TARGET=$1			# Let us use whatever
	if [ "$1" = "-r" ]; then	# Release shortcut
		TARGET=assembleRelease
	fi
fi

if [ "x${PARSE_CLIENT_ID}" = "x" ]; then
	. ${HOME}/.parse_sensorama_cred
fi

F=app/android/Sensorama/app/src/main/java/com/barvoy/sensorama/SRAPIPerms.java
cat $F | \
	sed "s/PARSE_API_ID/${PARSE_API_ID}/" | \
	sed "s/PARSE_CLIENT_ID/${PARSE_CLIENT_ID}/" > _.f
mv _.f $F


(
	cd app/android/Sensorama
	./gradlew build -x lint $TARGET | cat
)
