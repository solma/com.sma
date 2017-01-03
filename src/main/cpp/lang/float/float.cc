#include <iostream>
#include <cassert>

int main(int argc, char** argv) {
  float a = 1.23e-8;
  float b = 1 / a;
  float c = 1 / (a + 1 - 1);
  float d = 1 / (a - 1 + 1);
  // The below assertion fails since .07 cannot be precisely represented in binary
  assert(.07*100 == 7);
  std::cout << "a = " << a << std::endl;
  std::cout << "b = 1/a = " << b << std::endl;
  std::cout << "c = 1/(a+1-1) = " << c << std::endl;
  std::cout << "d = 1/(a-1+1) = " << d << std::endl;
}

