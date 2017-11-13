// http://en.cppreference.com/w/cpp/language/default_constructor
struct A
{
    int x, y;
    A(int x = 1) : x(x){} // user-defined default constructor
    A(int x, int y = 2): x(x), y(y) {} // user-defined default constructor
};

struct B: A
{
    // B::B() is implicitly-defined, calls A::A()
};

struct C
{
    A a;
    // C::C() is implicitly-defined, calls A::A()
};

struct D: A
{
    D(int y): A(y) {}
    // D::D() is not declared because another constructor exists
};

struct E: A
{
    E(int y): A(y) {}
    E() = default; // explicitly defaulted, calls A::A()
};

struct F
{
    int& ref; // reference member
    const int c; // const member
    // F::F() is implicitly defined as deleted
};

int main()
{
    A a;
    B b;
    // B b1(2);
    C c;
//  D d; // compile error
    D d(2);
    E e;
//  F f; // compile error
}
