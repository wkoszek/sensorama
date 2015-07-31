# Installing Sensorama

Sensorama was developed using Android Studio and its emulator. Sensorama
requires an external storage to store the samples. Android Studio 1.1.0
came with SD card disabled, thus storage detection was returning lack of
storage. Make sure your emulator comes with SD card enabled. To do this,
locate the file:

	config.ini

And make sure that hw.sdCard card setting is turned on:

	hw.sdCard=yes
