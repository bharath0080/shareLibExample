def call(body) {

        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        node {
            // Clean workspace before doing anything
            deleteDir()

            try {
                stage ('Clone') {
                    //checkout scm
                     bat "echo scm checkout"
                    
                }
                stage ('Build') {
                    bat "echo 'building ${config.projectName} ...'"
                }
                stage ('Tests') {
					bat "echo testing"
                }
                stage ('Deploy') {
                    bat "echo 'deploying to server ${config.serverDomain}...'"
                }
            } catch (err) {
                currentBuild.result = 'FAILED'
                throw err
            }
        }
    }
