#include <omp.h>
#include <stdio.h>
#include <stdlib.h>
#define CHUNKSIZE 100
#define N 1000

int main(int argc, char *argv[]){
  int i, chunk, tid, nthreads;
  double time_start, time_end, execution_time;
  float a[N], b[N], c[N];

  for(i = 0; i < N; i++)
    a[i] = b[i] = i*1.0;
  chunk = CHUNKSIZE;

  time_start = omp_get_wtime();

//Set le nombre de threads par variable d’environemment : export OMP_NUM_THREADS=4

//Set le nombre de threads par une fonction
//omp_set_num_threads(4);

//Set le nombre de threads par une directive : num_threads(4)
#pragma omp parallel shared(a,b,c,chunk) private(i, tid, nthreads) //num_threads(4)
  {
    nthreads = omp_get_num_threads();
#pragma omp for schedule(dynamic, chunk) nowait
    for(int i = 0; i < N; i++){
      c[i] = a[i] + b[i];

      tid = omp_get_thread_num();
      printf("Nombre de thread = %d\nc[i] = %f\ntID = %d\n\n", nthreads, c[i], tid);
    }
  }
  time_end = omp_get_wtime();
  execution_time = time_end - time_start;
  printf("Temps d’execution : %fs\n", execution_time);
}
