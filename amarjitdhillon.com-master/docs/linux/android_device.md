# Attach android device to android studio in Ubuntu

Make sure you have enabled `developer options`, if not then follow these steps

1. Go to `Settings` --> `About phone` --> `Build number`. On a Samsung Galaxy device, go to `Settings` --> `About device` --> `Build number`.
1. Tap `build number` 7 times.
1. Go back to Settings, where you’ll find a developer options entry in the menu.
1. In terminal type 
	```adb devices
	```

!!! error
	If you see something like `?????????? no permissions`, then create a new file using nano as
	```bash
	sudo nano /etc/udev/rules.d/51-android.rules
	```

	Then paste the following code into that file

	```bash
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0bb4", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0e79", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0502", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0b05", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="413c", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0489", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="091e", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="18d1", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0bb4", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="12d1", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="24e3", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="2116", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0482", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="17ef", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="1004", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="22b8", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0409", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="2080", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0955", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="2257", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="10a9", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="1d4d", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0471", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="04da", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="05c6", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="1f53", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="04e8", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="04dd", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0fce", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="0930", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="19d2", MODE="0666"
	SUBSYSTEM=="usb", ATTRS{idVendor}=="1bbb", MODE="0666"
	```

	Then enter following commands

	```bash
	sudo chmod 644 /etc/udev/rules.d/51-android.rules
	sudo chown root. /etc/udev/rules.d/51-android.rules
	sudo service udev restart
	sudo killall adb
	```

	Remove he USB cable and connect it again. Then enter

	```bash
	adb devices
	```
