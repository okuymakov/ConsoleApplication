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
                zip zipFile: '"CalcApp ${env.BUILD_NUMBER}".zip', archive: false, dir: 'CalculationApp/bin/Release/netcoreapp3.1/publish'
                archiveArtifacts artifacts: 'CalcApp.zip', fingerprint: true, onlyIfSuccessful: true           
            }
        }
        
        stage('deploy') {
            steps {
                path = "C:/Deploy"
                bat 'xcopy /Y /s "CalcApp ${env.BUILD_NUMBER}".zip "${path}" /D'
                unzip zipFile: '"${path}"/"CalcApp ${env.BUILD_NUMBER}".zip', dir: "${path}"                       
            }
        }
    }
}
