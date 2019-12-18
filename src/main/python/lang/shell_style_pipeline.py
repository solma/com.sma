from utils.decorators import Pipe

@Pipe
def even_filter(num):
  return num if num % 2 == 0 else None

@Pipe
def multiply_by_three(num):
  return num * 3

@Pipe
def convert_to_string(num):
  return 'The Number: %s' % num

@Pipe
def echo(item):
  print(item)
  return item

def force(sqs):
  for _ in sqs: pass

nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

force(nums | even_filter | multiply_by_three | convert_to_string | echo)
