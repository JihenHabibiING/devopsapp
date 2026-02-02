pipeline {
  agent any

  options {
    skipDefaultCheckout(true)
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
      }
    }

    stage('Deploy (docker-compose Spring + MySQL)') {
      steps {
        sh 'docker compose up -d --build'
      }
    }
  }
}
