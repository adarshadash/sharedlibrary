@Library('librarytest') _

pipeline{
    agent any
    stages{
      stage('Clean WS and checkout SCM') {
         steps {
           deleteDir()
           echo 'Pulling...' + env.BRANCH_NAME
           checkout scm
            }
        }
        stage('Demo'){
            steps{
               welcome("Adarsha dash") 
               script{
               calculator.add(4,5)
               calculator.multiply(5,6)
               welcome.anothercall("OtherCall-Name")
               welcome.incrementbyone(welcome.getBuild())
               welcome.updateApplication()    
               }
            }
        }
    }
    
    /*
   post {
    always {
      cleanWs()
    }
  }*/
}
