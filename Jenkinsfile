node {
    stage('Git Clone') {
        slackSend 'started ${env.JOB_NAME} ${env.BUILD_NUMBER} ${(<env.BUILD_URL|Open>)}'
        git 'https://github.com/Revature-1704-Java/MagicDraftApp.git'
    }
    stage('Build/Test Frontend') {
        sh 'npm install --prefix Angular'
        sh 'npm run-script build --prefix Angular'

    }
    stage('Build/Test Backend') {
        sh 'mvn clean install'
    }
    stage('Deploy') {
      sh 'mvn tomcat7:redeploy'
    }
}
