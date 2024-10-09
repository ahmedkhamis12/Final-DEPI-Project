// script.groovy

def buildNodeApp() {
    echo "Building Node.js application..."
    dir('app') {
        sh 'npm install'
    }
}

def runTests() {
    echo "Running tests..."
    dir('app') {
        sh 'npm test'  // This will generate the JUnit-compatible XML report
    }
}

def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'Dockeraut', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ghanemovic/depi-final-project:latest ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push ghanemovic/depi-final-project:latest"
    }
}

def deployApp() {
    echo "Deploying the application..."
}

return this
