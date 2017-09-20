#include <iostream>

namespace lang {
namespace tmpl {
using std::cout;
using std::endl;
using std::ostream;

template <typename Object, typename ComparatorImplementParentheseOperator>
const Object & isBetter(const Object& o1, const Object& o2, const ComparatorImplementParentheseOperator& bc) {
  // requirement: bc overloads operator()
  bc(o1, o2);
  // dummy: always return o2
  return o2;
}

class Toy {
  public:
    Toy(int qual): quality(qual) {};
    int getQuality() const { return quality; };
    void println (ostream & out) const {
      cout << "quality is " << quality << endl;
    }
  private:
    int quality;
}; // expected ';' after class

class ToyComparator {
  public:
    bool operator() (const Toy& t1, const Toy& t2) const {
      return t1.getQuality() > t2.getQuality();
    }
}; // expected ';' after class

class IntComparator {
  public:
    bool operator() (int d1, int d2) const {
      return d1 > d2;
    }
}; // expected ';' after class

ostream & operator<< (ostream & out, const Toy & t) {
  t.println(out);
  return out;
}

template <>
const Toy & isBetter(const Toy& t1, const Toy& t2, const ToyComparator& qc) {
  return qc(t1, t2) ? t1 : t2;
}
} // namespace tmpl
} // namespace lang

namespace lt = lang::tmpl;

int main(int argc, char** argv)
{
  std::cout << lt::isBetter(10, 6, lt::IntComparator()) << lt::endl;
  lt::cout << lt::isBetter(lt::Toy(10), lt::Toy(6), lt::ToyComparator());
}

