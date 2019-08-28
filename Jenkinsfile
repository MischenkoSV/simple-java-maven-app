pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock -v /usr/local/bin/docker:/bin/docker'
        }
    }
    stages {
        stage('Build') {
            steps {
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

        stage('Build Docker image') {
            steps {
                sh 'docker build -t my-app:1.0-SNAPSHOT .'
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
    }
}
