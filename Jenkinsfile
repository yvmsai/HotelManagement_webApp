pipeline {
 agent any
 tools {
   // Use the tool names you configured in Manage Jenkins → Tools
   jdk 'jdk17'
   maven 'maven-3.9'
 }
 options {
   timestamps()
   skipDefaultCheckout(true)   // we'll checkout explicitly
   ansiColor('xterm')
 }
 triggers {
   // Use ONE of these:
   // pollSCM('H/5 * * * *') // Poll every 5 minutes
   // OR if you set a GitHub webhook, use:
   // githubPush()
 }
 stages {
   stage('Checkout') {
     steps {
       checkout([
         $class: 'GitSCM',
         branches: [[name: '*/main']],        // adjust to your default branch
         userRemoteConfigs: [[
           url: 'https://github.com/<org-or-user>/<repo>.git',
           // if private: credentialsId: 'github-creds-id'
         ]]
       ])
     }
   }
   stage('Build & Unit Tests') {
     steps {
       sh 'mvn -B -DskipTests=false clean verify'
     }
   }
   stage('Package (Jar/WAR)') {
     steps {
       sh 'mvn -B package -DskipTests'
     }
   }
 }
 post {
   always {
     // Publish JUnit test results so they appear on the job + stage view
     junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
     // Archive build artifacts (adjust pattern to your packaging)
     archiveArtifacts artifacts: 'target/*.jar, target/*.war', fingerprint: true
   }
   success {
     echo 'Build succeeded!'
   }
   unstable {
     echo 'Build unstable (e.g., failing tests).'
   }
   failure {
     echo 'Build failed.'
   }
 }
}
