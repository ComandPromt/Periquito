#!/bin/bash

sudo apt install openjdk-8-jre

sudo apt install ffmpeg

sudo apt install xvfb

sudo apt install unzip

wget -N http://chromedriver.storage.googleapis.com/2.26/chromedriver_linux64.zip

unzip chromedriver_linux64.zip

chmod +x chromedriver

sudo mv -f chromedriver /usr/local/share/chromedriver

sudo ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver

sudo ln -s /usr/local/share/chromedriver /usr/bin/chromedriver

exit 0
