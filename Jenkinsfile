pipeline {
    agent {
        docker {
            image 'docker.aws.wiley.com/ctpm/builder-node:1.0'
            args '-u 0:0 -v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
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
                sh 'docker build -t my-app:1.0-SNAPSHOT .;'
            }
        }
    }
}
