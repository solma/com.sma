#include <iostream>
#include <vector>
#include <assert.h>
#include "dummy_class.h"

C f() {
  return C();
}

C ff() {
  return f();
}

C return_multiple(int x) {
  C c1 = C();
  C c2 = C();
  return x > 0 ? c1 : c2;
}

void e() {
  C c;
  // copying the named object c into the exception object.
  // It is unclear whether this copy may be elided.
  throw c;
}

void yes_case1() {
  std::cout << "Simplest example for RVO!\n";
  C c1 = f();

  std::cout << "RVO works when the called function returns a temporary object!\n";
  C c2 = ff();

  std::cout << "RVO works when the calling function immediately uses the returned value (which is stored in a temporary object)!\n";
  std::cout << f();
}


void no_case1() {
  std::cout << "??The compiler can’t do RVO if the calling function reuses an existing variable to store the return value??\n";
  C obj = f();
  obj = f();
}

void no_case2() {
  std::cout << "No RVO if the called function uses more than one variable for the return value!\n";
  C obj = return_multiple(0);
}

void no_case3() {
  std::cout << "copy elision does not work for exception!\n";
  // copy elision to exception
  try {
    e();
  }
  // copying the exception object into the temporary in the exception declaration.
  // It is also unclear whether this copy may be elided.
  catch(C c) {
  }
}

void std_move() {
  std::string str = "Hello";
  std::vector<std::string> v;

  // uses the push_back(const T&) overload, which means
  // we'll incur the cost of copying str
  v.push_back(str);
  std::cout << "After copy, str is \"" << str << "\"\n";

  // uses the rvalue reference push_back(T&&) overload,
  // which means no strings will be copied; instead, the contents
  // of str will be moved into the vector.  This is less
  // expensive, but also means str might now be empty.
  v.push_back(std::move(str));
  std::cout << "After move, str is \"" << str << "\"\n";

  std::cout << "The contents of the vector are \"" << v[0] << "\", \"" << v[1] << "\"\n";
}

int main() {
//  yes_case1();
//  no_case1();
//  no_case2();
//  no_case3();
  std_move();
  return 0;
}
