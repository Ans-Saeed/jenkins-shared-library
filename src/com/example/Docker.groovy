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

    def incrementVersion(String location){
        script.sh "cd $location && npm --no-git-tag-version version patch"      
        def packageJson = readJSON file: 'package.json'
        def version = packageJson.version
        def imageName="$version-$script.BUILD_NUMBER"
        return imageName            
}

def commitVersion(){
      withCredentials([
        usernamePassword(credentialsId:'git-hub-access-key', usernameVariable: 'USER', passwordVariable: 'PASS')
    ]){

                script.sh 'echo Minor Version Bump...'
                script.sh 'git status'
                script.sh 'git config user.email jenkins@example.com'
                script.sh 'git config user.name jenkins'
                script.sh "git remote set-url origin https://${script.PASS}@github.com/${script.USER}/online-exam-portal.git"
                script.sh 'git add .'
                script.sh 'git commit -m "[ci skip] : version bump"'
                script.sh 'git push origin HEAD:jenkins-job'

            }        
    }
}
