class MyClass(object):
    def callee(self, a, b):
        return 'Inside Callee'

    def caller(self, callee_name, *args):
        return getattr(self, callee_name)(*args)
