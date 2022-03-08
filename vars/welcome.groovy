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
    echo "I am inside Update applicationScript"    
    bat 'git checkout main'    
    _save(application)
    bat "git add ${env.WORKSPACE}/application.yaml"
    bat "echo 'The current build is: ${version}'"
    bat "git remote set-url origin https://github.com/adarshadash/sharedlibrary.git"
    bat "dir"
    bat "git config --global user.email 'adi.dash880@gmail.com'"
    bat "git config --global user.name 'adarshadash'"
    bat "git config --global core.preloadindex true"
    bat "git config --global core.preloadindex true" 
    bat "git config --global core.fscache true"
    bat "git add ."
    bat "git checkout %env.BRANCH_NAME%" 
    bat "git status"
    bat "git pull"
    bat "git commit -m 'ignore-commit'"
    bat "git status"
    bat "git push --verbose"
}
