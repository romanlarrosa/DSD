import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.lang.Thread;

public class Servidor extends UnicastRemoteObject implements Interfaz_cliente, Interfaz_replicas {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String replica; // Arraylist para varias replicas??

    private Interfaz_replicas stub_replica = null;

    private double subtotal = 0;

    private Map<String, Double> entidades; // String = entidad, double = total donado hasta el momento

    public Servidor(String nombre, String replica) throws RemoteException {
        this.nombre = nombre;
        this.replica = replica; // Como manejarlo para varias replicas??

        entidades = new HashMap();
    }

    private void conectarReplica() throws RemoteException {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost");
            stub_replica = (Interfaz_replicas) reg.lookup(replica);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        
    }
    @Override
    public void subordinarse(String nombre) throws RemoteException{
        // para tner varias replicas subordinadas a un servidor principal??

    }

    @Override
    public int getRegistrados() throws RemoteException {
        //Devuelve el total de registrados en ESTA REPLICA
        return entidades.size();
    }

    @Override
    public double getDonaciones() throws RemoteException {
        //devuelve el subtotal de donaciones
        return subtotal;
    }

    @Override
    public boolean registrado(String user) throws RemoteException{
        //devuelve si el usuario está registrado EN ESTA REPLICA
        return entidades.containsKey(user);
    }

    @Override
    public boolean donado(String user) throws RemoteException{
        // devuelve si un usuario user ha hecho alguna donacion
        if(entidades.containsKey(user)){
            if (entidades.get(user) > 0.0){
                //System.out.println("El usuario " + user + " ha donado");
                return true;
            }
        }
        System.out.println("El usuario " + user + " NO ha donado nada");
        return false;
    }

    @Override
    public boolean registrar(String entidad) throws RemoteException {
        if(stub_replica == null){
            this.conectarReplica();
        }
        if(!this.registrado(entidad) && !stub_replica.registrado(entidad)){
            if(this.getRegistrados() <= stub_replica.getRegistrados()){
                //Se registra en esta replica
                entidades.put(entidad, 0.0);
                System.out.println("Se registra a la entidad " + entidad);
                return true;
            }
            else{
                //Se registra en la otra replica
                return stub_replica.registrar(entidad);
            }
        }
        return false;
    }

    @Override
    public double getTotalDonaciones(String entidad) throws RemoteException {
        //Devuelve el total de donaciones (Esta replica + la otra)
        if(stub_replica == null){
            this.conectarReplica();
        }

        if(this.registrado(entidad)){
            if(this.donado(entidad)){
                return getDonaciones() + stub_replica.getDonaciones();
            }
        }
        else if(stub_replica.registrado(entidad)){
            return stub_replica.getTotalDonaciones(entidad);
        }
        
        return -1.0;
    }

    @Override
    public boolean donar(String entidad, double donacion) throws RemoteException {
        if(stub_replica == null){
            this.conectarReplica();
        }

        if(this.registrado(entidad)){
            entidades.put(entidad, entidades.get(entidad) + donacion);
            subtotal += donacion;
            System.out.println("La entidad " + entidad + " realiza una donacion de " + donacion);
            return true;
        }
        else if(stub_replica.registrado(entidad)){
            return stub_replica.donar(entidad, donacion);
        }
        return false;
    }

}