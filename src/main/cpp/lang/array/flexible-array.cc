#include<iostream>
using namespace std;

struct Data
{
  int i;
  int a[0]; // declare of size 0 array to make it a flexible array
};

int main()
{
  Data *p = (Data*)malloc(sizeof(Data) + 100 * sizeof(int));
  cout << "Size of pointer P: " << sizeof(p) << endl;

  int n = 12;
  void *p1 = calloc(sizeof(Data) * n + sizeof(unsigned long),1);
  *((unsigned long*)p1) = n;
  cout << "size: " << sizeof(unsigned long) << endl;

  // 1. Flexible arrays have adjacent addresses to its sibling members
  cout << "addresses of member i and flexible array a[0] are adjacent:\n" << &(p->i) << " " << &(p->a[0]) << endl;
  // 2. Flexible arrays do not occupy spaces
  cout << "size of struct Data: " << sizeof(Data) << " = size of member i (int): " << sizeof(int) <<endl;
  return 0;
}
