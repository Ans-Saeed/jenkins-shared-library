#!/usr/bin/env groovy
import com.example.Docker
def call(String name){
    return new Docker(this).builddockerImage(name)
}