#include <stdio.h>

typedef unsigned char* pointer;

void show_bytes(pointer start_address, size_t len) {
  size_t i;
  for (i = 0; i < len; i++) {
    printf("%p\t0x%.2x\n", start_address + i, start_address[i]);
  }
}

int main(int argc, char** argv)
{
  printf("Internet is big endian, but computers are often small endian.\n");
  int a = 0x1020304;
  printf("int a = %d\n", a);
  show_bytes((pointer) &a, sizeof(int));
  return 0;
}

