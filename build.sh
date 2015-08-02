#!/bin/sh

TARGET=connectedCheck			# Travis is using this for emulator
if [ "x$1" != "x" ]; then
	TARGET=$1			# Let us use whatever
	if [ "$1" = "-r" ]; then	# Release shortcut
		TARGET=assembleRelease
	fi
fi

(
	cd app/android/Sensorama
	./gradlew build -x lint $TARGET | cat
)
