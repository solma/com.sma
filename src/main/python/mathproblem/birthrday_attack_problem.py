from utils import math_helper

def birthday_attack(no_of_days_in_a_year=365., no_of_people=23, collision_thresold=0.5):
  t = 1.0 - collision_thresold
  no_of_people, p = 1, 1.
  while p > t:
    p *= (no_of_days_in_a_year - no_of_people) / no_of_days_in_a_year
    no_of_people += 1
  return no_of_people

print(birthday_attack())


