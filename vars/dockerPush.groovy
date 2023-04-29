def call(String name){
    sh "docker push $name"
}