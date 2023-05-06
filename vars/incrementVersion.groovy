import com.example.Docker

def call(String location){
    def docker = new Docker(this)
    return new Docker(this).docker.incrementVersion(location)
}
