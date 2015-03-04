def shunting_yard(expr):
    """
     translate an inorder expression to reverse polish notation
    """
    rpn = []
    stack = []
    priority = {'+': 1, '-': 1, '*': 2, '/': 2, '(': 5, ')': 5}
    for c in expr:
        print c, stack, rpn
        if ord(c) >= ord('0') and ord(c) <= ord('9'):  # digit
            rpn.append(c)
        else:
            if c != ')':
                while len(stack) > 0 and stack[-1] != '(' and priority[stack[-1]] >= priority[c]:
                    rpn.append(stack.pop())
                stack.append(c)
            else:
                while len(stack) > 0 and stack[-1] != '(':
                    rpn.append(stack.pop())
                stack.pop()
    rpn.extend(stack[::-1])
    return rpn


def eval(expr):
    """
      evaluate a reverse polish notation
    """
    stack = []
    for c in expr:
        if ord(c) >= ord('0') and ord(c) <= ord('9'):  # digit
            stack.append(c)
        else:
            stack.append(calc(int(stack.pop()), int(stack.pop()), c))
    return int(stack[-1])


def calc(op1, op2, operator):
    if operator == '+':
        return op1 + op2;
    if operator == '-':
        return op2 - op1;
    if operator == '*':
        return op1 * op2;
    if operator == '/':
        return op2 / op1;


print eval(shunting_yard("(3+3/3*(1+3))-4/2"))


