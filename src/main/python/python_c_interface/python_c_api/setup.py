from distutils.core import setup, Extension
import numpy

setup(
    ext_modules=[
        Extension('cos_module', sources=['cos_module.c']),
        Extension('cos_module_np',
            sources=['cos_module_np.c'],
            include_dirs=[numpy.get_include()])
])
