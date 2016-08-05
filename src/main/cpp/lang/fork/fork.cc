#include <stdio.h>
#include <unistd.h>

int main(int argc, char** argv){
  for(int i = 1; i <= 3; i++){
    int status = fork();
    if(status == 0) {
      printf("I'm a child: i: %d, PID: %d\n", i, getpid());
    }
  }
}

