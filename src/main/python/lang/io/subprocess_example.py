from subprocess import Popen, PIPE, STDOUT

def PrintStdPipe():
  cmd = 'ls .'
  p = Popen(cmd, shell=True, stdin=PIPE, stdout=PIPE, stderr=STDOUT, close_fds=True)
  for line in p.stdout: # equivalent to p.stdout.readlines()
    print line.strip()
  print 'End of loop.'

if __name__ == '__main__':
  PrintStdPipe()
