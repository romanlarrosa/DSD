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
        print('Usage: python cliente.py arg1 [ + | - | x | / ] arg2')
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

