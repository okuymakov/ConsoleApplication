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
                archiveArtifacts allowEmptyArchive: true, artifacts: 'bin/Release/netcoreapp3.1/publish/**/*.*', caseSensitive: false, followSymlinks: false, onlyIfSuccessful: true
            }
        }
    }
}
