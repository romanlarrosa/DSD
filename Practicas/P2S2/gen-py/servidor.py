import sys
import glob


from calculadora import Calculadora

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

import logging
logging.basicConfig(level=logging.DEBUG)

class CalculadoraHandler:
    def __init__(self):
        self.log = {}

    def ping(self):
        print('Me han hecho un ping')

    def suma(self, n1, n2):
        print('Voy a sumar')
        return n1 + n2

    def sub(self, n1, n2):
        print('Voy a restar')
        return n1 - n2

    def mult(self, n1, n2):
        print('Voy a multiplicar')
        return n1*n2

    def div(self, n1, n2):
        print('Voy a dividir')
        return n1/n2


if __name__ == '__main__':
    handler = CalculadoraHandler()
    processor = Calculadora.Processor(handler)
    transport = TSocket.TServerSocket(host='127.0.0.1', port=9090)
    tfactory = TTransport.TBufferedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

    print('Iniciando servidor...')
    server.serve()
    print('done.')
