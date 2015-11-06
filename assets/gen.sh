#!/bin/sh
for RES in 512x512 1024x500 180x120 320x180 1280x768 1280x800; do
	convert -size $RES xc:none -fill white icon-${RES}.png
done

