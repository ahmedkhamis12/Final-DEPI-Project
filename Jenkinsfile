def gv

pipeline {
    agent any
    tools {
        nodejs 'Nodejs-22.9.0'  // from the tools configuration
    }
    stages {
        stage("Init") {
            steps {
                script {
                    gv = load "script.groovy"  // Load external Groovy script
                }
            }
        }

        stage('Checkout Code') {
            steps {
                // Checkout code from your repo
                git branch: 'test', url: 'https://github.com/AhMed-GhaNem25/Final-DEPI-Project.git'
            }
        }
        stage("Install Dependencies") {
            steps {
                script {
                    // Installing Node.js dependencies
                    gv.buildNodeApp()  // Custom method to handle npm install and build
                }
            }
        }

        // stage('Run Unit Tests') {
        //     steps {
        //         sh 'npx jest'
        //     }
        // }

    stage('Parallel Tests') {
        parallel {
            stage('Unit Tests') {
                steps {
                    sh 'npx jest'
                }
            }
        }
    }
        
        stage("Build Image and Push to Docker Hub") {
            steps {
                script {
                    gv.buildImage()  // Build and push Docker image
                }
            }
        }
        stage('Run Ansible Playbook') {
            steps {
                // Run the playbook and specify the private key and user
                sh '''
                    ansible-playbook -i 54.204.216.90, \
                    --user ec2-user \
                    --private-key /var/jenkins_home/.ssh/Gh-test.pem \
                    deploy-docker.yaml
                '''
            }
        }
    }
    


    post {
        success {
            script {
                slackSend(channel: '#depi-slack-channel', message: "Build succeeded: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
            }
        }
        failure {
            script {
                slackSend(channel: '#depi-slack-channel', message: "Build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
            }
        }
    }

}