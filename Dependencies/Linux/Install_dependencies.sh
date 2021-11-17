#!/bin/bash

sudo apt autoremove -f

sudo apt install -y libimage-exiftool-perl openjdk-8-jre ffmpeg xvfb libxss1 libappindicator1 libindicator7 python python-tk python-imageio mpv python-pathlib

sudo pip3 install torch opencv-python scikit-image numpy matplotlib argparse pillow ipython

cd Image-ExifTool

perl Makefile.PL

make test

sudo make install

exit 0
