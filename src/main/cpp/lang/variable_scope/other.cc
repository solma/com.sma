/* 初始化的全局变量 */
int z_global = 11;
/* 另一个命名为y_global_init的全局变量 , 但它们都是static的 */
static int y_global_init = 2;
/* 声明另一个全局变量 */
extern int x_global_init;

int fn_a(int x, int y)
{
  return x * y;
}

