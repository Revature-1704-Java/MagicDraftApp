node {
    stage('Git Clone') {
        git branch: 'development', url: 'https://github.com/Revature-1704-Java/MagicDraftApp'
        sh 'service mtgdraft stop'
    }
    // stage('Build/Test Frontend') {
    //     sh 'npm install --prefix frontend'
    //     sh 'npm run-script build --prefix frontend'
    // }
    stage('Build/Test Backend') {
        sh 'mvn clean install'
    }
    stage('Deploy') {
        sh 'pwd'
        sh 'service mtgdraft start'
    }
}
