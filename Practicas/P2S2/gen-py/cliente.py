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
    print('Hago ping al servidor')
    client.ping()

    

    # Cerramos la conexion
    transport.close()

main()

