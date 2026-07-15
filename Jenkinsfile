pipeline {
    agent any
    options {
        timestamps(); disableConcurrentBuilds()
    }
    environment {
    TESTCONTAINERS_RYUK_DISABLED = 'true'
}
    stages {
        stage('Checkout'){
            steps {checkout scm}
        }
        // stage('Backend build & test'){
        //     steps{
        //         dir('apps/transaction-service'){
        //             sh './gradlew --no-daemon clean check build'
        //         }
        //     }
            
        // }
        stage('Frontend build'){
            steps{
                dir('apps/web'){
                    sh 'npm ci && npm run build'
                }
            }
        }
        stage('Docker build'){
            when {branch 'main'}
            steps{
                dir('apps/transaction-service'){
                    sh 'docker build -t spendwise/transaction-service:${GIT_COMMIT} .'
                }
            }
        }
    }
    post {
        failure {
            echo "pipeline failed - check the failing stage logs."
        }
    }
}