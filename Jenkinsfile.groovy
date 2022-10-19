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
        
        stage('backup') {
            steps {
                bat 'Publish.cmd'
                zip zipFile: 'CalcApp.zip', archive: false, dir: 'CalculationApp/bin/Release/netcoreapp3.1/publish'
                archiveArtifacts artifacts: 'CalcApp.zip', fingerprint: true           
            }
        }
    }
}
