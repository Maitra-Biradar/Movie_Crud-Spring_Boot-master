pipeline {
    agent any
    tools {
      maven 'maven'
    }
  
    environment {
        IMAGE_NAME = "maitra2517/movie-springboot-project"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/Maitra-Biradar/Movie_Crud-Spring_Boot-master.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $IMAGE_NAME:$IMAGE_TAG ."
            }
        }

        stage('Tag Image') {
            steps {
                sh "docker tag $IMAGE_NAME:$IMAGE_TAG $IMAGE_NAME:latest"
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                  credentialsId: 'dockerhub-creds',
                  usernameVariable: 'DOCKER_USER',
                  passwordVariable: 'DOCKER_PASS'
                )]) {
                  sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                  '''
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh "docker push $IMAGE_NAME:$IMAGE_TAG"
                sh "docker push $IMAGE_NAME:latest"
            }
        }

        stage('Remove Local Image') {
            steps {
                sh "docker rmi $IMAGE_NAME:$IMAGE_TAG || true"
                sh "docker rmi $IMAGE_NAME:latest || true"
            }
        }

        stage('Trigger Ansible') {
            steps {
                sh 'ansible-playbook deploy.yml'
            }
        }
    }
}
