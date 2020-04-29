import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Interfaz_cliente extends Remote {
    //OPERACIONES
    public boolean registrar (String entidad) throws RemoteException;
    public double getTotalDonaciones (String entidad) throws RemoteException;
    public boolean donar(String entidad, double donacion) throws RemoteException;

}

