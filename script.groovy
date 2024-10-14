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

return this


def buildNodeApp() {
    echo "Building Node.js application..."
    // Navigate to the subdirectory if necessary
    dir('app') {  // Replace with your actual directory
        sh 'npm install'
        
    }
}


def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'Dockeraut', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ghanemovic/depi-final-project:latest ."
        sh "echo $PASS| docker login -u $USER --password-stdin"
        sh "docker push ghanemovic/depi-final-project:latest"
    }
    }


def deployApp() {
    echo "Deploying the application to EKS..."
    
    // Use AWS credentials
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-cred']]) {
        // Set kubeconfig environment variable
        withCredentials([file(credentialsId: 'eks-kubeconfig', variable: 'config')]) {
            //Set the KUBECONFIG environment variable
            sh 'export KUBECONFIG=$config'

            // Change to the directory containing your deployment and service files
            // dir('/home/nour/depi/Final-DEPI-Project/') {
            //     // Deploy the application using kubectl
                sh 'kubectl apply -f deployment.yaml'
                sh 'kubectl apply -f service.yaml'
            // }
        }
    }
}

return this
