#include <omp.h>
#include <stdio.h>
#include <stdlib.h>
#define CHUNKSIZE 25
#define N 100000

int main(int argc, char *argv[]){
  int i, chunk, tid, nthreads;
  double time_start_thread, time_start,time_end_total, time_end, execution_time;
  float a[N], b[N], c[N];

  for(i = 0; i < N; i++)
    a[i] = b[i] = i*1.0;
  chunk = CHUNKSIZE;

  time_start = omp_get_wtime();

//Set le nombre de threads par variable d’environemment : export OMP_NUM_THREADS=4

//Set le nombre de threads par une fonction
//omp_set_num_threads(4);

//Set le nombre de threads par une directive : num_threads(4)
#pragma omp parallel shared(a,b,c,chunk) private(i, tid, nthreads,time_start_thread) //num_threads(4)
  {
    nthreads = omp_get_num_threads();
    tid = omp_get_thread_num();
    if(tid == 0)
      printf("Nombre de thread = %d\n", nthreads);
    time_start_thread = omp_get_wtime();
#pragma omp for schedule(static, chunk) nowait
    for(int i = 0; i < N; i++){
      c[i] = a[i] + b[i];
    }
    tid = omp_get_thread_num();
    printf("Temps d’execution thread %d : %fs\n",tid, omp_get_wtime()-time_start_thread);
  }
  printf("Temps d’execution total : %fs\n", omp_get_wtime() - time_start);
}
