pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'dotnet --version'
                
            }
        }
        stage('tests') {
            steps {
                bat 'RunTests.cmd'
            }
        }
    }
}
