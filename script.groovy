def buildJar() {
    echo "building Jar file..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
    sh 'docker build -t ghanemovic/jenkins-task:jm-.0 .'
    sh "echo $PASS | docker login -u $USER --password-stdin"
    sh 'docker push ghanemovic/jenkins-task:jm-3.0'
    }
} 

def deployApp() {
    steps {
        echo 'deploying the application...'
    }
}

return this
