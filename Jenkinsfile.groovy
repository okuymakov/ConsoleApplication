pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'Build.cmd'
                
            }
        }
        stage('tests') {
            steps {
                bat 'RunTests.cmd'
            }
        }
    }
}
