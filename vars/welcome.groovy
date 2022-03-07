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
    bat 'git checkout %env.BRANCH_NAME%'    
    _save(application)
    bat "git add ${env.WORKSPACE}/application.yaml"
    bat "cat ${env.WORKSPACE}/application.yaml"
    bat "echo 'The current build is: ${version}'"
    bat "git remote set-url origin git@github.com:adarshadash/sharedlibrary.git"
    bat "git add ."
     bat "git pull"
    bat "git commit -m 'ignore-commit increment version: ${version}'"
    bat "git push -u origin:$BRANCH_NAME"
}
