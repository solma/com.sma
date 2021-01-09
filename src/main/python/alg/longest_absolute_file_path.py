"""
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
"""
from alg.label import *
Label(Label.Hash, Label.Stack, Label.LinearTime, Label.LeetCode)

class LongestAbsoluteFilePath(object):
  def lengthLongestPath1(self, input):
    maxlen = 0
    pathlen = {0: 0}
    for line in input.splitlines():
      name = line.lstrip('\t')
      depth = len(line) - len(name)
      if '.' in name:
        maxlen = max(maxlen, pathlen[depth] + len(name))
      else:
        pathlen[depth + 1] = pathlen[depth] + len(name) + 1
    return maxlen

  def lengthLongestPath(self, input):
    def GetIndentation(f):
      idx, n  = 0, len(f)
      while idx < n and f[idx] == '\t':
        idx += 1
      return idx

    max_len = 0
    q = []

    for f in input.split('\n'):
      cur_indentation = GetIndentation(f)
      name = f[cur_indentation:]
      ln = len(name)
      if not q or cur_indentation > q[-1][2]:
        cur_len = ln
        if q:
          cur_len = q[-1][1] + ln + 1
      elif cur_indentation == q[-1][2]:
        cur_len = q[-1][1] - len(q[-1][0]) + ln
      else:
        while q and q[-1][2] >= cur_indentation:
          popped = q.pop()
          if '.' in popped[0]:
            max_len = max(max_len, popped[1])
        cur_len = popped[1] - len(popped[0]) + ln
      q.append((name, cur_len, cur_indentation))
      # print q
    if q:
      depth = q[-1][2]
      while q and q[-1][2] == depth:
        popped = q.pop()
        if '.' in popped[0]:
          max_len = max(max_len, popped[1])
    return max_len

ins = LongestAbsoluteFilePath()
for fs in [
  'dir\n\t        file.txt\n\tfile2.txt',
  'rzzmf\nv\n\tix\n\t\tiklav\n\t\t\ttqse\n\t\t\t\ttppzf\n\t\t\t\t\tzav\n\t\t\t\t\t\tkktei\n\t\t\t\t\t\t\thhmav\n\t\t\t\t\t\t\t\tbzvwf.txt',
  'a\n\tb1\n\t\tf1.txt\n\taaaaa\n\t\tf2.txt',
  'dir\n\tsubdir1\n\tsubdir2',
  'dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext',
  'dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext',
]:
  print(fs, '\n', ins.lengthLongestPath(fs))





