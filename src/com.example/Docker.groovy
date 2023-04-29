#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
 def script
    Docker(script){
        this.script=script
    }
    def builddockerimage(String image){
        script.echo 'Building the image ... '
        script.sh "docker build -t $script.name ."
    }
    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')])
                {
                    script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
                }
    }
    def dockerPush(String image){
        script.sh "docker push $script.name"
    }
}