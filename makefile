all: usage

LIB=app/android/Sensorama/app/libs

check:
	cd app/android/Sensorama
	./gradlew build -x lint $@ | cat

rel:
	cd app/android/Sensorama
	./gradlew build -x lint $@ | cat
fetch:
	mkdir -p $(LIB) _tmp
	wget -O _tmp/parse.zip https://www.parse.com/downloads/android/Parse/latest
	(cd _tmp && unzip -o parse.zip)
	(cd _tmp && cp bolts-android*.jar Parse-*.jar ParseCrash*.jar ../$(LIB)/)
