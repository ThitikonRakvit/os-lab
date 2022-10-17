#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

int csum, msum;
void *calculateCsum(void *param);

int main(int argc, char *argv[]){
	pthread_t	tid;
	pthread_attr_t	attr;
	pthread_attr_init(&attr);

	pthread_create(&tid, &attr, calculateCsum, argv[1]);

	pthread_join(tid, NULL);

	int i;
	msum = 0;
	for(i = 1; i <= atoi(argv[1]); i++){
		msum += i;
	}

	int output = csum - msum;
	printf("The difference is %d , The child is %d , The parent is %d\n",output, csum, msum);
       	return 0;	
}

void *calculateCsum(void *param){
	int i;
	csum = 0;
	for(i = 1; i <= 2*(atoi(param)); i++){
		csum += i;
	}

	pthread_exit(0);
}