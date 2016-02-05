#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#if defined (Win32)
#  include <windows.h>
#  define psleep(sec) Sleep ((sec) * 1000)
#elif defined (Linux)
#  include <unistd.h>
#  define psleep(sec) sleep ((sec))
#endif

typedef struct thread_info thread_info;
struct thread_info{
  int demande;
  int reservation;
  char* message;
  const int NBP;
};

int initial = PTHREAD_MUTEX_INITIALIZER;

void *afficherID(void* var){
  pthread_mutex_lock (&initial);

  thread_info* b = (thread_info*) var;

  b-> demande = 1;
  printf("Demande : %d\n",b->demande);
  b->reservation = 0;
  printf("Reservation : %d\n",b->reservation);
  b->message = "Mon message perso";
  printf("Message : %s\n",b->message);

  printf("ID : %p\n\n", pthread_self());
  fflush(stdout);
  pthread_mutex_unlock (&initial);
  pthread_exit(NULL);
}



int main(int argc, char *argv[]){
  pthread_t thread1;
  int ret = 0;
  int t_num[5];

  thread_info *thread_info1;
  thread_info1 = malloc(sizeof(struct thread_info));

  for(int i = 0; i < 5; i++){
    t_num[i] = i+1;
    ret = pthread_create(&thread1, NULL, afficherID, thread_info1);
    //printf("Returned value : %d\n", ret);
  }
  pthread_join(thread1, NULL);
}
