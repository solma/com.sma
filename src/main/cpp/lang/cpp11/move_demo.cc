#include <iostream>
#include <cassert>

class A {
  int state_;
 public:
  enum { destructed = -1, moved_from = 0, default_constructed = 1 };

  A() : state_(default_constructed) {}

  A(const A& a) : state_(a.state_) {}

  A& operator=(const A& a) {
    state_ = a.state_;
    std::cout << "Inside copy assignment operator " << *this << std::endl;
    return *this;
  }

  A(A&& a) : state_(a.state_) {
    a.state_ = moved_from;
    std::cout << "Inside copy constructor (move overload) " << *this << std::endl;
  }

  A& operator=(A&& a) {
    state_ = a.state_;
    a.state_ = moved_from;
    std::cout << "Inside copy assignment operator (move overload) " << *this << std::endl;
    return *this;
  }

  ~A() { state_ = destructed; }

  explicit A(int s) : state_(s) {assert(state_ > default_constructed);}

  friend std::ostream& operator<<(std::ostream& os, const A& a) {
    switch (a.state_) {
      case A::destructed:
        os << "A is destructed\n";
        break;
      case A::moved_from:
        os << "A is moved from\n";
        break;
      case A::default_constructed:
        os << "A is default constructed\n";
        break;
      default:
        os << "A = " << a.state_ << '\n';
        break;
    }
    return os;
  }

  friend bool operator==(const A& x, const A& y) {
    return x.state_ == y.state_;
  }
  friend bool operator<(const A& x, const A& y) {
    return x.state_ < y.state_;
  }
};

A&& f() {
  A y;
  std::cout << y;
  return std::move(y);
}

int main() {
  A a = f();
  std::cout << a;
}
