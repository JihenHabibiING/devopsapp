pipeline {
  agent any

  options {
    skipDefaultCheckout(true)
  }

  environment {
    DOCKER_IMAGE = "jihenhabibi/devopsapp-jihen"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Unit Tests (JUnit/Mockito)') {
      steps {
        sh './mvnw -q clean test'
      }
    }

    stage('SonarQube Analysis') {
      steps {
        withSonarQubeEnv('sonar-local') {
          sh '''
            ./mvnw -q sonar:sonar \
              -Dsonar.host.url=$SONAR_HOST_URL \
              -Dsonar.login=$SONAR_AUTH_TOKEN
          '''
        }
      }
    }

    stage('Package') {
      steps {
        sh './mvnw -q -DskipTests package'
      }
    }

    stage('Deploy Artifact to Nexus') {
      steps {
        sh './mvnw -q -DskipTests deploy'
      }
    }

    stage('Docker Build') {
      steps {
        sh 'docker build -t devopsapp:latest .'
        sh 'docker tag devopsapp:latest $DOCKER_IMAGE:latest'
      }
    }

    stage('Docker Push (DockerHub)') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DH_USER', passwordVariable: 'DH_TOKEN')]) {
          sh '''
            echo "$DH_TOKEN" | docker login -u "$DH_USER" --password-stdin
            docker push $DOCKER_IMAGE:latest
          '''
        }
      }
    }

    stage('Deploy (docker-compose Spring + MySQL)') {
      steps {
        sh 'APP_PORT=8083 docker compose up -d --build'
      }
    }
  }
}
