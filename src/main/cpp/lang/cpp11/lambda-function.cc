#include <iostream>

namespace {
  class MyClass {
    public:
      void run(const std::string& msg) const {
        std::cout << "  \"" << msg << "\" in member function run()" << std::endl;
      }

      void lambdaFunction() const {
        auto lf = [this](const std::string& msg) {
          run(msg);
        };
        lf("reference a member function in lambda function needs to capture this pointer.");
      }
  };
}


int main(int argc, char** argv)
{
  std::cout << "lambda function that captures argc by value and argv by reference:" << std::endl;
  auto lf = [argc, &argv]() {
    std::cout << "  argc: " << argc << std::endl;
    for (int arg = 0; arg < argc; arg++) {
      std::cout << "  " << arg << "th arg: " << argv[arg] << std::endl;
    }
  };
  lf();
  std::cout << std::endl;

  std::cout << "lambda function in a class:" << std::endl;
  MyClass mc;
  mc.lambdaFunction();
}

