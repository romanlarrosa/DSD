import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;


public class contador /*extends UnicastRemoteObject*/ implements I_contador {
    private int suma;

    public contador() throws RemoteException {
        super();
    }

    public int sumar() throws RemoteException {
        return suma;
    }

    public void sumar(int valor) throws RemoteException {
        suma = valor;
    }

    public int incrementar() throws RemoteException {
        suma ++;
        return suma;
    }
}
