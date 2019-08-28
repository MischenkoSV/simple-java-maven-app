pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '--user=root -v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'env'
                sh 'whoami'
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
