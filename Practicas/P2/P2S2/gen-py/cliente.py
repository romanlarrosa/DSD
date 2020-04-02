from __future__ import print_function
import sys
import glob


from calculadora import Calculadora

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

import logging
logging.basicConfig(level=logging.DEBUG)

def main():
    # Make socket
    transport = TSocket.TSocket('localhost', 9090)

    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)

    # Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

    # Create a client to use the protocol encoder
    client = Calculadora.Client(protocol)

    # Connect!
    transport.open()

    # Realizamos las operaciones
    client.ping()

    
    

    if len(sys.argv) != 4:
        print('Para hacer una operacion sencilla: \npython cliente.py arg1 [ + | - | x | / ] arg2 \nProbando todas las operaciones...')
        # Prueba de suma
        print('Voy a sumar 5 y 7...')
        resultado = client.suma(5, 7)
        print('Resultado: ' + str(resultado))

        # Prueba de resta
        print('Voy a restar 12 - 5...')
        resultado = client.sub(12, 5)
        print('Resultado: ' + str(resultado))

        # Prueba de multiplicacion
        print('Voy a multiplicar 2 x 6...')
        resultado = client.mult(2, 6)
        print('Resultado: ' + str(resultado))

        # Prueba de division
        print('Voy a dividir 12 / 2...')
        resultado = client.div(12, 2)
        print('Resultado: ' + str(resultado))

        # Prueba de suma
        print('Voy a hacer la suma de [3, 2, 1] y [1, 2, 3]')
        vec1 = [3, 2, 1]
        vec2 = [1, 2, 3]
        resultado = client.sumVec(vec1, vec2)
        print('Resultado: ')
        print('[', end ="") 
        for x in resultado:
            print(str(int(x)) + ",", end =" ") 
        print(']') 

        # Prueba de producto escalar
        print('Voy a hacer la resta de [3, 2, 1] y [1, 2, 3]')
        vec1 = [3, 2, 1]
        vec2 = [1, 2, 3]
        resultado = client.subVec(vec1, vec2)
        print('Resultado: ')
        print('[', end ="") 
        for x in resultado:
            print(str(int(x)) + ",", end =" ") 
        print(']') 

        # Prueba de producto escalar
        print('Voy a hacer el producto escalar de [3, 2, 1] y [1, 2, 3]')
        vec1 = [3, 2, 1] 
        vec2 = [1, 2, 3]
        resultado = client.escVec(vec1, vec2)
        print('Resultado: ' + str(int(resultado)))
    else:
        arg1 = int(sys.argv[1])
        arg2 = int(sys.argv[3])

        if sys.argv[2] == '+':
            print('Voy a sumar ' + sys.argv[1] + ' y ' + sys.argv[3] + '...')
            resultado = client.suma(arg1, arg2)
            print('Resultado: ' + str(resultado))
        if sys.argv[2] == '-':
            print('Voy a restar ' + sys.argv[1] + ' y ' + sys.argv[3] + '...')
            resultado = client.sub(arg1, arg2)
            print('Resultado: ' + str(resultado))
        if sys.argv[2] == 'x':
            print('Voy a multiplicar ' + sys.argv[1] + ' y ' + sys.argv[3] + '...')
            resultado = client.mult(arg1, arg2)
            print('Resultado: ' + str(resultado))
        if sys.argv[2] == '/':
            if arg2 != 0:
                print('Voy a dividir ' + sys.argv[1] + ' entre ' + sys.argv[3] + '...')
                resultado = client.div(arg1, arg2)
                print('Resultado: ' + str(resultado))
            else:
                print('ERROR: no se puede dividir entre 0')

    # Cerramos la conexion
    transport.close()

main()

