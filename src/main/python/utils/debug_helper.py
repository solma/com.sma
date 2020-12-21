def debug_print(is_debug: bool, *args, **kargs):
  if not is_debug:
    return
  print(*args, **kargs)
