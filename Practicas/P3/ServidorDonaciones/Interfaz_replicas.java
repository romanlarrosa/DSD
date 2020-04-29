import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Interfaz_replicas extends Remote {
    //OPERACIONES
    //Añadir a la lista de replicas subordinadas
    public void subordinarse(String nombre) throws RemoteException;
    public double getTotalDonaciones (String entidad) throws RemoteException;
    //Devuelve el numero de registrados
    public int getRegistrados() throws RemoteException;
    //Devuelve el subtotal de donaciones de una replica
    public double getDonaciones() throws RemoteException;
    //Registra a un usuario en una réplica
    public boolean registrar(String entidad) throws RemoteException;
    //Realiza una donación en una réplica
    public boolean donar(String entidad, double donacion) throws RemoteException;
    //Comprueba si un usuario está registrado en una réplica
    public boolean registrado(String user) throws RemoteException;
    //Comprueba si un usuario ha donado en una réplica
    public boolean donado(String user) throws RemoteException;


}