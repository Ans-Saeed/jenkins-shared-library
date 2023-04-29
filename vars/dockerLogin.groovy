def call (){
    withCredentials([usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')])
            {
                sh 'echo $PASS | docker login -u $USER --password-stdin'
            }
}