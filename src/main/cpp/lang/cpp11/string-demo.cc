#include <iostream>

void char_array() {
  char array1[] = "Foo" "bar";
  // same as
  char array2[] = { 'F', 'o', 'o', 'b', 'a', 'r', '\0' };
  std::cout << array1 << std::endl;
  std::cout << array2 << '\n';
}

void char_ptr() {
  const char* s1 = R"foo(Hello
    %s
  )foo";
  //same as
  const char* s2 = "\nHello\nWorld\n";
  std::cout << printf(s1, "world");
  std::cout << s2;
}

int main()
{
//  char_array();
  char_ptr();
}
