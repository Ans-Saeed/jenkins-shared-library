def call(String name){
    sh "docker push anssaeed/my-repo:${name}"
}