pipeline {
  agent any

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

    stage('Deploy Artifact to Nexus') {
      steps {
        sh './mvnw -q -DskipTests deploy'
      }
    }
  }
}
