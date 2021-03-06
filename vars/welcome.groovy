@Grab('org.yaml:snakeyaml:1.17')
    
import org.yaml.snakeyaml.Yaml

def call(name){
   echo "Hey ${name}, How are you?"
}

def anothercall(name){
   echo "Hey ${name}, Another how are you ? "
}

def _load() {

  Yaml parser = new Yaml()
  application = parser.load(("${env.WORKSPACE}/application.yaml" as File).text)
  return application

}

def _save(application) {

  Yaml parser = new Yaml()
  parser.dump(application, new FileWriter("${env.WORKSPACE}/application.yaml"))

}

def getVersion() {

    newtag = _load()
    tag = "${newtag['version']}"
    echo "HEy VErsion id is - ${tag}"
    return tag

}

def getBuild(){
    
    newtag = _load()
    tag = "${newtag['build']}"
    echo "HEy Build id is - ${tag}"
    return tag

}

def incrementbyone(number){
    number=number.toInteger()+1
    echo "updated number is ${number}"
    return number
}


def updateApplication(){
    application = _load()
    application['build']++
        sh "git checkout ${env.BRANCH_NAME}"    
    echo "I am inside Update applicationScript - ${env.BRANCH_NAME}"      
    _save(application)
    /*
    sh "git config --global user.email 'adi.dash880@gmail.com'"
    sh "git config --global user.name 'adarshadash'"
    sh "git remote set-url origin git@github.com:adarshadash/sharedlibrary.git"
    sh "git add ${env.WORKSPACE}/application.yaml"
    sh "echo 'The current build is: ${version}'"
    sh "git status"
    sh "git pull origin ${env.BRANCH_NAME} --allow-unrelated-histories"
    sh "git commit -m 'ignore-commit increment version: ${version}'"
    sh "git push origin HEAD:${env.BRANCH_NAME}"
    
    sh "git remote set-url origin git@github.com:adarshadash/sharedlibrary.git"
     sh "ls -la"
    sh "git config --global user.email 'adi.dash880@gmail.com'"
    sh "git config --global user.name 'adarshadash'"
    sh "git config --global core.preloadindex true"
    sh "git config --global core.preloadindex true" 
    sh "git config --global core.fscache true" 
    sh "git add ."
    sh "git status"
    sh "git commit -m 'ignore-tag'"
    sh "git push -u origin:main" */
}
