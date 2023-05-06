#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
 def script
    Docker(script){
        this.script=script
    }
    def builddockerImage(String name, String location){
        script.echo 'Building the image ... '
        script.sh "docker build -t $name $location"
    }
    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')])
                {
                    script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
                }
    }
    def dockerPush(String name){
        script.sh "docker push $name"
    }
}
