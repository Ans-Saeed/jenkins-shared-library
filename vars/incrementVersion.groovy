import com.example.Docker

def call(String location){
    return new Docker(this).incrementVersion(location)
}
