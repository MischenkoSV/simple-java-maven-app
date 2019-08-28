pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-u 0:0 -v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
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
                sh 'mkdir -p /tmp/download \
                    && curl -L https://download.docker.com/linux/static/stable/x86_64/docker-17.06.2-ce.tgz | tar -xz -C /tmp/download \
                    && mv /tmp/download/docker/docker /usr/local/bin/ \
                    && rm -rf /tmp/download'
                sh 'docker build -t my-app:1.0-SNAPSHOT .;'
            }
        }
    }
}
