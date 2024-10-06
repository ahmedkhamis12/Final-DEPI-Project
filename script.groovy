// def buildJar() {
//     echo "building Jar file..."
//     sh 'mvn package'
// } 

// def buildImage() {
//     echo "building the docker image..."
//     withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
//     sh 'docker build -t ghanemovic/jenkins-task:jm-.0 .'
//     sh "echo $PASS | docker login -u $USER --password-stdin"
//     sh 'docker push ghanemovic/jenkins-task:jm-3.0'
//     }
// } 

// def deployApp() {
//     steps {
//         echo 'deploying the application...'
//     }
// }

// return this


def buildNodeApp() {
    echo "Building Node.js application..."
    // Navigate to the subdirectory if necessary
    dir('app') {  // Replace with your actual directory
        sh 'npm install'
        sh 'npm run build'
    }
}


def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t o-app .'  // Adjust the Docker image name and tag as needed
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push o-app'  // Push the image to Docker Hub
    }
}

def deployApp() {
    echo "Deploying the application..."
    
}

return this
