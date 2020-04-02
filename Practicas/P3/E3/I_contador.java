import java.rmi.Remote;
import java.rmi.RemoteException;

public interface I_contador extends Remote {
    public int sumar() throws RemoteException;
    public void sumar(int valor) throws  RemoteException;

    public int incrementar() throws RemoteException;
}