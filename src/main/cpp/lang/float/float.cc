#include <iostream>

// ref: http://www.jianshu.com/p/fad987f1642d
int main(int argc, char** argv)
{
  float a = 1.23e-8;
  float b = 1 / a;
  float c = 1 / (a + 1 - 1);
  std::cout << "a = " << a << std::endl;
  std::cout << "b = 1/a = " << b << std::endl;
  std::cout << "c = 1/(a+1-1) = " << c << std::endl;
}

