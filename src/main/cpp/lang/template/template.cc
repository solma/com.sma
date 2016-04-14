#include <iostream>

namespace { // unamed namespace
using std::cout;
using std::endl;
using std::ostream;

template <typename Object, typename ComparatorImplementParentheseOperator>
const Object & isBetter(const Object & o1, const Object & o2, const ComparatorImplementParentheseOperator & bc) {
  // requirement: bc overloads operator()
  return bc(o1, o2) == true ? o1 : o2;
}

class Toy {
  public:
    Toy(int qual): quality(qual) {};
    int getQuality() const { return quality; };
    void print (ostream & out) const {
      cout << "quality is " << quality << endl;
    }
  private:
    int quality;
}; // expected ';' after class

class QualityComparator {
  public:
    bool operator() (const Toy & t1, const Toy & t2) const {
      return t1.getQuality() > t2.getQuality();
    }
}; // expected ';' after class

ostream & operator<< (ostream & out, const Toy & t) {
  t.print(out);
  return out;
}
} // namespace

int main(int argc, char** argv)
{
  Toy t1(10), t2(6);
  cout << ::isBetter(t1, t2, QualityComparator());
  cout << "Hello World, Hello Lexi." << endl;
}

