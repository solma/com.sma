def spiral_matrix(n):
  matrix = [[0] * n for _ in range(n)]

  x, y = 0, 0
  i = 1
  offset = (0, 1)
  while i <= n ** 2:
    print (x, y)
    matrix[x][y] = i
    i += 1
    if offset == (0, 1):
      if (y == n - 1 or matrix[x][y + 1] != 0):
        offset = (1, 0)
        x += 1
      else:
        y += 1
    elif offset == (1, 0):
      if (x == n - 1 or matrix[x + 1][y] != 0):
        offset = (0, -1)
        y -= 1
      else:
        x += 1
    elif offset == (0, -1):
      if (y == 0 or matrix[x][y - 1] != 0):
        offset = (-1, 0)
        x -= 1
      else:
        y -= 1
    elif offset == (-1, 0):
      if (x == 0 or matrix[x - 1][y] != 0):
        offset = (0, 1)
        y += 1
      else:
        x -= 1
    else:
      print('ERROR')
      break

  for i in range(n):
    print(' '.join(str(x) for x in matrix[i]))

spiral_matrix(5)
