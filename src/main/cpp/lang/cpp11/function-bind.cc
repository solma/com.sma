#include <functional>
#include <iostream>
#include <vector>

namespace { // unamed namespace
 class BindClassMemberMethod {
   public:
     void run() const {
      partialFooMinus();
     }

     void fooMinus(const std::string & msg, int a, int b) const {
       std::cout << msg << ": " << a - b << std::endl;
     }

     void partialFooMinus() const {
       auto pf = std::bind(&BindClassMemberMethod::fooMinus, this, "inside class", std::placeholders::_1, 0);
       pf(2); 
     }
 };
} // namespace


int main(int argc, char** argv)
{
  BindClassMemberMethod foo;
  foo.run();
  
  // bind outside the class
  auto pf = std::bind(&BindClassMemberMethod::fooMinus, &foo, "outside class, in the main()", std::placeholders::_1, 0);
  pf(2);
}

