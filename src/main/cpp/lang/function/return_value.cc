#include <iostream>
#include <memory>
#include "dummy_class.h"

C return_value() {
  return C();
}

const C return_const_value() {
  // make no sense to return a const value since it has no
  // difference from return_value() for all use cases
  // see return_value_cases() vs. return_const_value_cases()
  return C();
}

C& return_reference() {
  auto c = std::make_unique<C>();
  return *c.get();
}

const C& return_const_reference() {
  auto c = std::make_unique<C>();
  return *c.get();
}

void return_value_cases() {
  std::cout << "When function returns a value: " << std::endl;

  // no copy, rvo applied
  C c00 = return_value();

  const C c01 = return_value();
  //  c01 = c00;  // compile error, cannot change const

  // compile error since tmp obj has no address
  //  C& c02 = return_value();

  // surprisingly compiler allows a tmp obj initialized into a const reference
  const C& c03 = return_value();
}

void return_const_value_cases() {
  std::cout << "When function returns a const value: " << std::endl;

  C c00 = return_const_value();

  const C c01 = return_const_value(); // no copy
  //  c01 = c00;  // compile error, cannot change const

  // compile error since tmp obj has no address
  // C& c02 = return_const_value();

  // surprisingly compiler allows a tmp obj initialized into a const reference
  const C& c03 = return_const_value();
}

void return_reference_cases() {
  std::cout << "When function returns a reference: " << std::endl;

  // different name, incur a copy
  C c00 = return_reference();

  // different name, incur a copy
  const C c01 = return_reference();

  C& c02 = return_reference();
  const C& c03 = return_reference();
}

void return_const_reference_cases() {
  std::cout << "When function returns a const reference: " << std::endl;

  // different name, incur a copy
  C c00 = return_const_reference();

  // different name, incur a copy
  const C c01 = return_const_reference();

  // compile error due to loss of constness
//  C& c02 = return_const_reference();

  const C& c03 = return_const_reference();
}

int main() {
//  return_value_cases();
  return_const_value_cases();
//  return_reference_cases();
//  return_const_reference_cases();
}
