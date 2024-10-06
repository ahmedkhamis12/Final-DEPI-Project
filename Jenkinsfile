def gv

pipeline {
    agent any
    tools {
        maven 'Maven-3.6' //from the tools configuration
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("Build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("build image and push to docker hub") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo 'deploying the application...'
                }
            }
        }
    }
}
