pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'echo hello'
                
            }
        }
        stage('tests') {
            steps {
                bat 'RunTests.cmd'
            }
        }
    }
}
