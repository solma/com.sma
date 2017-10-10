class C {
 public:
  C() {}

  C(const C&) { std::cout << "A copy was made.\n"; }

  friend std::ostream& operator<<(std::ostream &out, const C& c) {
    return out << "C()\n";
  }
};
