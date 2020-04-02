import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class servidor {
    public static void main(String[] args) {
        //Crea e instala el gestor de seguridad
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try{
            Registry reg = LocateRegistry.getRegistry();
            I_contador contador = new contador();
            I_contador stub = (I_contador) UnicastRemoteObject.exportObject(contador, 0);
            reg.rebind("mmI_contador", stub);
            System.out.println("contador bound: OK");

            //Naming.rebind("rmi://localhost:1099/mmI_contador", mI_contador);
        }catch(Exception e){
            System.err.println("Exception: ");
            e.printStackTrace();
        }
    }
}