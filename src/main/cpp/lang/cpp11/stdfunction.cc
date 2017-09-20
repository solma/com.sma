#include <functional>
#include <iostream>

namespace { // unamed namespace
struct Foo {
  Foo(int num) : num_(num) {}
  void print_add(int i) const {
    std::cout << num_+i << '\n';
  }
  int num_;
};

void print_num(int i) {
  std::cout << i << '\n';
}

struct PrintNum {
  void operator()(int i) const {
    std::cout << i << '\n';
  }
};


class FooClass {

 public:
   static void print_no_arg() {
           for (int i = 6; i < 9; i++) { std::cout << i; }
           std::cout << std::endl;
         }

   void fooMinus(const std::string & msg, int a, int b) const {
     std::cout << msg << ": " << a - b << std::endl;
   }

   void partialFooMinus() const {
     // bind a class member function within the class body
     // 2nd paramter is an instance of the class
     auto bind_member = std::bind(&FooClass::fooMinus, this, "inside class", std::placeholders::_1, 0);
     bind_member(2);
   }
};
} // namespace

int main() {
  // store a free function
  std::function<void(int)> f_display = print_num;
  f_display(0);

  // bind a free function
  f_display = std::bind(print_num, std::placeholders::_1);
  f_display(1);

  // store a lambda
  std::function<void()> f_display_42 = []() { print_num(42); };
  f_display_42();

  // store the result of a call to std::bind
  std::function<void()> f_display_431333 = std::bind(print_num, 431333);
  f_display_431333();

  // store the result of a call without args to std::bind
  std::function<void()> f_display_678 = std::bind(FooClass::print_no_arg);
  f_display_678();

  // store a call to a member function
  std::function<void(const Foo&, int)> f_add_display = &Foo::print_add;
  const Foo foo(314159);
  f_add_display(foo, 1);

  // store a call to a data member accessor
  std::function<int(Foo const&)> f_num = &Foo::num_;
  std::cout << "num_: " << f_num(foo) << '\n';

  // store a call to a member function and object
  using std::placeholders::_1;
  std::function<void(int)> f_add_display2 = std::bind(&Foo::print_add, foo, _1 );
  f_add_display2(2);

  // store a call to a member function and object ptr
  std::function<void(int)> f_add_display3 = std::bind(&Foo::print_add, &foo, _1 );
  f_add_display3(3);

  // store a call to a function object
  std::function<void(int)> f_display_obj = PrintNum();
  f_display_obj(18);

  FooClass bar;
  bar.partialFooMinus();
  auto bind_member = std::bind(&FooClass::fooMinus, bar, "bind 2nd parameter of a member function", std::placeholders::_1, 2);
  bind_member(2);
}
