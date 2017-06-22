def compound(apr, principal):
  mpr = apr / 12
  return principal * (1 + mpr) ** 12

print(compound(0.01, 10000))
