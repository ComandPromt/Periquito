#!/bin/bash

sudo apt autoremove -f

sudo apt install -y openjdk-8-jre ffmpeg xvfb libxss1 libappindicator1 libindicator7 python python-tk python-imageio gifsicle mpv python-pathlib

sudo pip3 install torch scikit-image numpy matplotlib argparse pillow

exit 0
