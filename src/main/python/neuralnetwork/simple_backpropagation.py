# coding: utf-8
# Code modified based on https://github.com/mattm/simple-neural-network/blob/master/neural-network.py
# Ref: http://mattmazur.com/2015/03/17/a-step-by-step-backpropagation-example/
import random
import math

#
# Shorthand:
#   "pd_" as a variable prefix means "partial derivative"
#   "d_" as a variable prefix means "derivative"
#   "_wrt_" is shorthand for "with respect to"
#   "w_ho" and "w_ih" are the index of weights from hidden to output layer neurons and input to hidden layer neurons respectively
#
# Comment references:
#
# [1] Wikipedia article on Backpropagation
#   http://en.wikipedia.org/wiki/Backpropagation#Finding_the_derivative_of_the_error
# [2] Neural Networks for Machine Learning course on Coursera by Geoffrey Hinton
#   https://class.coursera.org/neuralnets-2012-001/lecture/39
# [3] The Back Propagation Algorithm
#   https://www4.rgu.ac.uk/files/chapter3%20-%20bp.pdf

class NeuralNetwork:
  LEARNING_RATE = 0.5

  def __init__(self, num_inputs, num_hidden, num_outputs,
               hidden_layer_weights=None, hidden_layer_bias=None,
               output_layer_weights=None, output_layer_bias=None,
               activation_func = lambda x : 1 /(1 + math.exp(-x)),
               activation_d_func = lambda y : y * (1 - y)):
    self.num_inputs = num_inputs

    self.hidden_layer = NeuralNetwork.NeuronLayer(num_hidden, hidden_layer_bias, activation_func, activation_d_func)
    self.output_layer = NeuralNetwork.NeuronLayer(num_outputs, output_layer_bias, activation_func, activation_d_func)

    self.init_weights_from_inputs_to_hidden_layer_neurons(hidden_layer_weights)
    self.init_weights_from_hidden_layer_neurons_to_output_layer_neurons(output_layer_weights)

  def init_weights_from_inputs_to_hidden_layer_neurons(self, hidden_layer_weights):
    weight_idx = 0
    for h in range(len(self.hidden_layer.neurons)):
      for _ in range(self.num_inputs):
        self.hidden_layer.neurons[h].weights.append(
          random.random() if hidden_layer_weights is None else hidden_layer_weights[weight_idx])
        weight_idx += 1

  def init_weights_from_hidden_layer_neurons_to_output_layer_neurons(self, output_layer_weights):
    weight_idx = 0
    for o in range(len(self.output_layer.neurons)):
      for h in range(len(self.hidden_layer.neurons)):
        self.output_layer.neurons[o].weights.append(
          random.random() if output_layer_weights is None else output_layer_weights[weight_idx])
        weight_idx += 1

  def inspect(self):
    print('------')
    print('* Inputs: {}'.format(self.num_inputs))
    print('------')
    print('Hidden Layer')
    self.hidden_layer.inspect()
    print('------')
    print('* Output Layer')
    self.output_layer.inspect()
    print('------')

  def feed_forward(self, inputs):
    hidden_layer_outputs = self.hidden_layer.feed_forward(inputs)
    return self.output_layer.feed_forward(hidden_layer_outputs)

  # Uses online learning, ie updating the weights after each training case
  def train(self, training_inputs, training_outputs):
    self.feed_forward(training_inputs)

    # 1. Output neuron deltas
    # ∂E/∂zⱼ
    pd_errors_wrt_output_neuron_total_net_input = [
      self.output_layer.neurons[o].calculate_pd_error_wrt_total_net_input(training_outputs[o])
       for o in range(len(self.output_layer.neurons))]

    # 2. Update output neuron weights
    for o in range(len(self.output_layer.neurons)):
      for w_ho in range(len(self.output_layer.neurons[o].weights)):
        # ∂Eⱼ/∂wᵢⱼ = ∂E/∂zⱼ * ∂zⱼ/∂wᵢⱼ
        pd_error_wrt_weight = (pd_errors_wrt_output_neuron_total_net_input[o] *
                               self.output_layer.neurons[o].calculate_pd_total_net_input_wrt_weight(w_ho))

        # Δw = α * ∂Eⱼ/∂wᵢ
        self.output_layer.neurons[o].weights[w_ho] -= self.LEARNING_RATE * pd_error_wrt_weight

    # 3. Hidden neuron deltas
    pd_errors_wrt_hidden_neuron_total_net_input = [0] * len(self.hidden_layer.neurons)
    for h in range(len(self.hidden_layer.neurons)):
      # We need to calculate the derivative of the total error with respect to the output of
      # each hidden layer neuron, zᵢ is the net total input of ith output layer neuron
      # dE/dyⱼ = Σᵢ ∂E/∂zᵢ * ∂zᵢ/∂yⱼ = Σ ∂E/∂zᵢ * wᵢⱼ
      d_error_wrt_hidden_neuron_output = sum(
        pd_errors_wrt_output_neuron_total_net_input[o] * self.output_layer.neurons[o].weights[h]
        for o in range(len(self.output_layer.neurons))
      )
      # ∂E/∂zⱼ = ∂E/∂yⱼ * dyⱼ/dzⱼ
      pd_errors_wrt_hidden_neuron_total_net_input[h] = (
        d_error_wrt_hidden_neuron_output * self.hidden_layer.neurons[h].calculate_pd_output_wrt_total_net_input())

    # 4. Update hidden neuron weights
    for h in range(len(self.hidden_layer.neurons)):
      for w_ih in range(len(self.hidden_layer.neurons[h].weights)):
        # ∂Eⱼ/∂wᵢ = ∂E/∂zⱼ * ∂zⱼ/∂wᵢ
        pd_error_wrt_weight = (pd_errors_wrt_hidden_neuron_total_net_input[h] *
                               self.hidden_layer.neurons[h].calculate_pd_total_net_input_wrt_weight(w_ih))

        # Δw = α * ∂Eⱼ/∂wᵢ
        self.hidden_layer.neurons[h].weights[w_ih] -= self.LEARNING_RATE * pd_error_wrt_weight

  def calculate_total_error(self, training_sets):
    total_error = 0
    for t in range(len(training_sets)):
      training_inputs, training_outputs = training_sets[t]
      self.feed_forward(training_inputs)
      total_error += sum([self.output_layer.neurons[o].calculate_error(training_outputs[o])
                          for o in range(len(training_outputs))])
    return total_error

  class Neuron:
    def __init__(self, bias, activation_func, activation_d_func):
      self.bias = bias
      self.weights = [] # weights for inputs
      self.activation_func = activation_func
      self.activation_d_func = activation_d_func
      self.inputs = self.outputs = None

    def calculate_output(self, inputs):
      # Simple weighted sum plus the bias
      def summation_unit_output():
        return self.bias + sum(t[0] * t[1] for t in zip(self.inputs, self.weights))

      self.inputs = inputs
      self.outputs = self.activation_func(summation_unit_output())
      return self.outputs

    # The error for each neuron is calculated by the Mean Square Error method:
    # The 1/2 is included so that exponent is cancelled when differentiate.
    # The result is eventually multiplied by a learning rate anyway
    # so it does not matter that we introduce a constant here [1].
    def calculate_error(self, target_output):
      return 0.5 * (target_output - self.outputs) ** 2

    # Determine how much the neuron's total input has to change to move closer to the expected output
    #
    # Now that we have the partial derivative of the error with respect to the output (∂E/∂yⱼ) and
    # the derivative of the output with respect to the total net input (dyⱼ/dzⱼ) we can calculate
    # the partial derivative of the error with respect to the total net input.
    # This value is also known as the delta (δ) [1]
    # δ = ∂E/∂zⱼ = ∂E/∂yⱼ * dyⱼ/dzⱼ
    #
    def calculate_pd_error_wrt_total_net_input(self, target_output):
      return self.calculate_pd_error_wrt_output(target_output) * self.calculate_pd_output_wrt_total_net_input()

    # The partial derivative of the error with respect to actual output then is calculated by:
    # = 2 * 0.5 * (target output - actual output) ^ (2 - 1) * -1
    # = -(target output - actual output)
    #
    # The Wikipedia article on backpropagation [1] simplifies to the following, but most other
    # learning material does not [2]
    # = actual output - target output
    #
    # Alternative, you can use (target - output), but then need to add it during backpropagation [3]
    #
    # Note that the actual output of the output neuron is often written as yⱼ and target output as tⱼ so:
    # = ∂E/∂yⱼ = -(tⱼ - yⱼ)
    def calculate_pd_error_wrt_output(self, target_output):
      return -(target_output - self.outputs)

    # The total net input into the neuron is squashed using activation function to calculate the neuron's output
    # e.g. if activation func is following:
    # yⱼ = φ = 1 / (1 + e^(-zⱼ))
    # then
    # dyⱼ/dzⱼ = yⱼ * (1 - yⱼ) = e^x / (1 + e^-x)^2
    def calculate_pd_output_wrt_total_net_input(self):
      return self.activation_d_func(self.outputs)

    # The total net input is the weighted sum of all the inputs to the neuron and their respective weights:
    # = zⱼ = netⱼ = x₁w₁ + x₂w₂ ...
    #
    # The partial derivative of the total net input with respective to a given weight
    # (with everything else held constant) then is:
    # = ∂zⱼ/∂wᵢ = some constant + 1 * xᵢw₁^(1-0) + some constant ... = xᵢ
    def calculate_pd_total_net_input_wrt_weight(self, index):
      return self.inputs[index]

  class NeuronLayer:
    def __init__(self, num_neurons, bias, activation_func, activation_d_func):
      # Every neuron in a layer shares the same bias
      self.bias = bias if bias else random.random()
      self.neurons = [NeuralNetwork.Neuron(self.bias, activation_func, activation_d_func) for _ in range(num_neurons)]

    def inspect(self):
      print('Neurons:', len(self.neurons))
      for n in range(len(self.neurons)):
        print(' Neuron', n)
        print('   Weights:', self.neurons[n].weights)
        print('   Bias:', self.bias)

    def feed_forward(self, inputs):
      return [neuron.calculate_output(inputs) for neuron in self.neurons]
###


def is_print(idx):
  base = 1
  while base * 10 < idx:
    base *= 10
  if idx % base == 0 or base == 1:
    return True
  return False

def foo_example():
  """
  Some trivial example
  """
  nn = NeuralNetwork(2, 2, 2,
                     hidden_layer_weights=[0.15, 0.2, 0.25, 0.3], hidden_layer_bias=0.35,
                     output_layer_weights=[0.4, 0.45, 0.5, 0.55], output_layer_bias=0.6)
  for i in range(10001):
    nn.train([0.05, 0.1], [0.01, 0.99])
    if is_print(i):
      print(i, round(nn.calculate_total_error([[[0.05, 0.1], [0.01, 0.99]]]), 9))

def xor():
  """
  XOR example:
  """
  training_sets = [
    [[0, 0], [0]],
    [[0, 1], [1]],
    [[1, 0], [1]],
    [[1, 1], [0]]
  ]

  nn = NeuralNetwork(len(training_sets[0][0]), 5, len(training_sets[0][1]))#,
                     # activation_func = math.tanh,
                     # activation_d_func = lambda x : 1 - math.tanh(x) ** 2)
  for i in range(10 ** 3 + 1):
    training_inputs, training_outputs = random.choice(training_sets)
    nn.train(training_inputs, training_outputs)
    if is_print(i):
      print(i, nn.calculate_total_error(training_sets))

if __name__ == '__main__':
  foo_example()
  # xor()
