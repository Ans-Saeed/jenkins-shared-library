#!/usr/bin/env groovy

def call(String name){
    echo 'Building the image ... '
        sh "docker build -t $name ."
}