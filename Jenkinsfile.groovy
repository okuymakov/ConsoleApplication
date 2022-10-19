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
                zip zipFile: '''"CalcApp ${env.BUILD_NUMBER}".zip''', archive: false, dir: 'CalculationApp/bin/Release/netcoreapp3.1/publish'
                archiveArtifacts artifacts: 'CalcApp.zip', fingerprint: true, onlyIfSuccessful: true           
            }
        }
        
        stage('deploy') {
            environment {
                DEPLOY_PATH = "C:/Deploy"
            }
            steps {
                bat 'xcopy /Y /s "CalcApp ${env.BUILD_NUMBER}".zip "${DEPLOY_PATH}" /D'
                unzip zipFile: '"${DEPLOY_PATH}"/"CalcApp ${env.BUILD_NUMBER}".zip', dir: "${DEPLOY_PATH}"                       
            }
        }
    }
    
    post {
        always {
            mail to: "o.kuymakov@gmail.com", 
                subject: "${env.JOB_NAME} build ${env.BUILD_NUMBER} - ${currentBuild.currentResult}!",
            body: "${env.JOB_NAME} build ${env.BUILD_NUMBER} - ${currentBuild.currentResult}!\nCheck console output at ${env.BUILD_URL} to view the results"
        }
    }
}
