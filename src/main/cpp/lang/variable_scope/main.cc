// Reference: http://blog.jobbole.com/96225/

/* 这是一个未初始化的全局变量的定义 */
int x_global_uninit;

/* 这是一个初始化的全局变量的定义 */
int x_global_init = 1;

/* 这是一个未初始化的全局变量的定义，尽管该变量只能在当前 C文件中访问 */
static int y_global_uninit;

/* 这是一个初始化的全局变量的定义，尽管该变量只能在当前 C文件中访问 */
static int y_global_init = 2;

/* 这是一个存在于程序别处的某个全局变量的声明 */
extern int z_global;

/* 这是一个存在于程序别处的某个函数的声明（如果你愿意，你可以在语句前加上 "extern"关键字，但没有这个必要） */
int fn_a( int x, int y);

/* 这是一个函数的定义，但由于这个函数前加了 static限定，因此它只能在当前 C文件内使用 */
static int fn_b(int x)
{
    return x +1;
}

/* 这是一个函数的定义，函数参数可以认为是局部变量 */
int fn_c(int x_local)
{
    /* 这是一个未初始化的局部变量的定义 */
    int y_local_uninit ;
    /* 这是一个初始化的局部变量的定义 */
    int y_local_init = 3 ;

    /* 以下代码通过局部变量、全局变量和函数的名字来使用它们 */
    x_global_uninit = fn_a (x_local, x_global_init);
    y_local_uninit = fn_a (x_local, y_local_init);
    y_local_uninit += fn_b (z_global);
    return (x_global_uninit + y_local_uninit);
}

int main(int argc, char** argv)
{
	fn_c(1)
}
