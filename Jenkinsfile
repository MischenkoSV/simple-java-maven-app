pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2 -e DOCKER_HOST=tcp://192.168.108.17:2376'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'env'
                sh 'ping -c 3 192.168.108.17'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Integration Tests') {
            steps {
                sh 'mvn -DskipTests verify'
            }
            post {
                always {
                    junit 'target/failsafe-reports/*.xml'
                }
            }
        }

        stage('Build Docker image') {
            steps {
                sh 'apk add docker-cli'
                sh 'docker build -t my-app:1.0-SNAPSHOT .;'
            }
        }
    }
}
