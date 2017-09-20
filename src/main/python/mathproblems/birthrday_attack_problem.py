from utils import math_helper
from mathproblems import log1p, sqrt

DEFAULT_OUTPUT_SPACE_SIZE = 365.
DEFAULT_SAMPLE_SIZE_FOR_COLLISION = 23.
DEFAULT_COLLISION_PROBABILITY = 1/2

def birthday_attack(
    output_space_size=DEFAULT_OUTPUT_SPACE_SIZE,
    sample_size=DEFAULT_SAMPLE_SIZE_FOR_COLLISION,
    collision_probability=DEFAULT_COLLISION_PROBABILITY):
  if sample_size is None:

    def alg1():
      sample_size, p = 1, 1.
      while p > 1.0 - collision_probability:
        p *= (output_space_size - sample_size) / output_space_size
        sample_size += 1
      return sample_size

    def alg2():
      assert collision_probability == 0.5
      return sqrt(2. * output_space_size * -log1p(-collision_probability))

    return 'sample_size : {0}, {1}'.format(alg1(), alg2())

for output_space_size in [16, 32, 64, 128, 256, 365, 512, 1024]:
  print(birthday_attack(output_space_size=output_space_size, sample_size=None))



