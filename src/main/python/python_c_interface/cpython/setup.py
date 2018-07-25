from distutils.core import setup, Extension
import numpy
from Cython.Distutils import build_ext

setup(
    cmdclass={'build_ext': build_ext},
    ext_modules=[
        Extension("cos_module", ["cos_module.pyx"]),
        Extension("cos_module_np",
            sources=["_cos_module_np.pyx", "cos_module_np.c"],
            include_dirs=[numpy.get_include()]),
    ]
)
